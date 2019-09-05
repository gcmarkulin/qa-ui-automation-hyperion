package com.company.framework.utils;


import com.company.framework.base.BaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Writer {

    public static BufferedWriter writer;
    private static String date = BaseUtil.getDate();
    private static File file;
    private static final Logger LOGGER = LoggerFactory.getLogger(Writer.class);

    public static BufferedWriter CreateWriter() {
        try {
            file = new File(BaseUtil.getFailedTests() + "FailedTest_"+ date + ".txt");
            file.getParentFile().mkdirs();
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return writer;
    }

    public static BufferedWriter getWriter() {
        return writer;
    }

    public static void CloseWriter() {

        try {
            writer.close();
        } catch (IOException e) {
            LOGGER.error("Close failed for FailedTest file!");
            e.printStackTrace();
        }

        if (file.length() == 0) {
            file.delete();
        }
    }
}
