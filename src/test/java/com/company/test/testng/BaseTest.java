package com.company.test.testng;

import com.company.framework.utils.Context;
import com.company.test.runner.listeners.ConditionalSkipTestAnalyzer;
import com.company.test.runner.listeners.ListenerTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import java.io.IOException;
import static com.company.test.runner.Commons.getStatisticalData;
import static com.company.test.runner.Commons.showCounter;


public abstract class BaseTest {
    private Context context;
    private WebDriver driver;

    public BaseTest() {
        driver = null;
        this.context = new Context();
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        driver = (WebDriver) context.getValue("driver");
        driver.quit();
    }

    @AfterTest
    public void afterTest() throws IOException {
        showCounter(ConditionalSkipTestAnalyzer.getCountWorking(),
                ConditionalSkipTestAnalyzer.getCountBroken(),
                ConditionalSkipTestAnalyzer.getCountNotImplementedYet(),
                ConditionalSkipTestAnalyzer.getCountOutOfScope(),
                ConditionalSkipTestAnalyzer.getCountNullTag());

        getStatisticalData(ListenerTest.getCountSuccess(), ListenerTest.getCountFailed());
    }

    public Context getContext(){
        return context;
    }

    public WebDriver getDriver(){
        return driver;
    }

}
