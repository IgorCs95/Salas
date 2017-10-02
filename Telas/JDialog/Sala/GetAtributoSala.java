package JDialog.Sala;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import entidades.Sala;
import execoes.RoomsAllocationException;
import fachada.Facade;
import manipuladores.ManipulacaoXml;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author igor
 *
 */
/**
 * @author igor
 *
 */
@SuppressWarnings("serial")
public class GetAtributoSala extends JDialog {


	private final JPanel contentPanel = new JPanel();

	JComboBox<String> JCBIdSalas = new JComboBox<String>();

	private JRadioButton JRBCapacidade = new JRadioButton("Capacidade");
	private JRadioButton JRBFinalidade = new JRadioButton("Finalidade");
	private JRadioButton JRBApelido = new JRadioButton("Apelido");
	private JRadioButton JRBTipo = new JRadioButton("Tipo");
	private JRadioButton JRBStatus = new JRadioButton("Status");

	private JButton JBBuscar = new JButton("Buscar");



	private JLabel JLResultados = new JLabel("Resultado");

	public GetAtributoSala() {
		iniciarBotoes(this);
		iniciarOuvintes(this);
		povoarComboBox();
		
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblSala = new JLabel("Sala");
		lblSala.setBounds(10, 21, 46, 14);
		contentPanel.add(lblSala);

		JCBIdSalas.setBounds(10, 46, 98, 26);
		contentPanel.add(JCBIdSalas);

		JPanel JPAtributos = new JPanel();
		JPAtributos.setBorder(new TitledBorder(null, "Atributo", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPAtributos.setBounds(123, 11, 301, 61);
		contentPanel.add(JPAtributos);
		JPAtributos.setLayout(new GridLayout(2, 0, 0, 0));

		JPAtributos.add(JRBCapacidade);
		JPAtributos.add(JRBFinalidade);
		JPAtributos.add(JRBTipo);
		JPAtributos.add(JRBApelido);
		JPAtributos.add(JRBStatus);

		JBBuscar.setBounds(430, 17, 89, 55);
		contentPanel.add(JBBuscar);


		
		JLResultados.setForeground(Color.RED);
		JLResultados.setFont(new Font("Tahoma", Font.BOLD, 18));
		JLResultados.setBounds(20, 83, 499, 41);
		contentPanel.add(JLResultados);
		
		setModal(true);
		setBounds(100, 100, 545, 173);
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Buscar Atributo Sala");
		setVisible(true);

	}
	
	/**
	 *  povoa o JComboBox com as salas 
	 */
	public void povoarComboBox(){
		ArrayList<Sala> salas = ManipulacaoXml.getInstace().todasSalas();
		for (Sala sala : salas) {
			JCBIdSalas.addItem(sala.getId());
		}
	}
	
	/** retorna o atributo selecionado no no painel do JRadionButton
	 * @return o atributo selecionado 
	 */
	public String getAtributoSala() {

		if (JRBCapacidade.isSelected())
			return "capacidade";

		if (JRBFinalidade.isSelected())
			return "finalidade";

		if (JRBApelido.isSelected())
			return "apelido";

		if (JRBTipo.isSelected())
			return "tipo";
		
		if (JRBStatus.isSelected())
			return "disponibilidade";

		else
			return "";

	}
	
	/** incia o ouvinte do botao buscar
	 * @param janela passa a janela atual como parametro
	 */
	public void iniciarBotoes(JDialog janela){
		JBBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String atributo = getAtributoSala();
					if(!atributo.equalsIgnoreCase("") ){
						String idSala = (String) JCBIdSalas.getSelectedItem();
						JLResultados.setText( Facade.getInstance().getAtributoSala(idSala, atributo));
						
						janela.repaint();
						janela.revalidate();
						
					}else
						JOptionPane.showMessageDialog(null, "Escolha um Atributo");
					
					
					
				} catch (RoomsAllocationException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}
	

	/** incio os eventos dos JRadionButtons
	 * @param janela passe a janela atual
	 */
	public void iniciarOuvintes(JDialog janela) {

		JRBCapacidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBFinalidade.setSelected(false);
				JRBApelido.setSelected(false);
				JRBTipo.setSelected(false);
				JRBStatus.setSelected(false);
				
				janela.repaint();
				janela.revalidate();

			}
		});
		JRBFinalidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBCapacidade.setSelected(false);
				JRBApelido.setSelected(false);
				JRBTipo.setSelected(false);
				JRBStatus.setSelected(false);
				
				janela.repaint();
				janela.revalidate();

			}
		});
		JRBApelido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBFinalidade.setSelected(false);
				JRBCapacidade.setSelected(false);
				JRBTipo.setSelected(false);
				JRBStatus.setSelected(false);
				
				janela.repaint();
				janela.revalidate();

			}
		});
		JRBTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBFinalidade.setSelected(false);
				JRBApelido.setSelected(false);
				JRBCapacidade.setSelected(false);
				JRBStatus.setSelected(false);
				
				janela.repaint();
				janela.revalidate();

			}
		});
		JRBStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBFinalidade.setSelected(false);
				JRBApelido.setSelected(false);
				JRBTipo.setSelected(false);
				JRBCapacidade.setSelected(false);
				
				janela.repaint();
				janela.revalidate();

			}
		});

	}

}
