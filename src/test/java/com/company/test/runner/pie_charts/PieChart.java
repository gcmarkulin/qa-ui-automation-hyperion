package com.company.test.runner.pie_charts;

import javax.swing.JPanel;
import com.company.framework.base.BaseUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.ChartUtilities;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class PieChart extends ApplicationFrame {
    private static String date = BaseUtil.getDate();

    public PieChart( String title, int working, int broken, int notimplementedyet, int outofscope, int withouttag ) throws IOException {
        super( title );
        setContentPane(createDemoPanel( title, working, broken, notimplementedyet, outofscope, withouttag));
    }

    private static PieDataset createDataset(int working, int broken, int notimplementedyet, int outofscope, int withouttag) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue( "Working" , working);
        dataset.setValue( "Broken" , broken );
        dataset.setValue( "NotImplementedYet" , notimplementedyet );
        dataset.setValue( "OutOfScope" , outofscope );
        dataset.setValue( "Without Tag" , withouttag );

        return dataset;
    }

    private static JFreeChart createChart( String title, PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart3D(
                title,       // chart title
                dataset,     // data
               true,  // include legend
               true,
               false);

        /* Get PiePlot object */
        PiePlot3D plot = (PiePlot3D) chart.getPlot();

        /* Set colors to sections */
        plot.setSectionPaint("Working", new Color(120, 230, 110));
        plot.setSectionPaint("Broken", new Color(243,218,011));
        plot.setSectionPaint("NotImplementedYet", new Color(250,210,001));
        plot.setSectionPaint("OutOfScope", new Color(248,243, 053));
        plot.setSectionPaint("Without Tag", new Color(197, 29,052));

        // Create the custom label generator
        final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}");
        plot.setLabelGenerator(labelGenerator);

        return chart;
    }

    public static JPanel createDemoPanel(String title, int working, int broken, int notimplementedyet, int outofscope, int withouttag) throws IOException {
        JFreeChart chart = createChart(title, createDataset(working, broken, notimplementedyet, outofscope, withouttag ) );
        int width = 560;    /* Width of the image */
        int height = 370;   /* Height of the image */
        File pieChart = new File( BaseUtil.getCharts() + "pie_Chart_" + date + ".jpg" );
        ChartUtilities.saveChartAsJPEG( pieChart, chart, width, height);
        return new ChartPanel( chart );
    }

}

