package com.company.test.runner.listeners;

import com.company.framework.utils.Commons;
import com.company.test.testng.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext ;
import org.testng.ITestListener ;
import org.testng.ITestResult ;
import java.io.BufferedWriter;
import java.io.IOException;
import static com.company.framework.utils.Writer.CreateWriter;
import static com.company.framework.utils.Writer.getWriter;
import static com.company.test.runner.Commons.logo;


public class ListenerTest extends BaseTest implements ITestListener {
    private static int countWorking, countSuccess, countSkipped, countFailed;
    private static BufferedWriter writer;
    private long duration;
    private long startTime;
    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerTest.class);


    @Override
    public void onFinish(ITestContext result) {
        LOGGER.info("============================== END =================================");
    }

    @Override
    public void onStart(ITestContext result) {
        startTime = System.currentTimeMillis();
        CreateWriter();
        logo("Hyperion");
        LOGGER.info("============================== START =================================");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult result) {
        writer = getWriter();
        duration = System.currentTimeMillis() - startTime;

        LOGGER.info("X - Testcase: " +  result.getName() + " - Status : Failed");
        countFailed += 1;
        try {
            writer.write(result.getName() + " - took " + duration + "ms");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Commons.makeScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.info("Testcase: " +  result.getName() + " - Status : Skipped");
        countSkipped += 1;
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info(result.getName()+" testcase started");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("OK - Testcase: " +  result.getName() + " - Status : Passed");
        countSuccess += 1;
    }

    public static int getCountSuccess() {
        return countSuccess;
    }

    public static int getCountFailed() {
        return countFailed;
    }

    public static int getCountSkipped() {
        return countSkipped;
    }

    public static int getCountWorking() {
        return countWorking;
    }

}
