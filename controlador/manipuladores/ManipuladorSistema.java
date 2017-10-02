package manipuladores;

import java.util.ArrayList;
import java.util.Date;

import entidades.Alocar;
import entidades.Evento;
import entidades.Sala;
import execoes.RoomsAllocationException;
import validacao.Validacao;

/**
 * @author igor
 *
 */
public class ManipuladorSistema {

	ManipulacaoXml sistema;

	Validacao validar;

	/**
	 * Metodo construtor da classe.
	 * 
	 * @param xml
	 *            objeto do tipo ManipulacaoXml.
	 */
	public ManipuladorSistema(ManipulacaoXml xml) {
		sistema = xml;
		validar = new Validacao(xml);
	}

	/**
	 * Apaga todos os dados do sistema.
	 */
	public void zerarSistema() {
		sistema.zerar();

	}

	/**
	 * Deve-se alocar uma sala para um evento (repetitivo ou não). O sistema
	 * deve informar as salas disponíveis que satisfaçam as restrições do
	 * evento.
	 * 
	 * @param idEvento
	 *            Identificador do evento.
	 * @param idSala
	 *            Identificador da sala onde o evento devera ocorrer.
	 * @throws RoomsAllocationException  erro equivalente.
	 */
	@SuppressWarnings("deprecation")
	public void alocarEvento(String idEvento, String idSala) throws RoomsAllocationException {

		Sala sala = sistema.recuperarSala(idSala);
		Evento evento = sistema.recuperarEvento(idEvento);

		try {
			validar.validarChoquedeDatas(sala, evento);
			validar.verificarFinalidadeAlocacao(sala, evento);

		} catch (Exception e) {
			throw new RoomsAllocationException(e.getMessage());
		}

		Alocar aloc = new Alocar(sala.getId(), evento.getId());

		if (new Date(evento.getInicio()).getDay() == 0) {
			throw new RoomsAllocationException("As salas nao sao alocadas nos fins de semana.");
		}

		sistema.salvarAlocar(aloc);

	}

	/**
	 * O usuario pode localizar um evento escalonado atravas do nome, contato,
	 * data etc. Ao utilizar o contato como atributo de busca, deve ser
	 * retornado todos os eventos que contenham o nome do contato inserido.
	 * (Exemplo: Prof Pedro e Pedro Silva contém Pedro) [Formato:LAB-03:EV-09,
	 * SA-01:EV-01]
	 * 
	 * @param atributo
	 *            Nome do atributo do evento (nome, contato, horario etc).
	 * @param valor
	 *            Valor do atributo do evento.
	 * @return O identificador do evento, se algum evento com esse atributo for
	 *         encontrado. Valores multiplos sao esperados quando mais de um
	 *         evento for localizado [Formato:LAB-03:EV-09, SA-01:EV-01]
	 * @throws RoomsAllocationException
	 *             retorna erro equivalente
	 */
	public String localizarEvento(String atributo, String valor) throws RoomsAllocationException {

		if (validar.validar(valor)) {
			throw new RoomsAllocationException("Entrada Invalida");
		}
		ArrayList<Evento> eventos = sistema.todosEventos();
		ArrayList<Evento> eventosAchados = new ArrayList<Evento>();

		String resultado = "";

		for (Evento evento : eventos) {
			try {

				switch (atributo) {
				case "id":
					if (evento.getId().equals(valor)) {
						eventosAchados.add(evento);
					}
					break;

				case "nome":
					if (evento.getNome().contains(valor)) {
						eventosAchados.add(evento);
					}
					break;

				case "horario":
					if (validar.choqueHorario(evento, valor)) {

						eventosAchados.add(evento);
					}
					break;

				case "area":
					if (evento.getArea().contains(valor)) {
						eventosAchados.add(evento);
					}
					break;

				case "contato":

					if (evento.getContato().contains(valor)) {
						eventosAchados.add(evento);
					}
					break;

				default:
					throw new RoomsAllocationException("Atributo Invalido");
				}
			} catch (Exception e) {

				throw new RoomsAllocationException("Neno.");
			}

		}

		ArrayList<Alocar> alocados = sistema.todasAlocacoes();

		for (Alocar alocar : alocados) {
			for (Evento evento : eventosAchados) {
				if (evento.getId().equals(alocar.getIdEvento())) {

					if (resultado.equalsIgnoreCase("")) {

						resultado = alocar.getIdSala() + ":" + alocar.getIdEvento();
					} else {
						resultado += ", " + alocar.getIdSala() + ":" + alocar.getIdEvento();

					}

				}
			}
		}

		if (validar.validar(resultado)) {
			return "Nenhum evento encontrado.";
		} else {
			return resultado;
		}

	}

	/**
	 * Desaloca um evento previamente alocado para uma sala.
	 * 
	 * @param idEvento
	 *            Identificador do evento a desalocar.
	 * 
	 * @throws RoomsAllocationException
	 *             retorna erro equivalente
	 */
	public void desalocarEvento(String idEvento) throws RoomsAllocationException {
		if (idEvento == null || idEvento.equals("")) {
			throw new RoomsAllocationException("Evento nao existe.");
		}
		ArrayList<Alocar> aloc = sistema.todasAlocacoes();

		boolean achou = false;
		for (Alocar alocar : aloc) {
			if (alocar.getIdEvento().equals(idEvento)) {
				sistema.removerAlocar(idEvento);
				achou = true;
				break;
			}
		}
		if (achou == false) {
			throw new RoomsAllocationException("O evento nao esta alocado.");
		}

	}

	/**
	 * O usuario pode cancelar um evento. Neste caso, o cancelamento remove o
	 * evento da base de dados e desvincula as possíveis alocacaes previamente
	 * computadas.
	 * 
	 * @param idEvento
	 *            Identificador do evento a cancelar.
	 * @throws RoomsAllocationException
	 *             retorna erro equivalente
	 */
	public void cancelarEvento(String idEvento) throws RoomsAllocationException {
		if (idEvento == null || idEvento.equals("")) {
			throw new RoomsAllocationException("Evento nao existe.");
		}
		Evento evento = sistema.recuperarEvento(idEvento);

		if (evento == null)
			throw new RoomsAllocationException("Evento nao existe.");
		else
			sistema.removerEvento(idEvento);

	}

	/**
	 * O usuario pode remover salas do sistema. A remoçao de uma sala também
	 * remove as possíveis alocações que referem a mesma, mas nao exclui os
	 * respectivos eventos da base de dados.
	 * 
	 * @param idSala
	 *            Identificador da sala.
	 * @throws RoomsAllocationException
	 *             retorna erro equivalente
	 */
	public void removerSala(String idSala) throws RoomsAllocationException {
		if (idSala == null || idSala.equals("")) {
			throw new RoomsAllocationException("Evento nao existe.");
		}

		ArrayList<Sala> salas = sistema.todasSalas();

		boolean achou = false;

		for (Sala sa : salas) {
			if (sa.getId().equals(idSala)) {
				sistema.removerSala(idSala);
				achou = true;
				break;
			}
		}
		if (achou == false) {
			throw new RoomsAllocationException("Sala não existe.");
		}

	}

}
