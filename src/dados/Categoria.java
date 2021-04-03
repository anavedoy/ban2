package dados;

import org.bson.types.ObjectId;

public class Categoria {
	private ObjectId id;
	private String tipo;
	private double tarifa;
	private int tempo_emprestimo;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getTarifa() {
		return tarifa;
	}
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}
	public int getTempo_emprestimo() {
		return tempo_emprestimo;
	}
	public void setTempo_emprestimo(int tempo_emprestimo) {
		this.tempo_emprestimo = tempo_emprestimo;
	}
	public String toString() {
		return "Categoria [tipo=" + tipo + ", tarifa=" + tarifa + ", tempo_emprestimo="
				+ tempo_emprestimo + "]";
	}
}
