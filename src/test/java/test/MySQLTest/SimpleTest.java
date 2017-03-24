package test.MySQLTest;

import com.github.schlak.database.ConnectionPool;
import com.github.schlak.database.Definition.FixedValues.BasicDataType;
import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.StatementBoxes.BasicCreateBox;
import com.github.schlak.database.Definition.StatementBoxes.InsertBox;
import com.github.schlak.database.Definition.StatementBoxes.SelectBox;
import com.github.schlak.database.Definition.StatementBoxes.UpdateBox;
import com.github.schlak.database.Definition.Statements.BasicCreateBuilder;
import com.github.schlak.database.Definition.Statements.BasicInsertBuilder;
import com.github.schlak.database.Definition.Statements.BasicSelectBuilder;
import com.github.schlak.database.Definition.Statements.BasicUpdateBuilder;
import com.github.schlak.database.Implementation.MySQL.MySQLConnector;
import com.github.schlak.database.Definition.QueryFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by jonas on 17.03.17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleTest {

    public static String TABLE = "testTabelle";
    public static String COLUMN_ID = "id";
    public static String COLUMN_NAME = "name";

    @Test
    public void AcreateTest() throws Exception {

        InitDatabase.setup();
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

    @Test
    public void BinsertTest() throws Exception {

        InitDatabase.setup();
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

        InsertBox insertBox = insertBuilder.getStatementBox();

        insertBox.getPreparedStatement(connectionPool.getConnection()).execute();
    }

    @Test
    public void CselectTest() throws Exception {

        InitDatabase.setup();
        ConnectionPool connectionPool = ConnectionPool.getInstance(new MySQLConnector().getDatabaseIdentifier());

        QueryFactory factory = connectionPool.getQueryFactory();

        BasicSelectBuilder selectBuilder = factory.getSelectBuilder();

        selectBuilder.setTableName(TABLE);

        SelectBox selectBox = selectBuilder.getStatementBox();
        selectBox.getPreparedStatement(connectionPool.getConnection()).execute();

    }

    @Test
    public void DupdateTest() throws Exception {

        InitDatabase.setup();
        ConnectionPool connectionPool = ConnectionPool.getDefaultInstance();

        QueryFactory factory = connectionPool.getQueryFactory();

        BasicUpdateBuilder updateBuilder = factory.getUpdateBuilder();

        updateBuilder.setTableName(TABLE);

        Column columnName = factory.getNewDBColumnInstance();
        columnName.setTableName(TABLE).setColumnName(COLUMN_NAME);

        ValueAllocation valueAllocation_name = factory.getNewValueAllocationInstance();
        valueAllocation_name.setColumn(columnName).setValue("BlaU");

        updateBuilder.set(valueAllocation_name);

        UpdateBox updateBox = updateBuilder.getStatementBox();
        updateBox.getPreparedStatement(connectionPool.getConnection()).execute();

    }


    @Test
    public void EdropTable() throws Exception {
        InitDatabase.setup();
        ConnectionPool connectionPool = ConnectionPool.getInstance(new MySQLConnector().getDatabaseIdentifier());

        connectionPool.getConnection().prepareStatement("DROP TABLE IF EXISTS " + TABLE).execute();
    }

}
