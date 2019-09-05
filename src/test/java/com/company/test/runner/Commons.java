package com.company.test.runner;

import com.company.test.runner.pie_charts.Donut;
import com.company.test.runner.pie_charts.PieChart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.company.framework.utils.Writer.CloseWriter;


public class Commons {

    public static final String RED = "\033[0;31m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String BLUE_BACKGROUND = "\033[44m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String GREEN_BACKGROUND = "\033[42m";

    public static void showCounter(int countWorking, int countBroken, int countNotImplementedYet, int countOutOfScope,
                                   int countNullTag) throws IOException {

        int skippedTotal = countBroken + countNotImplementedYet + countOutOfScope + countNullTag;
        int total =  countBroken + countNotImplementedYet + countWorking + countOutOfScope + countNullTag;

        System.out.println("╔═════════════════════════════╗");
        System.out.println("║" + BLUE_BACKGROUND + BLACK_BOLD + "      COUNTER OF TAGS        " + RESET + "║");
        System.out.println("╠═════════════════════════════╣");
        System.out.print("║ " + GREEN + "Working : " + RESET + "                  ║ " + GREEN + countWorking + " - " +
                RESET);
        showBar(countWorking, GREEN, total);
        System.out.println("╟────────── SKIPPED ──────────╢");
        System.out.print("║ " + YELLOW + "Broken : " + RESET + "                   ║ " + YELLOW + countBroken + " - " +
                RESET);
        showBar(countBroken, YELLOW, total);
        System.out.print("║ " + YELLOW + "NotImplementedYet : " + RESET + "        ║ " +YELLOW + countNotImplementedYet
                + " - " + RESET);
        showBar(countNotImplementedYet, YELLOW, total);
        System.out.print("║ " + YELLOW + "OutOfScope : " + RESET + "               ║ " + YELLOW + countOutOfScope + " - "
                + RESET);
        showBar(countOutOfScope, YELLOW, total);

        System.out.println("║" + YELLOW + "-----------------------------" + RESET + "║");
        System.out.print("║ " + RED + "Without Tag : " + RESET + "              ║ " + RED + countNullTag + " - "
                + RESET);
        showBar(countNullTag, RED, total);

        System.out.println("╟─────────────────────────────╢");
        System.out.print("║ " + YELLOW_BOLD + "Skipped Total : " + RESET + "            ║ " + YELLOW_BOLD +
                Integer.valueOf(skippedTotal) + " - " + RESET);
        showBar(skippedTotal, YELLOW, total);
        System.out.println("╟─────────────────────────────╢");
        System.out.print("║ " + BLUE_BOLD + "TOTAL (Skipped + Working) :" + RESET +" ║ " + BLUE_BOLD +
                Integer.valueOf(total) + " - " + RESET);
        showBar(total, BLUE, total);
        System.out.println("╚═════════════════════════════╝");

        PieChart demo = new PieChart( "COUNTER OF TAGS", countWorking, countBroken, countNotImplementedYet, countOutOfScope, countNullTag );
    }

    public static void showBar(int count, String color, int total) {
        float percentage = 0;
        for(int i = 1; i<=count;i++) {
            System.out.print(color + "■ " + RESET);
        }
        if (total != 0) {
            percentage = count*100/total;
        }

        System.out.print(color + percentage + "%" + RESET);
        System.out.println();
    }

    public static void logo(String word) {
        int width = 200;
        int height = 30;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 20));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(word, 10, 20);

        System.out.println();
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "▀");
            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(BLUE+ sb);
        }
        System.out.println(RESET);
    }

    public static void getStatisticalData(int counterSuccess, int counterFailed) throws IOException {

        int total = counterSuccess + counterFailed;
        System.out.println();
        System.out.println(" Statistical Data");
        System.out.println("┌───────────────────────┐");
        System.out.println("│" + GREEN_BACKGROUND + BLACK_BOLD + " COUNTER OF EXECUTIONS " + RESET + "│");
        System.out.println("├───────────────────────┤");
        System.out.print("│ " + GREEN + "Succesfull Tests : " + RESET + "   │ " + GREEN + counterSuccess + " - " + RESET);
        showBar(counterSuccess, GREEN, total);
        System.out.print("│ " + RED + "Failed Tests : " + RESET + "       │ " + RED + counterFailed + " - " + RESET);
        showBar(counterFailed, RED, total);
        System.out.println("├───────────────────────┤");
        System.out.print("│ " + BLUE_BOLD + "TOTAL TESTS : " + RESET + "        │ " + BLUE_BOLD + Integer.valueOf(total)
                + " - " + RESET);
        showBar(total, BLUE_BOLD, total);
        System.out.println("└───────────────────────┘");
        System.out.println();

        CloseWriter();

        Donut donut = new Donut();
        donut.createChart("COUNTER OF EXECUTIONS",counterSuccess, counterFailed);

    }

}

