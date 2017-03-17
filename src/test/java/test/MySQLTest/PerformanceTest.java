package test.MySQLTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by jschl on 17.03.2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PerformanceTest {


    public void performanceTest()throws Exception{

        InitDatabase.setup();

        String table = "performanceTestTable";
        String normalTable = "normalPerformanceTestTable";
        long begin;
        int rounds = 500;
        long result;


        System.out.println("Create Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds;i++){
            SimpleTestMethods.AcreateTest(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.println(result);
        System.out.println("Per action: " + (result / rounds));


        System.out.println("\n");


        System.out.println("normal Create Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds;i++){
            SimpleTestMethods.normalCreate(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.println(result);
        System.out.println("Per action: " + (result / rounds));


        System.out.println("\n");


        System.out.println("Insert Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds;i++){
            SimpleTestMethods.BinsertTest(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.println(result);
        System.out.println("Per action: " + (result / rounds));


        System.out.println("\n");


        System.out.println("normal Insert Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds;i++){
            SimpleTestMethods.normalInsert(normalTable + i, i +"");
        }
        result = System.currentTimeMillis() - begin;
        System.out.println(result);
        System.out.println("Per action: " + (result / rounds));


        System.out.println("\n");


        System.out.println("Select Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds;i++){
            SimpleTestMethods.CselectTest(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.println(result);
        System.out.println("Per action: " + (result / rounds));


        System.out.println("\n");


        System.out.println("normal select Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds;i++){
            SimpleTestMethods.normalSelect(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.println(result);
        System.out.println("Per action: " + (result / rounds));


        System.out.println("\n");


        System.out.println("Update Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds;i++){
            SimpleTestMethods.DupdateTest(table + i, "Performance");
        }
        result = System.currentTimeMillis() - begin;
        System.out.println(result);
        System.out.println("Per action: " + (result / rounds));


        System.out.println("\n");


        System.out.println("normal Update Data");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds;i++){
            SimpleTestMethods.normalUpdate(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.println(result);
        System.out.println("Per action: " + (result / rounds));


        System.out.println("\n");


        System.out.println("Drop Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds;i++){
            SimpleTestMethods.EdropTable(table + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.println(result);
        System.out.println("Per action: " + (result / rounds));


        System.out.println("\n");


        System.out.println("normal Drop Table");
        begin = System.currentTimeMillis();

        for (int i = 1; i <= rounds;i++){
            SimpleTestMethods.normalDrop(normalTable + i);
        }
        result = System.currentTimeMillis() - begin;
        System.out.println(result);
        System.out.println("Per action: " + (result / rounds));

    }

}
