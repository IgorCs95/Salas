package Botoes.Salvar;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import Botoes.XmlCommand;
import entidades.Evento;
import execoes.RoomsAllocationException;

/**Classe responsavel salvar um novo evento.
 * @author Igor
 *
 */
public class BotaoSalvarEvento implements XmlCommand {

	private Evento evento;
	private String print = "";

	public BotaoSalvarEvento(Evento ev) {
		evento = ev;
	}

	public void executar() throws RoomsAllocationException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		String horaInicio = formato.format(new Date(evento.getInicio()));
		String horaFim = formato.format(new Date(evento.getFim()));

		banco.adicionarEvento(evento.getId(), evento.getNome(), horaInicio, horaFim, evento.getArea(),
				evento.getContato(), evento.getRepeticao());
		print = "[Salvar Evento] " + evento.getId();

	}

	public void desfazer() throws RoomsAllocationException {
		banco.cancelarEvento(evento.getId());
		print = "[Desfazer Salvar Evento] " + evento.getId();
	}

	public String print() {
		return print;
	}

}
