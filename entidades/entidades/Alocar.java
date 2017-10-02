package entidades;


public class Alocar {

	private String idEvento, idSala;

	/**
	 * Construtor sem parmetros
	 */
	public Alocar() {
	}

	/**
	 * Contrutor com parametros
	 * 
	 * @param idSala
	 *            identificação dasala onde o evento esta alocado.
	 * @param idEvento
	 *            identificação do evento a ser alocado.
	 */
	public Alocar(String idSala, String idEvento) {
		setIdEvento(idEvento);
		setIdSala(idSala);

	}

	/**
	 * Metodo responsavel por retornar a identificação.
	 * 
	 * @return retorna o id de evento
	 */
	public String getIdEvento() {
		return idEvento;
	}

	/**
	 * Mudar o valor da Identificação do Evento.
	 * 
	 * @param idEvento
	 *            id do novo Evento ha ser Alocado.
	 */
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	/**
	 * Metodo responsavel por retornal a identificação da sala alocada.
	 * 
	 * @return Id da Sala Alocada.
	 */
	public String getIdSala() {
		return idSala;
	}

	/**
	 * Mudar o valor da Identificação da sala alocada.
	 * 
	 * @param idSala
	 *            novo id da nova sala ha ser alocada.
	 */
	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}

}
