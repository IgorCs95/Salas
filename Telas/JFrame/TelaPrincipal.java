package JFrame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import JDialog.TelaAlocar;
import JDialog.TelaCadastrarEvento;
import JDialog.TelaCadastrarSala;
import JDialog.TelaInformacoes;
import JDialog.Alocar.LocalizarAlocar;
import JDialog.Evento.GetAtributoEvento;
import JDialog.Sala.GetAtributoSala;
import JPanel.JPanelPricipalSala;
import JPanel.JPanelPrincipalAlocar;
import JPanel.JPanelPrincipalEvento;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.remote.JMXConnectorServerMBean;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

/**Janela principal no projeto
 * @author IgorCs 
 *
 */
public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel AreaTrabalho;

	private JMenu JMNovo = new JMenu("Criar");
	private JMenu JMAlocar = new JMenu("Alocar");
	private JMenu JMABuscar = new JMenu("Buscar");
	private JMenu JMAjuda = new JMenu("Ajuda");

	private JPanelPricipalSala JPSalas = new JPanelPricipalSala();
	private JPanelPrincipalAlocar JPAlocar = new JPanelPrincipalAlocar();
	private JPanelPrincipalEvento JPEventos = new JPanelPrincipalEvento();

	private JMenuItem JMTNovaSala = new JMenuItem("Nova Sala");
	private JMenuItem JMTNovoEvento = new JMenuItem("Novo Evento");
	private JMenuItem JMTLocalizar = new JMenuItem("Localizar");
	private JMenuItem JMTAlocarEvento = new JMenuItem("Alocar Evento");
	private JMenuItem JMTInformaes = new JMenuItem("Informações");

	private JMenuItem JMTBuscarSala = new JMenuItem("Buscar Sala");
	private JMenuItem JMTBuscarEvento = new JMenuItem("Buscar Sala");

	/**
	 * iniciar classe
	 */
	public void start() {
		iniciarOuvintes();
		AreaTrabalho = new JPanel();
		AreaTrabalho.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(AreaTrabalho);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuBar.add(JMNovo);
		menuBar.add(JMABuscar);
		menuBar.add(JMAlocar);
		menuBar.add(JMAjuda);

		JMNovo.add(JMTNovaSala);
		JMNovo.add(JMTNovoEvento);

		JMAlocar.add(JMTAlocarEvento);
		JMAlocar.add(JMTLocalizar);

		JMABuscar.add(JMTBuscarSala);
		JMABuscar.add(JMTBuscarEvento);

		JMAjuda.add(JMTInformaes);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPSalas.setBorder(new LineBorder(Color.BLACK));
		tabbedPane.addTab("Salas", null, JPSalas, null);

		JPEventos.setBorder(new LineBorder(Color.BLACK));
		tabbedPane.addTab("Eventos", null, JPEventos, null);

		JPAlocar.setBorder(new LineBorder(Color.BLACK));
		tabbedPane.addTab("Alocar", null, JPAlocar, null);

		GroupLayout gl_AreaTrabalho = new GroupLayout(AreaTrabalho);
		gl_AreaTrabalho.setHorizontalGroup(gl_AreaTrabalho.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE));
		gl_AreaTrabalho.setVerticalGroup(gl_AreaTrabalho.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE));

		AreaTrabalho.setLayout(gl_AreaTrabalho);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 650);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 *Iniciar ouvintes 
	 */
	public void iniciarOuvintes() {
		JMTNovaSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPSalas.apertarNovoSala();
				JPSalas.atualizarTabela();

			}
		});
		JMTNovoEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPEventos.apertarNovoEvento();
				JPEventos.atualizarTabela();

			}
		});

		JMTAlocarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPAlocar.apertarNovoAlocar();
				JPAlocar.atualizarTabela();

			}
		});
		JMTLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LocalizarAlocar();
			}
		});

		JMTInformaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaInformacoes();

			}
		});
		JMTBuscarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GetAtributoEvento();

			}
		});
		JMTBuscarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GetAtributoSala();

			}
		});

	}

}
