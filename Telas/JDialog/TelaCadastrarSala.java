package JDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.border.TitledBorder;

import Botoes.Controles.Controle;
import Botoes.Salvar.BotaoSalvarSala;
import JDialog.Sala.Finalidade.JPanelFinalidadeAbstract;
import JDialog.Sala.Finalidade.JPanelFinalidadeSalaConferencia;
import JDialog.Sala.Finalidade.JPanelFinalidadeSalaDeAula;
import JDialog.Sala.Finalidade.JPanelFinalidadeSalaEscritorio;
import JDialog.Sala.Finalidade.JPanelFinalidadeSalaLaboratorio;
import JPanel.JPanelPricipalSala;
import JPanel.JPanelPrincipalEvento;
import entidades.Sala;
import execoes.RoomsAllocationException;
import fachada.Facade;

import javax.swing.SpinnerNumberModel;

/**
 * @author IgorCS Esta tela é responsavel por criar e salvar salas no banco de
 *         dados.
 */
@SuppressWarnings("serial")
public class TelaCadastrarSala extends JDialog {

	private final JPanel jPanelGeralSala = new JPanel();
	private JTextField JTXSalaID;
	private JTextField JTXApelido;

	private JSpinner JSPSalaCapacidade = new JSpinner();
	private JRadioButton JRBSalaFechada = new JRadioButton("Fechada");

	private JRadioButton JRBSalaFinalidadeSalaDeAula = new JRadioButton("Sala de Aula");
	private JRadioButton JRBSalaFinalidadeSalaConferencia = new JRadioButton("Sala de Conferencia");
	private JRadioButton JRBSalaFinalidadeLaboratorio = new JRadioButton("Laboratorio");
	private JRadioButton JRBSalaFinalidadeEscritorio = new JRadioButton("Escritorio");

	private JPanelFinalidadeSalaConferencia jpConferencia = new JPanelFinalidadeSalaConferencia();
	private JPanelFinalidadeSalaDeAula jpAula = new JPanelFinalidadeSalaDeAula();
	private JPanelFinalidadeSalaEscritorio jpEscritorio = new JPanelFinalidadeSalaEscritorio();
	private JPanelFinalidadeSalaLaboratorio jpLaboratorio = new JPanelFinalidadeSalaLaboratorio();

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	private JPanelPricipalSala jp;

	private static JPanel jPanelTipoSala = new JPanel();

	/**
	 * Metodo construtor da classe.
	 * @param janela é passado como parametro um objeto do tipo JPanelPricipalSala que invoca a classe TelaCadastrarSala.
	 */
	public TelaCadastrarSala(JPanelPricipalSala janela) {
		jp = janela;

		inicarOuvintes(this);

		getContentPane().setLayout(new BorderLayout());
		jPanelGeralSala.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(jPanelGeralSala, BorderLayout.CENTER);

		setBounds(100, 100, 457, 334);
		setResizable(false);

		JLabel lblNome = new JLabel("ID");

		JLabel lblCapacidade = new JLabel("Capacidade");

		JTXSalaID = new JTextField();
		JTXSalaID.setToolTipText("Exemplo: SA-01");

		JSPSalaCapacidade.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		JSPSalaCapacidade.setToolTipText("Capacidade de Alunos que cabem dentro da sala");

		JPanel JPSalaFinalidadeSala = new JPanel();
		JPSalaFinalidadeSala.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Finalidade",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		jPanelTipoSala.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tipo  Sala",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblApelido = new JLabel("Apelido");

		JTXApelido = new JTextField();
		JTXApelido.setColumns(10);
		GroupLayout gl_jPanelGeralSala = new GroupLayout(jPanelGeralSala);
		gl_jPanelGeralSala.setHorizontalGroup(gl_jPanelGeralSala.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanelGeralSala.createSequentialGroup().addGap(22).addGroup(gl_jPanelGeralSala
						.createParallelGroup(Alignment.LEADING).addGroup(gl_jPanelGeralSala
								.createSequentialGroup()
								.addGroup(gl_jPanelGeralSala.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(jPanelTipoSala,
												Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(JPSalaFinalidadeSala, Alignment.LEADING,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
						.addGroup(gl_jPanelGeralSala.createSequentialGroup().addGroup(gl_jPanelGeralSala
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_jPanelGeralSala.createSequentialGroup().addComponent(lblApelido)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(JTXApelido, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(JRBSalaFechada))
								.addGroup(gl_jPanelGeralSala.createSequentialGroup().addComponent(lblNome)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(JTXSalaID, GroupLayout.PREFERRED_SIZE, 91,
												GroupLayout.PREFERRED_SIZE)
										.addGap(127).addComponent(lblCapacidade)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(JSPSalaCapacidade, GroupLayout.PREFERRED_SIZE, 80,
												GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)))
								.addGap(46)))));
		gl_jPanelGeralSala.setVerticalGroup(gl_jPanelGeralSala.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanelGeralSala.createSequentialGroup().addContainerGap()
						.addGroup(gl_jPanelGeralSala.createParallelGroup(Alignment.BASELINE)
								.addComponent(JTXSalaID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome).addComponent(lblCapacidade).addComponent(JSPSalaCapacidade,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_jPanelGeralSala.createParallelGroup(Alignment.BASELINE).addComponent(lblApelido)
								.addComponent(JTXApelido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(JRBSalaFechada))
						.addGap(18)
						.addComponent(JPSalaFinalidadeSala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(jPanelTipoSala, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addGap(53)));
		JPSalaFinalidadeSala.setLayout(new GridLayout(0, 3, 0, 0));

		JPSalaFinalidadeSala.add(JRBSalaFinalidadeSalaDeAula);

		JPSalaFinalidadeSala.add(JRBSalaFinalidadeSalaConferencia);

		JPSalaFinalidadeSala.add(JRBSalaFinalidadeLaboratorio);

		JPSalaFinalidadeSala.add(JRBSalaFinalidadeEscritorio);
		jPanelGeralSala.setLayout(gl_jPanelGeralSala);
		{
			JPanel jPanelButtonSala = new JPanel();
			jPanelButtonSala.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(jPanelButtonSala, BorderLayout.SOUTH);
			{
				okButton.setActionCommand("OK");
				jPanelButtonSala.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton.setActionCommand("Cancel");
				jPanelButtonSala.add(cancelButton);
			}
		}

		botoesFuncao();

		setModal(true);
		setTitle("Sala");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Aqui é retornado a finalidade da sala.
	 * 
	 * @return finalidade
	 */
	public String retornarOpcaoSelecionada() {

		if (JRBSalaFinalidadeSalaDeAula.isSelected())
			return "Sala de Aula";

		if (JRBSalaFinalidadeSalaConferencia.isSelected())
			return "Sala de Conferencia";

		if (JRBSalaFinalidadeLaboratorio.isSelected())
			return "Laboratorio";

		if (JRBSalaFinalidadeEscritorio.isSelected())
			return "Escritorio";

		else
			return "";

	}

	/**
	 * retorna o painel que da finalidade
	 * 
	 * @return o painel para certo para a finalidade escolhida
	 */
	public JPanelFinalidadeAbstract retornarRadion() {

		if (JRBSalaFinalidadeSalaDeAula.isSelected())
			return jpAula;

		if (JRBSalaFinalidadeSalaConferencia.isSelected())
			return jpConferencia;

		if (JRBSalaFinalidadeLaboratorio.isSelected())
			return jpLaboratorio;

		if (JRBSalaFinalidadeEscritorio.isSelected())
			return jpEscritorio;
		else
			return null;

	}

	/**
	 * inciar os ouvintres dos botoes salvar e cancelar
	 */
	private void botoesFuncao() {

		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Facade fachada = Facade.getInstance();

				String id = JTXSalaID.getText();
				String finalidade = retornarOpcaoSelecionada();

				String tipo = retornarRadion().retornarOpcaoSelecionada();

				String apelido = JTXApelido.getText();
				int capacidade = (int) JSPSalaCapacidade.getValue();
				boolean aberto = !JRBSalaFechada.isSelected();

				Sala sala = new Sala(id, capacidade, finalidade, tipo, apelido, aberto);

				try {
					BotaoSalvarSala c = new BotaoSalvarSala(sala);
					jp.precionarControle(c);

					dispose();
					JOptionPane.showMessageDialog(null, "Cadastrado Com Sucesso!");

				} catch (RoomsAllocationException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});

		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

	/**
	 * controla as acoes dos RadionButtons para a selecao da finalidade e ao
	 * depender da finalidade escolhida o painel tipo de sala muda conforme a
	 * finalidade escolhida
	 * 
	 * @param janela
	 *            esme metodo exige que seja passado como paramentro a JDialog
	 *            que contem o JPanel Finalidade.
	 */
	public void inicarOuvintes(JDialog janela) {
		JRBSalaFinalidadeLaboratorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jPanelTipoSala.removeAll();
				jPanelTipoSala.add(jpLaboratorio);

				JRBSalaFinalidadeEscritorio.setSelected(false);
				JRBSalaFinalidadeSalaDeAula.setSelected(false);
				JRBSalaFinalidadeSalaConferencia.setSelected(false);

				janela.repaint();
				janela.revalidate();

			}
		});

		JRBSalaFinalidadeEscritorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jPanelTipoSala.removeAll();
				jPanelTipoSala.add(jpEscritorio);

				JRBSalaFinalidadeLaboratorio.setSelected(false);
				JRBSalaFinalidadeSalaDeAula.setSelected(false);
				JRBSalaFinalidadeSalaConferencia.setSelected(false);

				janela.repaint();
				janela.revalidate();

			}
		});

		JRBSalaFinalidadeSalaDeAula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jPanelTipoSala.removeAll();
				jPanelTipoSala.add(jpAula);

				JRBSalaFinalidadeEscritorio.setSelected(false);
				JRBSalaFinalidadeLaboratorio.setSelected(false);
				JRBSalaFinalidadeSalaConferencia.setSelected(false);

				janela.repaint();
				janela.revalidate();

			}
		});

		JRBSalaFinalidadeSalaConferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jPanelTipoSala.removeAll();
				jPanelTipoSala.add(jpConferencia);

				JRBSalaFinalidadeEscritorio.setSelected(false);
				JRBSalaFinalidadeSalaDeAula.setSelected(false);
				JRBSalaFinalidadeLaboratorio.setSelected(false);

				janela.repaint();
				janela.revalidate();

			}
		});

	}

}
