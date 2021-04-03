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

import dados.Supervisao;

public class SupervisaoDAOMongo {
	private static SupervisaoDAOMongo instance = null;
	private static MongoCollection<Document> collection;
	
	public static SupervisaoDAOMongo getInstance() {
		
		if (instance == null) 
			instance = new SupervisaoDAOMongo();
		
		return instance;
	}
	
	private SupervisaoDAOMongo() {
		MongoDatabase conn = ConexaoMongo.getConexao();
		try {
			collection = conn.getCollection("supervisao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Supervisao supervisao) {
		try {
			Document document = new Document("id_assistente", supervisao.getId_assistente())
					.append("id_bibliotecario", supervisao.getId_bibliotecario());
			collection.insertOne(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Supervisao supervisao) {
		try {
			collection.updateOne(
	                eq("_id", supervisao.getId()),
	                combine(set("id_assistente", supervisao.getId_assistente()), 
	                		set("id_bibliotecario", supervisao.getId_bibliotecario())));
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
	
	public Supervisao select(ObjectId id) {
		Supervisao supervisao = new Supervisao();
		
		try {
			Document supervisao_atual= collection.find(eq("_id", id))
					.first();
			if(supervisao_atual != null) {
				supervisao.setId(supervisao_atual.getObjectId("_id"));
				supervisao.setId_assistente(supervisao_atual.getObjectId("id_assistente"));
				supervisao.setId_bibliotecario(supervisao_atual.getObjectId("id_bibliotecario"));		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return supervisao;
	}			
	
	public ArrayList<Supervisao> select_all() {
		ArrayList<Supervisao> supervisoes = new ArrayList<Supervisao>();
		
		try {
			MongoIterable<Document> supervisao_atual = collection.find();
			for(Document sup : supervisao_atual) {
				Supervisao supervisao = new Supervisao();
				
				supervisao.setId(sup.getObjectId("_id"));
				supervisao.setId_assistente(sup.getObjectId("id_assistente"));
				supervisao.setId_bibliotecario(sup.getObjectId("id_bibliotecario"));	
								
				supervisoes.add(supervisao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return supervisoes;
	}
	
}
