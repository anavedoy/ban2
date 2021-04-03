package persistenciaMongo;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import dados.Exemplar;

public class ExemplarDAOMongo {
	private static ExemplarDAOMongo instance = null;
	private static MongoCollection<Document> collection;
	
	public static ExemplarDAOMongo getInstance() {
		
		if (instance == null) 
			instance = new ExemplarDAOMongo();
		
		return instance;
	}
	
	private ExemplarDAOMongo() {
		MongoDatabase conn = ConexaoMongo.getConexao();
		try {
			collection = conn.getCollection("exemplar");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Exemplar exemplar) {
		try {
			Document document = new Document("isbn", exemplar.getIsbn()).append("usuario_reserva", exemplar.getId_user_reserva());
			collection.insertOne(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Exemplar exemplar) {
		try {
			collection.updateOne(
	                eq("_id", exemplar.getId()),
	                combine(set("isbn", exemplar.getIsbn()), 
	                		set("usuario_reserva", exemplar.getId_user_reserva())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(ObjectId id) {
		try {
			collection.deleteOne(eq("_id", id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Exemplar select(ObjectId id) {
		Exemplar exemplar = new Exemplar();
		
		try {
			Document exemplar_atual= collection.find(eq("_id", id))
					.first();
			if(exemplar_atual != null) {
				exemplar.setId(exemplar_atual.getObjectId("_id"));
				exemplar.setIsbn(exemplar_atual.getInteger("isbn"));
				exemplar.setId_user_reserva(exemplar_atual.getObjectId("usuario_reserva"));			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return exemplar;
	}			
	
	public ArrayList<Exemplar> select_all() {
		ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
		
		try {
			MongoIterable<Document> exemplar_atual = collection.find();
			for(Document exemp : exemplar_atual) {
				Exemplar exemplar = new Exemplar();
				
				exemplar.setId(exemp.getObjectId("_id"));
				exemplar.setIsbn(exemp.getInteger("isbn"));
				exemplar.setId_user_reserva(exemp.getObjectId("usuario_reserva"));		
								
				exemplares.add(exemplar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return exemplares;
	}
	
}
