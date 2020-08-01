
import java.util.Scanner;

import firebase.CRUDFirebase;

public class main {

	public static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void main(String args[]) {
		clear();

	    CRUDFirebase crudfirebase = new CRUDFirebase();
		
		login l = new login(crudfirebase);
    
        if(login.getLogIn()) {
        	Sistema s = new Sistema(login.getSetor(), crudfirebase);
        }
	}
}

/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
	public static void main(String args[]) {

		JFrame mainWindow = new JFrame("main");

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Nome", (new LayoutPeca("Nome", 5, 5, 500, 500)).getPanel());

		mainWindow.add(tabbedPane);
		mainWindow.setSize(500, 500);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}*/