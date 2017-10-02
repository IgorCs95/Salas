package validacao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import entidades.Alocar;
import entidades.Evento;
import entidades.Sala;
import execoes.RoomsAllocationException;
import manipuladores.ManipulacaoXml;

/**
 * Verificar requisitos do sistema para as funcoes.
 * 
 * @author igor
 *
 */
public class Validacao {

	ManipulacaoXml validar;

	/**
	 * Metodo contrutor.
	 * 
	 * @param xml
	 *            objeto ManiulaçãoXML
	 */
	public Validacao(ManipulacaoXml xml) {
		validar = xml;
	}

	/**
	 * Verificar se objetos Strings sao validos.
	 * 
	 * @param valor
	 *            objeto e ser verificado.
	 * @return false se o objeto for valido.
	 */
	public boolean validar(String valor) {
		if (valor.equals("") || valor == null)
			return true;
		else
			return false;

	}

	/**
	 * Metodo criado para verificar o horario passado esta dentro do horario do
	 * evento.
	 * 
	 * @param evento
	 *            e ser conparado o horario.
	 * @param horario
	 *            para comparar.
	 * @return true se o horario se encaixar dentro do horario do evento passado
	 */
	public boolean choqueHorario(Evento evento, String horario) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Date horaraioDate = null;

		try {
			horaraioDate = new Date(formato.parse(horario).getTime());

			if (horaraioDate.getTime() >= evento.getInicio()
					&& horaraioDate.getTime() <= evento.getFim()) {

				return true;
			}

		} catch (Exception e) {

		}

		return false;

	}

	/**
	 * Verifica se todas as condições para que um evento possa ser alocado em
	 * uma sala sao validas.
	 * 
	 * @param sala
	 *            objeto passado.
	 * @param evento
	 *            objeto passado.
	 * @throws RoomsAllocationException
	 *             erro equivalente.
	 */
	@SuppressWarnings("deprecation")
	public void verificarFinalidadeAlocacao(Sala sala, Evento evento) throws RoomsAllocationException {
		if (sala.getFinalidade().equalsIgnoreCase("Sala de Conferencia")) {
			if (sala.getTipo().equalsIgnoreCase("Normal")) {
				if (evento.getRepeticao() > 0)
					throw new RoomsAllocationException(
							"Salas de Conferencia do tipo Normal nao sao escalonaveis para eventos repetitivos.");
			}
		}

		if (sala.getFinalidade().equalsIgnoreCase("Laboratorio")) {
			if (sala.isDisponibilidade()) {
				throw new RoomsAllocationException("Laboratorios abertos nao sao escalonaveis.");
			} else {

				if (sala.getTipo().equalsIgnoreCase("quimica")) {
					if (!evento.getArea().equalsIgnoreCase("quimica")) {
						throw new RoomsAllocationException("Sala exclusiva para a area de Quimica.");
					}
				}
				if (sala.getTipo().equalsIgnoreCase("fisica")) {
					if (!evento.getArea().equalsIgnoreCase("fisica")) {
						throw new RoomsAllocationException("Sala exclusiva para a area de Fisica.");
					}
				}
				if (sala.getTipo().equalsIgnoreCase("biologia")) {
					if (!evento.getArea().equalsIgnoreCase("biologia")) {
						throw new RoomsAllocationException("Sala exclusiva para a area de Biologia.");
					}
				}

			}
		}

		if (sala.getFinalidade().equalsIgnoreCase("Escritorio")) {
			throw new RoomsAllocationException("Escritorios nao sao escalonaveis.");
		}

		if (new Date(evento.getInicio()).getDay() == 0) {
			throw new RoomsAllocationException("As salas nao sao alocadas nos fins de semana.");
		}

	}

	/**
	 * Verifica se um evento antes de ser alocado se é possivel dar choque de
	 * horario com repetiçoes de eventos anteriores.
	 * 
	 * @param sala
	 *            sala que ira receber o evento.
	 * @param evento
	 *            evento a ser alocado.
	 * @throws RoomsAllocationException
	 *             erro equivalente.
	 */
	public void validarChoquedeDatas(Sala sala, Evento evento) throws RoomsAllocationException {
		if (sala == null || evento == null) {
			throw new RoomsAllocationException("Sala/Evento nao existe.");
		}

		ArrayList<Alocar> lista = validar.todasAlocacoes();

		for (Alocar alocar : lista) {

			if (alocar.getIdEvento().equals(evento.getId())) {
				throw new RoomsAllocationException("O Evento ja foi alocado anteriormente.");
			}
			if (sala.getId().equals(alocar.getIdSala())) {

				Calendar inicio = Calendar.getInstance();
				Calendar fim = Calendar.getInstance();

				inicio.setTimeInMillis(validar.recuperarEvento(alocar.getIdEvento()).getInicio());
				fim.setTimeInMillis(validar.recuperarEvento(alocar.getIdEvento()).getFim());

				if (validar.recuperarEvento(alocar.getIdEvento()).getRepeticao() > 0) {

					for (int i = 0; i < validar.recuperarEvento(alocar.getIdEvento()).getRepeticao(); i++) {

						inicio.add(Calendar.WEEK_OF_YEAR, i);

						fim.add(Calendar.WEEK_OF_YEAR, i);

						if (inicio.get(Calendar.DAY_OF_WEEK) == 1) {
							inicio.add(Calendar.WEEK_OF_YEAR, ++i);
						}

						if (fim.get(Calendar.DAY_OF_WEEK) == 1) {
							fim.add(Calendar.WEEK_OF_YEAR, ++i);
						}

						if (evento.getInicio() >= inicio.getTime().getTime()) {
							if (evento.getFim() <= fim.getTime().getTime()) {
								throw new RoomsAllocationException("A sala nao esta disponivel neste horario.");
							}
						}

					}
				} else {
					if (evento.getInicio()>(validar.recuperarEvento(alocar.getIdEvento()).getInicio())) {
						if (evento.getInicio()<(validar.recuperarEvento(alocar.getIdEvento()).getFim())) {
							throw new RoomsAllocationException("A sala nao esta disponivel neste horario.");
						}
					}

				}

			}
		}
	}

}
