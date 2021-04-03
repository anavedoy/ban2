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

import dados.Categoria;

public class CategoriaDAOMongo {
	private static CategoriaDAOMongo instance = null;
	private static MongoCollection<Document> collection;
	
	public static CategoriaDAOMongo getInstance() {
		
		if (instance == null) 
			instance = new CategoriaDAOMongo();
		
		return instance;
	}
	
	private CategoriaDAOMongo() {
		MongoDatabase conn = ConexaoMongo.getConexao();
		try {
			collection = conn.getCollection("categoria");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Categoria categoria) {
		try {
			Document document = new Document("tipo", categoria.getTipo())
					.append("tarifa", categoria.getTarifa())
					.append("tempo_emprestimo", categoria.getTempo_emprestimo());
			collection.insertOne(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Categoria categoria) {
		try {
			collection.updateOne(
	                eq("_id", categoria.getId()),
	                combine(set("tipo", categoria.getTipo()), 
	                		set("tarifa", categoria.getTarifa()), 
	                		set("tempo_emprestimo", categoria.getTempo_emprestimo())));
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
	
	public Categoria select(ObjectId id) {
		Categoria categoria = new Categoria();
		
		try {
			Document categoria_atual= collection.find(eq("_id", id))
					.first();
			if(categoria_atual != null) {
				categoria.setId(categoria_atual.getObjectId("_id"));
				categoria.setTipo(categoria_atual.getString("tipo"));
				categoria.setTarifa(categoria_atual.getDouble("tarifa"));
				categoria.setTempo_emprestimo(categoria_atual.getInteger("tempo_emprestimo"));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categoria;
	}			
	
	public ArrayList<Categoria> select_all() {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		
		try {
			MongoIterable<Document> categoria_atual = collection.find();
			for(Document cat : categoria_atual) {
				Categoria categoria = new Categoria();
				
				categoria.setId(cat.getObjectId("_id"));
				categoria.setTipo(cat.getString("tipo"));
				categoria.setTarifa(cat.getDouble("tarifa"));
				categoria.setTempo_emprestimo(cat.getInteger("tempo_emprestimo"));		
								
				categorias.add(categoria);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categorias;
	}
	
}
