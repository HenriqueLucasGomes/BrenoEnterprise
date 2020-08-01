import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import firebase.CRUDFirebase;

public class login extends JFrame implements WindowListener, WindowStateListener {
      JLabel rotulo1,rotulo2,rotulo3,rotulo4;
      int height, width;
      boolean active, change;
      static boolean LogIn;
      Container tela;
      JButton inputButton;
      JTextArea login;
      JPasswordField senha;
      CRUDFirebase crudfirebase;
      Login loginFirebase;
      static String Setor;

     public login(CRUDFirebase crudfirebase) {

    	this.crudfirebase= crudfirebase; 
    	 
        load();

        while(active) {

            if(change) {
                render();
                change = false;
            }

            try {
                Thread.sleep(50);
            } catch(Exception e) {
                System.out.println("ERROR!");
            }
        }

        unload();

     }

     public void unload() {
        dispose();
     }

    public void load() {
        tela = getContentPane();
        setLayout(null);

        LogIn = false;

        inputButton = new JButton("Enviar");
        login = new JTextArea();
        senha = new JPasswordField();

        active = true;
        change = true;

        Setor = "indefinido";
        
        addWindowListener(this);
        addWindowStateListener(this);
        getContentPane().setBackground(Color.BLACK);

        Login loginFirebase = new Login();
        
        height = (getScreenHeight() * 60) / 100;
        width = (getScreenWidth() * 40) / 100;

        rotulo1 = new JLabel ("Bem vindo à");
        rotulo2 = new JLabel ("Breno Enterprise");
        rotulo3 = new JLabel ("Login");
        rotulo4 = new JLabel ("Senha");

        inputButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(senha.getPassword());
                
                if(!login.getText().equals("") && !password.equals("")) {
	                String rsl=loginFirebase.autenticar(String.valueOf(login.getText()), password, crudfirebase);
	        		
	        		if(rsl=="0") {
	        			System.out.println("Usuário Inválido!");
	        			rotulo3.setText("Usuário inválido");
	        			rotulo3.setForeground(Color.RED);
	        	        rotulo3.setBounds((width * 28)/100, (height * 30)/100, (width * 60)/100, (height * 11)/100);
	
	        	        rotulo4.setText("Senha");
	        			rotulo4.setForeground(Color.WHITE);
	        	        rotulo4.setBounds((width * 39)/100, (height * 50)/100, (width * 60)/100, (height * 10)/100);
	        		}else if (rsl=="1") {
	        			System.out.println("Senha Inválida!");
	        			rotulo3.setText("Login");
	        			rotulo3.setForeground(Color.WHITE);
	        	        rotulo3.setBounds((width * 40)/100, (height * 30)/100, (width * 60)/100, (height * 11)/100);
	
	        			rotulo4.setText("Senha inválida");
	        			rotulo4.setForeground(Color.RED);
	        	        rotulo4.setBounds((width * 32)/100, (height * 50)/100, (width * 60)/100, (height * 10)/100);
	        		}else if(rsl=="2") {
	        			System.out.println("Erro Desconhecido!");
	        		}else{
	        			active = false;
	                    LogIn = true;
	        			Setor=rsl;
	        			System.out.println(Setor);
	        		}
            	}
            }
        });

    }

    public void render() {

         rotulo1.setBounds((width * 27)/100, (height * 5)/100, (width * 80)/100, (height * 10)/100);
         rotulo2.setBounds((width * 20)/100, (height * 15)/100, (width * 90)/100, (height * 15)/100);
         rotulo3.setBounds((width * 40)/100, (height * 30)/100, (width * 60)/100, (height * 11)/100);
         rotulo4.setBounds((width * 39)/100, (height * 50)/100, (width * 60)/100, (height * 10)/100);

         login.setBounds((width * 25)/100, (height * 41)/100, (width * 50)/100, (height * 10)/100);
         senha.setBounds((width * 25)/100, (height * 60)/100, (width * 50)/100, (height * 10)/100);

         inputButton.setBounds((width * 25)/100, (height * 75)/100, (width * 50)/100, (height * 10)/100);

         rotulo1.setForeground(Color.WHITE);
         rotulo2.setForeground(Color.WHITE);
         rotulo3.setForeground(Color.WHITE);
         rotulo4.setForeground(Color.WHITE);

         login.setBackground(Color.WHITE);
         login.setForeground(Color.BLACK);

         senha.setBackground(Color.WHITE);
         senha.setForeground(Color.BLACK);

         inputButton.setBackground(Color.WHITE);
         inputButton.setForeground(Color.BLUE);

         rotulo1.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));
         rotulo2.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));
         rotulo3.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
         rotulo4.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));

         login.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
         senha.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));

         inputButton.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));

         tela.add(rotulo1);
         tela.add(rotulo2);
         tela.add(rotulo3);
         tela.add(rotulo4);

         tela.add(login);
         tela.add(senha);
         tela.add(inputButton);

         setSize(width, height);
         setVisible(true);
         // setLocationRelativeTo(null);
    }

    public static int getScreenWidth() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }

    public static int getScreenHeight() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }

    public void windowStateChanged(WindowEvent e) {
        int state = e.getNewState();
        if(convertStateToString(state).equals("MAXIMIZED_BOTH")) {
            height = getScreenHeight();
            width = getScreenWidth();
            change = true;
        } else if(convertStateToString(state).equals("NORMAL")) {
            height = (getScreenHeight() * 60) / 100;
            width = (getScreenWidth() * 40) / 100;
            change = true;
        }
    }

    String convertStateToString(int state) {
        if (state == Frame.NORMAL) {
            return "NORMAL";
        }
        String strState = " ";
        if ((state & Frame.ICONIFIED) != 0) {
            strState += "ICONIFIED";
        }
        //MAXIMIZED_BOTH is a concatenation of two bits, so
        //we need to test for an exact match.
        if ((state & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
            strState += "MAXIMIZED_BOTH";
        } else {
            if ((state & Frame.MAXIMIZED_VERT) != 0) {
                strState += "MAXIMIZED_VERT";
            }
            if ((state & Frame.MAXIMIZED_HORIZ) != 0) {
                strState += "MAXIMIZED_HORIZ";
            }
            if (" ".equals(strState)){
                strState = "UNKNOWN";
            }
        }
        return strState.trim();
    }
    public void windowClosing(WindowEvent e) {
        active = false;
    }
    
    public static String getSetor() {
    	return Setor;
    }
    
    public static boolean getLogIn() {
        return LogIn;
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

}