package com.company.test.testng;


import com.company.test.runner.listeners.ConditionalSkipTestAnalyzer;
import com.company.test.runner.listeners.ListenerOthersTest;
import com.company.test.runner.tags.Broken;
import com.company.test.runner.tags.NotImplementedYet;
import com.company.test.runner.tags.OutOfScope;
import com.company.test.runner.tags.Working;
import org.testng.annotations.*;
import java.io.IOException;
import static com.company.test.runner.Commons.getStatisticalData;
import static com.company.test.runner.Commons.showCounter;
import static org.assertj.core.api.Assertions.assertThat;


@Listeners({ ConditionalSkipTestAnalyzer.class, ListenerOthersTest.class})
public class TestExample {

    @BeforeGroups("regression")
    public void beforeGroups() {
        System.out.println("@BeforeGroups");
    }

    @AfterGroups("regression")
    public void afterGroups() {
        System.out.println("@AfterGroups");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("@BeforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("@AfterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("@AfterMethod");
    }

    @AfterTest
    public void afterTest() throws IOException {
        showCounter(ConditionalSkipTestAnalyzer.getCountWorking(),
                    ConditionalSkipTestAnalyzer.getCountBroken(),
                    ConditionalSkipTestAnalyzer.getCountNotImplementedYet(),
                    ConditionalSkipTestAnalyzer.getCountOutOfScope(),
                    ConditionalSkipTestAnalyzer.getCountNullTag());

        getStatisticalData(ListenerOthersTest.getCountSuccess(), ListenerOthersTest.getCountFailed());
    }

    @Working
    @Test(groups = "regression")
    public void runTest1() {
        System.out.println("@Test - runTest1");
    }

    @Broken
    @Test
    public void runTest2() {
        System.out.println("@Test - runTest2");
    }

    @NotImplementedYet
    @Test
    public void runTest3() {
        System.out.println("@Test - runTest3");
    }

    @OutOfScope
    @Test
    public void runTest4() {
        System.out.println("@Test - runTest4");
    }

    @Working
    @Test(groups = "regression")
    public void runTest5() {
        assertThat(false).isTrue();
        System.out.println("@Test - runTest5");
    }

    @Broken
    @Test
    public void runTest6() {
        System.out.println("@Test - runTest6");
    }

    @Working
    @Test(groups = "regression")
    public void runTest7() {
        System.out.println("@Test - runTest7");
    }

    @Working
    @Test(groups = "regression")
    public void runTest8() {
        System.out.println("@Test - runTest8");
    }


    @Test(groups = "regression")
    public void runTest9() {
        System.out.println("@Test - runTest9");
    }

    @Working
    @Test(groups = "regression")
    public void runTest10() {
        System.out.println("@Test - runTest10");
    }
}

