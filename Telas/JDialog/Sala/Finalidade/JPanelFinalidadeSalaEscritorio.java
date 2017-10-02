package JDialog.Sala.Finalidade;

import javax.swing.JRadioButton;

public class JPanelFinalidadeSalaEscritorio extends JPanelFinalidadeAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton JRBSalaTipoComputao = new JRadioButton("Computação");

	public JPanelFinalidadeSalaEscritorio() {
		JRBSalaTipoComputao.setSelected(true);
		JRBSalaTipoComputao.setEnabled(false);
		
		add(JRBSalaTipoComputao);

	}

	public String retornarOpcaoSelecionada() {
		return "computacao";
	}

	
	
	

}
