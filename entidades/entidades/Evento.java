package entidades;

import java.sql.Date;

/**
 * @author igor
 *
 */
public class Evento {

	private String id, nome, area, contato;

	private long inicio, fim;

	private int repeticao;

	public Evento() {
	}

	/**
	 * Contrutor com Parametros.
	 * 
	 * @param id
	 *            Identificação do Evento.
	 * @param nome
	 *            nome da Evento.
	 * @param inicio
	 *            data de Inicio do Evento
	 * @param fim
	 *            data final do Evento.
	 * @param area
	 *            Area que o Evento se categorisa.
	 * @param contato
	 *            Contato da Pessa Responsavel.
	 * @param repeticoes
	 *            Numer de repetições semanais.
	 */
	public Evento(String id, String nome, long inicio, long fim, String area, String contato, int repeticoes) {
		setId(id);
		setNome(nome);
		setInicio(inicio);
		setFim(fim);
		setArea(area);
		setContato(contato);
		setRepeticao(repeticoes);

	}

	/**
	 * Contrutor com Parametros.
	 * 
	 * @param id
	 *            Identificação do Evento.
	 * @param nome
	 *            nome da Evento.
	 * @param inicio
	 *            data de Inicio do Evento
	 * @param fim
	 *            data final do Evento.
	 * @param area
	 *            Area que o Evento se categorisa.
	 * @param contato
	 *            Contato da Pessa Responsavel.
	 */
	public Evento(String id, String nome, long inicio, long fim, String area, String contato) {
		setId(id);
		setNome(nome);
		setInicio(inicio);
		setFim(fim);
		setArea(area);
		setContato(contato);
	}

	/**
	 * @return retorna o id do evento
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public long getInicio() {
		return inicio;
	}

	public void setInicio(long inicio) {
		this.inicio = inicio;
	}

	public long getFim() {
		return fim;
	}

	public void setFim(long fim) {
		this.fim = fim;
	}

	public int getRepeticao() {
		return repeticao;
	}

	public void setRepeticao(int repeticao) {
		this.repeticao = repeticao;
	}

}
