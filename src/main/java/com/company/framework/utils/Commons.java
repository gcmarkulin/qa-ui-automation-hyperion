package com.company.framework.utils;

import com.company.framework.base.BaseUtil;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static com.company.framework.base.Framework.setup_desktop_browser;


public class Commons {
    private static String OS = System.getProperty("os.name");
    private static String folderOS;
    private static String date = BaseUtil.getDate();
    private static WebDriver driver;

    public static WebDriver setup(Context context, String configName) throws Exception {
        context.setValue("gridServerUrl", BaseUtil.getGridServerUrl());
        driver = setup_desktop_browser(context, configName);
        context.setValue("driver", driver);
        driver = (WebDriver) context.getValue("driver");
        return driver;
    }

    public static String[] getUserApplication() {
        String[] parts = null;
        parts = BaseUtil.getUserHyperion().split("/");
        return parts;
    }

    public static void makeScreenshot(String nametest) {
        try {
            Thread.sleep(2000);
            TakesScreenshot ts=(TakesScreenshot) driver;

            File source=ts.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(source, new File(System.getProperty("user.dir") + getFolderOS() +
                    nametest + "_" + date + ".jpg"));

            //Allure
            InputStream targetStream = new FileInputStream(source);
            Allure.addAttachment(nametest + "_" + date, "image/png", targetStream, "png");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFolderOS() {
        if (OS.toLowerCase().contains("windows")){
            folderOS = "\\screenshots\\";
        }
        else if(OS.toLowerCase().contains("mac")){
            folderOS = "/screenshots/";
        }
        return folderOS;
    }


}
