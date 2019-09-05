package com.company.framework.base;

import com.company.framework.constants.*;
import net.jodah.failsafe.RetryPolicy;
import org.joda.time.DateTime;
import org.openqa.selenium.*;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Web Driver Initializer
 */
public class BaseUtil {

    private static String facebook_url;
    private static String logPath;
    private static String date;
    private static String linuxChromePath;
    private static String linuxFirePath;
    private static String macChromePath;
    private static String macFirePath;
    private static String winChromePath;
    private static String winIePath;
    private static String win32firePath;
    private static String win64firePath;
    private static RetryPolicy retryPolicy;
    private static String webdriverConfigPath;
    private static String gridServerUrl;
    private static String hyperion_url;
    private static String userHyperion;
    private static String failedTests;
    private static String charts;

    static {
        try (FileInputStream input = new FileInputStream("properties/.properties")) {
            Properties props = new Properties();
            retryPolicy = new RetryPolicy().retryOn(NoSuchSessionException.class).withDelay(1,TimeUnit.SECONDS).withMaxRetries(3);

            props.load(input);
            date = getDate();

            hyperion_url = props.getProperty(Constants.PROPS_HYPERION_URL);
            linuxChromePath = props.getProperty(Constants.PROPS_LINUX_CHROME_PATH);
            linuxFirePath = props.getProperty(Constants.PROPS_LINUX_FIREFOX_PATH);
            macChromePath = props.getProperty(Constants.PROPS_MAC_CHROME_PATH);
            macFirePath = props.getProperty(Constants.PROPS_MAC_FIREFOX_PATH);
            winChromePath = props.getProperty(Constants.PROPS_WIN_CHROME_PATH);
            winIePath = props.getProperty(Constants.PROPS_WIN_IE_PATH);
            win32firePath = props.getProperty(Constants.PROPS_WIN_FIREFOX32_PATH);
            win64firePath = props.getProperty(Constants.PROPS_WIN_FIREFOX64_PATH);
            webdriverConfigPath = props.getProperty(Constants.PROPS_WEBDRIVER_CONFIG);
            gridServerUrl = props.getProperty(Constants.PROPS_GRID_SERVER);
            userHyperion = props.getProperty(Constants.PROPS_USER_HYPERION);
            failedTests = props.getProperty(Constants.PROPS_FAILED_TESTS);
            charts = props.getProperty((Constants.PROPS_CHARTS_CONFIG));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLogPath(){ return logPath; }

    public static String getLinuxChromePath() {
        return linuxChromePath;
    }

    public static String getLinuxFirePath() {
        return linuxFirePath;
    }

    public static String getMacChromePath() {
        return macChromePath;
    }

    public static String getMacFirePath() {
        return macFirePath;
    }

    public static String getWinChromePath() {
        return winChromePath;
    }

    public static String getWinIePath() {
        return winIePath;
    }

    public static String getWin32firePath() {
        return win32firePath;
    }

    public static String getWin64firePath() { return win32firePath; }

    public static RetryPolicy getRetryPolicy(){
        return retryPolicy;
    }

    public static String getWebdriverConfigPath() {
        return webdriverConfigPath;
    }

    public static String getGridServerUrl() {
        return gridServerUrl;
    }

    public static String getDate(){
        String date="";

        DateTime dateTime = new DateTime();
        date = date + dateTime.year().getAsString()+"_";
        date = date + dateTime.monthOfYear().getAsString()+"_";
        date = date + dateTime.dayOfMonth().getAsString()+"_";
        date = date + dateTime.hourOfDay().getAsString()+"_";
        date = date + dateTime.minuteOfHour().getAsString()+"_";
        date = date + dateTime.secondOfMinute().getAsString()+"_";
        date = date + dateTime.millisOfSecond().getAsString();

        return date;
    }

    public static String getHyperionUrl() {
        return hyperion_url;
    }

    public static String getUserHyperion() {
        return userHyperion;
    }

    public static String getFailedTests() {
        return failedTests;
    }

    public static String getCharts() {
        return charts;
    }

}