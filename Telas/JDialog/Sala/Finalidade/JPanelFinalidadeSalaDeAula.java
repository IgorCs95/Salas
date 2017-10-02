package JDialog.Sala.Finalidade;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class JPanelFinalidadeSalaDeAula extends JPanelFinalidadeAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton JRBSalaTipoNormal = new JRadioButton("Normal");
	private JRadioButton JRBSalaTipoInteligente = new JRadioButton("Inteligente ");
	private JRadioButton JRBSalaTipoConferncia = new JRadioButton("Video Confererencia");

	public JPanelFinalidadeSalaDeAula() {
		iniciarOuvintes();
		setLayout(new GridLayout(1, 0, 0, 0));

		add(JRBSalaTipoNormal);
		add(JRBSalaTipoInteligente);
		add(JRBSalaTipoConferncia);

	}

	public String retornarOpcaoSelecionada() {
		if(JRBSalaTipoNormal.isSelected())
			return "Normal";
		
		if(JRBSalaTipoInteligente.isSelected())
			return "Inteligente";
		
		if(JRBSalaTipoConferncia.isSelected())
			return "Videoconferencia";
		
		
		else
			return null;
	}

	public void iniciarOuvintes() {
		
		JRBSalaTipoNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBSalaTipoInteligente.setSelected(false);
				JRBSalaTipoConferncia.setSelected(false);

			}
		});
		
		JRBSalaTipoInteligente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBSalaTipoNormal.setSelected(false);
				JRBSalaTipoConferncia.setSelected(false);

			}
		});
		
		JRBSalaTipoConferncia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBSalaTipoInteligente.setSelected(false);
				JRBSalaTipoNormal.setSelected(false);

			}
		});
		
		
	}

}
