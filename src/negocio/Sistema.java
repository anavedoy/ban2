package negocio;

import persistenciaMongo.*;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import dados.*;

public class Sistema {
	private CategoriaDAOMongo categoria_mongo;
	private EmprestimoDAOMongo emprestimo_mongo;
	private ExemplarDAOMongo exemplar_mongo;
	private FuncionarioDAOMongo funcionario_mongo;
	private LivroDAOMongo livro_mongo;
	private SupervisaoDAOMongo supervisao_mongo;
	private UsuarioDAOMongo usuario_mongo;
	
	public Sistema() {
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public Categoria select_categoria(ObjectId id){
		categoria_mongo = CategoriaDAOMongo.getInstance();
		Categoria categoria = categoria_mongo.select(id);
		return categoria;
	}
	
	public ArrayList<Categoria> select_categorias(){
		ArrayList<Categoria> categorias = CategoriaDAOMongo.getInstance().select_all();		
		return categorias;
	}
	
	public void add_categoria(Categoria categoria){
		categoria_mongo = CategoriaDAOMongo.getInstance();
		categoria_mongo.insert(categoria);
	}
	
	public void edit_categoria(Categoria categoria){
		categoria_mongo.update(categoria);
	}
	
	public void del_categoria(ObjectId Id) {
		categoria_mongo = CategoriaDAOMongo.getInstance();
		categoria_mongo.delete(Id);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Emprestimo select_emprestimo(ObjectId id){
		Emprestimo emprestimo = emprestimo_mongo.select(id);
		return emprestimo;
	}
	
	public ArrayList<Emprestimo> select_emprestimos(){
		ArrayList<Emprestimo> emprestimos = EmprestimoDAOMongo.getInstance().select_all();		
		return emprestimos;
	}
	
	public void add_emprestimo(Emprestimo emprestimo){
		emprestimo_mongo = EmprestimoDAOMongo.getInstance();
		emprestimo_mongo.insert(emprestimo);
	}
	
	public void edit_emprestimo(Emprestimo emprestimo){
		emprestimo_mongo = EmprestimoDAOMongo.getInstance();
		emprestimo_mongo.update(emprestimo);
	}
	
	public void del_emprestimo(ObjectId Id) {
		emprestimo_mongo = EmprestimoDAOMongo.getInstance();
		emprestimo_mongo.delete(Id);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Exemplar select_exemplar(ObjectId id){
		exemplar_mongo = ExemplarDAOMongo.getInstance();
		Exemplar exemplar = exemplar_mongo.select(id);
		return exemplar;
	}
	
	public ArrayList<Exemplar> select_exemplares(){
		exemplar_mongo = ExemplarDAOMongo.getInstance();
		ArrayList<Exemplar> exemplares = exemplar_mongo.select_all();
		return exemplares;			
	}
	
	public void add_exemplar(Exemplar exemplar){
		exemplar_mongo = ExemplarDAOMongo.getInstance();
		exemplar_mongo.insert(exemplar);
	}
	
	public void edit_exemplar(Exemplar exemplar){
		exemplar_mongo = ExemplarDAOMongo.getInstance();
		exemplar_mongo.update(exemplar);
	}
	
	public void del_exemplar(ObjectId Id) {
		exemplar_mongo = ExemplarDAOMongo.getInstance();
		exemplar_mongo.delete(Id);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public Funcionario select_funcionario(ObjectId id){
		funcionario_mongo = FuncionarioDAOMongo.getInstance();
		Funcionario funcionario = funcionario_mongo.select(id);
		return funcionario;
	}
	
	public ArrayList<Funcionario> select_funcionarios(){
		funcionario_mongo = FuncionarioDAOMongo.getInstance();
		ArrayList<Funcionario> funcionarios = funcionario_mongo.select_all();		
		return funcionarios;
	}
	
	public void add_funcionario(Funcionario funcionario){
		funcionario_mongo = FuncionarioDAOMongo.getInstance();
		funcionario_mongo.insert(funcionario);
	}
	
	public void edit_funcionario(Funcionario funcionario){
		funcionario_mongo.update(funcionario);
	}
	
	public void del_funcionario(ObjectId Id) {
		funcionario_mongo = FuncionarioDAOMongo.getInstance();
		funcionario_mongo.delete(Id);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Livro select_livro(ObjectId id){
		livro_mongo = LivroDAOMongo.getInstance();
		Livro livro = livro_mongo.select(id);
		return livro;
	}

	public ArrayList<Livro> select_livros(){
		livro_mongo = LivroDAOMongo.getInstance();
		ArrayList<Livro> livros = livro_mongo.select_all();		
		return livros;
	}

	public void add_livro(Livro livro){
		livro_mongo = LivroDAOMongo.getInstance();
		livro_mongo.insert(livro);
	}

	public void edit_livro(Livro livro){
		livro_mongo.update(livro);
	}
	
	public void del_livro(ObjectId Id) {
		livro_mongo = LivroDAOMongo.getInstance();
		livro_mongo.delete(Id);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Supervisao select_supervisao(ObjectId id){
		Supervisao supervisao = supervisao_mongo.select(id);
		return supervisao;
	}

	public ArrayList<Supervisao> select_supervisoes(){
		ArrayList<Supervisao> supervisoes = SupervisaoDAOMongo.getInstance().select_all();		
		return supervisoes;
	}

	public void add_supervisao(Supervisao supervisao){
		supervisao_mongo = SupervisaoDAOMongo.getInstance();
		supervisao_mongo.insert(supervisao);
	}

	public void edit_supervisao(Supervisao supervisao){
		supervisao_mongo.update(supervisao);
	}
	
	public void del_supervisao(ObjectId Id) {
		supervisao_mongo = SupervisaoDAOMongo.getInstance();
		supervisao_mongo.delete(Id);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Usuario select_usuario(ObjectId id){
		usuario_mongo = UsuarioDAOMongo.getInstance();
		Usuario usuario = usuario_mongo.select(id);
		return usuario;
	}

	public ArrayList<Usuario> select_usuarios(){
		usuario_mongo = UsuarioDAOMongo.getInstance();
		ArrayList<Usuario> usuarios = usuario_mongo.select_all();		
		return usuarios;
	}

	public void add_usuario(Usuario usuario){
		usuario_mongo = UsuarioDAOMongo.getInstance();
		usuario_mongo.insert(usuario);
	}

	public void edit_usuario(Usuario usuario){
		usuario_mongo = UsuarioDAOMongo.getInstance();
		usuario_mongo.update(usuario);
	}
	
	public void del_usuario(ObjectId Id) {
		usuario_mongo = UsuarioDAOMongo.getInstance();
		usuario_mongo.delete(Id);
	}
	
}
