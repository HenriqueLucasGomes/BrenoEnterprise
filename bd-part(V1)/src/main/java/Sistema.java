import javax.swing.*;

import Modelos.Caixa;
import firebase.CRUDFirebase;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sistema extends JFrame implements WindowListener, WindowStateListener {

	String setor;
	float dinheiro, mudar;
	int width, height, control, qtdPecas, amount, i, amountPecas, j;
	boolean change, active, isRight, exist;
	JLabel titulo, tituloCaixa, dinheiroCaixa, tituloEstoque;
	JTextArea nome, preco, quantidade, search;
    JButton caixa, estoque, adicionarCaixa, removerCaixa, voltarCaixa, procurarPeca, addPeca, voltarEstoque, voltarAdicionar, adicionar, voltarProcurar, procurar;
    JPanel menu, menuCaixa, menuEstoque, adicionarPeca, searchPeca;
    JTabbedPane tabsSearch;
    String nomePeca;
    List<Peca> pecas;
    List<Integer> indexs;
    List<LayoutPeca> arrayPecas;
	File file;
	FileWriter fileWriter;
	Scanner fileReader;
	Caixa caixaFirebase;
	CRUDFirebase crudfirebase;
	
	public Sistema(String setor, CRUDFirebase crudfirebase) {

		this.setor = setor;
		this.crudfirebase = crudfirebase;
		
        load();

        while(active) {

            if(change) {
                render();
            }

            try {
                Thread.sleep(50);
            } catch(Exception e) {
                System.out.println("ERROR!");
            }
        }

        unload();
	}

	public void render() {

		switch(control) {
			case 1:

				menu();

				break;

			case 2:

				caixa();

				break;

			case 3:

				estoque();

				break;

			case 4:

				adicionarPeca();

				break;

			case 5:

				searchPeca();

				break;


			case 6:

				tabsSearch();

				break;

			default: break;
		}
	}

	public void menu() {

	    titulo.setBounds((width * 20)/100, (height * 5)/100, (width * 80)/100, (height * 15)/100);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));

		caixa.setBounds((width * 25)/100, (height * 35)/100, (width * 50)/100, (height * 10)/100);
		estoque.setBounds((width * 25)/100, (height * 60)/100, (width * 50)/100, (height * 10)/100);

		caixa.setBackground(Color.WHITE);
		caixa.setForeground(Color.BLUE);

		estoque.setBackground(Color.WHITE);
		estoque.setForeground(Color.BLUE);

		caixa.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
		estoque.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));

        menu.add(titulo);
        menu.add(caixa);
        menu.add(estoque);

        menu.setLayout(null);
        menu.setBackground(Color.BLACK);

		add(menu);
		setSize(width, height);
        setVisible(true);
		revalidate();
		repaint();

		change = false;
	}

	public int numberDigits(float n) {
		int count = 0;
		while(n >= 1) {
            n /= 10;
            ++count;
        }

        return count;
	}

	public void caixa() {

	    tituloCaixa.setBounds((width * 20)/100, (height * 5)/100, (width * 80)/100, (height * 15)/100);
        tituloCaixa.setForeground(Color.WHITE);
        tituloCaixa.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));

        dinheiroCaixa.setText(String.valueOf(dinheiro) + " R$");
	    if(dinheiro >= 0) dinheiroCaixa.setBounds((width * (42 - (numberDigits(dinheiro) * 2)))/100, (height * 28)/100, (width * 80)/100, (height * 10)/100);
	    else dinheiroCaixa.setBounds((width * (35 - (numberDigits(dinheiro))))/100, (height * 28)/100, (width * 80)/100, (height * 10)/100);
	    
        if(dinheiro > 0) dinheiroCaixa.setForeground(Color.GREEN);
        else if(dinheiro < 0) dinheiroCaixa.setForeground(Color.RED);
        else dinheiroCaixa.setForeground(Color.BLUE);
        
        dinheiroCaixa.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));

		adicionarCaixa.setBounds((width * 25)/100, (height * 50)/100, (width * 50)/100, (height * 10)/100);
		removerCaixa.setBounds((width * 25)/100, (height * 70)/100, (width * 50)/100, (height * 10)/100);
		voltarCaixa.setBounds(0, 0, (width * 10)/100, (height * 10)/100);

		adicionarCaixa.setBackground(Color.WHITE);
		adicionarCaixa.setForeground(Color.BLUE);

		removerCaixa.setBackground(Color.WHITE);
		removerCaixa.setForeground(Color.BLUE);

		voltarCaixa.setBackground(Color.WHITE);
		voltarCaixa.setForeground(Color.BLUE);

		adicionarCaixa.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
		removerCaixa.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
		voltarCaixa.setFont(new Font("TimesRoman", Font.PLAIN, (width * 3)/100));

        menuCaixa.add(tituloCaixa);
        menuCaixa.add(dinheiroCaixa);
        menuCaixa.add(adicionarCaixa);
        menuCaixa.add(removerCaixa);
        menuCaixa.add(voltarCaixa);

        menuCaixa.setLayout(null);
        menuCaixa.setBackground(Color.BLACK);

        add(menuCaixa);
        setSize(width, height);
        setVisible(true);
		revalidate();
		repaint();

		change = false;
	}

	public void estoque() {

	    tituloEstoque.setBounds((width * 30)/100, (height * 5)/100, (width * 80)/100, (height * 15)/100);
        tituloEstoque.setForeground(Color.WHITE);
        tituloEstoque.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));
        tituloEstoque.setText("Em breve ...");

		procurarPeca.setBounds((width * 25)/100, (height * 30)/100, (width * 50)/100, (height * 10)/100);
		addPeca.setBounds((width * 25)/100, (height * 50)/100, (width * 50)/100, (height * 10)/100);
		voltarEstoque.setBounds(0, 0, (width * 10)/100, (height * 10)/100);

		procurarPeca.setBackground(Color.WHITE);
		procurarPeca.setForeground(Color.BLUE);

		addPeca.setBackground(Color.WHITE);
		addPeca.setForeground(Color.BLUE);

		voltarEstoque.setBackground(Color.WHITE);
		voltarEstoque.setForeground(Color.BLUE);

		procurarPeca.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
		addPeca.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
		voltarEstoque.setFont(new Font("TimesRoman", Font.PLAIN, (width * 3)/100));

        menuEstoque.add(tituloEstoque);
//        menuEstoque.add(procurarPeca);
//        menuEstoque.add(addPeca);
        menuEstoque.add(voltarEstoque);

        menuEstoque.setLayout(null);
        menuEstoque.setBackground(Color.BLACK);

        add(menuEstoque);
        setSize(width, height);
        setVisible(true);
		revalidate();
		repaint();

		change = false;
	}

	public void adicionarPeca() {

	    tituloEstoque.setBounds((width * 20)/100, (height * 5)/100, (width * 80)/100, (height * 15)/100);
        tituloEstoque.setForeground(Color.WHITE);
        tituloEstoque.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));

	    nome.setBounds((width * 25)/100, (height * 20)/100, (width * 50)/100, (height * 9)/100);
        nome.setForeground(Color.BLACK);
        nome.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
        nome.setText("Nome");

	    preco.setBounds((width * 25)/100, (height * 40)/100, (width * 50)/100, (height * 9)/100);
        preco.setForeground(Color.BLACK);
        preco.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
        preco.setText("Preço");

	    quantidade.setBounds((width * 25)/100, (height * 60)/100, (width * 50)/100, (height * 9)/100);
        quantidade.setForeground(Color.BLACK);
        quantidade.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
        quantidade.setText("Quantidade");

		adicionar.setBounds((width * 25)/100, (height * 80)/100, (width * 50)/100, (height * 10)/100);
		voltarAdicionar.setBounds(0, 0, (width * 10)/100, (height * 10)/100);

		adicionar.setBackground(Color.WHITE);
		adicionar.setForeground(Color.BLUE);

		voltarAdicionar.setBackground(Color.WHITE);
		voltarAdicionar.setForeground(Color.BLUE);

		adicionar.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
		voltarAdicionar.setFont(new Font("TimesRoman", Font.PLAIN, (width * 3)/100));

        adicionarPeca.add(tituloEstoque);
        adicionarPeca.add(nome);
        adicionarPeca.add(preco);
        adicionarPeca.add(quantidade);
        adicionarPeca.add(adicionar);
        adicionarPeca.add(voltarAdicionar);

        adicionarPeca.setLayout(null);
        adicionarPeca.setBackground(Color.BLACK);

        add(adicionarPeca);
        setSize(width, height);
        setVisible(true);
		revalidate();
		repaint();

		change = false;
	}

	public void searchPeca() {

	    tituloEstoque.setBounds((width * 20)/100, (height * 5)/100, (width * 80)/100, (height * 15)/100);
        tituloEstoque.setForeground(Color.WHITE);
        tituloEstoque.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));

		search.setBounds((width * 5)/100, (height * 20)/100, (width * 50)/100, (height * 9)/100);
        search.setForeground(Color.BLACK);
        search.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));

		procurar.setBounds((width * 60)/100, (height * 20)/100, (width * 35)/100, (height * 10)/100);
		voltarProcurar.setBounds(0, 0, (width * 10)/100, (height * 10)/100);

		procurar.setBackground(Color.WHITE);
		procurar.setForeground(Color.BLUE);

		voltarProcurar.setBackground(Color.WHITE);
		voltarProcurar.setForeground(Color.BLUE);

		procurar.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));
		voltarProcurar.setFont(new Font("TimesRoman", Font.PLAIN, (width * 3)/100));

        searchPeca.add(tituloEstoque);
        searchPeca.add(search);
        searchPeca.add(procurar);
        searchPeca.add(voltarProcurar);

        searchPeca.setLayout(null);
        searchPeca.setBackground(Color.BLACK);

        add(searchPeca);
        setSize(width, height);
        setVisible(true);
		revalidate();
		repaint();

		change = false;
	}

	public void tabsSearch() {

    	indexs.clear();
    	amountPecas = 0;

    	if(!nomePeca.equals("")) {
        	for(i = 0; i < amount; i++) {
        		if(pecas.get(i).getNome().contains(nomePeca)) {
        			indexs.add(i);
        			amountPecas++;
        		}
        	}
    	}

    	if(amountPecas > 0) {

        	tabsSearch.removeAll();
    		arrayPecas.clear();

        	for(i = 0; i < amountPecas; i++) {
        		arrayPecas.add(new LayoutPeca(pecas.get(indexs.get(i)).getNome(),
        										pecas.get(indexs.get(i)).getPreco(),
        										pecas.get(indexs.get(i)).getQuantidade(),
        										width, height));
        		tabsSearch.add(pecas.get(indexs.get(i)).getNome(), arrayPecas.get(i).getPanel());
        	}

        	for(i = 0; i < amountPecas; i++) {
        		arrayPecas.get(i).getVoltar().addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	remove(tabsSearch);
		            	control = 5;
						change = true;
		            }
		        });

        		arrayPecas.get(i).getExcluir().addActionListener(new ActionListener() {

        			int index = i;

		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	pecas.remove(pecas.get(indexs.get(index)));
		            	amount--;
		            	arrayPecas.remove(index);
		            	tabsSearch.remove(index);

		            	if(tabsSearch.getTabCount() == 0) {
		            		remove(tabsSearch);
		            		control = 5;
		            		change = true;
		            	}
		            }
		        });

        		arrayPecas.get(i).getMais().addActionListener(new ActionListener() {

        			int index = i;

		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	pecas.get(indexs.get(index)).setQuantidade(pecas.get(indexs.get(index)).getQuantidade() + 1);
		            	arrayPecas.get(index).getQuantidade().setText("Quantidade: " + String.valueOf(pecas.get(indexs.get(index)).getQuantidade()));
		            }
		        });

        		arrayPecas.get(i).getMenos().addActionListener(new ActionListener() {

        			int index = i;

		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	if(pecas.get(indexs.get(index)).getQuantidade() > 0) {
			            	pecas.get(indexs.get(index)).setQuantidade(pecas.get(indexs.get(index)).getQuantidade() - 1);
			            	arrayPecas.get(index).getQuantidade().setText("Quantidade: " + String.valueOf(pecas.get(indexs.get(index)).getQuantidade()));
		            	}
		            }
		        });


        	}

	        add(tabsSearch);
	        setSize(width, height);
	        setVisible(true);
			revalidate();
			repaint();

			change = false;
        } else {
        	change = true;
	        control = 5;
        } 
	}

	public void unload() {
		dispose();
	}

	public void load() {

        active = true;
        change = true;

        caixaFirebase = new Caixa(setor, crudfirebase);
        
        dinheiro = caixaFirebase.getSaldo();
        amount = 0;
		amountPecas = 0;

        pecas = new ArrayList<Peca>();
		indexs = new ArrayList<Integer>();
		arrayPecas = new ArrayList<LayoutPeca>();

        addWindowListener(this);
        addWindowStateListener(this);

		caixa = new JButton("Caixa");
		estoque = new JButton("Estoque");
		adicionarCaixa = new JButton("Adicionar");
		removerCaixa = new JButton("Remover");
		voltarCaixa = new JButton("<-");
		procurarPeca = new JButton("Procurar peça");
		addPeca = new JButton("Adicionar peça");
		voltarEstoque = new JButton("<-");
		voltarAdicionar = new JButton("<-");
		adicionar = new JButton("Adicionar");
		voltarProcurar = new JButton("<-");
		procurar = new JButton("Procurar");

        menuCaixa = new JPanel();
        menuEstoque = new JPanel();
        adicionarPeca = new JPanel();
        menu = new JPanel();
        searchPeca = new JPanel();

        tabsSearch = new JTabbedPane();

        height = (getScreenHeight() * 60) / 100;
        width = (getScreenWidth() * 40) / 100;

		try {

			file = new File("file.txt");

			if (file.createNewFile()) {
				exist = false;
			} else {
				exist = true;
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		if(exist) {
			try {
				fileReader = new Scanner(file);

				amount = Integer.parseInt(fileReader.nextLine());

				for(i = 0; i < amount; i++) {
					pecas.add(new Peca(fileReader.nextLine(), Float.parseFloat(fileReader.nextLine()), Integer.parseInt(fileReader.nextLine())));
				}

				fileReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}

        titulo = new JLabel("Breno Enterprise");
        tituloCaixa = new JLabel("Breno Enterprise");
		tituloEstoque = new JLabel("Breno Enterprise");
		dinheiroCaixa = new JLabel(String.valueOf(dinheiro) + " R$");

		nome = new JTextArea("Nome");
		preco = new JTextArea("Preço");
		quantidade = new JTextArea("Quantidade");
		search = new JTextArea();

	    // 1 -> menu
	    // 2 -> caixa
	    // 3 -> menu estoque
	    // 4 -> procurar peça
	    // 5 -> adicionar peça
	    control = 1;

        caixa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	remove(menu);
            	control = 2;
            	change = true;
            }
        });

        estoque.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	remove(menu);
            	control = 3;
            	change = true;
            }
        });

        procurarPeca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	remove(menuEstoque);
            	control = 5;
            	change = true;
            }
        });


        voltarCaixa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	remove(menuCaixa);
            	control = 1;
            	change = true;
            }
        });

        voltarEstoque.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	remove(menuEstoque);
            	control = 1;
            	change = true;
            }
        });

        voltarAdicionar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	remove(adicionarPeca);
            	control = 3;
            	change = true;
            }
        });

        voltarProcurar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	remove(searchPeca);
            	control = 3;
            	change = true;
            	qtdPecas = 0;
            }
        });

		addPeca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	remove(menuEstoque);
            	control = 4;
            	change = true;
            }
        });

        adicionarCaixa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	isRight = false;
            	mudar = 0;
				do {
					try {
						mudar = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor:"));
						isRight = true;
						break;
					} catch (Exception exception) {
						if(String.valueOf(mudar).equals("0.0")) {
							isRight = true;
						}
					}
				} while (!isRight);
				dinheiro+=mudar;
				caixaFirebase.addGanhos(mudar);
				change = true;
            }
        });

        removerCaixa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	isRight = false;
            	mudar = 0;
				do {
					try {
						mudar = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor:"));
						isRight = true;
						break;
					} catch (Exception exception) {
						if(String.valueOf(mudar).equals("0.0")) {
							isRight = true;
						}
					}
				} while (!isRight);
				dinheiro-=mudar;
				caixaFirebase.addCustos(mudar);
				change = true;
            }
        });

		adicionar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            	isRight = true;

            	try {
            		pecas.add(new Peca(nome.getText(), Float.parseFloat(preco.getText()), Integer.parseInt(quantidade.getText())));
            	} catch(Exception exception) {
            		isRight = false;
            	}

            	if(isRight) {
	            	amount++;
	            	control = 3;
	            }

            	remove(adicionarPeca);
            	change = true;
            }
        });

        procurar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	nomePeca = search.getText();

            	remove(searchPeca);
            	change = true;
            	control = 6;
            }
        });

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
            if(control == 6) remove(tabsSearch);
        } else if(convertStateToString(state).equals("NORMAL")) {
            height = (getScreenHeight() * 60) / 100;
            width = (getScreenWidth() * 40) / 100;
            change = true;
            if(control == 6) remove(tabsSearch);
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
        
		try {
			fileWriter = new FileWriter("file.txt");
			fileWriter.write(String.valueOf(amount) + "\n");

			for(i = 0; i < amount; i++) {
				fileWriter.write(pecas.get(i).getNome() + "\n");
				fileWriter.write(String.valueOf(pecas.get(i).getPreco()) + "\n");
				fileWriter.write(String.valueOf(pecas.get(i).getQuantidade()) + "\n");
			}

			fileWriter.close();
		} catch (IOException exception) {
			System.out.println("An error occurred.");
			exception.printStackTrace();
		}
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