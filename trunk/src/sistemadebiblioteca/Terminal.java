//Caroline Castello Letizio, ra:059664

package sistemadebiblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Terminal {

	private ControleBiblioteca controlBib;


	public Terminal(){
		this.setControlBib(new ControleBiblioteca());
	}

	/**
	 * @param args
	 */
	public void iniciarSistema() {

		int idUsuario;
		int op;
		System.out.println("\nAtendimento aos usuarios");
		try {
			idUsuario = this.getIDUsuario();

			// seleciona usuario 
			while( idUsuario > -1) {
				//System.out.println("Bem-vindo "+usuario.getNome());
				//se o usuario for um professor, aparece as opcoes de bloqueio e desbloqueio de livro
				Usuario u = controlBib.buscarUsuario(idUsuario);
				if (u.isProfessor()){
					op = this.getOperacao2();
					this.executaOperacao2(idUsuario, op);
					idUsuario = this.getIDUsuario();
				}
				else{
					op = this.getOperacao(); // seleciona operacao
					this.executaOperacao(idUsuario,op); // e executa
					idUsuario = this.getIDUsuario();
				}
			}
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		} 


	}

	/**
	 * Esse metodo pega qual foi a operacao selecionada pelo usuario
	 * e retorna o codigo dessa operacao
	 * @param usuario
	 * @return
	 * @throws IOException
	 */
	private int getOperacao()  throws IOException {
		int op=getInt("operacao:\n1=empresta, 2=devolve, 3=ver exemplar emprestado, 4=sair",1,4);
		return(op);
	}
	
	private int getOperacao2()  throws IOException{
		int op=getInt("operacao:\n1=empresta, 2=devolve, 3=ver exemplar emprestado, 4=sair, 5=bloqueia, 6=desbloqueia",1,6);
		return(op);
	}

	/**
	 * Esse metodo retorna o id, que foi digitado pelo usuario do sistema.
	 * @return
	 * @throws IOException
	 */
	private int getIDUsuario() throws IOException {
		ControleBiblioteca controlBib = this.getControlBib();
		int qtdUsuarios = controlBib.getQuantidadeDeUsuarios();
		int id = getInt("id do usuario ( ou digite -1 para terminar)",-1, (qtdUsuarios -1));
		return id;
	}

	/**
	 * retorna um objeto Exemplar a partir do id selecionado pelo usuario 
	 * @return
	 * @throws IOException
	 */
	private int getIDExemplar() throws IOException {
		ControleBiblioteca controlBib = this.getControlBib();
		int qtdExemplares = controlBib.getQuantidadeDeExemplares();
		int id = this.getInt( "o id do exemplar" , -1 , (qtdExemplares-1) );
		return id;
	}

	private void executaOperacao(int idUsuario, int op)	throws IOException {
		ControleBiblioteca controlBib = this.getControlBib();
		if(op==3){
			controlBib.listaExemplarEmprestado(idUsuario);
		}
		else{
			if(op==4){
				System.out.println("Obrigado por usar o sistema de bibliotecas.");
				System.exit(0);
			}

			else {
				boolean r = false;
				int idExemplar = this.getIDExemplar();
				switch(op) { 
				case 1: 
					r = controlBib.emprestarExemplar(idExemplar, idUsuario);
					if(r)
						System.out.println("Exemplar emprestado com sucesso!");
					else
						System.out.println("Nao foi possivel emprestar o exemplar.");
					break;
				case 2: 
					int diasEmprestado = this.getTempoDeLocacao();
					if( diasEmprestado < 0){
						System.err.println("Valor invalido");
						break;
					}

					r = controlBib.devolverExemplar(idExemplar, idUsuario, diasEmprestado);

					if( r )
						System.out.println("Exemplar devolvido com sucesso.");
					else
						System.out.println("Nao foi possivel devolver o exemplar.");
					break;
				default: System.err.println("Opcao invalida");
				}
			}
		}
	}
	
	private void executaOperacao2(int idUsuario, int op) 	throws IOException {
		ControleBiblioteca controlBib = this.getControlBib();
		if(op==3){
			controlBib.listaExemplarEmprestado(idUsuario);
		}
		else{
			if(op==4){
				System.out.println("Obrigado por usar o sistema de bibliotecas.");
				System.exit(0);
			}

			else {
				boolean r = false;
				int idExemplar = this.getIDExemplar();
				switch(op) { 
				case 1: 
					r = controlBib.emprestarExemplar(idExemplar, idUsuario);
					if(r)
						System.out.println("Exemplar emprestado com sucesso!");
					else
						System.out.println("Nao foi possivel emprestar o exemplar.");
					break;
				case 2: 
					int diasEmprestado = this.getTempoDeLocacao();
					if( diasEmprestado < 0){
						System.err.println("Valor invalido");
						break;
					}

					r = controlBib.devolverExemplar(idExemplar, idUsuario, diasEmprestado);

					if( r )
						System.out.println("Exemplar devolvido com sucesso.");
					else
						System.out.println("Nao foi possivel devolver o exemplar.");
					break;
				case 5:
					Usuario t = controlBib.buscarUsuario(idUsuario);
					if (t.isProfessor()){
						Professor p = (Professor)t;
						p.bloqueia(controlBib.buscaExemplar(idExemplar));
					}
				case 6:
					Usuario v = controlBib.buscarUsuario(idUsuario);
					if (v.isProfessor()){
						Professor l = (Professor) v;
						l.desbloqueia(controlBib.buscaExemplar(idExemplar));
					}
				default: System.err.println("Opcao invalida");
				}
			}
		}
	}

	private int getInt(String str, int de, int ate)	throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader (System.in));
		StreamTokenizer st = new StreamTokenizer(r);

		do {
			System.out.println("Entre com "+str);
			try {st.nextToken();}
			catch (IOException e) {
				System.err.println("Erro na leitura do teclado");
				return(0);
			}
		} while ( st.ttype != StreamTokenizer.TT_NUMBER ||
				st.nval< de || st.nval > ate );
		return((int)st.nval);
	}

	protected ControleBiblioteca getControlBib() {
		return controlBib;
	}

	protected void setControlBib(ControleBiblioteca controlBib) {
		this.controlBib = controlBib;
	}

	protected int getTempoDeLocacao() throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader (System.in));
		StreamTokenizer st = new StreamTokenizer(r);
		System.out.println("Por quantos dias o exemplar foi emprestado?");
		while(st.ttype != StreamTokenizer.TT_NUMBER ){
			st.nextToken();
		}
		return ((int)st.nval);
	}

}
