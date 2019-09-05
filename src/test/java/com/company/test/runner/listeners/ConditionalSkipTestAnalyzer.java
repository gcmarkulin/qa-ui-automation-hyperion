package com.company.test.runner.listeners;

import java.lang.reflect.Method;
import com.company.test.runner.tags.Broken;
import com.company.test.runner.tags.NotImplementedYet;
import com.company.test.runner.tags.OutOfScope;
import com.company.test.runner.tags.Working;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;


public class ConditionalSkipTestAnalyzer implements IInvokedMethodListener {
    public static int countWorking, countBroken, countNotImplementedYet, countOutOfScope, countNullTag;
    public static String tagName;

    public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method == null) {
            return;
        }

        if (method.isAnnotationPresent(Working.class)) {
           countWorking += 1;
        } else if (method.isAnnotationPresent(NotImplementedYet.class)) {
            countNotImplementedYet += 1;
            throw new SkipException("Test tagged as notImplementedYet, so skipping.");
        } else if (method.isAnnotationPresent(OutOfScope.class)) {
            countOutOfScope += 1;
            throw new SkipException("Test tagged as outOfScope, so skipping");
        } else if (method.isAnnotationPresent(Broken.class)) {
            countBroken += 1;
            throw new SkipException("Test tagged as broken, so skipping");

        } else if (method.isAnnotationPresent(BeforeMethod.class)) {

        } else if (method.isAnnotationPresent(AfterMethod.class)) {

        } else if (method.isAnnotationPresent(BeforeGroups.class)) {

        } else if (method.isAnnotationPresent(AfterGroups.class)) {

        } else if (method.isAnnotationPresent(AfterClass.class)) {

        } else if (method.isAnnotationPresent(BeforeClass.class)) {

        } else if (method.isAnnotationPresent(BeforeTest.class)) {

        } else if (method.isAnnotationPresent(AfterTest.class)) {

        } else if (method.isAnnotationPresent(BeforeSuite.class)) {

        } else if (method.isAnnotationPresent(AfterSuite.class)) {

        } else {
            countNullTag += 1;
          throw new SkipException("Test tagged incorrectly, so skipping");
        }
        return;
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        tagName = testResult.getMethod().getConstructorOrMethod().getMethod().getName();
    }

    public static int getCountBroken() {
        return countBroken;
    }

    public static int getCountNotImplementedYet() {
        return countNotImplementedYet;
    }

    public static int getCountOutOfScope() {
        return countOutOfScope;
    }

    public static int getCountWorking() {
        return countWorking;
    }

    public static int getCountNullTag() {
        return countNullTag;
    }

}
