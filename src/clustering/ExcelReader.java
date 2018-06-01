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
	private ArrayList<String> dataset;	
	private String[] re;
	public ExcelReader() {
		this.dataset = dataset;
		this.dataList = dataList;
	}
	
	public void getdata (String path) {
		ArrayList<KmeansData> dataList = new ArrayList<KmeansData>();
		List dataset = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			//FileInputStream file = new FileInputStream(new File(path));
			reader.readLine();
			String line = null;
			while((line=reader.readLine())!=null){  
	            String str[] = line.split(",");  
	           KmeansData point1 = new KmeansData(0, 0, 0); 
	          //System.out.println(str[0].trim());
	            point1.setId(Double.parseDouble(str[0].trim()));  
	            point1.setDate(Double.parseDouble(str[1].trim()));
	            point1.setSum(Double.parseDouble(str[2].trim())); 
	            dataList.add(point1);
	            //dataset.add(point1.toString());
	        };
	        System.out.println(dataList.get(0).getId());
	        reader.close();
	    }catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }catch (IOException e) {  
	        e.printStackTrace();  
	    }
		/*CsvReader reader = new CsvReader(path);
			reader.readHeaders();
			
			while(reader.readRecord()) {
			
			}*/
		
		/*	for(int row = 0; row<dataset.size();row ++) {
				String cell = dataset.get(row)[0];
				 System.out.println(cell);
				
			}
			reader.close();
		} catch (Exception x){
			x.printStackTrace();
			System.out.println(x.getMessage());
		}*/
	}
}
