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

import dados.Livro;

public class LivroDAOMongo {
	private static LivroDAOMongo instance = null;
	private static MongoCollection<Document> collection;
	
	public static LivroDAOMongo getInstance() {
		
		if (instance == null) 
			instance = new LivroDAOMongo();
		
		return instance;
	}
	
	private LivroDAOMongo() {
		MongoDatabase conn = ConexaoMongo.getConexao();
		try {
			collection = conn.getCollection("livro");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Livro livro) {
		try {
			Document document = new Document("isbn", livro.getIsbn())
					.append("nome", livro.getNome())
					.append("autores", livro.getAutores())
					.append("editora", livro.getEditora())
					.append("colecao", livro.getColecao());
			collection.insertOne(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Livro livro) {
		try {
			collection.updateOne(
	                eq("_id", livro.getId()),
	                combine(set("isbn", livro.getIsbn()), 
	                		set("nome", livro.getNome()), 
	                		set("autores", livro.getAutores()), 
	                		set("editora", livro.getEditora()), 
	                		set("colecao", livro.getColecao())));
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
	
	public Livro select(ObjectId id) {
		Livro livro = new Livro();
		
		try {
			Document livro_atual= collection.find(eq("_id", id))
					.first();
			if(livro_atual != null) {
				livro.setId(livro_atual.getObjectId("_id"));
				livro.setIsbn(livro_atual.getInteger("isbn"));
				livro.setNome(livro_atual.getString("nome"));
				livro.setAutores(livro_atual.getString("autores"));
				livro.setEditora(livro_atual.getString("editora"));
				livro.setColecao(livro_atual.getString("colecao"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return livro;
	}			
	
	public ArrayList<Livro> select_all() {
		ArrayList<Livro> livros = new ArrayList<Livro>();
		
		try {
			MongoIterable<Document> livro_atual = collection.find();
			for(Document liv : livro_atual) {
				Livro livro = new Livro();
				livro.setId(liv.getObjectId("_id"));
				livro.setIsbn(liv.getInteger("isbn"));
				livro.setNome(liv.getString("nome"));
				livro.setAutores(liv.getString("autores"));
				livro.setEditora(liv.getString("editora"));
				livro.setColecao(liv.getString("colecao"));
								
				livros.add(livro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return livros;
	}
	
}
