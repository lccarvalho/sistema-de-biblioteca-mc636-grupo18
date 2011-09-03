//Caroline Castello Letizio, ra:059664

package sistemadebiblioteca;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String nome;

	private int id;

	private List<Exemplar> exemplarEmprestado;

	private int quantidadeDiasPermitida;
	
	private int quantidadeLivrosPermitida;

	public Usuario(){
		this.exemplarEmprestado = new ArrayList<Exemplar>();
		//usuario normal pode emprestar o livro por 3 dias
		this.setQuantidadeDiasPermitida(3);
		//usuario normal pode pegar ate 3 livros
		this.setQuantidadeLivrosPermitida(2);
	}


	public Usuario(String nome, int id){
		this.setNome(nome);
		this.setId( id );
		this.exemplarEmprestado = new ArrayList<Exemplar>();
		//usuario normal pode emprestar o livro por 3 dias
		this.setQuantidadeDiasPermitida(3);
		//usuario normal pode pegar ate 3 livros
		this.setQuantidadeLivrosPermitida(2);
	}

	public boolean estaComLivroEmprestado(){
		if((this.getExemplarEmprestado() != null) && 
				( !this.getExemplarEmprestado().isEmpty()))
			return true;
		else
			return false;
	}


	public boolean emprestaExemplar(Exemplar exemplar) {
		if( ( exemplar != null ) && (this.isAptoAEmprestar())) {	
			this.diminuiQuantidadeLivros();
			this.exemplarEmprestado.add( exemplar );
			return true;
		}
		else{
			return false;
		}
	}

	public boolean devolveExemplar() {
		if( this.estaComLivroEmprestado() ){
			this.exemplarEmprestado.remove(0);
			this.aumentaQuantidadeLivros();
			return true;
		}
		else
			return false;

	}

	public String getNome(){
		return this.nome;
	}

	public void setNome(String name){
		this.nome = name;
	}

	public int getId(){
		return this.id;
	}

	public void setId(int x){
		this.id = x;
	}


	public boolean isAtrasado(int diasEmprestado){
		if( diasEmprestado > this.getQuantidadeDiasPermitida() )
			return true;
		else
			return false;
	}

	public boolean isAptoAEmprestar(){
		if((this.getExemplarEmprestado().isEmpty()) || (this.getQuantidadeLivrosPermitida() != 0))
			return true;
		else
			return false;
	}

	public boolean isProfessor(){
		return false;
	}

	public boolean isAluno(){
		return false;
	}

	public boolean isUsuario(){
		return true;
	}

	public List<Exemplar> getExemplarEmprestado() {
		return exemplarEmprestado;
	}

	public void setExemplarEmprestado(List<Exemplar> exemplarEmprestado) {
		this.exemplarEmprestado = exemplarEmprestado;
	}

	public int getQuantidadeDiasPermitida() {
		return quantidadeDiasPermitida;
	}

	public void setQuantidadeDiasPermitida(int quantidadeDiasPermitida) {
		this.quantidadeDiasPermitida = quantidadeDiasPermitida;
	}
	
	public int getQuantidadeLivrosPermitida() {
		return quantidadeLivrosPermitida;
	}

	public void setQuantidadeLivrosPermitida(int quantidadeLivrosPermitida) {
		this.quantidadeLivrosPermitida = quantidadeLivrosPermitida;
	}
	
	//quantidade original de quantos livros pode pegar emprestado
	public int quantidadeLivrosOriginal() {
		if (this.isUsuario())
			return 1;
		if (this.isAluno())
			return 2;
		if (this.isProfessor())
			return 3;
		return 0;
	}
	
	//diminui a quantidade de livros que o usuario pode pegar
	public void diminuiQuantidadeLivros(){
		if (this.getQuantidadeLivrosPermitida() != 0){
			this.setQuantidadeLivrosPermitida((this.getQuantidadeLivrosPermitida() - 1));
		}
		else
			setQuantidadeLivrosPermitida(0);
	}
	
	//aumenta a quantidade de livros que o usuario pode pegar
	public void aumentaQuantidadeLivros(){
		this.setQuantidadeLivrosPermitida((this.getQuantidadeLivrosPermitida() + 1));
	}

}

