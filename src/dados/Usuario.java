package dados;

import org.bson.types.ObjectId;

public class Usuario {
	private ObjectId id;
	private String nome;
	private int documento;
	private int telefone;
	private String endereco;
	private String campus;
	private String universidade;
	private ObjectId id_categoria;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId objectId) {
		this.id = objectId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getUniversidade() {
		return universidade;
	}
	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}
	public ObjectId getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(ObjectId objectId) {
		this.id_categoria = objectId;
	}
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", documento=" + documento + ", telefone=" + telefone
				+ ", endereco=" + endereco + ", campus=" + campus + ", universidade=" + universidade + ", id_categoria="
				+ id_categoria + "]";
	}
	
	
}
