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

import dados.Usuario;

public class UsuarioDAOMongo {
	private static UsuarioDAOMongo instance = null;
	private static MongoCollection<Document> collection;
	
	public static UsuarioDAOMongo getInstance() {
		
		if (instance == null) 
			instance = new UsuarioDAOMongo();
		
		return instance;
	}
	
	private UsuarioDAOMongo() {
		MongoDatabase conn = ConexaoMongo.getConexao();
		try {
			collection = conn.getCollection("usuario");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Usuario usuario) {
		try {
			Document document = new Document("nome", usuario.getNome())
					.append("documento", usuario.getDocumento())
					.append("telefone", usuario.getTelefone())
					.append("endereco", usuario.getEndereco())
					.append("campus", usuario.getCampus())
					.append("universidade", usuario.getUniversidade())
					.append("id_categoria", usuario.getId_categoria());
			collection.insertOne(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Usuario usuario) {
		try {
			collection.updateOne(
	                eq("_id", usuario.getId()),
	                combine(set("nome", usuario.getNome()), 
	                		set("documento", usuario.getDocumento()), 
	                		set("telefone", usuario.getTelefone()), 
	                		set("endereco", usuario.getEndereco()), 
	                		set("campus", usuario.getCampus()), 
	                		set("universidade", usuario.getUniversidade()), 
	                		set("id_categoria", usuario.getId_categoria())));
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
	
	public Usuario select(ObjectId id) {
		Usuario usuario = new Usuario();
		
		try {
			Document usuario_atual= collection.find(eq("_id", id))
					.first();
			if(usuario_atual != null) {
				usuario.setId(usuario_atual.getObjectId("_id"));
				usuario.setNome(usuario_atual.getString("nome"));
				usuario.setDocumento(usuario_atual.getInteger("documento"));
				usuario.setTelefone(usuario_atual.getInteger("telefone"));
				usuario.setEndereco(usuario_atual.getString("endereco"));
				usuario.setCampus(usuario_atual.getString("campus"));
				usuario.setUniversidade(usuario_atual.getString("universidade"));
				usuario.setId_categoria(usuario_atual.getObjectId("id_categoria"));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario;
	}			
	
	public ArrayList<Usuario> select_all() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			MongoIterable<Document> usuario_atual = collection.find();
			for(Document user : usuario_atual) {
				Usuario usuario = new Usuario();
				usuario.setId(user.getObjectId("_id"));
				usuario.setNome(user.getString("nome"));
				usuario.setDocumento(user.getInteger("documento"));
				usuario.setTelefone(user.getInteger("telefone"));
				usuario.setEndereco(user.getString("endereco"));
				usuario.setCampus(user.getString("campus"));
				usuario.setUniversidade(user.getString("universidade"));
				usuario.setId_categoria(user.getObjectId("id_categoria"));		
								
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
}
