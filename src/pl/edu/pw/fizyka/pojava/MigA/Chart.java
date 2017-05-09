package pl.edu.pw.fizyka.pojava.MigA;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import java.lang.*;
import org.jfree.chart.*;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;

public class Chart extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Chart() {
		// Create a simple XY chart
		 XYSeries series = new XYSeries("pomiar");
		 series.add(1, 0.5);
		 series.add(2, 2);
		 series.add(3, 4);
		 series.add(4, 9);
		 series.add(5, 10);
		 series.add(5.05, 10);
		 series.add(10, 0);
		 // Add the series to your data set
		 XYSeriesCollection dataset = new XYSeriesCollection();
		 dataset.addSeries(series);
		 // Generate the graph
		 JFreeChart chart = ChartFactory.createXYLineChart(
		 "VelocityChart", // Title
		 "time", // x-axis Label
		 "velocity", // y-axis Label
		 dataset, // Dataset
		 PlotOrientation.VERTICAL, // Plot Orientation
		 true, // Show Legend
		 true, // Use tooltips
		 false // Configure chart to generate URLs?
		 );

		 //JPanel jPanel1 = new JPanel();
		 this.setLayout(new java.awt.BorderLayout());
		  
		 ChartPanel CP = new ChartPanel(chart);
		 //this.setVisible(true);
		 //initComponents();
		 this.add(CP);
		 this.validate();
		 
	}

}
