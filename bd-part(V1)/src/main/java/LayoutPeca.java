import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LayoutPeca {

	JPanel mainPanel;
	JLabel nome, preco, quantidade;
	JButton mais, menos, excluir, voltar;

	public LayoutPeca(String nome, float preco, int quantidade, int width, int height) {

		this.nome = new JLabel(nome);
		this.preco = new JLabel("Preço: " + String.valueOf(preco) + " R$");
		this.quantidade = new JLabel("Quantidade: " + String.valueOf(quantidade));

	    this.nome.setBounds((width * 5)/100, (height * 15)/100, (width * 60)/100, (height * 10)/100);
        this.nome.setForeground(Color.WHITE);
        this.nome.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));

	    this.preco.setBounds((width * 5)/100, (height * 30)/100, (width * 60)/100, (height * 10)/100);
        this.preco.setForeground(Color.WHITE);
        this.preco.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));

	    this.quantidade.setBounds((width * 5)/100, (height * 45)/100, (width * 60)/100, (height * 10)/100);
        this.quantidade.setForeground(Color.WHITE);
        this.quantidade.setFont(new Font("TimesRoman", Font.PLAIN, (width * 7)/100));

        this.mais = new JButton("+");
        this.menos = new JButton("-");
        this.excluir = new JButton("Excluir");
        this.voltar = new JButton("<-");

		this.mais.setBounds((width * 75)/100, (height * 15)/100, (width * 10)/100, (height * 10)/100);
		this.mais.setBackground(Color.WHITE);
		this.mais.setForeground(Color.BLUE);
		this.mais.setFont(new Font("TimesRoman", Font.PLAIN, (width * 4)/100));

		this.menos.setBounds((width * 75)/100, (height * 30)/100, (width * 10)/100, (height * 10)/100);
		this.menos.setBackground(Color.WHITE);
		this.menos.setForeground(Color.BLUE);
		this.menos.setFont(new Font("TimesRoman", Font.PLAIN, (width * 4)/100));

		this.voltar.setBounds(0, 0, (width * 10)/100, (height * 10)/100);
		this.voltar.setBackground(Color.WHITE);
		this.voltar.setForeground(Color.BLUE);
		this.voltar.setFont(new Font("TimesRoman", Font.PLAIN, (width * 3)/100));

		this.excluir.setBounds((width * 25)/100, (height * 70)/100, (width * 50)/100, (height * 10)/100);
		this.excluir.setBackground(Color.WHITE);
		this.excluir.setForeground(Color.BLUE);
		this.excluir.setFont(new Font("TimesRoman", Font.PLAIN, (width * 5)/100));

	}

	public JPanel getPanel() {
		mainPanel = new JPanel();

    	mainPanel.add(this.nome);
    	mainPanel.add(this.preco);
    	mainPanel.add(this.quantidade);
		mainPanel.add(this.mais);
		mainPanel.add(this.menos);
		mainPanel.add(this.excluir);
		mainPanel.add(this.voltar);

    	mainPanel.setVisible(true);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);

		return mainPanel;
	}

	public JButton getMais() {
		return mais;
	}

	public JButton getMenos() {
		return menos;
	}

	public JButton getVoltar() {
		return voltar;
	}

	public JButton getExcluir() {
		return excluir;
	}

	public JLabel getQuantidade() {
		return quantidade;
	}
}