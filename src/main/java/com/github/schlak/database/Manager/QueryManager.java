package com.github.schlak.database.Manager;

import com.github.schlak.database.ConnectionPool;
import com.github.schlak.database.Debug;
import com.github.schlak.database.Definition.StatementBoxes.*;
import com.github.schlak.database.Exeptions.SQLAppendException;
import com.github.schlak.database.Definition.IGetConnection;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 16.01.2017.
 */
public class QueryManager {

    private Connection connectionQuick;
    private Connection connection;

    private boolean hasSuccess = true;
    private IGetConnection connector;

    private ArrayList<StatementBox> queryList = new ArrayList<>();

    private ArrayList<BasicDeleteBox> deleteBoxes = new ArrayList<>();
    private ArrayList<BasicCreateBox> createBoxes = new ArrayList<>();
    private ArrayList<BasicUpdateBox> updateBoxes = new ArrayList<>();
    private ArrayList<BasicInsertBox> insertBoxes = new ArrayList<>();

    /**
     * Instantiates a new {@link QueryManager}.
     *
     * @param connector the {@link IGetConnection}
     * @throws SQLException the {@link SQLException}
     */
    public QueryManager(IGetConnection connector) throws SQLException {
        this.connector = connector;
        this.connection = connector.getConnection();
        this.connectionQuick = connector.getConnection();

        this.connection.setAutoCommit(false);
    }

    /**
     * Add a {@link BasicDeleteBox} to the {@link QueryManager}.
     * The method try's to identify querys that can be connected to achieve a better performance.
     *
     * @param deleteBox the {@link BasicDeleteBox}
     */
    public void add(BasicDeleteBox deleteBox) {

        for (BasicDeleteBox b : deleteBoxes) {
            if (b.validateAppend(deleteBox)) {
                try {
                    b.appendStatement(deleteBox);
                    return;
                } catch (SQLAppendException e) {
                    e.printStackTrace();
                }
            }
        }
        deleteBoxes.add(deleteBox);
    }

    /**
     * Add a {@link BasicCreateBox} to the {@link QueryManager}.
     * The method try's to identify statements that got added a second time and remove the second {@link StatementBox}.
     *
     * @param createBox the {@link BasicCreateBox}
     */
    public void add(BasicCreateBox createBox) {

        for (BasicCreateBox stmtBox :
                createBoxes) {
            if (stmtBox.equals(createBox)) return;
        }

        createBoxes.add(createBox);

    }

    /**
     * Add a {@link BasicInsertBox} to the {@link QueryManager}.
     * The method try's to identify querys that can be connected to achieve a better performance.
     *
     * @param insertBox the insert box
     */
    public void add(BasicInsertBox insertBox) {

        for (BasicInsertBox b : insertBoxes) {
            if (b.validateAppend(insertBox)) {
                try {
                    b.appendStatement(insertBox);
                    return;
                } catch (SQLAppendException e) {
                    e.printStackTrace();
                }
            }
        }
        insertBoxes.add(insertBox);
    }

    /**
     * Add a {@link StatementBox} an uses the specific methods if they exists.
     *
     * @param statementBox the statement box
     */
    public void add(StatementBox statementBox) {

        if (statementBox.getType().equals(BasicCreateBox.class))
            add((BasicCreateBox) statementBox);
        if (statementBox.getType().equals(BasicUpdateBox.class))
            updateBoxes.add((BasicUpdateBox) statementBox);
        if (statementBox.getType().equals(BasicInsertBox.class))
            add((BasicInsertBox) statementBox);
        if (statementBox.getType().equals(BasicDeleteBox.class))
            add((BasicDeleteBox) statementBox);
    }

    /**
     * Execute all querys. At first the create querys, than the delete,
     * insert and at least the update querys got executed.
     *
     * @throws SQLException the sql exception
     */
    public void execute() throws SQLException {

        ArrayDeque<StatementBox> queue = new ArrayDeque<>();
        List<String> stringList = new ArrayList<>();
        Statement statement = connection.createStatement();

        //adding the create section

        createBoxes.stream().filter(createBox -> {
            if (stringList.contains(createBox.getPreparedStatementString()))
                return false;
            else {
                stringList.add(createBox.getPreparedStatementString());
                return true;
            }
        }).forEach(queue::add);

        //adding the delete section

        deleteBoxes.forEach(queue::add);

        //adding the insert section

        insertBoxes.forEach(queue::add);

        //adding the update section

        updateBoxes.forEach(queue::add);

        int size = queue.size();

        try {
            for (int i = 0; i < size; i++) {
                PreparedStatement preparedStatement = queue.poll().getPreparedStatement(this.connection);

                Debug.out(preparedStatement.toString());
                preparedStatement.execute();
            }

            connection.commit();
            this.hasSuccess = true;
        } catch (SQLException e) {
//            e.printStackTrace();
            hasSuccess = false;
            connection.rollback();
            throw e;
        }
    }

    /**
     * Rollback the executed querys.
     *
     * @throws SQLException the sql exception
     */
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * Execute the given query immediately and get the {@link ResultSet} of the query.
     *
     * @param statementBox the statement box
     * @return the result set
     * @throws SQLException the sql exception
     */
    public ResultSet executeQueryNow(StatementBox statementBox) throws SQLException {
        return statementBox.getPreparedStatement(connectionQuick).executeQuery();
    }

    /**
     * Execute the given query immediately without getting the {@link ResultSet} of the query.
     *
     * @param statementBox the statement box
     * @throws SQLException the sql exception
     */
    public void executeNow(StatementBox statementBox) throws SQLException {
        statementBox.getPreparedStatement(connectionQuick).executeQuery().close();
    }

    /**
     * Closes the connections and return them to the {@link ConnectionPool}.
     */
    public void close() {
        ConnectionPool.getDefaultInstance().returnConnection(connectionQuick);
        ConnectionPool.getDefaultInstance().returnConnection(connection);

        connectionQuick = null;
        connection = null;
    }

    /**
     * Possibility to get the status of the executed querys.
     * If the querys are successful
     *
     * @return the boolean
     */
    public boolean hadSuccess() {
        return this.hasSuccess;
    }

}
