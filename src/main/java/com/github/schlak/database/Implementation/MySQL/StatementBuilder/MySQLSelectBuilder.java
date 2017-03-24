package com.github.schlak.database.Implementation.MySQL.StatementBuilder;

import com.github.schlak.database.Debug;
import com.github.schlak.database.Definition.GeneralObjects.*;
import com.github.schlak.database.Definition.GeneralOperations.AddGroupByClause;
import com.github.schlak.database.Definition.GeneralOperations.AddHavingClause;
import com.github.schlak.database.Definition.GeneralOperations.AddJoinClause;
import com.github.schlak.database.Definition.GeneralOperations.AddOrderByClause;
import com.github.schlak.database.Definition.Statements.BasicSelectBuilder;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.MySQLConditionStack;
import com.github.schlak.database.Implementation.MySQL.StatmentBoxes.MysqlSelectBox;

import java.util.ArrayList;
import java.util.List;


public class MySQLSelectBuilder extends BasicSelectBuilder implements AddJoinClause, AddGroupByClause, AddHavingClause, AddOrderByClause {

    private MySQLConditionStack havingConditionStack;
    private List<TableJoinInformation> joinList;
    private List<Column> groupByList;
    private List<OrderByDefinition> orderByList;

    /**
     * Instantiates a new {@link MySQLSelectBuilder}.
     */
    public MySQLSelectBuilder() {
        super();
        this.whereConditionStack = new MySQLConditionStack();
        this.havingConditionStack = new MySQLConditionStack();
        this.joinList = new ArrayList<>();
        this.groupByList = new ArrayList<>();
        this.orderByList = new ArrayList<>();
    }


    public String toString() {
        Debug.out(new Exception().getStackTrace()[0].toString(), "do not use the getConditionString method (select)");
        return "";
    }

    /**
     * The validate method is used to rudimentary check whether it is possible or not to build a proper SQL statement based on the
     * parameters given. For the select statement the validation only checks the tableName name because without a tableName
     * name the selection could base no statement will work. The other logical dependencies are not checked.
     *
     * @throws QueryBuildException
     */
    private void validate() throws QueryBuildException {
        if (this.table == null)
            throw new QueryBuildException("No tableName is set for the query");
    }


    /**
     * The method builds a string with all columns that are in the show column list,
     * separated by commas. If the number of columns in the list equals zero the return
     * string contains a "*" (star/asterisk) which means in the SQL syntax that every column
     * should be selected.
     *
     * @return string with the columns
     */
    private String getColumnString() {
        //building the string with the columns that should be shown
        String columnsToShowString = "";
        boolean isFirst = true;

        if (columnList.size() == 0) columnsToShowString = "*";

        else {
            for (Column column : columnList) {
                if (!isFirst) columnsToShowString = columnsToShowString.concat(", ");
                columnsToShowString = columnsToShowString.concat(column.getColumnString());
                isFirst = false;
            }
        }
        return columnsToShowString;
    }

    /**
     * The method is connecting the joins of the join information list to one big join string
     * The parts of the string, which were generated in the {@link TableJoinInformation#getJoinString()} method
     * and get connected by spaces.
     * <p>
     * //TODO change the method naming setTableName to string to something like getJoinStatement
     *
     * @return join string
     */
    private String getJoinString() {
        //building the string for joining tables
        String joinInformationString = "";
        boolean isFirst = true;

        isFirst = true;
        for (TableJoinInformation tableJoinInformation : joinList) {
            if (!isFirst) joinInformationString = joinInformationString.concat(" ");
            joinInformationString = joinInformationString.concat(tableJoinInformation.getJoinString());
            isFirst = false;
        }

        return joinInformationString;
    }

    /**
     * The group by string that is generate is based on the {@link MySQLSelectBuilder#groupByList} and contains
     * the "GROUP BY" preamble. If there are multiple groupings the columns are separated by an comma.
     *
     * @return group by string
     */
    private String getGroupByString() {
        //building the group by string
        boolean isFirst = true;
        String groupByString = "";

        //TODO my implement a plausibility check weather the columns exists in select
        for (Column column : groupByList) {
            if (!isFirst) groupByString = groupByString.concat(", ");
            groupByString = groupByString.concat(column.getColumnString());
            isFirst = false;
        }
        if (!groupByString.equals(""))
            groupByString = "GROUP BY " + groupByString;

        return groupByString;
    }

    /**
     * The generation of the order by part is based on the {@link MySQLSelectBuilder#orderByList} with its
     * {@link OrderByDefinition}s which create the String for its column by calling the {@link OrderByDefinition#getOrderByString()}.
     * If there are multiple columns the result should be ordered by, the order strings per column will be connected with
     * commas.
     *
     * @return order by String
     */
    private String getOrderByString() {
        boolean isFirst = true;
        String orderByString = "";

        for (OrderByDefinition column : orderByList) {
            if (!isFirst) orderByString = orderByString.concat(", ");
            orderByString = orderByString.concat(column.getOrderByString());
            isFirst = false;
        }
        if (!orderByString.equals(""))
            orderByString = "ORDER BY " + orderByString;

        return orderByString;
    }

    /**
     * The limit string just contains the key word "LIMIT" and the number of data sets the query will effect or in case
     * of a select statement, the statement will return. If the field {@link MySQLSelectBuilder#limit} is zero the method
     * will return an empty string because there will be no limitation of the number of data sets.
     *
     * @return limit string
     */
    private String getLimitString() {
        //build limit string
        String limitString = "";

        if (this.limit != 0) {
            limitString = "LIMIT " + this.limit;
        }

        return limitString;
    }

    /**
     * The {@link MysqlSelectBox} is the representation of the Statement in a form that you can't change the statement anymore.
     * This format is the only one, that is excepted by the {@link com.github.schlak.database.Manager.QueryManager}
     * to execute a query or to be able to get a prepared statement.
     *
     * @return the {@link MysqlSelectBox}
     * @throws QueryBuildException if the validation fails
     */
    @Override
    public MysqlSelectBox getStatementBox() throws QueryBuildException {
        validate();
        return new MysqlSelectBox(table, getColumnString(),
                getOrderByString(), getGroupByString(),
                getJoinString(), whereConditionStack,
                havingConditionStack);
    }

    @Override
    public void groupBy(Column column) {
        this.groupByList.add(column);
    }

    @Override
    public void join(TableJoinInformation joinInformation) {
        this.joinList.add(joinInformation);
    }

    @Override
    public void orderBy(OrderByDefinition orderByColumn) {
        this.orderByList.add(orderByColumn);
    }

    @Override
    public void having(ValueAllocation valueAllocation) {
        this.havingConditionStack.addCondition(valueAllocation);
    }

    @Override
    public void having(ConditionStack conditionStack) {
        this.havingConditionStack.addCondition(conditionStack);
    }
}
