package JDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Botoes.Controles.Controle;
import Botoes.Desfazer.BotaoCancelarEvento;
import Botoes.Salvar.BotoesSalvarAlocar;
import JPanel.JPanelPrincipalAlocar;
import JPanel.JPanelPrincipalEvento;
import entidades.Alocar;
import entidades.Evento;
import entidades.Sala;
import execoes.RoomsAllocationException;
import fachada.Facade;
import manipuladores.ManipulacaoXml;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** Tela responsavel por fazer a ligação de uma sala á um evento.
 * @author IgorCs
 *
 */
@SuppressWarnings("serial")
public class TelaAlocar extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JComboBox<String> JCBIDEvento = new JComboBox<String>();
	private JComboBox<String> JCBIDSala = new JComboBox<String>();

	private JButton okButton = new JButton("Salvar");
	private JButton cancelButton = new JButton("Cancel");

	private JPanelPrincipalAlocar jp;
	
	
	
	/**
	 * Metodo construtor da classe.
	 * @param janela é passado como parametro um objeto do tipo JPanelPrincipalAlocar que invoca a classe TelaAlocar
	 */
	public TelaAlocar(JPanelPrincipalAlocar janela) {
		jp=janela;
		
		
		povoarComboBox();
		iniciarOuvintes();
		
		setModal(true);
		setBounds(100, 100, 483, 172);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JCBIDSala.setBounds(10, 36, 214, 44);
		contentPanel.add(JCBIDSala);
		
		JCBIDEvento.setBounds(243, 36, 214, 44);
		contentPanel.add(JCBIDEvento);
		
		JLabel lblSala = new JLabel("Sala");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblSala.setBounds(10, 11, 214, 14);
		contentPanel.add(lblSala);
		
		JLabel lblEvento = new JLabel("Evento");
		lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblEvento.setBounds(243, 11, 214, 14);
		contentPanel.add(lblEvento);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
			{
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
		setVisible(true);
	}
	/**
	 * este metodo busca os eventos e salas salvas no banco de dados para povar os jComboBox
	 */
	public void povoarComboBox(){
		ArrayList<Sala> salas = ManipulacaoXml.getInstace().todasSalas();
		for (Sala sala : salas) {
			JCBIDSala.addItem(sala.getId());
		}
		
		ArrayList<Evento> eventos = ManipulacaoXml.getInstace().todosEventos();
		for (Evento e : eventos) {
			JCBIDEvento.addItem(e.getId());
		}
		
	}
	
	/**
	 * os ouvintes dos botoes salvar e cancelar sao iniciados
	 */
	public void iniciarOuvintes(){
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idEvento = (String)JCBIDEvento.getSelectedItem();
					String idSala = (String)JCBIDSala.getSelectedItem();
					
					BotoesSalvarAlocar c = new BotoesSalvarAlocar(new Alocar(idSala,idEvento));
					jp.precionarControle(c);
					
					dispose();
					JOptionPane.showMessageDialog(null,"Evento "+idEvento+" alocado na sala "+idSala +" com sucesso!");
					
				} catch (RoomsAllocationException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
	}
	
}
