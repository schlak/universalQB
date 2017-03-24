package com.github.schlak.database.Implementation.MySQL.StatmentBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.Definition.StatementBoxes.BasicCreateBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Created by Jonas Schlak on 25.01.17.
 */
public class MysqlCreateBox extends BasicCreateBox {

    public MysqlCreateBox(List<ColumnDefinition> columnDefinitionList, String tableName) {
        super(columnDefinitionList, tableName);
    }

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("CREATE TABLE " +
                this.tableName +
                this.getColumnDefinition() +
                ";");
    }

    @Override
    public boolean validate() {
        return !(tableName.equals("") || columnDefinitionList.size() == 0);
    }

    @Override
    public Queue<String> getParameterQueue() {
        return new ArrayDeque<>();
    }

    @Override
    public String getPreparedStatementString() {
        return "CREATE TABLE " +
                this.tableName +
                this.getColumnDefinition() +
                ";";
    }

    private String getColumnDefinition() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;

        stringBuilder.append(" (");

        for (ColumnDefinition idbColumnDefinition : this.columnDefinitionList) {
            if (!isFirst) stringBuilder.append(", ");
            stringBuilder.append(idbColumnDefinition.getDefinition());
            isFirst = false;
        }


        stringBuilder.append(")");

        return stringBuilder.toString();
    }
}
