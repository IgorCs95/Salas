package validacaoDeEventos;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import execoes.RoomsAllocationException;
import manipuladores.ManipulacaoXml;

/** Validar o eventos.
 * @author igor
 *
 */
public abstract class ValidarEvento {
	
	
	/** Verifica as finalidades do evento.
	 *  @param id
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
	 * @throws RoomsAllocationException erro equivalente
	 */
	 
	public static void finalidadeEvento(String id, String nome, String inicio, String fim, String area, String contato,
			int repeticoes) throws RoomsAllocationException{
		
		if (id == null || id.equals("")) {
			throw new RoomsAllocationException("Identificacao Invalida");
		}
		if (nome == null || nome.equalsIgnoreCase("")) {
			throw new RoomsAllocationException("Atributo invalido.");
		}
		if (area == null || area.equalsIgnoreCase("")) {
			throw new RoomsAllocationException("Atributo invalido.");
		}
		if (contato == null || contato.equalsIgnoreCase("")) {
			throw new RoomsAllocationException("Atributo invalido.");
		}

		if (inicio == null || inicio.equals("")) {
			throw new RoomsAllocationException("Intervalo de datas invalido.");
		}

		if (fim == null || fim.equals("")) {
			throw new RoomsAllocationException("Intervalo de datas invalido.");
		}
		if (repeticoes < 0) {
			throw new RoomsAllocationException("Atributo invalido.");
		}

		
	}

	/** Validar as data dos eventos.
	 * @param inicio data que inicia o evento.
	 * @param fim termino do evento
	 * @return retorna true se tudo der certo.
	 * @throws RoomsAllocationException erro equivalente.
	 */
	@SuppressWarnings("deprecation")
	public static boolean validarDatasEventos(String inicio, String fim) throws RoomsAllocationException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		Date horaInicio = null;
		Date horaFim = null;

		try {
			horaInicio = new Date(formato.parse(inicio).getTime());
			horaFim = new Date(formato.parse(fim).getTime());

			if (inicio.equals("") || inicio == null) {
				throw new RoomsAllocationException("Intervalo de datas invalido.");
			}

		
			if (horaInicio.after(horaFim)) {
				throw new RoomsAllocationException("Intervalo de datas invalido.");
			}

			if (horaInicio.getDay() != horaFim.getDay()) {
				throw new RoomsAllocationException("Intervalo de datas invalido.");
			}
			return true;

		} catch (ParseException e) {
			return false;
		}
	}
}
