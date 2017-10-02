package JPanel;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import JDialog.TelaCadastrarEvento;
import JDialog.Evento.GetAtributoEvento;
import TableModel.TableModelEvento;
import TableModel.TableModelSalas;
import entidades.Evento;
import execoes.RoomsAllocationException;
import fachada.Facade;
import manipuladores.ManipulacaoXml;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import Botoes.XmlCommand;
import Botoes.Controles.Controle;
import Botoes.Desfazer.BotaoCancelarEvento;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ListSelectionModel;

/**Classe responsavel por manipular os eventos
 * @author IgorCS 
 *
 */
public class JPanelPrincipalEvento extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable JTEventos;
	private ManipulacaoXml banco = ManipulacaoXml.getInstace();

	private JButton JBNovoEvento = new JButton("Novo Evento");
	private JButton JBCancelarEvento = new JButton("Cancelar");
	private JButton JBBuscarEvento = new JButton("Buscar");
	private JButton JBDesfazerEvento = new JButton("Desfazer");

	private TableModelEvento tabelaEvento;
	private JScrollPane scrolEventos = new JScrollPane();
	
	private static Controle control;
	private static int posicao;

	/**
	 * Contrutor da classe
	 */
	public JPanelPrincipalEvento() {
		posicao = 0;
		control = new Controle();

		iniciarOuvintes();

		JBNovoEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JBNovoEvento.setVerticalAlignment(SwingConstants.TOP);

		JBCancelarEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JBBuscarEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JBDesfazerEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JTEventos = new JTable();
		
		
		JTEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTEventos.setModel(tabelaEvento = new TableModelEvento(banco.todosEventos()));
		JTEventos.getTableHeader().setReorderingAllowed(false);
		
		scrolEventos.setViewportView(JTEventos);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(JBNovoEvento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(JBDesfazerEvento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(JBBuscarEvento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(JBCancelarEvento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrolEventos, GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrolEventos, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup().addComponent(JBNovoEvento)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(JBCancelarEvento)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(JBBuscarEvento)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(JBDesfazerEvento)))
						.addContainerGap()));
		setLayout(groupLayout);

	}
	
	
	/**Adiciona ao controle um botao que manipula a entrada de dados no banco.
	 * @param c Botao passado uma ação.
	 * @throws RoomsAllocationException erro correspondente.
	 */
	public void precionarControle(XmlCommand c) throws RoomsAllocationException{
		control.adicionarCommando(posicao, c);
		control.precionarCommando(posicao);
		posicao++;
	}
	
	/**
	 *Aperta o botao que inicia a classe {@link TelaCadastrarEvento}. 
	 */
	public void apertarNovoEvento() {
		JBNovoEvento.doClick();
	}
	
	
	/**Desfaz a ultima operação realizada
	 * @throws RoomsAllocationException retorna a erro aquivalente que possa ocorrer durante a operação realizada.
	 */
	public void desfazer() throws RoomsAllocationException{
		control.desfazer();
	}
	
	
	
	/**
	 * Executa o chamado para instanciar a classe {@link TelaCadastrarEvento}.
	 */
	public void executarTeladecadastro(){
		new TelaCadastrarEvento(this);
	}
	
	/**
	 * Inicia os ouvintes
	 */
	private void iniciarOuvintes() {
		JBNovoEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarTeladecadastro();
				JTEventos.setModel(tabelaEvento = new TableModelEvento(ManipulacaoXml.getInstace().todosEventos()));
			}
		});

		JBCancelarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JTEventos.getSelectedRow() != -1) {
					Evento evento = tabelaEvento.getEvento(JTEventos.getSelectedRow());
					
					BotaoCancelarEvento botao = new BotaoCancelarEvento(evento);
					try {
						int op = JOptionPane.showConfirmDialog(null,
								"Deseja Realmente cancelar o Evento: " + evento.getId(), "Cancelar Evento",
								JOptionPane.YES_NO_OPTION);
						if (op == 0) {
							precionarControle(botao);
							JOptionPane.showMessageDialog(null, "Evento '"+evento.getId()+"' cancelado com sucesso!","",JOptionPane.PLAIN_MESSAGE);
							JTEventos.setModel(tabelaEvento=new TableModelEvento(ManipulacaoXml.getInstace().todosEventos())); 
						}
						
					
					} catch (RoomsAllocationException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(),"",JOptionPane.ERROR_MESSAGE);
					
					}
					

				}else{
					JOptionPane.showMessageDialog(null, "Um Evento da tabela deve estar selecionado!","",JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		JBBuscarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GetAtributoEvento();
			}
		});
		JBDesfazerEvento.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {					
					desfazer();
					JTEventos.setModel(tabelaEvento=new TableModelEvento(ManipulacaoXml.getInstace().todosEventos())); 
				} catch (RoomsAllocationException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		

	}
	
	/**
	 * Atualiza tabela.
	 */
	public void atualizarTabela(){
		JTEventos.setModel(tabelaEvento=new TableModelEvento(ManipulacaoXml.getInstace().todosEventos())); 
	}

}
