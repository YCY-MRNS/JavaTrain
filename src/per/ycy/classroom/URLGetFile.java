package per.ycy.classroom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLGetFile extends JFrame{ 
	JScrollPane jsp=new JScrollPane()	;	
	JPanel jp=new JPanel();
	JTextField txtUrl = new JTextField("http://www.baidu.com",20);
	JButton btn = new JButton("��ȡ��ҳ����");
	JTextArea txtContent = new JTextArea("���ص����ݣ�",10,40); 
	
	public URLGetFile(String title) {
		super(title);
	}

	public void dodo() { 
		jp.add(txtUrl);
		jp.add(btn);
		this.add(jp,BorderLayout.NORTH);
		jsp.setViewportView(txtContent);	
		this.add(jsp,BorderLayout.CENTER);

		btn.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String strurl = txtUrl.getText(); 
				try { 
					URL url= new URL(strurl); 
					txtContent.setText("");
					getPageContent(url);
				}catch ( MalformedURLException e) { 
					System.out.println("URL��ʽ�д�" ); 
				}
			}
		});
	} 

	public void getPageContent(URL url) { 
		InputStream filecon = null; 
		BufferedReader filedata = null; 
		String line; 
		try { 
			filecon = url.openStream(); 
			filedata = new BufferedReader(new InputStreamReader(filecon,"UTF-8")); 
			while ((line = filedata.readLine()) != null) { 
				txtContent.append(line+"\n"); 
			} 
		}catch (IOException e) { 
			System.out.println("Error in I/O:" + e.getMessage()); 
		} 
	} 
	public static void main( String[] args){
		URLGetFile f = new URLGetFile("URL Test");
		
		f.dodo();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize( 450,300 );
		f.setVisible(true);		

	}
} 
