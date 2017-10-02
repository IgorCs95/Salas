package JDialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * @author IgorCs
 *tela que mostra as informações do programa
 */
@SuppressWarnings("serial")
public class TelaInformacoes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ImageIcon ifpb  = new ImageIcon(getClass().getResource("/icon/IFPBMonteiro.jpg"));

	/**
	 * metodo construtor
	 */
	public TelaInformacoes() {
		setBounds(100, 100, 484, 408);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setIcon(ifpb);
			lblNewLabel.setBounds(10, 30, 458, 309);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblSistemaParaAlocao = new JLabel("Sistema para alocação de Eventos");
			lblSistemaParaAlocao.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblSistemaParaAlocao.setHorizontalAlignment(SwingConstants.CENTER);
			lblSistemaParaAlocao.setBounds(87, 11, 281, 40);
			contentPanel.add(lblSistemaParaAlocao);
		}
		{
			JLabel lblIgorDeCarvalho = new JLabel("Igor de Carvalho Soares");
			lblIgorDeCarvalho.setBounds(309, 344, 149, 14);
			contentPanel.add(lblIgorDeCarvalho);
		}
		
		JLabel lblPadroesDeProjetos = new JLabel("PAdroes de Projetos 2016.2");
		lblPadroesDeProjetos.setBounds(20, 344, 176, 14);
		contentPanel.add(lblPadroesDeProjetos);
		setModal(true);
		setTitle("Informações");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
