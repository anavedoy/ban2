package dados;

import java.util.Date;

import org.bson.types.ObjectId;

public class Emprestimo {
	private ObjectId id;
	private ObjectId id_usuario;
	private ObjectId id_funcionario;
	private ObjectId id_exemplar;
	private Date data_emprestimo;
	private Date data_estipulada_entrega;
	private Date data_real_entrega;
	private double multa;
	private int renovacoes;
	private String status;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(ObjectId id_usuario) {
		this.id_usuario = id_usuario;
	}
	public ObjectId getId_funcionario() {
		return id_funcionario;
	}
	public void setId_funcionario(ObjectId id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	public ObjectId getId_exemplar() {
		return id_exemplar;
	}
	public void setId_exemplar(ObjectId id_exemplar) {
		this.id_exemplar = id_exemplar;
	}
	public Date getData_emprestimo() {
		return data_emprestimo;
	}
	public void setData_emprestimo(Date date) {
		this.data_emprestimo = date;
	}
	public Date getData_estipulada_entrega() {
		return data_estipulada_entrega;
	}
	public void setData_estipulada_entrega(Date data_estipulada_entrega) {
		this.data_estipulada_entrega = data_estipulada_entrega;
	}
	public Date getData_real_entrega() {
		return data_real_entrega;
	}
	public void setData_real_entrega(Date data_real_entrega) {
		this.data_real_entrega = data_real_entrega;
	}
	public double getMulta() {
		return multa;
	}
	public void setMulta(double d) {
		this.multa = d;
	}
	public int getRenovacoes() {
		return renovacoes;
	}
	public void setRenovacoes(int renovacoes) {
		this.renovacoes = renovacoes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Emprestimo [id_funcionario=" + id_funcionario
				+ ", id_exemplar=" + id_exemplar + ", data_emprestimo=" + data_emprestimo + ", data_estipulada_entrega="
				+ data_estipulada_entrega + ", data_real_entrega=" + data_real_entrega + ", multa=" + multa
				+ ", renovacoes=" + renovacoes + ", status=" + status + "]";
	}
	
}
