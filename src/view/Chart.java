package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart extends JFrame{
	
	private String title;
	private String xLabel;
	private String yLabel;
	private int countCluster;
	
	public Chart(String title,String xLabel, String yLabel, List<String[]> dataResult, int nCluster) {
		this.countCluster= nCluster;
	
		//intergrer data
		XYDataset dataset = createDataset(dataResult);
		
		//create chart
		JFreeChart chart = ChartFactory.createScatterPlot(title, xLabel, yLabel, dataset);
		//Changes background color
	    XYPlot plot = (XYPlot)chart.getPlot();
	    plot.setBackgroundPaint(new Color(255,228,196));
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}
  
	private XYDataset createDataset(List<String[]> dataResult) {
		// TODO Auto-generated method stub
		XYSeriesCollection dataset = new XYSeriesCollection();
		ArrayList<XYSeries> series = new ArrayList<XYSeries>();
		for(int i=1; i<=this.countCluster; i++) {
			XYSeries serie = new XYSeries("Cluster"+i);
			series.add(serie);
		}
		int clusterNumber = countCluster;
		System.out.print(String.valueOf(clusterNumber));
		for(int l=0;l<dataResult.size();l++) {
			if(String.valueOf(dataResult.get(l)[3]) == String.valueOf(clusterNumber) ) {
				series.get(clusterNumber).add(Double.valueOf(dataResult.get(l)[1]),Double.valueOf(dataResult.get(l)[2]));
			} else if(String.valueOf(dataResult.get(l)[3]) == String.valueOf(clusterNumber-1)) {
				series.get(clusterNumber-1).add(Double.valueOf(dataResult.get(l)[1]),Double.valueOf(dataResult.get(l)[2]));
			}
		}
		for(int n=0; n<this.countCluster; n++) {
			
		dataset.addSeries(series.get(n));
		}
		
		return dataset;
	}

	
}
