package manipuladores;

import entidades.Sala;
import execoes.RoomsAllocationException;
import validacaoDeSalas.ValidarSalas;

/**
 * @author igor
 *
 */
public class ManipuladorSalas {

	ManipulacaoXml salasDao;

	/**
	 * Construtor da classe.
	 * 
	 * @param xml
	 *            parametro de tipoManipulacaoXml.
	 */
	public ManipuladorSalas(ManipulacaoXml xml) {
		salasDao = xml;
	}

	/**
	 * Adiciona uma sala ao sistema. O usuário pode adicionar diferentes tipos
	 * de salas. Cada sala tem uma capacidade física. Cada sala possui uma
	 * identificação única: abreviação do prédio seguido de um número (Exemplo:
	 * CN-216).
	 *
	 * @param id
	 *            Identificador (unico) da Sala.
	 * @param capacidade
	 *            Capacidade da Sala.
	 * @param finalidade
	 *            Finalidade da Sala.
	 * @param tipo
	 *            Tipo da Sala.
	 * 
	 * @throws RoomsAllocationException
	 *             erro equivalente
	 */
	public void adicionarSala(String id, int capacidade, String finalidade, String tipo)
			throws RoomsAllocationException {

		try {
			ValidarSalas.condicoesSala(id, capacidade, finalidade, tipo, "");

			if (salasDao.recuperarSala(id) != null) {
				throw new RoomsAllocationException("Ja existe sala com esta identificacao.");
			}

			salasDao.salvarSala(new Sala(id, capacidade, finalidade, tipo));

		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	/**
	 * 
	 * Adiciona uma sala ao sistema. O usuário pode adicionar diferentes tipos
	 * de salas. Cada sala tem uma capacidade física. Cada sala possui uma
	 * identificação única: abreviação do prédio seguido de um número (Exemplo:
	 * CN-216). Algumas salas possuem apelidos (Exemplo: Auditório Mário
	 * Toyotaro).
	 * 
	 * @param id
	 *            Identificador (unico) da Sala.
	 * @param capacidade
	 *            Capacidade da Sala.
	 * @param finalidade
	 *            Finalidade da Sala.
	 * @param tipo
	 *            Tipo da Sala.
	 * @param apelido
	 *            Apelido da sala.
	 * @throws RoomsAllocationException
	 *             erro equivalente
	 */
	public void adicionarSala(String id, int capacidade, String finalidade, String tipo, String apelido)
			throws RoomsAllocationException {

		try {
			ValidarSalas.condicoesSala(id, capacidade, finalidade, tipo, apelido);
			if (salasDao.recuperarSala(id) != null) {
				throw new RoomsAllocationException("Ja existe sala com esta identificacao.");
			}
			salasDao.salvarSala(new Sala(id, capacidade, finalidade, tipo, apelido));

		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	/**
	 * Adiciona uma sala ao sistema. O usuário pode adicionar diferentes tipos
	 * de salas. Cada sala tem uma capacidade física. Cada sala possui uma
	 * identificação única: abreviação do prédio seguido de um número (Exemplo:
	 * CN-216). Algumas salas possuem apelidos (Exemplo: Auditório Mário
	 * Toyotaro).
	 *
	 * @param id
	 *            Identificador (unico) da Sala.
	 * @param capacidade
	 *            Capacidade da Sala.
	 * @param finalidade
	 *            Finalidade da Sala.
	 * @param tipo
	 *            Tipo da Sala.
	 * @param apelido
	 *            Apelido da sala.
	 * @param aberto
	 *            Indica se a sala esta aberta.
	 * 
	 * @throws RoomsAllocationException
	 *             erro equivalente
	 */
	public void adicionarSala(String id, int capacidade, String finalidade, String tipo, String apelido, boolean aberto)
			throws RoomsAllocationException {

		try {
			ValidarSalas.condicoesSala(id, capacidade, finalidade, tipo, apelido);
			if (salasDao.recuperarSala(id) != null) {
				throw new RoomsAllocationException("Ja existe sala com esta identificacao.");
			}
			salasDao.salvarSala(new Sala(id, capacidade, finalidade, tipo, apelido, aberto));

		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	/**
	 * Recupera e retorna um atributo da sala.
	 *
	 * @param idSala
	 *            Identificador da sala.
	 * @param atributo
	 *            Atributo a recuperar.
	 * @return O atributo buscado.
	 * 
	 * @throws RoomsAllocationException
	 *             erro equivalente
	 */
	public String getAtributoSala(String idSala, String atributo) throws RoomsAllocationException {
		Sala evento = salasDao.recuperarSala(idSala);

		if (evento == null) {
			throw new RoomsAllocationException("Sala não existe.");
		}

		switch (atributo) {
		case "id":
			return evento.getId();

		case "finalidade":
			return evento.getFinalidade();

		case "tipo":
			return evento.getTipo();

		case "apelido":
			return evento.getApelido();

		case "capacidade":
			return String.valueOf(evento.getCapacidade());

		case "disponibilidade":
			return String.valueOf(evento.isDisponibilidade());
		default:
			throw new RoomsAllocationException("Atributo nao existe.");

		}
	}

}
