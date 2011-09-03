//Caroline Castello Letizio, ra:059664

package sistemadebiblioteca;

public class Aluno extends Usuario {

	public Aluno() {
		super();
		this.setQuantidadeDiasPermitida(7);
		this.setQuantidadeLivrosPermitida(3);
	}
	
	public Aluno(String nome, int id){
		super();
		this.setNome(nome);
		this.setId( id );
		//aluno pode emprestar o livro por 7 dias
		this.setQuantidadeDiasPermitida(7);
		//aluno pode pegar ate 3 livros
		this.setQuantidadeLivrosPermitida(3);
	}
	
	
	public boolean isAluno(){
		return true;
	}
 
	public boolean isUsuario(){
		return false;
	}
}
 
