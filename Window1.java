import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Dialog.ModalExclusionType;

public class Window1 extends JFrame implements ActionListener {

	private JFrame frame;
	public JMenuBar menubar = new JMenuBar();
	public JMenu file = new JMenu("File");
	public JMenu more = new JMenu("More");
	public JMenuItem open = new JMenuItem("Open");
	public JMenuItem exit = new JMenuItem("Exit");
	public JMenuItem help = new JMenuItem("Help");
	public JMenuItem about = new JMenuItem("About");
	public JFileChooser filechooser = new JFileChooser();
	public JTextArea info = new JTextArea();
	public JTextArea result = new JTextArea();
	public JTextArea textfile = new JTextArea();
	public JButton count, showfile, clear;
	public File f;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	
	/**
	 * Initialize the contents of the frame.
	 */
	public Window1() {
		frame = new JFrame();
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setBounds(100, 100, 448, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Word Counter Program");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		//menu bar
		frame.setJMenuBar(menubar);
		menubar.add(file);
		menubar.add(more);
		
		//menu list
		file.add(open);
		frame.getContentPane().add(filechooser);
		open.addActionListener(this);	
		
		file.add(exit);
		exit.addActionListener(this);
		
		more.add(help);
		more.add(about);
		
		
		info.setBounds(10, 11, 422, 40);
		info.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		info.setEditable(false);
		frame.getContentPane().add(info);
		
		result.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		result.setWrapStyleWord(true);
		result.setLineWrap(true);
		result.setEditable(false);
		
		textfile.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textfile.setLineWrap(true);	
		textfile.setWrapStyleWord(true);
		textfile.setEditable(false);
		
		count = new JButton("COUNT");
		count.setBounds(59, 62, 101, 44);
		count.addActionListener(this);
		frame.getContentPane().add(count);
		
		showfile = new JButton("SHOW FILE");
		showfile.setBounds(273, 62, 101, 44);
		showfile.addActionListener(this);
		frame.getContentPane().add(showfile);
		
		
		clear = new JButton("CLEAR");
		clear.setBounds(179, 327, 89, 23);
		clear.addActionListener(this);
		frame.getContentPane().add(clear);
		
		scrollPane.setBounds(10, 117, 207, 199);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(result);
		
		scrollPane_1.setBounds(225, 117, 207, 202);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(textfile);
	
		
	}
	
	public void callReadEngine(){
		
		try{
			int x = filechooser.showOpenDialog(frame);
			
			if(x == JFileChooser.APPROVE_OPTION){
			f = filechooser.getSelectedFile();
			info.append("You have selected the "+f.getName()+"\n");
			//System.out.print(f.getName());
			
			FileReader fr = new FileReader(f);	
			BufferedReader br = new BufferedReader(fr);
			
			br.close();
			}
		}catch(FileNotFoundException fnfe){
			JOptionPane.showMessageDialog(null, fnfe);
		}catch(IOException ioe){
			JOptionPane.showMessageDialog(null, ioe);
		}
	}
	
	public void showFile(){
		String line = null;
		
		try{
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null){
				textfile.append(line + "\n");
			}
			br.close();
		}catch(FileNotFoundException fnfe){
			JOptionPane.showMessageDialog(null, fnfe);
		}catch(IOException ioe){
			JOptionPane.showMessageDialog(null, ioe);
		}
	}
	
	public void counter(){
		int totalwords = 0;
		int totallines = 0;
		int totalcharacters = 0;
		String line = null;
		
		try{
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null){
				totallines++;
				
				String[] words = line.replaceAll("\\s+", " ").split(" ");
				for(String x: words){
					totalcharacters += x.length();
				}
				totalwords += words.length;
			}
			result.append("Number of lines: "+totallines+"\n");
			result.append("Number of words: "+totalwords+"\n");
			result.append("Number of characters: "+totalcharacters+"\n");
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == open){
			try{
				info.setText("");
				callReadEngine();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		} else if(ae.getSource() == exit){
			try{
				int n = JOptionPane.showConfirmDialog(exit, "Are you sure you want to exit?", "Exiting...", JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		} else if(ae.getSource() == count){
			try{
				counter();
			}catch(Exception e){
				
			}
		}else if(ae.getSource() == showfile){
			try{
				showFile();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		}else if(ae.getSource() == clear){
			try{
				result.setText("");
				textfile.setText("");
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		}else if(ae.getSource() == help){
			try{
				
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		} else if(ae.getSource() == about){
			try{
				
			}catch(Exception e){
				
			}
		}
	}
}
