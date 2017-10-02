package manipuladores;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import entidades.Evento;
import execoes.RoomsAllocationException;
import validacao.Validacao;
import validacaoDeEventos.ValidarEvento;

public class ManipuladorEventos {

	ManipulacaoXml eventosDao;

	Validacao validar;

	/**
	 * Construtor da classe
	 * 
	 * @param xml
	 *            deve ser passado como parametro um objeto do tipo
	 *            ManipulacaoXml
	 */
	public ManipuladorEventos(ManipulacaoXml xml) {
		eventosDao = xml;
		validar = new Validacao(xml);
	}

	/**
	 * O usuário pode adicionar eventos ao sistema. Cada evento possui um nome,
	 * datas de inicio e fim, um nome para contato e um numero de repetiçoes
	 * semanais
	 * 
	 * @param id
	 *            Identificador (unico) do evento.
	 * @param nome
	 *            Nome do evento.
	 * @param inicio
	 *            Data de inicio do evento.
	 * @param fim
	 *            Data de fim do evento.
	 * @param area
	 *            Area do conhecimento a qual o evento pertence.
	 * @param contato
	 *            Contato do responsavel pelo evento.
	 * @param repeticoes
	 *            Numero de repeticoes do evento.
	 * @throws RoomsAllocationException
	 *             erro equivalente.
	 */
	public void adicionarEvento(String id, String nome, String inicio, String fim, String area, String contato,
			int repeticoes) throws RoomsAllocationException {

		try {
			ValidarEvento.finalidadeEvento(id, nome, inicio, fim, area, contato, repeticoes);
			if (eventosDao.recuperarEvento(id) != null) {
				throw new RoomsAllocationException("Ja existe evento com esta identificacao.");
			}
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Date horaInicio = null;
		Date horaFim = null;

		try {
			ValidarEvento.validarDatasEventos(inicio, fim);

			horaInicio = new Date(formato.parse(inicio).getTime());
			horaFim = new Date(formato.parse(fim).getTime());

			eventosDao.salvarEvento(new Evento(id, nome, horaInicio, horaFim, area, contato, repeticoes));

		} catch (ParseException e) {
			throw new RoomsAllocationException(e.getMessage());
		}

	}

	/**
	 * O usuário pode adicionar eventos ao sistema. Cada evento possui um nome,
	 * datas de inicio e fim e um nome para contato.
	 * 
	 * @param id
	 *            Identificador (unico) do evento.
	 * @param nome
	 *            Nome do evento.
	 * @param inicio
	 *            Data de inicio do evento.
	 * @param fim
	 *            Data de fim do evento.
	 * @param area
	 *            Area do conhecimento a qual o evento pertence.
	 * @param contato
	 *            Contato do responsavel pelo evento.
	 * 
	 * @throws RoomsAllocationException
	 *             erro equivalente
	 */
	public void adicionarEvento(String id, String nome, String inicio, String fim, String area, String contato)
			throws RoomsAllocationException {

		try {
			ValidarEvento.finalidadeEvento(id, nome, inicio, fim, area, contato, 0);
			if (eventosDao.recuperarEvento(id) != null) {
				throw new RoomsAllocationException("Ja existe evento com esta identificacao.");
			}
		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Date horaInicio = null;
		Date horaFim = null;

		try {
			ValidarEvento.validarDatasEventos(inicio, fim);

			horaInicio = new Date(formato.parse(inicio).getTime());
			horaFim = new Date(formato.parse(fim).getTime());

			eventosDao.salvarEvento(new Evento(id, nome, horaInicio, horaFim, area, contato));

		} catch (ParseException e) {
			throw new RoomsAllocationException(e.getMessage());
		}
	}

	/**
	 * 
	 * Recupera e retorna um atributo de um evento.
	 * 
	 * @param idEvento
	 *            Identificador do evento.
	 * @param atributo
	 *            Atributo procurado.
	 * @return O atributo.
	 * 
	 * @throws RoomsAllocationException
	 *             erro equivalente
	 */
	public Object getAtributoEvento(String idEvento, String atributo) throws RoomsAllocationException {

		if (idEvento == null || idEvento.equalsIgnoreCase("")) {
			throw new RoomsAllocationException("Evento nao existe.");
		}

		Evento evento = eventosDao.recuperarEvento(idEvento);

		if (evento == null) {
			throw new RoomsAllocationException("Evento nao existe.");
		}

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		switch (atributo) {
		case "id":
			return evento.getId();

		case "nome":
			return evento.getNome();

		case "inicio":
			return formato.format(evento.getInicio());

		case "fim":
			return formato.format(evento.getFim());

		case "area":
			return evento.getArea();

		case "contato":
			return evento.getContato();

		case "repeticoes":
			String rep = Integer.toString(evento.getRepeticao());
			return rep;

		default:
			throw new RoomsAllocationException("Atributo nao existe.");
		}

	}

}
