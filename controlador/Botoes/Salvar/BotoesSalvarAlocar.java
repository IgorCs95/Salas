package Botoes.Salvar;

import Botoes.XmlCommand;
import entidades.Alocar;
import execoes.RoomsAllocationException;

/**Classe responsavel salvar uma alocação de evento dentro de uma sala.
 * @author Igor
 *
 */
public class BotoesSalvarAlocar implements XmlCommand{
	
	private Alocar alocar;
	private String print = "";
	
	public BotoesSalvarAlocar(Alocar alocar) {
		this.alocar=alocar;
	}

	
	public void executar()  throws RoomsAllocationException{
		banco.alocarEvento(alocar.getIdEvento(), alocar.getIdSala());
		print = "[Desfazer Desalocar]" + alocar.getIdEvento();
	}
	
	public void desfazer()  throws RoomsAllocationException{
		banco.desalocarEvento(alocar.getIdEvento());
		print="[Desalocar]"+alocar.getIdEvento();
	}


	public String print() {
		return print;
	}

}
