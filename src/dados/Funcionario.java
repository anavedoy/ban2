package dados;

import org.bson.types.ObjectId;


public class Funcionario {
	private ObjectId id;
	private String nome;
	private int ctps;
	private int telefone;
	private String endereco;
	private double salario;
	private String turno;
	private String funcao;
	
	
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
	public int getCtps() {
		return ctps;
	}
	public void setCtps(int ctps) {
		this.ctps = ctps;
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
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	@Override
	public String toString() {
		return "Funcionario [nome = "+ nome +", ctps=" + ctps + ", telefone=" + telefone + ", endereco="
				+ endereco + ", salario=" + salario + ", turno=" + turno + ", funcao=" + funcao + "]";
	}
	
}
