package clustering;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.csvreader.CsvWriter;

import view.Chart;
import view.Window;


public class Test {

	static char separator = ',';
	private static int num;
	 public static int flag = 0;  
	 
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
	
		Window win = new Window ();
		win.frame.setVisible(true);
JLabel label =new JLabel();
win.frame.add(label);
		win.getButton().addActionListener(new ActionListener()
	   {
			 public void actionPerformed(ActionEvent e) {
				  
				 String s = win.getIn().getText();    //文本框得到文本
			
		          int d = Integer.parseInt(s);
		          num =d;
		          
		          label.setText("Nombre de K: "+ num );
		          
			 }
	   });
		
		
	   win.getBtn().addActionListener( new ActionListener()
	   {
		   public void actionPerformed(ActionEvent e)
		   {
			  
			  
	          //  System.out.println("Entrer le nombre de cluster：");              
	 	      // num=(new Scanner(System.in)).nextInt();  
	 	       KmeansClustering k=new KmeansClustering(num);
	 	        ArrayList<KmeansData> dataSet = win.dataset;
	 	        k.setDataSet(dataSet);    
	 	        
	 	        k.kmeans();  
	 	        
	 	        ArrayList<ArrayList<KmeansData>> cluster=k.getCluster();    
	 	        ArrayList<ArrayList<Float>> myhopelist =k.getHopelist();
	 		
	 	     //résultat
	 	         List<String[]> dataList = new ArrayList<String[]>();
	 	         List<String[]> tabnDataList[] = new ArrayList[num];
	 	         dataList.add(new String[] {"id","date","TotalCount","nCluster"});
	 	         
	 	         int[] calculateNumber = new int[num];
	 	         for(int i=0;i<num;i++){
	 	          calculateNumber[i]=0;
	 	         }
	 	         
	 	         for(int i=0;i<cluster.size();i++)    
	 	         {    
	 	          //dataList.add(new String[] {"cluster",String.valueOf(i)});
	 	          int number_cluster=0;
	 	          for(int t=0;t<num;t++){
	 	           if(myhopelist.get(t).get(1)==(float)i){
	 	            number_cluster=t;
	 	           }
	 	          }
	 	          tabnDataList[i] = k.printDataArray(cluster.get(i), number_cluster);
	 	          for(int j=0;j<tabnDataList[i].size();j++){
	 	           dataList.add(tabnDataList[i].get(j));
	 	           calculateNumber[number_cluster]++;
	 	          }
	 	             //k.printDataArray(cluster.get(i), "cluster["+i+"]");    
	 	          //System.out.println("i:"+i+"   numbercluster:"+number_cluster);
	 	          
	 	         }  
	 	         
	 	         for(int i=0;i<num;i++){
	 	          System.out.println("cluster "+ (i+1) + "  nb points: " + calculateNumber[i]);
	 	         }
	 	        String filePath="/Users/jijie/eclipse-workspace/Kmeans/demo.csv";
	 	        try {
					exportCsv(dataList, filePath);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	 	       //visualisation 
	 	        SwingUtilities.invokeLater(() -> {
	 	            Chart example = new Chart("Visualisation","date", "TotalCount",dataList, num);
	 	            example.setSize(800, 700);
	 	            example.setLocationRelativeTo(null);
	 	            example.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	 	            example.setVisible(true);
	 	          });
			   }   
		   
		 });  
	      
	  
	    

	
		
		
	}
		
	
	 
    /** 
     * export csv
     *  
     * @param dataList 
     *             
     * @param filePath 
     *             
     * @return 
     * @throws Exception 
     */  
    public static boolean exportCsv(List<String[]> dataList, String filePath) throws Exception {  
        boolean isSuccess = false;  
        CsvWriter writer = null;  
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream(filePath, true);  
            writer = new CsvWriter(out, separator, Charset.forName("GBK"));  
            for (String[] strs : dataList) {  
                writer.writeRecord(strs);  
            }  
  
            isSuccess = true;  
        } catch (Exception e) {  
            System.out.println("export error" + e);  
            throw e;  
        } finally {  
            if (null != writer) {  
                writer.close();  
            }  
            if (null != out) {  
                try {  
                    out.close();  
                } catch (IOException e) {  
                    System.out.println("exportCsv close Exception: " + e);  
                    throw e;  
                }  
            }  
        }  
  
  
        return isSuccess;  
    }  
 
}
