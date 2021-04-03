package dados;

import org.bson.types.ObjectId;

public class Exemplar {
	private ObjectId id;
	private int isbn;
	private ObjectId id_user_reserva;
	
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
	public ObjectId getId_user_reserva() {
		return id_user_reserva;
	}
	public void setId_user_reserva(ObjectId id_user_reserva) {
		this.id_user_reserva = id_user_reserva;
	}
	@Override
	public String toString() {
		return "Exemplar [id="+ id+ ", id_livro=" + isbn + ", id_user_reserva=" + id_user_reserva + "]";
	}
	
}
