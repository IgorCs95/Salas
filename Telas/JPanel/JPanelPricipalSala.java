package JPanel;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import Botoes.XmlCommand;
import Botoes.Controles.Controle;
import Botoes.Desfazer.BotaoRemoverSala;
import JDialog.TelaCadastrarEvento;
import JDialog.TelaCadastrarSala;
import JDialog.Sala.GetAtributoSala;
import TableModel.TableModelSalas;
import entidades.Sala;
import execoes.RoomsAllocationException;
import fachada.Facade;
import manipuladores.ManipulacaoXml;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;

/**Classe responsavel por manipular as salas
 * @author IgorCs 
 *
 */
public class JPanelPricipalSala extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private ManipulacaoXml banco = ManipulacaoXml.getInstace();
	
	private JTable JTSalas;
	
	private JButton JBNovaSala = new JButton("Nova Sala");
	private JButton JBRemoverSala = new JButton("Remover");
	private JButton JBBuscarSala = new JButton("Buscar");
	private JButton JBDesfazerSala = new JButton("Desfazer");
	
	
	private TableModelSalas tabelaSalas;
	private JScrollPane scrolSalas = new JScrollPane();

	private static Controle control;
	private static int posicao;

	
	/**
	 * Contrutor da classe
	 */
	public JPanelPricipalSala(){
		posicao = 0;
		control = new Controle();
		
		
		iniciarOuvintes();
		
		JBNovaSala.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JBNovaSala.setVerticalAlignment(SwingConstants.TOP);
		
		JBRemoverSala.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JBBuscarSala.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JBDesfazerSala.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTSalas = new JTable();
		JTSalas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JTSalas.setModel(tabelaSalas = new TableModelSalas(banco.todasSalas()));
		scrolSalas.setViewportView(JTSalas);
		
		JTSalas.getTableHeader().setReorderingAllowed(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(JBNovaSala, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(JBDesfazerSala, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(JBBuscarSala, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(JBRemoverSala, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrolSalas, GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrolSalas, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(JBNovaSala)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(JBRemoverSala)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(JBBuscarSala)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(JBDesfazerSala)))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
	
	
	/**
	 * Inicia os ouvintes responsaveis por adicionarSala BuscarSala RemoverSala e Desfazer Operaçao.
	 */
	public void iniciarOuvintes(){
		JBNovaSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarTeladecadastro();
				JTSalas.setModel(tabelaSalas = new TableModelSalas(ManipulacaoXml.getInstace().todasSalas()));
				
				
			}
		});
		
		JBBuscarSala.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new GetAtributoSala();
			}
		});
		
		JBRemoverSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JTSalas.getSelectedRow()!=-1){
					Sala idSala = tabelaSalas.getSala(JTSalas.getSelectedRow());
					int op =  JOptionPane.showConfirmDialog(null,"Deseja Realmente Apagar a Sala: "+idSala.getId(), "Remover Sala", JOptionPane.YES_NO_OPTION);
					if(op==0){
						try {
							BotaoRemoverSala c = new BotaoRemoverSala(idSala);
							precionarControle(c);
							
							JOptionPane.showMessageDialog(null,"A sala "+idSala.getId()+" foi removida!");
							JTSalas.setModel(tabelaSalas = new TableModelSalas(ManipulacaoXml.getInstace().todasSalas()));
						} catch (RoomsAllocationException e) {
							JOptionPane.showMessageDialog(null,e.getMessage());
						}
					}
					
				}else
					JOptionPane.showMessageDialog(null,"Escolha uma sala.","",JOptionPane.ERROR_MESSAGE,null);
				
			}
		});
		
		JBDesfazerSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desfazer();
					JTSalas.setModel(tabelaSalas = new TableModelSalas(ManipulacaoXml.getInstace().todasSalas()));
				} catch (RoomsAllocationException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				
			}
		});
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
	 * Aciona botao que inicia a tela de cadastro sala.
	 */
	public void apertarNovoSala(){
		JBNovaSala.doClick();
	}
	
	/**Desfaz a ultima operação
	 * @throws RoomsAllocationException retorn erro correspondente que possa ocorrer na açao.
	 */
	public void desfazer() throws RoomsAllocationException{
		control.desfazer();
	}
	
	/**
	 * cria tela de Cadastro Sala.
	 */
	public void executarTeladecadastro(){
		new TelaCadastrarSala(this);
	}
	
	
	/**
	 * Atualiza tabela.
	 */
	public void atualizarTabela(){
		JTSalas.setModel(tabelaSalas = new TableModelSalas(ManipulacaoXml.getInstace().todasSalas()));
	}
	

}
