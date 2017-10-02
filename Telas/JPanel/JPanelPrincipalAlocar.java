package JPanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import Botoes.XmlCommand;
import Botoes.Controles.Controle;
import Botoes.Desfazer.BotaoDesalocar;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import JDialog.TelaAlocar;
import JDialog.TelaCadastrarEvento;
import JDialog.Alocar.LocalizarAlocar;
import TableModel.TableModelAlocar;
import entidades.Alocar;
import execoes.RoomsAllocationException;
import fachada.Facade;
import manipuladores.ManipulacaoXml;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;

/**
 * Classe responsavel por manipular as alocações
 * 
 * @author IgorCs painel pricipal alocar
 */
public class JPanelPrincipalAlocar extends JPanel {

	private static final long serialVersionUID = 1L;

	private ManipulacaoXml banco = ManipulacaoXml.getInstace();

	private JTable JTAlocar = new JTable();
	private TableModelAlocar modelAlocar;
	private JScrollPane scrolAlocar = new JScrollPane();

	private JButton JBAlocar = new JButton("Alocar");
	private JButton JBLocalizar = new JButton("Localizar");
	private JButton JBDesalocar = new JButton("Desalocar");
	private JButton JBDesfazerAlocar = new JButton("Desfazer");

	private static Controle control;
	private static int posicao;

	/**
	 * contrutor da classe
	 */
	public JPanelPrincipalAlocar() {
		posicao = 0;
		control = new Controle();

		inicarOuvintes();

		JBAlocar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JBAlocar.setVerticalAlignment(SwingConstants.TOP);

		JBDesalocar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JBLocalizar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JBDesfazerAlocar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JTAlocar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTAlocar.setModel(modelAlocar = new TableModelAlocar(banco.todasAlocacoes()));
		scrolAlocar.setViewportView(JTAlocar);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(JBDesfazerAlocar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(JBLocalizar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(JBAlocar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(JBDesalocar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(scrolAlocar, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
				.addGap(497)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrolAlocar, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup().addComponent(JBAlocar).addGap(25)
										.addComponent(JBDesalocar).addGap(5).addComponent(JBLocalizar).addGap(5)
										.addComponent(JBDesfazerAlocar)))
						.addContainerGap()));
		setLayout(groupLayout);
		JTAlocar.getTableHeader().setReorderingAllowed(false);

	}

	/**Adiciona ao controle um botao que manipula a entrada de dados no banco.
	 * @param c Botao passado uma ação.
	 * @throws RoomsAllocationException erro correspondente.
	 */
	public void precionarControle(XmlCommand c) throws RoomsAllocationException {
		control.adicionarCommando(posicao, c);
		control.precionarCommando(posicao);
		posicao++;
	}

	
	/**
	 *Aciona botao para abrir a {@link TelaAlocar}  
	 */
	public void apertarNovoAlocar() {
		JBAlocar.doClick();
	}

	/**Desfaz a ultima operação realizada
	 * @throws RoomsAllocationException retorna a erro aquivalente que possa ocorrer durante a operação realizada.
	 */
	public void desfazer() throws RoomsAllocationException {
		control.desfazer();
	}

	/**
	 * executa o chamado da para que seja instanciado {@link TelaAlocar} 
	 */
	public void executarTelaAlocacao() {
		new TelaAlocar(this);
	}

	/**
	 * Incia os ouvintes do painel alocar, localizar evento, desalocar evento.
	 */
	public void inicarOuvintes() {
		JBAlocar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				executarTelaAlocacao();
				JTAlocar.setModel(modelAlocar = new TableModelAlocar(ManipulacaoXml.getInstace().todasAlocacoes()));

			}
		});

		JBDesalocar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (JTAlocar.getSelectedRow() != -1) {
					Alocar alocado = modelAlocar.getAlocar(JTAlocar.getSelectedRow());

					try {
						int op = JOptionPane.showConfirmDialog(null,
								"Deseja Realmente desalocar o Evento: " + alocado.getIdEvento(), "Desalocar Evento",
								JOptionPane.YES_NO_OPTION);
						if (op == 0) {
							BotaoDesalocar c = new BotaoDesalocar(alocado);
							precionarControle(c);

							JOptionPane.showMessageDialog(null,"O Evento " + alocado.getIdEvento() + " foi desalocado!");
							modelAlocar.removerAlocar(JTAlocar.getSelectedRow());
						}
					} catch (RoomsAllocationException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}

				} else
					JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela para poder desalocar o evento.",
							"", JOptionPane.ERROR_MESSAGE, null);

			}
		});
		
		JBLocalizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new LocalizarAlocar();

			}
		});
		
		JBDesfazerAlocar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					desfazer();
					JTAlocar.setModel(modelAlocar = new TableModelAlocar(ManipulacaoXml.getInstace().todasAlocacoes()));
				} catch (RoomsAllocationException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			}
		});
	}

	/**
	 * atualiza a tabela alocar
	 */
	public void atualizarTabela() {
		JTAlocar.setModel(modelAlocar = new TableModelAlocar(ManipulacaoXml.getInstace().todasAlocacoes()));
	}

}
