package JDialog.Evento;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import entidades.Evento;
import execoes.RoomsAllocationException;
import fachada.Facade;
import manipuladores.ManipulacaoXml;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Color;

/**
 * @author IgorCs
 * tela responsaavel por localizar eventos alocados em salas
 *
 */
@SuppressWarnings("serial")
public class GetAtributoEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();


		JComboBox<String> JCBIdEventos = new JComboBox<String>();

		private JRadioButton Nome = new JRadioButton("Nome");
		private JRadioButton JRBInicio = new JRadioButton("Inicio");
		private JRadioButton JRBArea = new JRadioButton("Area");
		private JRadioButton JRBFim = new JRadioButton("Fim");
		private JRadioButton JRBContato = new JRadioButton("Contato");
		private JRadioButton JRBRepeticoes = new JRadioButton("Repeticoes");

		private JButton JBBuscar = new JButton("Buscar");



		private JLabel JLResultados = new JLabel("Resultado");
		
		
		/**
		 * Construtor da classe
		 */
		public GetAtributoEvento() {
			iniciarBotoes(this);
			iniciarOuvintes(this);
			povoarComboBox();
			
			
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);

			JLabel lblSala = new JLabel("Evento");
			lblSala.setBounds(10, 15, 46, 14);
			contentPanel.add(lblSala);

			JCBIdEventos.setBounds(10, 29, 98, 26);
			contentPanel.add(JCBIdEventos);

			JPanel JPAtributos = new JPanel();
			JPAtributos.setBorder(new TitledBorder(null, "Atributo", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			JPAtributos.setBounds(123, 15, 301, 69);
			contentPanel.add(JPAtributos);
			JPAtributos.setLayout(new GridLayout(2, 0, 0, 0));

			JPAtributos.add(Nome);
			JPAtributos.add(JRBInicio);
			JPAtributos.add(JRBFim);
			JPAtributos.add(JRBArea);
			JPAtributos.add(JRBContato);
			
		
			JPAtributos.add(JRBRepeticoes);

			JBBuscar.setBounds(440, 15, 90, 55);
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
			setTitle("Buscar Atributo Evento");
			setVisible(true);

		}
		
		public void povoarComboBox(){
			ArrayList<Evento> eventos = ManipulacaoXml.getInstace().todosEventos();
			for (Evento evento : eventos) {
				JCBIdEventos.addItem(evento.getId());
			}
		}
		
		/** Atravez do JRadionButton selecionado ele retorna o atributo selecionado para a pesquisa
		 * @return atributo selecionado
		 */
		public String getAtributoEvento() {

			if (Nome.isSelected())
				return "nome";

			if (JRBInicio.isSelected())
				return "inicio";

			if (JRBArea.isSelected())
				return "area";

			if (JRBFim.isSelected())
				return "fim";
			
			if (JRBContato.isSelected())
				return "contato";
			
			if (JRBRepeticoes.isSelected())
				return "repeticoes";

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
						String atributo = getAtributoEvento();
						if(!atributo.equalsIgnoreCase("")){
							String idSala = (String) JCBIdEventos.getSelectedItem();
							JLResultados.setText((String) Facade.getInstance().getAtributoEvento(idSala, atributo));
							
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
		
		/** cria ouvintes para os JradionButtons
		 * @param janela Janela ativa
		 */
		public void iniciarOuvintes(JDialog janela) {

			Nome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JRBInicio.setSelected(false);
					JRBArea.setSelected(false);
					JRBFim.setSelected(false);
					JRBContato.setSelected(false);
					JRBRepeticoes.setSelected(false);
					
					janela.repaint();
					janela.revalidate();

				}
			});
			JRBInicio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Nome.setSelected(false);
					JRBArea.setSelected(false);
					JRBFim.setSelected(false);
					JRBContato.setSelected(false);
					JRBRepeticoes.setSelected(false);
					
					janela.repaint();
					janela.revalidate();

				}
			});
			JRBArea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JRBInicio.setSelected(false);
					Nome.setSelected(false);
					JRBFim.setSelected(false);
					JRBContato.setSelected(false);
					JRBRepeticoes.setSelected(false);
					
					janela.repaint();
					janela.revalidate();

				}
			});
			JRBFim.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JRBInicio.setSelected(false);
					JRBArea.setSelected(false);
					Nome.setSelected(false);
					JRBContato.setSelected(false);
					JRBRepeticoes.setSelected(false);
					
					janela.repaint();
					janela.revalidate();

				}
			});
			JRBContato.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JRBInicio.setSelected(false);
					JRBArea.setSelected(false);
					JRBFim.setSelected(false);
					Nome.setSelected(false);
					JRBRepeticoes.setSelected(false);
					
					janela.repaint();
					janela.revalidate();

				}
			});
			JRBRepeticoes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JRBInicio.setSelected(false);
					JRBArea.setSelected(false);
					JRBFim.setSelected(false);
					Nome.setSelected(false);
					JRBContato.setSelected(false);
					
					janela.repaint();
					janela.revalidate();

				}
			});

		}
	}