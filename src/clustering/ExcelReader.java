package clustering;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;

public class ExcelReader {

	public ArrayList<KmeansData> dataList;
	private ArrayList<String> dataset;	
	private String[] re;
	public ExcelReader() {
		
	}
	
	public void getdata (String path) {
		try {
			//FileInputStream file = new FileInputStream(new File(path));
			
			CsvReader reader = new CsvReader(path);
			reader.readHeaders();
			
			while(reader.readRecord()) {
			
			}
		
		/*	for(int row = 0; row<dataset.size();row ++) {
				String cell = dataset.get(row)[0];
				 System.out.println(cell);
				
			}*/
			reader.close();
		} catch (Exception x){
			x.printStackTrace();
			System.out.println(x.getMessage());
		}
	}
}
