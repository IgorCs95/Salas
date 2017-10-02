package validacaoDeSalas;

import enums.TipoSala;
import execoes.RoomsAllocationException;
import manipuladores.ManipulacaoXml;

/**
 * @author igor
 *
 */
public abstract class ValidarSalas {

	/**
	 * Verificar se a sala atende a todas as condiçoes.
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
	 * 
	 * @throws RoomsAllocationException
	 *             erro equivalente.
	 */
	public static void condicoesSala(String id, int capacidade, String finalidade, String tipo, String apelido)
			throws RoomsAllocationException {
		if (id == null || id.equals("")) {
			throw new RoomsAllocationException("Identificacao Invalida");
		}

		
		if (tipo == null || tipo.equalsIgnoreCase("")) {
			throw new RoomsAllocationException("Tipo invalido.");
		}

		if (finalidade.equalsIgnoreCase("Laboratorio")) {
			if (tipo.equalsIgnoreCase(TipoSala.QUIMICA.tipo) || tipo.equalsIgnoreCase(TipoSala.BIOLOGIA.tipo)
					|| tipo.equalsIgnoreCase(TipoSala.FISICA.tipo) || tipo.equalsIgnoreCase(TipoSala.COMPUTACAO.tipo)) {
			} else
				throw new RoomsAllocationException("Tipo invalido.");
		}

		if (finalidade.equalsIgnoreCase("Sala de Aula")) {
			if (tipo.equalsIgnoreCase(TipoSala.NORMAL.tipo) || tipo.equalsIgnoreCase(TipoSala.INTELIGENTE.tipo)
					|| tipo.equalsIgnoreCase(TipoSala.VIDIOCONFERENCIA.tipo)) {
			} else
				throw new RoomsAllocationException("Tipo invalido.");
		}
		if (finalidade.equalsIgnoreCase("escritorio")) {
			if (!tipo.equalsIgnoreCase(TipoSala.COMPUTACAO.tipo)) {
				throw new RoomsAllocationException("Tipo invalido.");
			}
		}

		if (finalidade.equalsIgnoreCase("Sala de Conferencia")) {
			if (tipo.equalsIgnoreCase(TipoSala.VIDIOCONFERENCIA.tipo) || tipo.equalsIgnoreCase(TipoSala.NORMAL.tipo)) {
			} else
				throw new RoomsAllocationException("Tipo invalido.");
		}
	}

}
