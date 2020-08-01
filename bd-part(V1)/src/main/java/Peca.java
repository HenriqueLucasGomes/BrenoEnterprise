public class Peca {

	String nome;
	float preco;
	int quantidade;

	public Peca(String nome, float preco, int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public float getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}
}