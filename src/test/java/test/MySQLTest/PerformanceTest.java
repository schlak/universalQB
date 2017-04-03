package test.MySQLTest;

import com.github.schlak.database.ObjectRecycler;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by jschl on 17.03.2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PerformanceTest {

    //    @Test
    public void performanceTest()throws Exception{

        InitDatabase.setup();

        String table = "performanceTestTable";
        String normalTable = "normalPerformanceTestTable";
        long begin;
        int rounds = 100;
        long result;


        System.out.print("Create Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++){
            SimpleTestMethods.AcreateTest(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print((result / rounds));


        System.out.print("\n");
        System.out.print(ObjectRecycler.numberOfCachedItems());
        System.out.print("\n");


        System.out.print("normal Create Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++){
            SimpleTestMethods.normalCreate(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print((result / rounds));


        System.out.print("\n");


        System.out.print("Insert Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++){
            SimpleTestMethods.BinsertTest(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print((result / rounds));


        System.out.print("\n");
        System.out.print(ObjectRecycler.numberOfCachedItems());
        System.out.print("\n");


        System.out.print("normal Insert Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++){
            SimpleTestMethods.normalInsert(normalTable + i, i +"");
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print((result / rounds));


        System.out.print("\n");


        System.out.print("Select Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++){
            SimpleTestMethods.CselectTest(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print((result / rounds));


        System.out.print("\n");
        System.out.print(ObjectRecycler.numberOfCachedItems());
        System.out.print("\n");


        System.out.print("normal select Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++){
            SimpleTestMethods.normalSelect(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print((result / rounds));


        System.out.print("\n");


        System.out.print("Update Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++){
            SimpleTestMethods.DupdateTest(table + i, "Performance");
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print((result / rounds));


        System.out.print("\n");
        System.out.print(ObjectRecycler.numberOfCachedItems());
        System.out.print("\n");


        System.out.print("normal Update Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++){
            SimpleTestMethods.normalUpdate(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print((result / rounds));


        System.out.print("\n");


        System.out.print("Drop Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++){
            SimpleTestMethods.EdropTable(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print((result / rounds));


        System.out.print("\n");
        System.out.print(ObjectRecycler.numberOfCachedItems());
        System.out.print("\n");


        System.out.print("normal Drop Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++){
            SimpleTestMethods.normalDrop(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print((result / rounds));

    }

    @Test
    public void reRun() throws Exception {

        for (int i = 100; i <= 1000000; i = i + 100) {
            System.out.print(i);
            System.out.print("|");

            forExcel(i);
            System.out.print("\n");
        }

    }


    public void forExcel(int rounds) throws Exception {

        InitDatabase.setup();

        String table = "performanceTestTable";
        String normalTable = "normalPerformanceTestTable";
        long begin;
        long result;

        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++) {
            SimpleTestMethods.normalCreate(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;

        System.out.print(result);
        System.out.print("|");
        System.out.print((result / rounds));
        System.out.print("|");


        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++) {
            SimpleTestMethods.normalInsert(normalTable + i, i + "");
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print("|");
        System.out.print((result / rounds));
        System.out.print("|");

//        System.out.print("normal select Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++) {
            SimpleTestMethods.normalSelect(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print("|");
        System.out.print((result / rounds));
        System.out.print("|");


//        System.out.print("normal Update Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++) {
            SimpleTestMethods.normalUpdate(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print("|");
        System.out.print((result / rounds));
        System.out.print("|");

//        System.out.print("normal Drop Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++) {
            SimpleTestMethods.normalDrop(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print("|");
        System.out.print((result / rounds));
        System.out.print("|");


//        System.out.print("Create Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++) {
            SimpleTestMethods.AcreateTest(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print("|");
        System.out.print((result / rounds));
        System.out.print("|");


//        System.out.print("Insert Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++) {
            SimpleTestMethods.BinsertTest(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print("|");
        System.out.print((result / rounds));
        System.out.print("|");


//        System.out.print("Select Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++) {
            SimpleTestMethods.CselectTest(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print("|");
        System.out.print((result / rounds));
        System.out.print("|");


//        System.out.print("Update Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++) {
            SimpleTestMethods.DupdateTest(table + i, "Performance");
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print("|");
        System.out.print((result / rounds));
        System.out.print("|");


//        System.out.print("Drop Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds; i++) {
            SimpleTestMethods.EdropTable(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.print(result);
        System.out.print("|");
        System.out.print((result / rounds));
        System.out.print("|");
    }

}
