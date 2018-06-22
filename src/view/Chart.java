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
	      /*  r.setBaseToolTipGenerator(new XYToolTipGenerator() {
	        	 @Override
	        	    protected String generateLabelString( List<String[]> dataset, int row, int col) {
	        	        return dataset.getColumnKey(col) + " " + dataset.getValue(row, col);
	        	    }
	        });
	        System.out.println(generator1);
	        CustomXYToolTipGenerator generator2 = new CustomXYToolTipGenerator();
	        generator2.addToolTipSeries(ToolTips2);
	        
	        plot.setRenderer(r);
	      /*  Vector<String> ToolTips1 = new Vector<String>();
	        for (int j = 0; j < 10; j++)
	        {
	          ToolTips1.add("First: "+j);
	        }

	        Vector<String> ToolTips2 = new Vector<String>();
	        for (int j = 0; j < 10; j++)
	        {
	          ToolTips2.add("12344");
	        } 
	        
	        

	        r.setSeriesToolTipGenerator(0, generator1);
	        r.setSeriesToolTipGenerator(1, generator2);

	        r.setBaseShape(new Ellipse2D.Double(-3.0, -3.0, 8.0, 8.0));
	        CustomXYToolTipGenerator cttGen = new CustomXYToolTipGenerator();

	        ArrayList tips = new ArrayList();
	        tips.add("#1: fine, ok");
	        tips.add("#2: 5.0, 694.1");
	        tips.add("#3: 4.0, 100.0");
	       
	        cttGen.addToolTipSeries(tips);
	        renderer.setToolTipGenerator(cttGen);*/

	      
	        this.chartPanel = new ChartPanel(chart) {

	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(640, 480);
	            }
	        };
	        // zomm in zoom out
	      /*  chartPanel.addMouseWheelListener(new MouseWheelListener() {
	            public void mouseWheelMoved(MouseWheelEvent e) {
	             if (-3 == e.getUnitsToScroll()) {
	            	 chartPanel.zoomInBoth(10, 10);
	              System.out.println("向上");
	             } else if (3 == e.getUnitsToScroll()) {
	            	 chartPanel.zoomOutBoth(10, 10);
	              System.out.println("向下");
	             }
	            }
	           });*/
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
