package pl.edu.pw.fizyka.pojava.MigA;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
	private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));


	public Chart(XYSeries vel,XYSeries acc,XYSeries pos,DropCharge dC){
		
	//VEL
		 // Add the series to your data set
		 XYSeriesCollection datasetVel = new XYSeriesCollection();
		 datasetVel.addSeries(vel);
		 // Generate the graph
		 JFreeChart chartVel = ChartFactory.createXYLineChart(
		 "VelocityChart", // Title
		 "time [s]", // x-axis Label
		 "velocity [m/s]", // y-axis Label
		 datasetVel, // Dataset
		 PlotOrientation.VERTICAL, // Plot Orientation
		 true, // Show Legend
		 true, // Use tooltips
		 false // Configure chart to generate URLs?
		 );
		 
		 this.setLayout(new java.awt.GridLayout(2,2));
  
		 ChartPanel velCP = new ChartPanel(chartVel);
		 velCP.setPopupMenu(null);
		 this.add(velCP);//,BorderLayout.EAST
		 
	//ACC
		 
		 XYSeriesCollection datasetAcc = new XYSeriesCollection();
		 datasetAcc.addSeries(acc);
		 // Generate the graph
		 JFreeChart chartAcc = ChartFactory.createXYLineChart(
		 "AccelerationChart", // Title
		 "time [s]", // x-axis Label
		 "acceleration [m/s^2]", // y-axis Label
		 datasetAcc, // Dataset
		 PlotOrientation.VERTICAL, // Plot Orientation
		 true, // Show Legend
		 true, // Use tooltips
		 false // Configure chart to generate URLs?
		 );
		 
		 ChartPanel accCP = new ChartPanel(chartAcc);
		 accCP.setPopupMenu(null);
		 this.add(accCP);
		 
		 this.validate();
		 
	//POS
		 
		 XYSeriesCollection datasetPos = new XYSeriesCollection();
		 datasetPos.addSeries(pos);
		 // Generate the graph
		 JFreeChart chartPos = ChartFactory.createXYLineChart(
		 "PositionChart", // Title
		 "time [s]", // x-axis Label
		 "position [m]", // y-axis Label
		 datasetPos, // Dataset
		 PlotOrientation.VERTICAL, // Plot Orientation
		 true, // Show Legend
		 true, // Use tooltips
		 false // Configure chart to generate URLs?
		 );

		 ChartPanel posCP = new ChartPanel(chartPos);
		 posCP.setPopupMenu(null);
		 this.add(posCP);
		 
		 this.validate();
	//Result Panel
		 Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		 TitledBorder tBorder = BorderFactory.createTitledBorder(lowerEtched, "Results");
		 
		 JPanel outPanel=new JPanel();
		 outPanel.setBorder(tBorder);
		 outPanel.setLayout(new java.awt.GridLayout(3,2));
	//calculated value of e. 
		 outPanel.add(new JLabel("Calculated Charge [e]: "));
		 JTextField calCharge=new JTextField(Double.toString(dC.ratio()));
		 calCharge.setEnabled(false);
		 calCharge.setDisabledTextColor(Color.BLACK);
		 outPanel.add(calCharge);
	//actual speed
		 outPanel.add(new JLabel("Instant speed [m/s]: "));
		 
		 JTextField nowSpeed=new JTextField("0.0");
		 nowSpeed.setEnabled(false);
		 nowSpeed.setDisabledTextColor(Color.BLACK);
		 outPanel.add(nowSpeed);
		
	//calcbutton 
		 JButton calcButton = new JButton("Calculate");
		 outPanel.add(calcButton);
			calcButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	calCharge.setText(Double.toString(dC.ratio()));
			    	nowSpeed.setText(vel.getItemCount()!=0?
			    			Double.toString((double)vel.getY(vel.getItemCount()-1)):"0.0");
			    }
			});
		 

	//Save

			JButton saveButton = new JButton("Save results");
			 outPanel.add(saveButton);
				saveButton.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	dC.saving=true;
				    	if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
					    	//saving charts&charge
				    		String path=dialog.getSelectedFile().getAbsolutePath();
				    		File fRes = new File(path);
				    		
				    		//data folder creation
				    		path=dialog.getSelectedFile().getParent();
				    		File dir = new File(path+"/Data");
				    		dir.mkdir();
				    		path+="/Data/";
					    	File fVel = new File(path+"VelocityChart.jpg");
					    	File fAcc = new File(path+"AccelerationChart.jpg");
					    	File fPos = new File(path+"PositionChart.jpg");
					    	
					    	try {
					    	//jpg
								ChartUtilities.saveChartAsJPEG(fVel, chartVel, 480, 300);
								ChartUtilities.saveChartAsJPEG(fAcc, chartAcc, 480, 300);
								ChartUtilities.saveChartAsJPEG(fPos, chartPos, 480, 300);
							//txt	
								Writer writer = new OutputStreamWriter( new FileOutputStream(fRes), "UTF-8");
								BufferedWriter fout = new BufferedWriter(writer);
								//charge
								fout.write("Calculated charge [e]: ");
								//calculate feature
								calcButton.doClick();
								calCharge.write(fout);
								fout.newLine();
								//data
								fout.write("Time [s]"+"\t"
										+ "Velocity [m/s]"+"\t"
										+ "Acceleration[m/s^2]"+"\t"
										+ "Position [m]");
								fout.newLine();
								for(int i=0;i<vel.getItemCount();++i){
									fout.write(vel.getX(i)+"\t"
												+vel.getY(i)+"\t"
												+acc.getY(i)+"\t"
												+pos.getY(i));
									fout.newLine();
								}
								
								fout.close();							
							} 
					    	catch (IOException e1) {
					    		e1.printStackTrace();
					    		dC.saving=false;
					    		}
				    	}
				    	dC.saving=false;
				    }
				});
			/*Save button all info x vx ay plus Calculated charge*/
		 this.add(outPanel);
		
	}

}
