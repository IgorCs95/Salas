package Botoes.Desfazer;

import Botoes.XmlCommand;

import entidades.Alocar;
import execoes.RoomsAllocationException;
/**Classe responsavel pela a ação de deslaocar um avento alocado em uma sala.
 * @author Igor
 *
 */
public class BotaoDesalocar implements XmlCommand {

	private Alocar alocar;
	private String print = "";

	public BotaoDesalocar(Alocar aloc) {
		this.alocar = aloc;
	}

	public void executar() throws RoomsAllocationException {
		banco.desalocarEvento(alocar.getIdEvento());
		print = "[Desalocar]" + alocar.getIdEvento();
	}

	public void desfazer() throws RoomsAllocationException {
		banco.alocarEvento(alocar.getIdEvento(), alocar.getIdSala());
		print = "[Desfazer Desalocar]" + alocar.getIdEvento();
	}

	public String print() {
		return print;
	}

}
