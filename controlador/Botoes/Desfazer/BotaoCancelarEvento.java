package Botoes.Desfazer;

import java.sql.Date;
import java.text.SimpleDateFormat;

import Botoes.XmlCommand;
import entidades.Evento;
import execoes.RoomsAllocationException;
import validacaoDeEventos.ValidarEvento;

/**Classe responsavel pela a ação de cancelar um evento.
 * @author Igor
 *
 */
public class BotaoCancelarEvento implements XmlCommand {

	private Evento evento;
	private String print = "";

	public BotaoCancelarEvento(Evento evento) {
		this.evento = evento;
	}

	public void executar() throws RoomsAllocationException {
		banco.cancelarEvento(evento.getId());
		print = "[Salvar Evento] " + evento.getId();
	}

	public void desfazer() throws RoomsAllocationException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		String horaInicio = formato.format(new Date(evento.getInicio()));
		String horaFim = formato.format(new Date(evento.getFim()));

		banco.adicionarEvento(evento.getId(), evento.getNome(), horaInicio, horaFim, evento.getArea(),
				evento.getContato(), evento.getRepeticao());
		print = "[Desfazer Salvar Evento] " + evento.getId();

	}

	public String print() {
		return print;
	}

}
