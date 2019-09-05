package com.company.test.runner.pie_charts;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import com.company.framework.base.BaseUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;


public class Donut {
    private static String date = BaseUtil.getDate();

    public JFreeChart createChart(String strTitle, long countSuccess, long countFailed) throws IOException {
        // Set up the data set for the donut/ring chart
        DefaultPieDataset rDataSet = new DefaultPieDataset();
        rDataSet.setValue("Success", countSuccess);
        rDataSet.setValue("Failed", countFailed);

        // Initialize values
        boolean bShowLegend = true;

        // Create ring plot
        CustomDonutPlot rPlot = new CustomDonutPlot(rDataSet);
        rPlot.setLabelGenerator(new StandardPieSectionLabelGenerator(Locale.ENGLISH));
        rPlot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        rPlot.setSectionDepth(0.50);
        JFreeChart chart = new JFreeChart(strTitle, JFreeChart.DEFAULT_TITLE_FONT, rPlot, bShowLegend);
        ChartFactory.getChartTheme().apply(chart);

        // Create the chart
        rPlot.setBackgroundPaint(Color.WHITE);
        rPlot.setLabelGenerator(null);
        rPlot.setOutlineVisible(false);
        rPlot.setSeparatorsVisible(false);
        rPlot.setShadowPaint(null);
        rPlot.setSectionOutlinesVisible(false);
        rPlot.setOuterSeparatorExtension(0);
        rPlot.setInnerSeparatorExtension(0);

        // Set colors of the chart
        rPlot.setSectionPaint("Success", new Color(120, 230, 110));
        rPlot.setSectionPaint("Failed", new Color(197, 29,052));

        rPlot.setExplodePercent("Success", 0.05);

        // Create the custom label generator
        final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}");
        rPlot.setLabelGenerator(labelGenerator);

        int width = 500;    /* Width of the image */
        int height = 400;   /* Height of the image */

        File pieChart = new File( BaseUtil.getCharts() + "donut_" + date + ".jpg" );
        ChartUtilities.saveChartAsJPEG( pieChart, chart, width, height);
        return chart;
    }

    public static class CustomDonutPlot extends RingPlot {

        private static final long serialVersionUID = 1L;

        public CustomDonutPlot(DefaultPieDataset dataSet) {
            super(dataSet);
        }
    }

}
