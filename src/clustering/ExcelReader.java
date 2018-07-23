package clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

public class ExcelReader {

	private ArrayList<KmeansData> dataList;
	private ArrayList<float[]> dataset;	
	
	private String[] re;
	public ExcelReader() {
		this.dataset = dataset;
		this.dataList = dataList;
	}
	
	public ArrayList<KmeansData> getdata (String path) {
		ArrayList<KmeansData> dataList = new ArrayList<KmeansData>();
		List dataset = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
		
			reader.readLine();
			String line = null;
			while((line=reader.readLine())!=null){  
	            String str[] = line.split(",");  
	           KmeansData point1 = new KmeansData(0, 0, 0,0); 
	        
	            point1.setId(Float.parseFloat(str[0].trim()));  
	            point1.setDate(Float.parseFloat(str[1].trim()));
	            point1.setSum(Float.parseFloat(str[2].trim())); 
	            dataList.add(point1);
	           
	            
	        };
	       
	        
	        reader.close();
	    }catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }catch (IOException e) {  
	        e.printStackTrace();  
	    }
		return dataList;
	}
}
