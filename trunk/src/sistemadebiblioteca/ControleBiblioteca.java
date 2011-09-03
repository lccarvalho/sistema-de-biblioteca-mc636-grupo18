//Caroline Castello Letizio, ra:059664
	package sistemadebiblioteca;
	
	import java.util.List;
	
	public class ControleBiblioteca {
	
		private CadastroUsuario cadUsuarios;
	
		private CadastroExemplar cadExemplar;
		
		private ExemplarBloqueado exBloq;
	
		public ControleBiblioteca(){
			this.cadExemplar = new CadastroExemplar();
			this.cadUsuarios = new CadastroUsuario();
			this.exBloq = new ExemplarBloqueado();
		}
	
		public void calcularPrazoDeDevolucao(Usuario user){
			if( ( user != null ) && ( !user.getExemplarEmprestado().isEmpty() ) ){ 
				Exemplar ex = user.getExemplarEmprestado().get(0);
				if( ex != null )
					System.out.println(ex.toString());
			}
		}
	
		public boolean devolverExemplar(int idExemplar, int idUser, int diasEmprestado){
			//verifica se existe o exemplar
			Exemplar ex = this.buscaExemplar(idExemplar);
			if( ex == null){
				System.out.println("Nao existe exemplar com id especificado.");
				return false;
			}
			ex.devolver();
			//verifica se existe o usuario
			Usuario user = this.buscarUsuario(idUser);
			if( ( user != null ) && ( user.estaComLivroEmprestado() ) ){
				//pega o id do exemplar emprestado pelo usuario
				int aux = user.getExemplarEmprestado().get(0).getId();
				//compara com o id do exemplar passado pelo usuario do sistema
				if( aux != idExemplar){
					System.out.println( user.getNome() + " nao esta com o exemplar citado.");
					return false;
				}
			}
			else{
				System.out.println("Ou o id do usuario esta errado ou ele nao esta com exemplar emprestado.");
			}			
			// atualiza o estado de exemplar e usuario
			boolean res = user.devolveExemplar();
			if( (res) && ( user.isAtrasado(diasEmprestado) ) ){
				int tempoPermitido = user.getQuantidadeDiasPermitida();
				int qtdDiasAtrasado = diasEmprestado - tempoPermitido;
				System.out.println(user.getNome()+ " esta "+qtdDiasAtrasado+" dias atrasado.");
			}
			else{
				if( res )
					System.out.println("Devolucao do exemplar "+ex.getTitulo()+" id = "+ex.getId());
			}
			return res;
		}
	
		public void listaExemplarEmprestado(int idUsuario){
			Usuario user = this.buscarUsuario( idUsuario );
			if( user != null ){ 
				int i = 0;
				while((user.estaComLivroEmprestado()) && (i <= user.quantidadeLivrosOriginal() - user.getQuantidadeLivrosPermitida())){
					Exemplar ex = user.getExemplarEmprestado().get(i);
					i++;
					System.out.println(ex.toString());
				}
			}
			else{
				System.out.println("O usuario nao tem exemplar");
			}
		}
	
		public boolean emprestarExemplar(int idExemplar, int  idUsuario) {
			//verifica se o exemplar existe
			boolean aptoAEmprestar;
			Exemplar ex = this.buscaExemplar( idExemplar );
			Exemplar ex2 = this.buscaExemplarBloqueado(idExemplar);
			if (ex2 != null)
				System.out.println("Exemplar bloqueado.");
			if( (ex != null) && (ex2 == null))
				aptoAEmprestar = ex.empresta();
			else{
				System.out.println("Nao foi possivel achar o exemplar especificado.");
				return false;
			}
	
			//se ele existe e realmente nao foi emprestado, atualiza os dados do usuario
			if( aptoAEmprestar ){
				Usuario user = this.buscarUsuario(idUsuario);
				if( user != null){
					boolean resultado = user.emprestaExemplar(ex);
					if( resultado ){
						System.out.println("Emprestimo do exemplar "+ex.getTitulo()+" id "+ex.getId()+" pelo(a) "+user.getNome());
						//System.out.println("Quant livros depois = " +user.getQuantidadeLivrosPermitida());
					}
					else
						System.out.println(user.getNome()+" ja emprestou outros livros.");	
					return resultado;
				}
				else{
					System.out.println("O id do usuario especificado esta incorreto.");
					return false;
				}
			}
			else{
				System.out.println("O exemplar esta indisponivel");
				return false;
			}
		}
	
		public CadastroUsuario getCadUsuarios() {
			return cadUsuarios;
		}
	
		public void setCadUsuarios(CadastroUsuario cadUsuarios) {
			this.cadUsuarios = cadUsuarios;
		}
	
		public CadastroExemplar getCadExemplar() {
			return cadExemplar;
		}
	
		public void setCadExemplar(CadastroExemplar cadExemplar) {
			this.cadExemplar = cadExemplar;
		}
	
		public int getQuantidadeDeUsuarios(){
			return this.getCadUsuarios().getListaUsuarios().size();
		}
	
		public Usuario buscarUsuario(int id){
			List<Usuario> listaUsuario = this.getCadUsuarios().getListaUsuarios();
			if(  listaUsuario != null )
				for(int i = 0; i < listaUsuario.size(); i++){
					Usuario user = listaUsuario.get(i);
					if( id == user.getId() ){
						return user;
					}
				}
			return null;
		}
		
		public Exemplar buscaExemplar(int id){
			List<Exemplar> listaExemplar = this.getCadExemplar().getListaExemplares();
			if(  listaExemplar != null )
				for(int i = 0; i < listaExemplar.size(); i++){
					Exemplar ex = listaExemplar.get(i);
					if( id == ex.getId() ){
						return ex;
					}
				}
			return null;
	
		}
		
		public ExemplarBloqueado getExBloq() {
			return exBloq;
		}
	
		public void setExBloq(ExemplarBloqueado exBloq) {
			this.exBloq = exBloq;
		}
		
		public Exemplar buscaExemplarBloqueado(int id){
			List<Exemplar> listaExemplarBloq = this.getExBloq().getListaExemplaresBloq();
			if(  listaExemplarBloq != null )
				for(int i = 0; i < listaExemplarBloq.size(); i++){
					Exemplar ex = listaExemplarBloq.get(i);
					if( id == ex.getId() ){
						return ex;
					}
				}
			return null;
		}
	
		public int getQuantidadeDeExemplares(){
			return this.getCadExemplar().getListaExemplares().size();
		}
	
	}
	
