//Caroline Castello Letizio, ra:059664

package sistemadebiblioteca;

public class Professor extends Usuario {

	private ExemplarBloqueado ex = new ExemplarBloqueado();

	public Professor() {
		super();
		this.setQuantidadeDiasPermitida(15);
		this.setQuantidadeLivrosPermitida(4);
	}
	
	public Professor(String nome, int id){
		super();
		this.setNome(nome);
		this.setId(id);
		//professor pode emprestar o livro por 15 dias
		this.setQuantidadeDiasPermitida(15);
		//professor pode pegar ate 4 livros
		this.setQuantidadeLivrosPermitida(4);
	}
	
	public boolean isProfessor(){
		return true;
	}
	
	public boolean isUsuario(){
		return false;
	}

	//bloqueia exemplar para emprestimo
	public void bloqueia(Exemplar ex2) {
		if (!(ex2.estaBloqueado())){
			ex2.setBloqueio(true);
			ex.adicionaListaBloqueados(ex2);
		}
	}

	//desbloqueia o exemplar para emprestimo:
	public void desbloqueia(Exemplar ex2){
		if (ex2.estaBloqueado()){
			ex2.setBloqueio(false);
			ex.removeListaBloqueados(ex2.getId());
		}
	}
}
