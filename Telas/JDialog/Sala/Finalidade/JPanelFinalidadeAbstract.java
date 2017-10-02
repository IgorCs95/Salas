package JDialog.Sala.Finalidade;

import javax.swing.JPanel;

/**
 * @author Igor
 *Classe abstract para tratar os Jpannel que definiram a tipo e finalidade da sala.
 */
public abstract class JPanelFinalidadeAbstract extends JPanel{
	
	
	private static final long serialVersionUID = 1L;

	/** Classe que deve ser implementada pela classe filho para retornar a opcao escolhida.
	 * @return String com o resultado da escolha finalidade;
	 */
	public abstract String retornarOpcaoSelecionada();
	
	
	
	
}
