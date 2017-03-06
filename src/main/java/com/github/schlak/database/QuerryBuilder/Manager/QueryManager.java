package com.github.schlak.database.QuerryBuilder.Manager;

import com.github.schlak.database.DBConnectionPool;
import com.github.schlak.database.Debug;
import com.github.schlak.database.QuerryBuilder.Interface.IGetConnection;
import com.github.schlak.database.QuerryBuilder.SQLAppendException;
import com.github.schlak.database.QuerryBuilder.Statement.CreateBox;
import com.github.schlak.database.QuerryBuilder.Statement.DeleteBox;
import com.github.schlak.database.QuerryBuilder.Statement.InsertBox;
import com.github.schlak.database.QuerryBuilder.Statement.UpdateBox;
import com.github.schlak.database.QuerryBuilder.StatementBox;

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

    private ArrayList<DeleteBox> deleteBoxes = new ArrayList<>();
    private ArrayList<CreateBox> createBoxes = new ArrayList<>();
    private ArrayList<UpdateBox> updateBoxes = new ArrayList<>();
    private ArrayList<InsertBox> insertBoxes = new ArrayList<>();

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
     * Add a {@link DeleteBox} to the {@link QueryManager}.
     * The method try's to identify querys that can be connected to achieve a better performance.
     *
     * @param deleteBox the {@link DeleteBox}
     */
    public void add(DeleteBox deleteBox) {

        for (DeleteBox b : deleteBoxes) {
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
     * Add a {@link CreateBox} to the {@link QueryManager}.
     * The method try's to identify statements that got added a second time and remove the second {@link StatementBox}.
     *
     * @param createBox the {@link CreateBox}
     */
    public void add(CreateBox createBox) {

        for (CreateBox stmtBox :
                createBoxes) {
            if (stmtBox.equals(createBox)) return;
        }

        createBoxes.add(createBox);

    }

    /**
     * Add a {@link InsertBox} to the {@link QueryManager}.
     * The method try's to identify querys that can be connected to achieve a better performance.
     *
     * @param insertBox the insert box
     */
    public void add(InsertBox insertBox) {

        for (InsertBox b : insertBoxes) {
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

        if (statementBox.getType().equals(CreateBox.class))
            add((CreateBox) statementBox);
        if (statementBox.getType().equals(UpdateBox.class))
            updateBoxes.add((UpdateBox) statementBox);
        if (statementBox.getType().equals(InsertBox.class))
            add((InsertBox) statementBox);
        if (statementBox.getType().equals(DeleteBox.class))
            add((DeleteBox) statementBox);
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
     * Closes the connections and return them to the {@link DBConnectionPool}.
     */
    public void close() {
        DBConnectionPool.getDefaultInstance().returnConnection(connectionQuick);
        DBConnectionPool.getDefaultInstance().returnConnection(connection);

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
