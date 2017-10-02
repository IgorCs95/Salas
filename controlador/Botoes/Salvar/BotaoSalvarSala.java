package Botoes.Salvar;

import Botoes.XmlCommand;
import entidades.Sala;
import execoes.RoomsAllocationException;
import manipuladores.ManipulacaoXml;


/**Classe responsavel salvar uma nova sala.
 * @author Igor
 *
 */
public class BotaoSalvarSala implements XmlCommand{
	
	
	private Sala sala;
	private String print = "";
	
	public BotaoSalvarSala(Sala sala) {
		this.sala=sala;
	}

	public void executar()  throws RoomsAllocationException {
		banco.adicionarSala(sala.getId(), sala.getCapacidade(), sala.getFinalidade(), sala.getTipo(), sala.getApelido(), sala.isDisponibilidade());
		print="[Salvar Sala] "+sala.getId();
	}

	public void desfazer()  throws RoomsAllocationException {
		banco.removerSala(sala.getId());
		print="[Desfazer Salvar Sala] "+sala.getId();
	}

	public String print() {
		return print;
	}

}
