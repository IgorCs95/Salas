package Botoes;

import execoes.RoomsAllocationException;
import fachada.Facade;

/**
 * @author Igor
 *
 */
public interface XmlCommand {
	
	Facade banco = Facade.getInstance();
	
	/**Metodo responsavel por executar o comando do botao;
	 * @throws RoomsAllocationException retorna erro se ocorrido no momento da manipulação do banco.
	 */
	public void executar() throws RoomsAllocationException ;
	
	/**Metodo responsavel por desfazer a ultima alteração realizada pelo botao;
	 * @throws RoomsAllocationException retorna erro se ocorrido no momento da manipulação do banco.
	 */
	public void desfazer() throws RoomsAllocationException ;
	
	/** Imprimir na tela
	 * @return String com o nome e o objeto do controle acionado.
	 */
	public String print();

}
