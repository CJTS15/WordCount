import java.awt.EventQueue;

import javax.swing.UIManager;

/**
 * 
 */

/**
 * @author CJ
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Window1 frame = new Window1();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
