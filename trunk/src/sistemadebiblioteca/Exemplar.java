//Caroline Castello Letizio, ra:059664

package sistemadebiblioteca;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Exemplar {

	private int id;
	
	private String titulo;

	private boolean isEmprestado;

	private GregorianCalendar dtEmprestimo;
	
	private boolean bloqueado;

	
	public Exemplar(String titulo,int id,int numeroExemplar){
		this.setTitulo(titulo);
		this.setId(id);
		this.setEmprestado(false);
		this.setBloqueio(false);
	}
	
	public boolean estaBloqueado(){
		return this.bloqueado;
	}
	
	public boolean naoBloqueado(){
		return (!this.bloqueado);
	}
	
	public void setBloqueio(boolean bloqueio){
		this.bloqueado = bloqueio;
	}
	
	public boolean isDisponivel() {
		return (!this.isEmprestado());

	}

	public boolean isEmprestado() {
		return this.isEmprestado;
	}

	public boolean empresta() {
		if ( (isDisponivel()) && (naoBloqueado())) {
			this.setEmprestado(true);
			this.setDtEmprestimo(new GregorianCalendar());
			return true;
		}
		else 
			return false;
	}

	public boolean devolver(){
		if ( this.isEmprestado()) {
			this.setEmprestado(false);
			this.setDtEmprestimo(null);
			return true;
		}
		else 
			return false;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public GregorianCalendar getDtEmprestimo() {
		return dtEmprestimo;
	}

	public void setDtEmprestimo(GregorianCalendar dtEmprestimo) {
		this.dtEmprestimo = dtEmprestimo;
	}

	public String toString() {
		String st = new String();
		if ((isDisponivel()) && (naoBloqueado()))
			return( titulo + " disponivel");
		if (estaBloqueado())
			return (titulo + " bloqueado por professor");
		if (isEmprestado())
			st = " emprestado em " + this.printDate( this.getDtEmprestimo() );
		return( titulo + st );
	}
	
	private String printDate(GregorianCalendar gc){
		String str = new String();
		str = String.valueOf( gc.get(Calendar.DAY_OF_MONTH) );
		str = str + "/" + String.valueOf( gc.get(Calendar.MONTH) +1 );
		str = str + "/" + String.valueOf( gc.get(Calendar.YEAR) );
		return str;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmprestado(boolean isEmprestado) {
		this.isEmprestado = isEmprestado;
	}

}

