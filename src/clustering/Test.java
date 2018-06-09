package clustering;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import com.csvreader.CsvWriter;

import view.Window;

public class Test {

	static char separator = ',';
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int num;
		Window win = new Window ();
		win.frame.setVisible(true);
		 System.out.println("Entrer le nombre de cluster：");              
	        num=(new Scanner(System.in)).nextInt();  
	        KmeansClustering k=new KmeansClustering(num); 
	        ArrayList<KmeansData> dataSet = win.dataset;
	        k.setDataSet(dataSet);    
	        
	        k.kmeans();  
	        
	        ArrayList<ArrayList<KmeansData>> cluster=k.getCluster();    
	        
	        //résultat
	        List<String[]> dataList = new ArrayList<String[]>();
	        List<String[]> tabnDataList[] = new ArrayList[num];
	        dataList.add(new String[] {"id","date","TotalCount","nCluster"});
	        for(int i=0;i<cluster.size();i++)    
	        {    
	        	//dataList.add(new String[] {"cluster",String.valueOf(i)});
	        	tabnDataList[i] = k.printDataArray(cluster.get(i), i);
	        	for(int j=0;j<tabnDataList[i].size();j++){
	        		dataList.add(tabnDataList[i].get(j));
	        	}
	            //k.printDataArray(cluster.get(i), "cluster["+i+"]");    
	        }  
	        String filePath="/Users/jijie/eclipse-workspace/Kmeans/test.csv";
	        exportCsv(dataList, filePath); 
	}
	 
    /** 
     * java导出cvs文件 
     *  
     * @param dataList 
     *            数据集 
     * @param filePath 
     *            导出路径 
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
            System.out.println("生成CSV出错..." + e);  
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
