package view;

import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractButton;
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
private JButton btn = new JButton("Clustering"); 
private JButton button = new JButton("Enter"); 
private JLabel out = new JLabel("R��sultat"); 
private String input;
private int clusterNum;
private boolean ok = false;
private  Boolean continueThread = false;


public ArrayList<KmeansData> dataset = new ArrayList<KmeansData>();
public Window () {
	initialize();
	this.importFile = false;
}

private void initialize() {
	frame = new JFrame();
	frame.setBounds(200, 200, 300, 500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JMenuBar menuBar = new JMenuBar();
	frame.setJMenuBar(menuBar);
	frame.setLayout(new FlowLayout());

	JMenuItem menuOpen = new JMenuItem("Import Data");
	
//action import data
	menuOpen.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg) {
			String path = getFile();
			
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
	
	frame.add(in);
	
	frame.add(button);

	frame.add(btn);

	
}



	public boolean isImportFile() {
	return importFile;
}

public void setImportFile(boolean importFile) {
	this.importFile = importFile;
}

	public JTextField getIn() {
	return in;
}

public void setIn(JTextField in) {
	this.in = in;
}

public JButton getButton() {
	return button;
}

public void setButton(JButton button) {
	this.button = button;
}

	//operat data file
	private void operateFile(String path) throws InterruptedException {
		if (path.isEmpty()) {
			text += "Fichier pas bon";
			System.out.println(text);
		}else{
			ExcelReader data = new ExcelReader();
			
			dataset = data.getdata(path);
			this.importFile=true;
		/*	String str = JOptionPane.showInputDialog("Entrer:");
			int d = Integer.parseInt(str);
			clusterNum = d;
			continueThread = !continueThread;*/
		}
	}
	
	public  Boolean getContinueThread() {
		return continueThread;
	}

	public void setContinueThread(Boolean continueThread) {
		this.continueThread = continueThread;
	}

/*	private void enterCluster() {
		
	        btn.addActionListener(new BtnActionAdapter());  //newһ���¼���������ʵ�ּ�������
	      
	  
	    }
	    public class BtnActionAdapter implements ActionListener {      //���һ���¼���������ʵ�ּ�������
	        public void actionPerformed(ActionEvent e) {    //�����¼�
	            String s = in.getText();    //�ı���õ��ı�
	            clusterNum = Integer.parseInt(s);       //������ʵ��
	             
	           out.setText("Nombre de K: "+ clusterNum );      //��ʾ���
	        }
	    }*/
		public int getClusterNum() {
			return clusterNum;
		}

		public void setClusterNum(int clusterNum) {
			this.clusterNum = clusterNum;
		}

		public boolean isOk() {
			return ok;
		}

		public void setOk(boolean ok) {
			this.ok = ok;
		}

		public JButton getBtn() {
			return btn;
		}

		public void setBtn(JButton btn) {
			this.btn = btn;
		}

	
}
