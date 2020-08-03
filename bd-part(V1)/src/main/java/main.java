
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
		File file;
		Scanner fileReader;
		login l;
		Sistema s;
		FileWriter fileWriter;
		String usuario;
		
		try {

			file = new File("file.txt");

			if (file.createNewFile()) {
				l = new login(crudfirebase);
			    
		        if(login.getLogIn()) {

		        	fileWriter = new FileWriter("file.txt");
		        	
		        	if(login.getSetor().equals("Eletronica")) {
		        		fileWriter.write("Breno" + "\n");
		        		fileWriter.write("0");
		        	} else if(login.getSetor().equals("Eletrica")) {
		        		fileWriter.write("Luiz");
		        		fileWriter.write("0");
		        	}
		        	fileWriter.close();
		        	s = new Sistema(login.getSetor(), crudfirebase);
		        }
		    } else {
		    	try {
		    		fileReader = new Scanner(file);

		    		usuario = fileReader.nextLine();
		    		
		    		if(usuario.equals("Breno")) {
		    			s = new Sistema("Eletronica", crudfirebase);
		    		} else if(usuario.equals("Breno")) {
		    			s = new Sistema("Eletrica", crudfirebase);
		    		} else if(usuario.equals("Deslogado")) {

						l = new login(crudfirebase);
					    
				        if(login.getLogIn()) {
				        	s = new Sistema(login.getSetor(), crudfirebase);
				        }
		    			
		    		} else {
		    			System.out.println("Usuário não encontrado no arquivo.");
		    		}
		    	
		    		fileReader.close();
		    	} catch(FileNotFoundException exception) {
					System.out.println("An error occurred.");
					exception.printStackTrace();
		    	}
		    }
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
}