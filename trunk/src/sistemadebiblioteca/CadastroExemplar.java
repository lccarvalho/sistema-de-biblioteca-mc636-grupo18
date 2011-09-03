//Caroline Castello Letizio, ra:059664

package sistemadebiblioteca;

import java.util.ArrayList;
import java.util.List;

public class CadastroExemplar {

	private List<Exemplar> listaExemplares;
	
	public CadastroExemplar(){
		Exemplar ex1 = new Exemplar("Dom Casmurro",0,0);
		Exemplar ex2 = new Exemplar("Bras Cubas",1,0);
		Exemplar ex3 = new Exemplar("Bras Cubas",2,1);
		Exemplar ex4 = new Exemplar("Primo Basilio",3,5);
		Exemplar ex5 = new Exemplar("Macunaima",4,8);
		
		this.listaExemplares = new ArrayList<Exemplar>();
		this.listaExemplares.add(ex1);
		this.listaExemplares.add(ex2);
		this.listaExemplares.add(ex3);
		this.listaExemplares.add(ex4);
		this.listaExemplares.add(ex5);
		
		
	}

	
	public List<Exemplar> getListaExemplares() {
		return listaExemplares;
	}

	public void setListaExemplares(List<Exemplar> listaExemplares) {
		this.listaExemplares = listaExemplares;
	}
	
}
