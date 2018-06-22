package view;

import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clustering.ExcelReader;
import clustering.KmeansData;

public class Window {
public JFrame frame;
private String text ="";
private boolean importFile;
private TextArea textarea;
private JTextField in = new JTextField(5);  
private JButton btn = new JButton("enter"); 
private JLabel out = new JLabel("Résultat"); 

public ArrayList<KmeansData> dataset = new ArrayList<KmeansData>();
public Window () {
	initialize();
	this.importFile = false;
}

private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 450, 536);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JMenuBar menuBar = new JMenuBar();
	frame.setJMenuBar(menuBar);
	
     

	JMenuItem menuOpen = new JMenuItem("Import Data");
	
//action import data
	menuOpen.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg) {
			String path = getFile();
			System.out.println(path);
			try {
				 operateFile(path);
				// JOptionPane.showMessageDialog(text,"Printing complete");
				 importFile = true;
				 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		public String getFile() {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			
		int retour = chooser.showOpenDialog(null);
			return chooser.getSelectedFile().getAbsolutePath();
		}
	});
	menuBar.add(menuOpen);
	
	
}

//operat data file
	private void operateFile(String path) throws InterruptedException {
		if (path.isEmpty()) {
			text += "Fichier pas bon";
			System.out.println(text);
		}else{
			ExcelReader data = new ExcelReader();
			
			dataset = data.getdata(path);	
		}
	}
	
	/*private void enterCluster() {
		
	        btn.addActionListener(new BtnActionAdapter());  //new一个事件监听对象，实现监听功能
	        setSize(400, 100);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //退出
	        setVisible(true);  //显示界面
	    }
	    class BtnActionAdapter implements ActionListener {      //添加一个事件监听对象，实现监听功能
	        public void actionPerformed(ActionEvent e) {    //动作事件
	            String s = in.getText();    //文本框得到文本
	            double d = Double.parseDouble(s);       //解析成实数
	            double sq = d * d;
	            out.setText( d + "的平方是" + sq);      //显示结果
	        }
	    }
	}*/
	 
}
