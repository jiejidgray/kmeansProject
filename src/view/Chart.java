package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.labels.CustomXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;

public class Chart extends JFrame{
	
	private String title;
	private String xLabel;
	private String yLabel;
	private int countCluster;
	private ChartPanel chartPanel;
	
	@SuppressWarnings("deprecation")
	public Chart(String title,String xLabel, String yLabel, List<String[]> dataResult, int nCluster) {
		this.countCluster= nCluster;
	
//intergrer data
		XYDataset dataset = createDataset(dataResult);
		
//create chart
		JFreeChart chart = ChartFactory.createScatterPlot(title, xLabel, yLabel, dataset, PlotOrientation.VERTICAL, true, true, false);
		
//Changes background color
		
	    XYPlot plot = (XYPlot)chart.getPlot();
	    
		
	    plot.setBackgroundPaint(new Color(255,228,196));
		
		plot.setNoDataMessage("No data");
		
		   XYLineAndShapeRenderer r = (XYLineAndShapeRenderer) plot.getRenderer();
		   XYItemRenderer renderer = plot.getRenderer();

	        r.setBaseShapesVisible(true);
	        r.setSeriesShape(0, ShapeUtilities.createTranslatedShape(
	            new Rectangle(12, 12), -6, -6));
	      

	      
	        this.chartPanel = new ChartPanel(chart) {

	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(640, 480);
	            }
	        };
	        
	 //MouseListner
	        setInfo(dataResult);
	        setContentPane(chartPanel);
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
		//List<float[]> listId = new List<float[]>();

		for(int l=1;l<dataResult.size();l++) {

			   for(int k=0;k<clusterNumber;k++){    
			    if(Float.valueOf(dataResult.get(l)[3])==(float)(k+1)){  
			    	
			     series.get(k).add(Float.valueOf(dataResult.get(l)[1]),Float.valueOf(dataResult.get(l)[2]));
			    //XYDataItem item = new XYDataItem(Float.valueOf(dataResult.get(l)[1]),Float.valueOf(dataResult.get(l)[2]));
			   
			      
			    //series.get(k).add();
			    }
			   }

			  }
		for(int n=0; n<this.countCluster; n++) {
			
		dataset.addSeries(series.get(n));
		}
		
		return dataset;
	}
	
	private void setInfo(List<String[]> dataResult) {
		
        chartPanel.addChartMouseListener(new ChartMouseListener() {

            @Override
            public void chartMouseClicked(ChartMouseEvent cme) {
                report(cme);
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {
                //report(cme);
            }

            private void report(ChartMouseEvent cme) {
                ChartEntity ce = cme.getEntity();
                if (ce instanceof XYItemEntity) {
                    XYItemEntity e = (XYItemEntity) ce;
                    XYDataset d = e.getDataset();
                    int s = e.getSeriesIndex();
                    int i = e.getItem();
                    System.out.println("X:" + d.getX(s, i) + ", Y:" + d.getY(s, i));
                }
            }
        });
	}

	
}
