//Caroline Castello Letizio, ra:059664

package sistemadebiblioteca;

import java.util.ArrayList;
import java.util.List;

public class ExemplarBloqueado {

	private List<Exemplar> listaExemplaresBloq;

	public ExemplarBloqueado(){
		this.listaExemplaresBloq = new ArrayList<Exemplar>();
		Exemplar ex1 = new Exemplar(" ",-1,-1);
		this.listaExemplaresBloq.add(ex1);
	}
	
	public List<Exemplar> getListaExemplaresBloq() {
		return listaExemplaresBloq;
	}

	public void setListaExemplaresBloq(List<Exemplar> listaExemplaresBloq) {
		this.listaExemplaresBloq = listaExemplaresBloq;
	}
	
	public void removeListaBloqueados(int id){
		this.listaExemplaresBloq.remove(id);
	}
	
	public void adicionaListaBloqueados(Exemplar ex){
		this.listaExemplaresBloq.add(ex);
	}
	
}