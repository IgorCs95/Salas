package manipuladores;

import java.util.ArrayList;

import Factory.PercistenciaFabrica;
import Factory.PercistenciaFactory;
import Factory.PercistenciaLoja;
import PercistenciasTipos.Percistencias;
import Persistencia.PersistenciaXml;
import entidades.Alocar;
import entidades.Evento;
import entidades.Sala;

/**Classe responsavel por manipular a entrada ou saida de dados.
 * @author Igor
 */
public class ManipulacaoXml {
	
	PercistenciaFactory fabrica = new PercistenciaFabrica();
	
	PercistenciaLoja loja = new PercistenciaLoja(fabrica);
	
	Percistencias<Evento> eventos = loja.pedirObjeto("Evento");
	Percistencias<Sala> salas = loja.pedirObjeto("Sala");
	Percistencias<Alocar> alocar = loja.pedirObjeto("Alocar");

	private static ManipulacaoXml xml;
	
	private  ManipulacaoXml() {
	}

	/**Salvar um objeto sala pasado como parametro deste metodo no arquivo Salas.xml 
	 * @param sala sala que devera ser salva
	 */
	public void salvarSala(Sala sala) {
		salas.salvar(sala);
		
		
	}
	
	/**Remover um objeto sala pasado a id deste objeto como parametro, do arquivo Salas.xml 
	 * @param idSala  id da sala a ser removida
	 */
	public void removerSala(String idSala){
		salas.excluir(idSala);
		alocar.excluirAlocarSala(idSala);
	}

	
	/** Salvar um objeto evento pasado como parametro deste metodo no arquivo Eventos.xml 
	 * @param evento o evento que deve ser salvo 
	 */
	public void salvarEvento(Evento evento) {
		eventos.salvar(evento);
	}
	
	/**Remove o Evento salvo no arquivo Xml, faz a busca no arrayList evento
	 * @param idEvento id do evento a ser apagado
	 */
	public void removerEvento(String idEvento){
		eventos.excluir(idEvento);
		alocar.excluirAlocarEvento(idEvento);
	}

	/**Salva a alocacao do evento na sala
	 * @param aloca o objeto "alocar"a ser alocado 
	 */
	public void salvarAlocar(Alocar aloca) {
		alocar.salvar(aloca);
		
	}
	
	/** remove o objeto Alocar de lista de alocações assim desalocando o evento.
	 * @param idEvento identificação do objeto Evento
	 */
	public void removerAlocar(String idEvento){
		alocar.excluirAlocarEvento(idEvento);
	}
	

	/** 
	 * Remove todos os dados dentro dos arquivos "Salas.xml","Eventos.xml" e "Alocar.xml".
	 */
	public void zerar() {
		salas.zerar();
		eventos.zerar();
		alocar.zerar();
	}

	/** Recupera o Objeto Sala dentro da Lista de salas que possua o mesmo "id" passado como parametro. 
	 * @param idSala para procurar uma sala contenha esse id
	 * @return Sala a sala buscada.
	 */
	public Sala recuperarSala(String idSala) {
		return salas.recuperar(idSala);

	}
	/** Recupera o Objeto Evento dentro da Lista de eventos que possua o mesmo "id" passado como parametro. 
	 * @param idEvento para recuperar o evento que tenha esse id.
	 * @return Evento o evento buscado.
	 */
	public Evento recuperarEvento(String idEvento) {
		return eventos.recuperar(idEvento);
	}
	
	
	/**Retorna um ArrayList contendo todos os eventos cadastrados no arquivo Eventos.xml.
	 * @return Retorna a lista de todos os eventos ArrayList.
	 */
	public ArrayList<Evento> todosEventos(){
		return eventos.todos();
	}
	/**Retorna um ArrayList contendo todas as salas cadastradas no arquivo Sala.xml.
	 * @return Salas lista de todas as salas no ArrayList.
	 */
	public ArrayList<Sala> todasSalas(){
		return salas.todos();
	}
	
	/**Retorna um ArrayList contendo todos os alocar que contenten todos os eventos alocados em salas cadastrados no arquivo Alocar.xml.
	 * @return Alocaçoes Retorna a lista de todas as alocacoes no ArrayList.
	 */
	public ArrayList<Alocar> todasAlocacoes(){
		return alocar.todos();
	}
	
	public static ManipulacaoXml getInstace(){
		if(xml!=null){
			return xml;
		}else
			synchronized (ManipulacaoXml.class) {
				if(xml!=null){
					return xml;
				}else
					return new ManipulacaoXml();
			}
	}
}
