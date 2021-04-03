package apresentacao;
import java.util.ArrayList;
import java.util.Scanner;

import org.bson.types.ObjectId;

import dados.*;

import negocio.Sistema;
public class Main {
	static Scanner in = new Scanner(System.in);
	static Sistema sistema = new Sistema();
	
	public static void main(String[] args) {
		int op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção:");
			System.out.println("0- Sair");
			System.out.println("1- Emprestimos");
			System.out.println("2- Reservas");
			System.out.println("3- Cadastros ");		
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				emprestimos_opcoes();
				break;
			case 2:
				reservas_opcoes();
				break;
			case 3:
				cadastros_opcoes();
				break;				
			}	
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	public static void emprestimos_opcoes() {
		int op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção:");
			System.out.println("0- Sair");
			System.out.println("1- Fazer novo emprestimo");
			System.out.println("2- Devolver exemplar");	
			System.out.println("3- Visualizar todos os emprestimos");
			System.out.println("4- Visualizar todos os emprestimos de um usuário");		
			System.out.println("5- Renovar Emprestimo");	
			System.out.println("6- Pagar multa");	
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				cadastrar_emprestimo();
				break;
			case 2:
				devolver_exemplar();
				break;
			case 3:
				visualizar_emprestimos();
				break;			
			case 4:
				visualizar_emprestimo();
				break;
			case 5:
				renovar_emprestimo();
				break;
			case 6: 
				pagar_multa();
				break;
			}		
		}
	}
	
	public static void reservas_opcoes() {
		int op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção:");
			System.out.println("0- Sair");
			System.out.println("1- Fazer reserva");
			System.out.println("2- Visualizar todas as reservas");	
			System.out.println("3- Visualizar todas as reservas de um usuario");
			System.out.println("4- Remover reserva");
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				fazer_reserva();
				break;
			case 2:
				visualizar_reservas();
				break;
			case 3:
				visualizar_reserva();
				break;			
			case 4:
				remover_reserva();
				break;
			}		
		}
	}
	
	public static void cadastros_opcoes() {
		int op = -1;
		while(op != 0) {
			System.out.println("Escolha uma ação:");
			System.out.println("1-Adiciocar\n2-Remover\n3-Editar\n4-Listar");
			System.out.println("0- Para sair");
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				adicionar_opcoes();
				break;
			case 2:
				remover_opcoes();
				break;
			case 3:
				editar_opcoes();
				break;			
			case 4:
				listar_opcoes();
				break;
			}		
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void cadastrar_emprestimo() {}
	
	public static void devolver_exemplar() {}
	
	public static void visualizar_emprestimos() {}
	
	public static void visualizar_emprestimo() {}
	
	public static void renovar_emprestimo() {}
	
	public static void pagar_multa() {}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void fazer_reserva() {
		
		ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
		ArrayList<Exemplar> exemplares_disponiveis = new ArrayList<Exemplar>();
		exemplares = sistema.select_exemplares();
		
		System.out.println("Exemplares disponíveis para reserva");
		
		int j=0;
		for(int i=0;i<exemplares.size();i++) {
			if(exemplares.get(i).getId_user_reserva()==null){
				System.out.print(j);
				j++;
				System.out.println("\t"+exemplares.get(i).toString());
				exemplares_disponiveis.add(exemplares.get(i));
			}
		}
		
		System.out.println("Digite o índice do exemplar que quer adicionar de reservas:");
		int op = in.nextInt();
		in.nextLine();
		
		ObjectId id_exemplar = exemplares_disponiveis.get(op).getId();
		Exemplar exemplar = new Exemplar();
		exemplar.setId(id_exemplar);
		exemplar.setIsbn(exemplares_disponiveis.get(op).getIsbn());
		
		System.out.println("Usuários:");
		listar_usuarios();
		
		System.out.println("Digite o índice do usuario que quer fazer uma reservas:");
		op = in.nextInt();
		in.nextLine();
		
		exemplar.setId_user_reserva(sistema.select_usuarios().get(op).getId());
		
		sistema.edit_exemplar(exemplar);
		
	}
	
	public static void visualizar_reservas() {
		
		ArrayList<Exemplar> exemplares_com_reserva = new ArrayList<Exemplar>();
		exemplares_com_reserva = sistema.select_exemplares();
		
		System.out.println("Exemplares com reserva:");
		
		int j=0;
		for(int i=0;i<exemplares_com_reserva.size();i++) {
			if(exemplares_com_reserva.get(i).getId_user_reserva()!=null){
				System.out.print(j);
				j++;
				System.out.println("\t"+exemplares_com_reserva.get(i).toString());
			}
		}
	}
	
	public static void visualizar_reserva() {}
	
	public static void remover_reserva() {
		ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
		ArrayList<Exemplar> exemplares_com_reserva = new ArrayList<Exemplar>();
		exemplares = sistema.select_exemplares();
		
		System.out.println("Exemplares com reserva:");
		
		int j=0;
		for(int i=0;i<exemplares.size();i++) {
			if(exemplares.get(i).getId_user_reserva()!=null){
				System.out.print(j);
				j++;
				System.out.println("\t"+exemplares.get(i).toString());
				exemplares_com_reserva.add(exemplares.get(i));
			}
		}
		
		System.out.println("Digite o índice do exemplar que quer remover de reservas:");
		int op = in.nextInt();
		in.nextLine();
		
		Exemplar exemplar = new Exemplar();
		exemplar.setId(exemplares_com_reserva.get(op).getId());
		exemplar.setIsbn(exemplares_com_reserva.get(op).getIsbn());
		exemplar.setId_user_reserva(null);
		System.out.printf(exemplar.toString());
		sistema.edit_exemplar(exemplar);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void adicionar_opcoes() {
		int op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção para adicionar:");
			System.out.println("1-EXEMPLAR\n2-USUARIO\n3-FUNCIONARIO\n4-CATEGORIA\n5-SUPERVISÃO\n6-LIVRO");
			System.out.println("0- Para sair");
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				adicionar_exemplar();
				break;
			case 2:
				adicionar_usuario();
				break;
			case 3:
				adicionar_funcionario();
				break;			
			case 4:
				adicionar_categoria();
				break;
			case 5:
				adicionar_supervisao();
				break;
			case 6: 
				adicionar_livro();
				break;
			}		
		}
	}
	
	public static void remover_opcoes() {
		int op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção para remover:");
			System.out.println("1-EXEMPLAR\n2-USUARIO\n3-FUNCIONARIO\n4-CATEGORIA\n5-SUPERVISÃO\n6-LIVRO");
			System.out.println("0- Para sair");
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				remover_exemplar();
				break;
			case 2:
				remover_usuario();
				break;
			case 3:
				remover_funcionario();
				break;			
			case 4:
				remover_categoria();
				break;
			case 5:
				remover_supervisao();
				break;
			case 6:
				remover_livro();
				break;
			}		
		}
	}
	
	public static void editar_opcoes() {
		int op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção para editar:");
			System.out.println("1-EXEMPLAR\n2-USUARIO\n3-FUNCIONARIO\n4-CATEGORIA\n5-LIVRO");
			System.out.println("0- Para sair");
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				editar_exemplar();
				break;
			case 2:
				editar_usuario();
				break;
			case 3:
				editar_funcionario();
				break;			
			case 4:
				editar_categoria();
				break;
			case 5: 
				editar_livro();
				break;
			}		
		}
	}
	
	public static void listar_opcoes() {
		int op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção para listar:");
			System.out.println("1-EXEMPLAR\n2-USUARIO\n3-FUNCIONARIO\n4-CATEGORIA\n5-SUPERVISÃO\n6-LIVRO");
			System.out.println("0- Para sair");
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				listar_exemplares();
				break;
			case 2:
				listar_usuarios();
				break;
			case 3:
				listar_funcionarios();
				break;			
			case 4:
				listar_categorias();
				break;
			case 5:
				listar_supervisoes();
				break;
			case 6:
				listar_livros();
				break;
			}		
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void adicionar_exemplar() {
		System.out.println("Livros:");
		listar_livros();
		System.out.println("Digite o índice do livro que deseja cadastrar novo exemplar.");
		int op = in.nextInt();
		in.nextLine();
	
		Exemplar exemplar = new Exemplar();
		exemplar.setIsbn(op);
		sistema.add_exemplar(exemplar);
		System.out.println("Exemplar inserido!");
	}
	
	public static void adicionar_usuario() {
		Usuario usuario = new Usuario();
		System.out.println("Digite o nome do novo usúario.");
		String str = in.next();
		in.nextLine();
		usuario.setNome(str);
		
		System.out.println("Digite o documento do novo usúario.");
		int op = in.nextInt();
		in.nextLine();
		usuario.setDocumento(op);

		System.out.println("Digite o telefone do novo usúario.");
		op = in.nextInt();
		in.nextLine();
		usuario.setTelefone(op);
		
		System.out.println("Digite o endereco do novo usúario.");
		str = in.next();
		in.nextLine();
		usuario.setEndereco(str);
		
		System.out.println("Digite o campus do novo usúario.");
		str = in.next();
		in.nextLine();
		usuario.setCampus(str);
		
		System.out.println("Digite o universidade do novo usúario.");
		str = in.next();
		in.nextLine();
		usuario.setUniversidade(str);
		
		listar_categorias();
		
		System.out.println("Digite o índice da categoria do novo usúario.");
		op = in.nextInt();
		in.nextLine();
		
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		categorias = sistema.select_categorias();
		usuario.setId_categoria(categorias.get(op).getId());
		
		sistema.add_usuario(usuario);
	
	}

	public static void adicionar_funcionario() {
		Funcionario funcionario = new Funcionario();
		System.out.println("Digite o nome do novo funcionário.");
		String str = in.next();
		in.nextLine();
		funcionario.setNome(str);
		
		System.out.println("Digite o ctps do novo funcionário.");
		int op = in.nextInt();
		in.nextLine();
		funcionario.setCtps(op);
		
		System.out.println("Digite o telefone do novo funcionário.");
		op = in.nextInt();
		in.nextLine();
		funcionario.setTelefone(op);
		
		System.out.println("Digite o endereco do novo funcionário.");
		str = in.next();
		in.nextLine();
		funcionario.setEndereco(str);
		
		System.out.println("Digite o salario do novo funcionário.");
		double op_d = in.nextDouble();
		in.nextLine();
		funcionario.setSalario(op_d);
		
		System.out.println("Digite o turno do novo funcionário.");
		str = in.next();
		in.nextLine();
		funcionario.setTurno(str);
		
		System.out.println("Digite o funcao do novo funcionário.");
		str = in.next();
		in.nextLine();
		funcionario.setFuncao(str);
		
		sistema.add_funcionario(funcionario);
		
	}

	public static void adicionar_categoria() {
		Categoria categoria = new Categoria();
		System.out.println("Digite o tipo da nova categoria.");
		String str = in.next();
		in.nextLine();
		categoria.setTipo(str);
		
		System.out.println("Digite a tarifa para a nova categoria.");
		double op_d = in.nextDouble();
		in.nextLine();
		categoria.setTarifa(op_d);
		
		System.out.println("Digite o tempo de empréstimo para a nova categoria.");
		int op = in.nextInt();
		in.nextLine();
		categoria.setTempo_emprestimo(op);
		
		sistema.add_categoria(categoria);
	}

	public static void adicionar_supervisao() {
		Supervisao supervisao = new Supervisao();
		
		System.out.println("Lista de funcionarios bibliotecarios:");
		
		ArrayList<Funcionario> bibliotecarios = new ArrayList<Funcionario>();
		int j=0;
		for(int i=0;i<sistema.select_funcionarios().size();i++) {
			if(sistema.select_funcionarios().get(i).getFuncao().contentEquals("bibliotecario")) {
				System.out.print(j);
				j++;
				System.out.println("\t"+sistema.select_funcionarios().get(i).toString());
				bibliotecarios.add(sistema.select_funcionarios().get(i));
			}			
		}
		
		System.out.println("Digite o índice do bibliotecario supervisor:");
		
		int op = in.nextInt();
		in.nextLine();
		
		ObjectId id_bibliotecario = bibliotecarios.get(op).getId();
		supervisao.setId_bibliotecario(id_bibliotecario);
		
		ArrayList<Funcionario> assistentes = new ArrayList<Funcionario>();
		j=0;
		for(int i=0;i<sistema.select_funcionarios().size();i++) {
			if(sistema.select_funcionarios().get(i).getFuncao().contentEquals("assistente")) {
				System.out.print(j);
				j++;
				System.out.println("\t"+sistema.select_funcionarios().get(i).toString());
				assistentes.add(sistema.select_funcionarios().get(i));
			}			
		}
		
		System.out.println("Digite o índice do assistente supervisor:");
		
		op = in.nextInt();
		in.nextLine();
		
		ObjectId id_assistente = assistentes.get(op).getId();
		supervisao.setId_assistente(id_assistente);
		
		sistema.add_supervisao(supervisao);
		
	}

	public static void adicionar_livro() {
		Livro livro = new Livro();
		
		System.out.println("Digite o isbn da novo livro.");
		int op = in.nextInt();
		in.nextLine();		
		livro.setIsbn(op);
		
		System.out.println("Digite o nome do novo livro.");
		String str = in.next();
		in.nextLine();
		livro.setNome(str);
		
		System.out.println("Digite os autores do novo livro.");
		str = in.next();
		in.nextLine();
		livro.setAutores(str);
		
		System.out.println("Digite a editora do novo livro.");
		str = in.next();
		in.nextLine();
		livro.setEditora(str);
		
		System.out.println("Digite a colecao do novo livro.");
		str = in.next();
		in.nextLine();
		livro.setColecao(str);	
		
		sistema.add_livro(livro);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	public static void remover_exemplar() {
		System.out.println("Exemplares:");
		listar_exemplares();
		System.out.println("Digite o índice do exemplar que deseja excluir");
		int op = in.nextInt();
		in.nextLine();
		
		ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
		exemplares = sistema.select_exemplares();
		ObjectId id_exemplar=exemplares.get(op).getId();
		sistema.del_exemplar(id_exemplar);
	}

	public static void remover_usuario() {
		System.out.println("Usuários:");
		listar_usuarios();
		System.out.println("Digite o índice do usuário que deseja excluir");
		int op = in.nextInt();
		in.nextLine();
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = sistema.select_usuarios();
		ObjectId id_usuario=usuarios.get(op).getId();
		sistema.del_usuario(id_usuario);
	}
	
	public static void remover_funcionario() {
		System.out.println("Funcionários:");
		listar_funcionarios();
		System.out.println("Digite o índice do funcionário que deseja excluir");
		int op = in.nextInt();
		in.nextLine();
		
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios = sistema.select_funcionarios();
		ObjectId id_funcionarios=funcionarios.get(op).getId();
		sistema.del_funcionario(id_funcionarios);
		
		//deletar supervisoes
	}
	
	public static void remover_categoria() {
		System.out.println("Categoria:");
		listar_categorias();
		System.out.println("Digite o índice do categoria que deseja excluir");
		int op = in.nextInt();
		in.nextLine();
		
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		categorias = sistema.select_categorias();
		ObjectId id_categorias=categorias.get(op).getId();
		sistema.del_categoria(id_categorias);
	}
	
	public static void remover_supervisao() {
		System.out.println("Lista de supervisões:");
		listar_supervisoes();
		System.out.println("Digite o índice da supervisão que deseja excluir");
		int op = in.nextInt();
		in.nextLine();
		
		ArrayList<Supervisao> supervisoes = new ArrayList<Supervisao>();
		supervisoes = sistema.select_supervisoes();
		ObjectId id_supervisoes=supervisoes.get(op).getId();
		sistema.del_supervisao(id_supervisoes);
	}
	
	public static void remover_livro() {
		System.out.println("Livros:");
		listar_livros();
		System.out.println("Digite o índice do livro que deseja excluir");
		int op = in.nextInt();
		in.nextLine();
		
		ArrayList<Livro> livros = new ArrayList<Livro>();
		livros = sistema.select_livros();
		ObjectId id_livro=livros.get(op).getId();
		sistema.del_livro(id_livro);
		
		//deletar exemplares
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	public static void editar_exemplar() {
		
		System.out.println("Exemplares:");
		listar_exemplares();	
		
		System.out.println("Digite o índice do exemplar que deseja editar");
		
		int op = in.nextInt();
		in.nextLine();
		
		ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
		exemplares = sistema.select_exemplares();
		ObjectId id_exemplar=exemplares.get(op).getId();
		
		
		Exemplar exemplar = new Exemplar();
		exemplar.setId(id_exemplar);
		exemplar.setIsbn(exemplares.get(op).getIsbn());
		exemplar.setId_user_reserva(exemplares.get(op).getId_user_reserva());
		System.out.println(exemplar.toString());
		
		
		op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção para modificar:");
			System.out.println("0- Alterações feitas!");
			System.out.println("1- isbn");
			System.out.println("2- id_user_reserva");	
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				listar_livros();
				System.out.println("Digite o novo código ISBN do livro que deseja colocar no campo");	
				int isbn = in.nextInt();
				in.nextLine();
				exemplar.setIsbn(isbn);
				break;
			case 2:
				listar_usuarios();
				System.out.println("Digite o índice do usuario que deseja colocar no campo");
				System.out.println("Usuários:");
				listar_usuarios();
				
				int indice_usuario = in.nextInt();
				in.nextLine();
				
				ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
				usuarios = sistema.select_usuarios();
				ObjectId id_usuario=usuarios.get(indice_usuario).getId();				
				exemplar.setId_user_reserva(id_usuario);
			}
			
		}
		System.out.println(exemplar.toString());
		sistema.edit_exemplar(exemplar);
	}
	
	public static void editar_usuario() {
		System.out.println("Usuários:");
		listar_usuarios();		
		
		System.out.println("Digite o índice do usuario que deseja editar");
		
		int op = in.nextInt();
		in.nextLine();
				
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = sistema.select_usuarios();
		ObjectId id_usuario=usuarios.get(op).getId();
		
		
		Usuario usuario = new Usuario();
		usuario.setId(id_usuario);
		usuario.setNome(usuarios.get(op).getNome());
		usuario.setDocumento(usuarios.get(op).getDocumento());
		usuario.setTelefone(usuarios.get(op).getTelefone());
		usuario.setEndereco(usuarios.get(op).getEndereco());
		usuario.setCampus(usuarios.get(op).getCampus());
		usuario.setUniversidade(usuarios.get(op).getUniversidade());
		usuario.setId_categoria(usuarios.get(op).getId_categoria());
		
		op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção para modificar:");
			System.out.println("0- Alterações feitas!");
			System.out.println("1- nome");
			System.out.println("2- documento");	
			System.out.println("3- telefone");	
			System.out.println("4- endereco");	
			System.out.println("5- campus");	
			System.out.println("6- universidade");	
			System.out.println("7- Categoria");	


			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				System.out.println("Digite o nome que deseja colocar no campo");	
				String str = in.next();
				in.nextLine();
				usuario.setNome(str);
				break;
			case 2:
				System.out.println("Digite o numero do documento que deseja colocar no campo");	
				int n = in.nextInt();
				in.nextLine();;
				usuario.setDocumento(n);
				break;
			case 3:
				System.out.println("Digite o numero do telefone que deseja colocar no campo");	
				n = in.nextInt();
				in.nextLine();;
				usuario.setTelefone(n);
				break;
			case 4:
				System.out.println("Digite o endereco que deseja colocar no campo");	
				str = in.next();
				in.nextLine();
				usuario.setEndereco(str);
				break;
			case 5:
				System.out.println("Digite o campus que deseja colocar no campo");	
				str = in.next();
				in.nextLine();
				usuario.setCampus(str);
				break;
			case 6:
				System.out.println("Digite a universidade que deseja colocar no campo");	
				str = in.next();
				in.nextLine();
				usuario.setUniversidade(str);
				break;
			case 7:
				listar_categorias();
				System.out.println("Digite o índice da nova categoria do usuário");
				
				System.out.println("Categorias:");
				listar_categorias();
				
				n = in.nextInt();
				in.nextLine();
				
				ArrayList<Categoria> categorias = new ArrayList<Categoria>();
				categorias = sistema.select_categorias();
				ObjectId id_categoria = categorias.get(n).getId();
				
				usuario.setId_categoria(id_categoria);
				break;
			}
			
		}
		System.out.println(usuario.toString());
		sistema.edit_usuario(usuario);
	
	}

	public static void editar_funcionario() {
		System.out.println("Funcionarios:");
		listar_funcionarios();
			
		System.out.println("Digite o índice do funcionario que deseja editar");
			
		int op = in.nextInt();
		in.nextLine();
				
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios = sistema.select_funcionarios();
		ObjectId id_funcionario=funcionarios.get(op).getId();
		
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id_funcionario);
		funcionario.setNome(funcionarios.get(op).getNome());
		funcionario.setCtps(funcionarios.get(op).getCtps());
		funcionario.setTelefone(funcionarios.get(op).getTelefone());
		funcionario.setEndereco(funcionarios.get(op).getEndereco());
		funcionario.setSalario(funcionarios.get(op).getSalario());
		funcionario.setTurno(funcionarios.get(op).getTurno());
		funcionario.setFuncao(funcionarios.get(op).getFuncao());
		
		op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção para modificar:");
			System.out.println("0- Alterações feitas!");
			System.out.println("1- nome");
			System.out.println("2- ctps");	
			System.out.println("3- telefone");	
			System.out.println("4- endereco");	
			System.out.println("5- salario");	
			System.out.println("6- turno");	
			System.out.println("7- funcao");	


			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				System.out.println("Digite o nome que deseja colocar no campo");	
				String str = in.next();
				in.nextLine();
				funcionario.setNome(str);
				break;
			case 2:
				System.out.println("Digite o numero do documento CTPS que deseja colocar no campo");	
				int n = in.nextInt();
				in.nextLine();;
				funcionario.setCtps(n);
				break;
			case 3:
				System.out.println("Digite o numero do telefone que deseja colocar no campo");	
				n = in.nextInt();
				in.nextLine();;
				funcionario.setTelefone(n);
				break;
			case 4:
				System.out.println("Digite o endereco que deseja colocar no campo");	
				str = in.next();
				in.nextLine();
				funcionario.setEndereco(str);
				break;
			case 5:
				System.out.println("Digite o salario que deseja colocar no campo");	
				double d = in.nextDouble();
				in.nextLine();
				funcionario.setSalario(d);
				break;
			case 6:
				System.out.println("Digite o turno que deseja colocar no campo");	
				str = in.next();
				in.nextLine();
				funcionario.setTurno(str);
				break;
			case 7:
				listar_categorias();
				System.out.println("Digite a funcao que deseja colocar no campo");
				str = in.next();
				in.nextLine();
				funcionario.setFuncao(str);
				break;
			}
			
		}
		System.out.println(funcionario.toString());
		sistema.edit_funcionario(funcionario);
	
	}

	public static void editar_categoria() {
		System.out.println("Categoria:");
		listar_categorias();
		
		System.out.println("Digite o índice da categoria que deseja editar");
		
		int op = in.nextInt();
		in.nextLine();
				
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		categorias = sistema.select_categorias();
		ObjectId id_categoria=categorias.get(op).getId();		
		
		Categoria categoria = new Categoria();
		categoria.setId(id_categoria);
		categoria.setTipo(categorias.get(op).getTipo());
		categoria.setTarifa(categorias.get(op).getTarifa());
		categoria.setTempo_emprestimo(categorias.get(op).getTempo_emprestimo());
		
		op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção para modificar:");
			System.out.println("0- Alterações feitas!");
			System.out.println("1- tipo");
			System.out.println("2- tarifa");	
			System.out.println("3- tempo de empréstimo");
			
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				System.out.println("Digite o tipo de categoria que deseja colocar no campo");	
				String str= in.next();
				in.nextLine();
				categoria.setTipo(str);
				break;
			case 2:
				System.out.println("Digite a tarifa pra essa categoria que deseja colocar no campo");	
				double i = in.nextDouble();
				in.nextLine();
				categoria.setTarifa(i);
				break;
			case 3:
				System.out.println("Digite o novo tempo de emprestimo pra essa categoria que deseja colocar no campo");	
				int j = in.nextInt();
				in.nextLine();
				categoria.setTempo_emprestimo(j);
				break;
			}
			
		}
		System.out.println(categoria.toString());
		sistema.edit_categoria(categoria);
	}

	public static void editar_livro() {
		System.out.println("Livros:");
		listar_livros();
		
		int op;
		
		System.out.println("Digite o índice do livro que deseja editar");
		
		op = in.nextInt();
		in.nextLine();
		
		ArrayList<Livro> livros = new ArrayList<Livro>();
		livros = sistema.select_livros();
		ObjectId id_livro=livros.get(op).getId();		
		
		
		Livro livro = new Livro();
		livro.setId(id_livro);
		livro.setIsbn(livros.get(op).getIsbn());
		livro.setNome(livros.get(op).getNome());
		livro.setAutores(livros.get(op).getAutores());
		livro.setEditora(livros.get(op).getEditora());
		livro.setColecao(livros.get(op).getColecao());
		System.out.println(livro.toString());
		
		op = -1;
		while(op != 0) {
			System.out.println("Escolha uma opção para modificar:");
			System.out.println("0- Alterações feitas!");
			System.out.println("1- nome");
			System.out.println("2- autores");	
			System.out.println("3- Editoras");
			System.out.println("3- Coleção");
			System.out.println("4- ISBN");
			
			op = in.nextInt();
			in.nextLine();
		
			switch(op) {
			case 0: 
				break;
			case 1: 
				System.out.println("Digite o nome do livro que deseja colocar no campo");	
				String str= in.next();
				in.nextLine();
				livro.setNome(str);
				break;
			case 2:
				System.out.println("Digite os nomes dos autores que deseja colocar no campo");	
				str= in.next();
				in.nextLine();
				livro.setAutores(str);
				break;
			case 3:
				System.out.println("Digite o nome da editora que deseja colocar no campo");	
				str = in.next();
				in.nextLine();
				livro.setEditora(str);
				break;
			case 4:
				System.out.println("Digite o nome da coleção que deseja colocar no campo");
				str = in.next();
				in.nextLine();
				livro.setColecao(str);
				break;
			}
			
		}
		System.out.println(livro.toString());
		sistema.edit_livro(livro);	
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void listar_exemplares() {
		for(int i=0;i<sistema.select_exemplares().size();i++) {
			System.out.print(i);
			System.out.println("\t"+sistema.select_exemplares().get(i).toString());
		}
	}
	
	public static void listar_usuarios() {
		for(int i=0;i<sistema.select_usuarios().size();i++) {
			System.out.print(i);
			System.out.println("\t"+sistema.select_usuarios().get(i).toString());
		}
	}
	
	public static void listar_funcionarios() {
		for(int i=0;i<sistema.select_funcionarios().size();i++) {
			System.out.print(i);
			System.out.println("\t"+sistema.select_funcionarios().get(i).toString());
		}
	}
	
	public static void listar_categorias() {
		for(int i=0;i<sistema.select_categorias().size();i++) {
			System.out.print(i);
			System.out.println("\t"+sistema.select_categorias().get(i).toString());
		}
	}
	
	public static void listar_supervisoes() {
		for(int i=0;i<sistema.select_supervisoes().size();i++) {
			System.out.print(i);
			System.out.println("\t"+sistema.select_supervisoes().get(i).toString());
		}
	}
	
	public static void listar_livros() {
		for(int i=0;i<sistema.select_livros().size();i++) {
			System.out.print(i);
			System.out.println("\t"+sistema.select_livros().get(i).toString());
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
