package Botoes.Desfazer;

import Botoes.XmlCommand;
import entidades.Sala;
import execoes.RoomsAllocationException;

/**Classe responsavel por remover uma sala.
 * @author Igor
 *
 */
public class BotaoRemoverSala implements XmlCommand {

	private Sala sala;
	private String print = "";
	
	
	public BotaoRemoverSala(Sala sala) {
		this.sala= sala;
	}
	
	public void executar()   throws RoomsAllocationException {
		banco.removerSala(sala.getId());
		print="[Desfazer Salvar Sala] "+sala.getId();
	}

	public void desfazer()   throws RoomsAllocationException {
		banco.adicionarSala(sala.getId(), sala.getCapacidade(), sala.getFinalidade(), sala.getTipo(), sala.getApelido(), sala.isDisponibilidade());
		print="[Salvar Sala] "+sala.getId();
	}

	public String print() {
		return print; 
	}
	


}
