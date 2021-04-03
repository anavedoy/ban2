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

import dados.Emprestimo;

public class EmprestimoDAOMongo {
	private static EmprestimoDAOMongo instance = null;
	private static MongoCollection<Document> collection;
	
	public static EmprestimoDAOMongo getInstance() {
		
		if (instance == null) 
			instance = new EmprestimoDAOMongo();
		
		return instance;
	}
	
	private EmprestimoDAOMongo() {
		MongoDatabase conn = ConexaoMongo.getConexao();
		try {
			collection = conn.getCollection("emprestimo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Emprestimo emprestimo) {
		try {
			Document document = new Document("id_usuario", emprestimo.getId_usuario())
					.append("id_funcionario", emprestimo.getId_exemplar())
					.append("id_exemplar", emprestimo.getId_exemplar())
					.append("data_emprestimo", emprestimo.getData_emprestimo())
					.append("data_estipulada_entrega", emprestimo.getData_estipulada_entrega())
					.append("data_real_entrega", emprestimo.getData_real_entrega())
					.append("multa", emprestimo.getMulta())
					.append("renovacoes",emprestimo.getRenovacoes())
					.append("status", emprestimo.getStatus());
			collection.insertOne(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Emprestimo emprestimo) {
		try {
			collection.updateOne(
	                eq("_id", emprestimo.getId()),
	                combine(set("id_usuario", emprestimo.getId_usuario()), 
	                		set("id_funcionario", emprestimo.getId_exemplar()), 
	                		set("id_exemplar", emprestimo.getId_exemplar()), 
	                		set("data_emprestimo", emprestimo.getData_emprestimo()), 
	                		set("data_estipulada_entrega", emprestimo.getData_estipulada_entrega()), 
	                		set("data_real_entrega", emprestimo.getData_real_entrega()), 
	                		set("multa", emprestimo.getMulta()),
	                		set("renovacoes", emprestimo.getRenovacoes()),
	                		set("status", emprestimo.getStatus())));
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
	
	public Emprestimo select(ObjectId id) {
		Emprestimo emprestimo = new Emprestimo();
		
		try {
			Document emprestimo_atual= collection.find(eq("_id", id))
					.first();
			if(emprestimo_atual != null) {
				emprestimo.setId(emprestimo_atual.getObjectId("_id"));
				emprestimo.setId_usuario(emprestimo_atual.getObjectId("id_usuario"));
				emprestimo.setId_funcionario(emprestimo_atual.getObjectId("id_funcionario"));
				emprestimo.setId_exemplar(emprestimo_atual.getObjectId("id_exemplar"));
				emprestimo.setData_emprestimo(emprestimo_atual.getDate("data_emprestimo"));
				emprestimo.setData_estipulada_entrega(emprestimo_atual.getDate("data_estipulada_entrega"));
				emprestimo.setData_real_entrega(emprestimo_atual.getDate("data_real_entrega"));
				emprestimo.setMulta(emprestimo_atual.getDouble("multa"));
				emprestimo.setRenovacoes(emprestimo_atual.getInteger("renovacoes"));
				emprestimo.setStatus(emprestimo_atual.getString("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emprestimo;
	}			
	
	public ArrayList<Emprestimo> select_all() {
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			MongoIterable<Document> emprestimo_atual = collection.find();
			for(Document emp : emprestimo_atual) {
				Emprestimo emprestimo = new Emprestimo();
				
				emprestimo.setId(emp.getObjectId("_id"));
				emprestimo.setId_usuario(emp.getObjectId("id_usuario"));
				emprestimo.setId_funcionario(emp.getObjectId("id_funcionario"));
				emprestimo.setId_exemplar(emp.getObjectId("id_exemplar"));
				emprestimo.setData_emprestimo(emp.getDate("data_emprestimo"));
				emprestimo.setData_estipulada_entrega(emp.getDate("data_estipulada_entrega"));
				emprestimo.setData_real_entrega(emp.getDate("data_real_entrega"));
				emprestimo.setMulta(emp.getDouble("multa"));
				emprestimo.setRenovacoes(emp.getInteger("renovacoes"));
				emprestimo.setStatus(emp.getString("status"));
								
				emprestimos.add(emprestimo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emprestimos;
	}
	
}
