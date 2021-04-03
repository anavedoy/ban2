package dados;

import org.bson.types.ObjectId;

public class Livro {
	private ObjectId id;	
	private int isbn;
	private String nome;
	private String autores;
	private String editora;
	private String colecao;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAutores() {
		return autores;
	}
	public void setAutores(String autores) {
		this.autores = autores;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getColecao() {
		return colecao;
	}
	public void setColecao(String colecao) {
		this.colecao = colecao;
	}
	@Override
	public String toString() {
		return "Livro [ISBN=" + isbn +", nome=" + nome + ", autores=" + autores + ", editora=" + editora + ", colecao="
				+ colecao + "]";
	}
	
	
	
}
