package entidades;



public class Sala {
	private String id, tipo, finalidade, apelido;
	private int capacidade;
	private boolean disponibilidade;

	/**
	 * Classe Sala contrutor com Parametros.
	 * 
	 * @param id
	 *            Identificação da Sala.
	 * @param capacidade
	 *            Capacidade maxima da Sala.
	 * @param finalidade
	 *            Finalidade da Sala.
	 * @param tipo
	 *            Tipo da Sala.
	 */
	public Sala(String id, int capacidade, String finalidade, String tipo) {
		setId(id);
		setCapacidade(capacidade);
		setFinalidade(finalidade);
		setTipo(tipo);
	}

	/**
	 * Classe Sala contrutor com Parametros.
	 * 
	 * @param id
	 *            Identificação da Sala.
	 * @param capacidade
	 *            Capacidade maxima da Sala.
	 * @param finalidade
	 *            Finalidade da Sala.
	 * @param tipo
	 *            Tipo da Sala.
	 * @param apelido
	 *            Apelido dado a sala.
	 * 
	 */
	public Sala(String id, int capacidade, String finalidade, String tipo, String apelido) {
		setId(id);
		setCapacidade(capacidade);
		setFinalidade(finalidade);
		setTipo(tipo);
		setApelido(apelido);
	}

	/**
	 * Classe Sala contrutor com Parametros.
	 * 
	 * @param id
	 *            Identificação da Sala.
	 * @param capacidade
	 *            Capacidade maxima da Sala.
	 * @param finalidade
	 *            Finalidade da Sala.
	 * @param tipo
	 *            Tipo da Sala.
	 * @param apelido
	 *            Apelido dado a sala.
	 * @param aberto
	 *            se a sala esta disponivel para ser alocada.
	 */
	public Sala(String id, int capacidade, String finalidade, String tipo, String apelido, boolean aberto) {
		setId(id);
		setCapacidade(capacidade);
		setFinalidade(finalidade);
		setTipo(tipo);
		setApelido(apelido);
		setDisponibilidade(aberto);
	}

	public Sala() {

	}

	public String getId() {
		return id;
	}

	public void setId(String identificador) {
		this.id = identificador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public String getApelido() {
		if (apelido != null) {
			return apelido;
		} else
			return "";
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

}
