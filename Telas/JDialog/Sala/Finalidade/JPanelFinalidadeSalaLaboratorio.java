package JDialog.Sala.Finalidade;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class JPanelFinalidadeSalaLaboratorio extends JPanelFinalidadeAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton JRBSalaTipoQuimica = new JRadioButton("Quemica");
	private JRadioButton JRBSalaTipoFisica = new JRadioButton("Fisica");
	private JRadioButton JRBSalaTipoBiologia = new JRadioButton("Biologia ");
	private JRadioButton JRBSalaTipoComputacao = new JRadioButton("Computacao");

	public JPanelFinalidadeSalaLaboratorio() {
		iniciarOuvintes();
		setLayout(new GridLayout(1, 0, 0, 0));

		add(JRBSalaTipoQuimica);
		add(JRBSalaTipoFisica);
		add(JRBSalaTipoBiologia);
		add(JRBSalaTipoComputacao);
		setVisible(true);

	}
	
	public String retornarOpcaoSelecionada() {
		
		if(JRBSalaTipoQuimica.isSelected())
			return "quimica";
		
		if(JRBSalaTipoFisica.isSelected())
			return "fisica";
		
		if(JRBSalaTipoBiologia.isSelected())
			return "biologia";
		
		if(JRBSalaTipoComputacao.isSelected())
			return "computacao";
		
		else
			return null;
		
	}
	
	
	

	public void iniciarOuvintes() {
		JRBSalaTipoQuimica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBSalaTipoFisica.setSelected(false);
				JRBSalaTipoBiologia.setSelected(false);
				JRBSalaTipoComputacao.setSelected(false);

			}
		});
		
		JRBSalaTipoFisica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JRBSalaTipoQuimica.setSelected(false);
				JRBSalaTipoBiologia.setSelected(false);
				JRBSalaTipoComputacao.setSelected(false);

			}
		});
		
		JRBSalaTipoBiologia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JRBSalaTipoFisica.setSelected(false);
				JRBSalaTipoQuimica.setSelected(false);
				JRBSalaTipoComputacao.setSelected(false);

			}
		});
		
		JRBSalaTipoComputacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JRBSalaTipoFisica.setSelected(false);
				JRBSalaTipoBiologia.setSelected(false);
				JRBSalaTipoQuimica.setSelected(false);

			}
		});

	}





}