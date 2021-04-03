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

import dados.Funcionario;

public class FuncionarioDAOMongo {

	private static FuncionarioDAOMongo instance = null;
	private static MongoCollection<Document> collection;
	
	public static FuncionarioDAOMongo getInstance() {
		
		if (instance == null) 
			instance = new FuncionarioDAOMongo();
		
		return instance;
	}
	
	private FuncionarioDAOMongo() {
		MongoDatabase conn = ConexaoMongo.getConexao();
		try {
			collection = conn.getCollection("funcionario");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Funcionario funcionario) {
		try {
			Document document = new Document("nome", funcionario.getNome())
					.append("ctps", funcionario.getCtps())
					.append("telefone", funcionario.getTelefone())
					.append("endereco", funcionario.getEndereco())
					.append("salario", funcionario.getSalario())
					.append("turno", funcionario.getTurno())
					.append("funcao", funcionario.getFuncao());
			collection.insertOne(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Funcionario funcionario) {
		try {
			collection.updateOne(
	                eq("_id", funcionario.getId()),
	                combine(set("nome", funcionario.getNome()), 
	                		set("ctps", funcionario.getCtps()), 
	                		set("telefone", funcionario.getTelefone()), 
	                		set("endereco", funcionario.getEndereco()), 
	                		set("salario", funcionario.getSalario()), 
	                		set("turno", funcionario.getTurno()), 
	                		set("funcao", funcionario.getFuncao())));
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
	
	public Funcionario select(ObjectId id) {
		Funcionario funcionario = new Funcionario();
		
		try {
			Document funcionario_atual= collection.find(eq("_id", id))
					.first();
			if(funcionario_atual != null) {
				funcionario.setId(funcionario_atual.getObjectId("_id"));
				funcionario.setNome(funcionario_atual.getString("nome"));
				funcionario.setCtps(funcionario_atual.getInteger("ctps"));
				funcionario.setTelefone(funcionario_atual.getInteger("telefone"));
				funcionario.setEndereco(funcionario_atual.getString("endereco"));
				funcionario.setSalario(funcionario_atual.getDouble("salario"));
				funcionario.setTurno(funcionario_atual.getString("turno"));
				funcionario.setFuncao(funcionario_atual.getString("funcao"));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return funcionario;
	}			
	
	public ArrayList<Funcionario> select_all() {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			MongoIterable<Document> funcionario_atual = collection.find();
			for(Document func : funcionario_atual) {
				Funcionario funcionario = new Funcionario();

				funcionario.setId(func.getObjectId("_id"));
				funcionario.setNome(func.getString("nome"));
				funcionario.setCtps(func.getInteger("ctps"));
				funcionario.setTelefone(func.getInteger("telefone"));
				funcionario.setEndereco(func.getString("endereco"));
				funcionario.setSalario(func.getDouble("salario"));
				funcionario.setTurno(func.getString("turno"));
				funcionario.setFuncao(func.getString("funcao"));	
								
				funcionarios.add(funcionario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return funcionarios;
	}
	
	
}
