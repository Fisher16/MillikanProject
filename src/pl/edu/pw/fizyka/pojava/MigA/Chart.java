package pl.edu.pw.fizyka.pojava.MigA;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.*;

import org.jfree.chart.*;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartFactory;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



/**
 * Panel for charts and data handling. 
 * 
 * @author AR MK
 *
 */

public class Chart extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Chart(XYSeries vel,XYSeries acc,XYSeries pos,DropCharge dC){
		
	//VEL
		 // Add the series to your data set
		 XYSeriesCollection datasetVel = new XYSeriesCollection();
		 datasetVel.addSeries(vel);
		 // Generate the graph
		 JFreeChart chartVel = ChartFactory.createXYLineChart(
		 "VelocityChart", // Title
		 "time", // x-axis Label
		 "velocity", // y-axis Label
		 datasetVel, // Dataset
		 PlotOrientation.VERTICAL, // Plot Orientation
		 true, // Show Legend
		 true, // Use tooltips
		 false // Configure chart to generate URLs?
		 );

		 //JPanel jPanel1 = new JPanel();
		 this.setLayout(new java.awt.GridLayout(2,2));
		  
		 ChartPanel velCP = new ChartPanel(chartVel);
		 //this.setVisible(true);
		 //initComponents();
		 velCP.setPopupMenu(null);
		 this.add(velCP);//,BorderLayout.EAST
		 
	//ACC
		 
		 XYSeriesCollection datasetAcc = new XYSeriesCollection();
		 datasetAcc.addSeries(acc);
		 // Generate the graph
		 JFreeChart chartAcc = ChartFactory.createXYLineChart(
		 "AccelerationChart", // Title
		 "time", // x-axis Label
		 "acceleration", // y-axis Label
		 datasetAcc, // Dataset
		 PlotOrientation.VERTICAL, // Plot Orientation
		 true, // Show Legend
		 true, // Use tooltips
		 false // Configure chart to generate URLs?
		 );

		 //JPanel jPanel1 = new JPanel();
		 
		 ChartPanel accCP = new ChartPanel(chartAcc);
		 //this.setVisible(true);
		 //initComponents();
		 accCP.setPopupMenu(null);
		 this.add(accCP);//,BorderLayout.WEST
		 
		 this.validate();
		 
	//POS
		 
		 XYSeriesCollection datasetPos = new XYSeriesCollection();
		 datasetPos.addSeries(pos);
		 // Generate the graph
		 JFreeChart chartPos = ChartFactory.createXYLineChart(
		 "PositionChart", // Title
		 "time", // x-axis Label
		 "position", // y-axis Label
		 datasetPos, // Dataset
		 PlotOrientation.VERTICAL, // Plot Orientation
		 true, // Show Legend
		 true, // Use tooltips
		 false // Configure chart to generate URLs?
		 );

		 //JPanel jPanel1 = new JPanel();
		 
		 ChartPanel posCP = new ChartPanel(chartPos);
		 //this.setVisible(true);
		 //initComponents();
		 posCP.setPopupMenu(null);
		 this.add(posCP);//,BorderLayout.CENTER
		 
		 this.validate();
	//Text
		 JPanel outPanel=new JPanel();
		 outPanel.setLayout(new java.awt.GridLayout(2,2));
		 outPanel.add(new JLabel("Calculated Charge [e]: "));
		 JTextField calCharge=new JTextField(Double.toString(dC.ratio()));
		 outPanel.add(calCharge);
		
		 
		 JButton calcButton = new JButton("Calculate");
		 outPanel.add(calcButton);
			calcButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	calCharge.setText(Double.toString(dC.ratio()));
			    }
			});
		 

	//Save
			JButton saveButton = new JButton("Save");
			 outPanel.add(saveButton);
				saveButton.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	//saving charts&charge
				    	File fvel = new File("VelocityChart.jpg");
				    	File facc = new File("AccelerationChart.jpg");
				    	File fpos = new File("PositionChart.jpg");
				    	File fres = new File("results.txt");
				    	try {
							ChartUtilities.saveChartAsJPEG(fvel, chartVel, 480, 300);
							ChartUtilities.saveChartAsJPEG(facc, chartAcc, 480, 300);
							ChartUtilities.saveChartAsJPEG(fpos, chartPos, 480, 300);
							Writer writer = new OutputStreamWriter( new FileOutputStream(fres), "UTF-8");
							BufferedWriter fout = new BufferedWriter(writer);
							calCharge.write(fout);
							fout.close();
							
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
				    }
				});
			/*Save button all info x vx ay plus Calculated charge*/
		 this.add(outPanel);
		
	}

}
