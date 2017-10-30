import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * @author CJ
 *
 */
public class ReadFileEngine{

	static Window1 frame = new Window1();
	
	public static void readFile(){
		String line = null;
		JFileChooser filechooser = new JFileChooser();
		
		try{
			int x = filechooser.showOpenDialog(frame);
			
			if(x == JFileChooser.APPROVE_OPTION){
			File f = filechooser.getSelectedFile();
			System.out.print(f.getName());
			
			FileReader fr = new FileReader(f);	
			BufferedReader br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
			br.close();
		}
	}catch(FileNotFoundException fnfe){
		JOptionPane.showMessageDialog(null, fnfe);
	}catch(IOException ioe){
		JOptionPane.showMessageDialog(null, ioe);
	}
	
	
	} 
	
	/**
	 * @param args
	 */	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readFile();
	}

}
