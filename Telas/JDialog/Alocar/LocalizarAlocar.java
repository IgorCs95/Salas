package JDialog.Alocar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import execoes.RoomsAllocationException;
import fachada.Facade;

import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;

/**
 * @author IgorCs
 * tela responsaavel por localizar eventos alocados em salas
 *
 */
public class LocalizarAlocar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField JTXValorBuscado;

	private JRadioButton JRBID = new JRadioButton("ID");
	private JRadioButton JRBHorario = new JRadioButton("Horario");
	private JRadioButton JRBNome = new JRadioButton("Nome");
	private JRadioButton JRBArea = new JRadioButton("Area");
	private JRadioButton JRBContato = new JRadioButton("Contato");
	
	private JButton JBBuscar = new JButton("Buscar");
	
	private JTextArea JTAResultado = new JTextArea();
	
	
	
	/**
	 * Construtor da classe
	 */
	public LocalizarAlocar() {
		
		iniciarOuvintes(this);
		iniciarBotoes(this);
		
	
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel JPAtributo = new JPanel();
		JPAtributo.setBorder(new TitledBorder(null, "Atributo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JPAtributo.setBounds(10, 11, 356, 104);
		contentPanel.add(JPAtributo);
		JPAtributo.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPAtributo.add(JRBID);
		JPAtributo.add(JRBHorario);
		JPAtributo.add(JRBNome);
		JPAtributo.add(JRBArea);
		JPAtributo.add(JRBContato);
		
		JTXValorBuscado = new JTextField();
		JTXValorBuscado.setBounds(376, 43, 158, 26);
		contentPanel.add(JTXValorBuscado);
		JTXValorBuscado.setColumns(10);
		
		JBBuscar.setBounds(375, 80, 89, 35);
		contentPanel.add(JBBuscar);
		
		JLabel lblValorBuscado = new JLabel("Valor Buscado");
		lblValorBuscado.setBounds(375, 18, 106, 14);
		contentPanel.add(lblValorBuscado);
		
		JTAResultado.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 18));
		JTAResultado.setForeground(Color.RED);
		JTAResultado.setEditable(false);
		JTAResultado.setWrapStyleWord(true);
		JTAResultado.setLineWrap(true);
		JTAResultado.setBounds(10, 126, 524, 317);
		contentPanel.add(JTAResultado);
		
		
	
		setBounds(100, 100, 549, 483);
		
		setModal(true);
		setTitle("Evento");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/** Atravez do JRadionButton selecionado ele retorna o atributo selecionado para a pesquisa
	 * @return atributo selecionado
	 */
	public String getAtributo() {

		if (JRBID.isSelected())
			return "id";

		if (JRBHorario.isSelected())
			return "horario";

		if (JRBNome.isSelected())
			return "nome";

		if (JRBArea.isSelected())
			return "area";
		
		if (JRBContato.isSelected())
			return "contato";

		else
			return "";

	}
	
	/** inicia os botao buscar da janela
	 * @param janela passa a janela ativa
	 */
	public void iniciarBotoes(JDialog janela){
		JBBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String valor = JTXValorBuscado.getText();
					
					if(valor!=null){
						String atributo = getAtributo();
						if(!atributo.equalsIgnoreCase("") ){
							JTAResultado.setText( Facade.getInstance().localizarEvento(atributo, valor));
							
							janela.repaint();
							janela.revalidate();
							
						}else
							JOptionPane.showMessageDialog(null, "Escolha um Atributo");
					}else
						JOptionPane.showMessageDialog(null, "O campo Valor deve estar preenchido");
				
				} catch (RoomsAllocationException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}
	
	
	/** cria ouvintes para os JradionButtons
	 * @param janela Janela ativa
	 */
	public void iniciarOuvintes(JDialog janela) {

		JRBID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBHorario.setSelected(false);
				JRBNome.setSelected(false);
				JRBArea.setSelected(false);
				JRBContato.setSelected(false);
				
				janela.repaint();
				janela.revalidate();

			}
		});
		
		JRBHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBHorario.setSelected(false);
				JRBNome.setSelected(false);
				JRBArea.setSelected(false);
				JRBContato.setSelected(false);
				
				janela.repaint();
				janela.revalidate();

			}
		});
		
		JRBNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBHorario.setSelected(false);
				JRBID.setSelected(false);
				JRBArea.setSelected(false);
				JRBContato.setSelected(false);
				
				janela.repaint();
				janela.revalidate();

			}
		});
		
		JRBArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBHorario.setSelected(false);
				JRBNome.setSelected(false);
				JRBID.setSelected(false);
				JRBContato.setSelected(false);
				
				janela.repaint();
				janela.revalidate();

			}
		});
		
		JRBContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRBHorario.setSelected(false);
				JRBNome.setSelected(false);
				JRBArea.setSelected(false);
				JRBID.setSelected(false);
				
				janela.repaint();
				janela.revalidate();

			}
		});
		
	
	}
}
