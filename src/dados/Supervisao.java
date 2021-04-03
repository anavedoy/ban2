package dados;

import org.bson.types.ObjectId;

public class Supervisao {
	private ObjectId id;
	private ObjectId id_assistente;
	private ObjectId id_bibliotecario;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getId_assistente() {
		return id_assistente;
	}

	public void setId_assistente(ObjectId id_assistente) {
		this.id_assistente = id_assistente;
	}

	public ObjectId getId_bibliotecario() {
		return id_bibliotecario;
	}

	public void setId_bibliotecario(ObjectId id_bibliotecario) {
		this.id_bibliotecario = id_bibliotecario;
	}

	@Override
	public String toString() {
		return "Supervisao [id_bibliotecario=" + id_bibliotecario + "]";
	}
	
}
