package com.schlak.Database.QuerryBuilder;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Queue;

/**
 * Created by joans on 24.01.17.
 */
public abstract class StatementBox {

    /**
     * Creates the prepared statement and returns it.
     *
     * @param connection the connection
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        Queue<String> parameterQueue = this.getParameterQueue();
        int queueLength = parameterQueue.size();

        PreparedStatement stmt = connection.prepareStatement(this.getPreparedStatementString());

        for (int i = 0; i < queueLength; i++) {
            stmt.setString(i + 1, parameterQueue.poll());
        }

        return stmt;
    }

    /**
     * Generates the plane statement without replacing the variables is question marks and
     * binding them after that.
     *
     * @param connection the connection
     * @return the statement
     * @throws SQLException the sql exception
     */
    public abstract PreparedStatement getStatement(Connection connection) throws SQLException;

    /**
     * Used to validate the query box. If the query box is valid it will return <b>true</b>
     * if not the return value will be <b>false</b>
     *
     * @return the boolean
     */
    public abstract boolean validate();

    /**
     * The method is used to get the parameter queue that could be used to prepare the statement like
     * the method {@link StatementBox#getPreparedStatement(Connection)}.
     *
     * @return the parameter queue
     */
    public abstract Queue<String> getParameterQueue();

    /**
     * The returning value is the query, that contains <b>question marks</b> instead of the values
     * that could be bound on there later.
     *
     * @return the prepared statement string
     */
    public abstract String getPreparedStatementString();

    /**
     * The type that is returned is used to identify the type of statement box like
     * in the {@link com.schlak.Database.QuerryBuilder.Manager.QueryManager} class.
     * The method will return its on class.
     *
     *
     * @return the type
     */
    public abstract Class getType();

}
