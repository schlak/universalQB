package test.MySQLTest;

import com.github.schlak.database.ConnectionPool;
import com.github.schlak.database.Definition.FixedValues.BasicDataType;
import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.QueryFactory;
import com.github.schlak.database.Definition.StatementBoxes.BasicCreateBox;
import com.github.schlak.database.Definition.StatementBoxes.BasicInsertBox;
import com.github.schlak.database.Definition.StatementBoxes.BasicSelectBox;
import com.github.schlak.database.Definition.StatementBoxes.BasicUpdateBox;
import com.github.schlak.database.Definition.Statements.BasicCreateBuilder;
import com.github.schlak.database.Definition.Statements.BasicInsertBuilder;
import com.github.schlak.database.Definition.Statements.BasicSelectBuilder;
import com.github.schlak.database.Definition.Statements.BasicUpdateBuilder;
import com.github.schlak.database.Implementation.MySQL.MySQLConnector;

/**
 * Created by jschl on 17.03.2017.
 */
public class SimpleTestMethods {

    public static String COLUMN_ID = "id";
    public static String COLUMN_NAME = "name";

    public static void AcreateTest(String TABLE) throws Exception {

        ConnectionPool connectionPool = ConnectionPool.getInstance(new MySQLConnector().getDatabaseIdentifier());

        QueryFactory factory = connectionPool.getQueryFactory();

        BasicCreateBuilder createBuilder = factory.getCreateBuilder();

        createBuilder.setTableName(TABLE);

        Column column = factory.getNewDBColumnInstance();
        column.setTableName(TABLE).setColumnName(COLUMN_ID);

        Column column2 = factory.getNewDBColumnInstance();
        column2.setTableName(TABLE).setColumnName(COLUMN_NAME);

        ColumnDefinition columnDefinition = createBuilder.getNewColumnDefinition();
        columnDefinition.setColumn(column).setType(BasicDataType.INTEGER);

        ColumnDefinition columnDefinition1 = createBuilder.getNewColumnDefinition();
        columnDefinition1.setColumn(column2).setType(BasicDataType.TEXT);

        createBuilder.addColumnDefinition(columnDefinition);
        createBuilder.addColumnDefinition(columnDefinition1);

        BasicCreateBox createBox = createBuilder.getStatementBox();


        createBox.getPreparedStatement(connectionPool.getConnection()).execute();
    }

    public static void BinsertTest(String TABLE) throws Exception {

        ConnectionPool connectionPool = ConnectionPool.getInstance(new MySQLConnector().getDatabaseIdentifier());

        QueryFactory factory = connectionPool.getQueryFactory();

        BasicInsertBuilder insertBuilder = factory.getInsertBuilder();

        insertBuilder.setTableName(TABLE);

        Column columnID = factory.getNewDBColumnInstance();
        columnID.setTableName(TABLE).setColumnName(COLUMN_ID);

        Column columnName = factory.getNewDBColumnInstance();
        columnName.setTableName(TABLE).setColumnName(COLUMN_NAME);

        ValueAllocation valueAllocation_id = factory.getNewValueAllocationInstance();
        valueAllocation_id.setColumn(columnID).setValue(factory.getIdProvider().getNewID(TABLE) + "");

        ValueAllocation valueAllocation_name = factory.getNewValueAllocationInstance();
        valueAllocation_name.setColumn(columnName).setValue("Bla");

        insertBuilder.set(valueAllocation_id);
        insertBuilder.set(valueAllocation_name);

        BasicInsertBox basicInsertBox = insertBuilder.getStatementBox();

        basicInsertBox.getPreparedStatement(connectionPool.getConnection()).execute();
    }

    public static void CselectTest(String TABLE) throws Exception {

        ConnectionPool connectionPool = ConnectionPool.getInstance(new MySQLConnector().getDatabaseIdentifier());

        QueryFactory factory = connectionPool.getQueryFactory();

        BasicSelectBuilder selectBuilder = factory.getSelectBuilder();

        selectBuilder.setTableName(TABLE);

        BasicSelectBox selectBox = selectBuilder.getStatementBox();
        selectBox.getPreparedStatement(connectionPool.getConnection()).execute();

    }

    public static void DupdateTest(String TABLE, String newText) throws Exception {

        ConnectionPool connectionPool = ConnectionPool.getDefaultInstance();

        QueryFactory factory = connectionPool.getQueryFactory();

        BasicUpdateBuilder updateBuilder = factory.getUpdateBuilder();

        updateBuilder.setTableName(TABLE);

        Column columnName = factory.getNewDBColumnInstance();
        columnName.setTableName(TABLE).setColumnName(COLUMN_NAME);

        ValueAllocation valueAllocation_name = factory.getNewValueAllocationInstance();
        valueAllocation_name.setColumn(columnName).setValue(newText);

        updateBuilder.set(valueAllocation_name);

        BasicUpdateBox basicUpdateBox = updateBuilder.getStatementBox();
        basicUpdateBox.getPreparedStatement(connectionPool.getConnection()).execute();

    }

    public static void EdropTable(String TABLE) throws Exception {

        ConnectionPool connectionPool = ConnectionPool.getInstance(new MySQLConnector().getDatabaseIdentifier());

        connectionPool.getConnection().prepareStatement("DROP TABLE IF EXISTS " + TABLE).execute();
    }


    public static void normalSelect(String Table)throws Exception{
        ConnectionPool.getDefaultInstance().getConnection().prepareStatement("SELECT  * FROM " + Table).execute();
    }

    public static void normalCreate(String Table)throws Exception{
        ConnectionPool.getDefaultInstance().getConnection().prepareStatement("CREATE TABLE "+ Table +"(id INTEGER, name TEXT);").execute();
    }

    public static void normalInsert(String Table, String id)throws Exception{
        ConnectionPool.getDefaultInstance().getConnection().prepareStatement("INSERT INTO "+ Table +" (id, name) VALUES ( " + id + " , 'bal' );").execute();
    }

    public static void normalDrop(String Table)throws Exception{
        ConnectionPool.getDefaultInstance().getConnection().prepareStatement("DROP TABLE " + Table).execute();
    }


    public static void normalUpdate(String Table)throws Exception{
        ConnectionPool.getDefaultInstance().getConnection().prepareStatement("UPDATE "+ Table +" SET name = 'bal' ; ").execute();
    }

}