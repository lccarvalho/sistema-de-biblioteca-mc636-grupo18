//Caroline Castello Letizio, ra:059664

package sistemadebiblioteca;

import java.util.ArrayList;
import java.util.List;

public class CadastroUsuario {

	private List<Usuario> listaUsuarios;
	
	public CadastroUsuario(){
		
		Usuario user1 = new Usuario("Maria",0);
		Usuario user2 = new Usuario("Mariana",1);
		Aluno aluno1 = new Aluno("Jose",2);
		Aluno aluno2 = new Aluno("Joao",3);
		Professor prof1 = new Professor("Ana",4);
		Professor prof2 = new Professor("Andre",5);
		
		this.listaUsuarios = new ArrayList<Usuario>();
		
		this.listaUsuarios.add(user1);
		this.listaUsuarios.add(user2);
		this.listaUsuarios.add(aluno1);
		this.listaUsuarios.add(aluno2);
		this.listaUsuarios.add(prof1);
		this.listaUsuarios.add(prof2);
		
	}
	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
}
