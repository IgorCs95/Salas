package JDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Botoes.Controles.Controle;
import Botoes.Salvar.BotaoSalvarEvento;
import JPanel.JPanelPrincipalEvento;
import entidades.Evento;
import entidades.Sala;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;
import java.text.SimpleDateFormat;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import execoes.RoomsAllocationException;
import fachada.Facade;

import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * Tela responsavel por criar e salvar Eventos no banco do dados.
 * 
 * @author IgorCs
 */
@SuppressWarnings("serial")
public class TelaCadastrarEvento extends JDialog {

	private final JPanel AreaCadastrarEvento = new JPanel();

	private JTextField JTXEventoNome;
	private JTextField JTXEventoContato;
	private JTextField JTXEventoID = new JTextField();
	private JTextField JTXEventoArea;

	private JSpinner JSPEventoFim = new JSpinner();
	private JSpinner JSPEventoInicio = new JSpinner();
	private JSpinner JSPEventoRepeticoes = new JSpinner();

	private JButton JBEventoCancelar = new JButton("Cancel");
	private JButton JBEventoSalvar = new JButton("Salvar");
	private JComboBox comboBox = new JComboBox();

	private JPanelPrincipalEvento jp;

	private Facade fachada;

	/**
	 * metodo construtor da classe aqui é criado a janela.
	 *
	 * @param janela
	 *            deve ser passado como parametro um objeto do tipo
	 *            {@link JPanelPrincipalEvento} que invoca esta classe
	 */
	public TelaCadastrarEvento(JPanelPrincipalEvento janela) {
		jp = janela;

		iniciarBotoes();

		this.fachada = Facade.getInstance();

		getContentPane().setLayout(new BorderLayout());
		AreaCadastrarEvento.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(AreaCadastrarEvento, BorderLayout.CENTER);
		JSPEventoInicio.setToolTipText("Ex: 12/02/17 02:00");

		JSPEventoInicio.setBounds(50, 54, 135, 29);
		JSPEventoInicio.setModel(
				new SpinnerDateModel(new Date(1486872000000L), new Date(315543600000L), null, Calendar.DAY_OF_YEAR));

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(15, 22, 43, 14);

		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setBounds(15, 61, 35, 14);

		JLabel lblFim = new JLabel("Fim");
		lblFim.setBounds(236, 61, 35, 14);

		JLabel lblContato = new JLabel("Contato");
		lblContato.setBounds(15, 145, 49, 14);
		JSPEventoFim.setToolTipText("Ex: 12/02/17 02:00");

		JSPEventoFim.setBounds(275, 55, 135, 27);
		JSPEventoFim.setModel(
				new SpinnerDateModel(new Date(1486879200000L), new Date(315543600000L), null, Calendar.HOUR_OF_DAY));

		JTXEventoNome = new JTextField();
		JTXEventoNome.setBounds(50, 16, 360, 27);
		JTXEventoNome.setColumns(10);

		JTXEventoContato = new JTextField();
		JTXEventoContato.setBounds(68, 137, 342, 31);
		JTXEventoContato.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(299, 183, 25, 22);

		JSPEventoRepeticoes.setBounds(78, 179, 43, 31);
		JSPEventoRepeticoes.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

		JTXEventoID.setBounds(324, 179, 86, 31);
		JTXEventoID.setToolTipText("Exemplo: EV-00");
		JTXEventoID.setColumns(10);

		AreaCadastrarEvento.setLayout(null);

		AreaCadastrarEvento.add(lblContato);
		AreaCadastrarEvento.add(JTXEventoContato);
		AreaCadastrarEvento.add(lblNome);
		AreaCadastrarEvento.add(JTXEventoNome);
		AreaCadastrarEvento.add(lblInicio);
		AreaCadastrarEvento.add(JSPEventoInicio);
		AreaCadastrarEvento.add(lblFim);
		AreaCadastrarEvento.add(JSPEventoFim);
		AreaCadastrarEvento.add(JSPEventoRepeticoes);
		AreaCadastrarEvento.add(lblId);
		AreaCadastrarEvento.add(JTXEventoID);

		JTXEventoArea = new JTextField();
		JTXEventoArea.setBounds(50, 94, 360, 31);
		AreaCadastrarEvento.add(JTXEventoArea);
		JTXEventoArea.setColumns(10);

		JLabel lblArea = new JLabel("Area");
		lblArea.setBounds(15, 102, 46, 14);
		AreaCadastrarEvento.add(lblArea);

		JLabel lblRepeticoes = new JLabel("Repeticoes");
		lblRepeticoes.setBounds(15, 187, 65, 14);
		AreaCadastrarEvento.add(lblRepeticoes);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Semanalmente","Mensalmente"}));
		comboBox.setBounds(131, 179, 146, 31);
		AreaCadastrarEvento.add(comboBox);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonPane.add(JBEventoSalvar);
			}
			{

				buttonPane.add(JBEventoCancelar);
			}
		}
		setBounds(100, 100, 441, 292);

		setModal(true);
		setTitle("Evento");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Inicia os ouvintes dos botoes salvar e cancelar da classe.
	 */
	public void iniciarBotoes() {
		JBEventoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Evento evento = new Evento();

				evento.setNome(JTXEventoNome.getText());
				evento.setId(JTXEventoID.getText());
				evento.setArea(JTXEventoArea.getText());
				evento.setContato(JTXEventoContato.getText());
				evento.setRepeticao((int) JSPEventoRepeticoes.getValue());

				Date inicio = (Date) JSPEventoInicio.getValue();
				Date fim = (Date) JSPEventoFim.getValue();

				evento.setInicio(inicio.getTime());
				evento.setFim(fim.getTime());

				try {

					BotaoSalvarEvento banco = new BotaoSalvarEvento(evento);
					jp.precionarControle(banco);

					dispose();
					JOptionPane.showMessageDialog(null, "Cadastrado Com Sucesso!");

				} catch (RoomsAllocationException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});

		JBEventoCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

	}
}
