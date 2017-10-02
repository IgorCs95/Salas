package JDialog.Sala.Finalidade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class JPanelFinalidadeSalaConferencia extends JPanelFinalidadeAbstract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton JRBSalaTipoNormal = new JRadioButton("Normal");
	private JRadioButton JRBSalaTipoConferencia = new JRadioButton("VidioConferencia");
	
	/**
	 * Construtor da classe.
	 */
	public JPanelFinalidadeSalaConferencia() {
		iniciarOuvintes();
		add(JRBSalaTipoNormal);
		add(JRBSalaTipoConferencia);
		

	}

	public String retornarOpcaoSelecionada() {
		if(JRBSalaTipoNormal.isSelected())
			return "Normal";
		
		if(JRBSalaTipoConferencia.isSelected())
			return "Videoconferencia";
		
		else
			return null;
	}

	
	/**
	 * Inicia os ouvintes desta classe
	 */
	public void iniciarOuvintes() {
		JRBSalaTipoNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBSalaTipoConferencia.setSelected(false);

			}
		});
		
		JRBSalaTipoConferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBSalaTipoNormal.setSelected(false);

			}
		});
	}
	
}
