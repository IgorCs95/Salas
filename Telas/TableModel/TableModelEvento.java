package TableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entidades.Evento;

/** Classe responsavel por tratar a tabela Evento
 * @author IgorCs 
 *
 */
@SuppressWarnings("serial")
public class TableModelEvento extends AbstractTableModel {

	private ArrayList<Evento> linhas;
	private String[] colunas = new String[] { "id", "nome","inicio","fim","area","contato","repeticoes"};
	private final static int id = 0,nome = 1 , inicio = 2,fim = 3,area=4,contato=5,repeticoes=6;

	
	public TableModelEvento(ArrayList<Evento> listaDeSalas) {
		
		
		linhas = new ArrayList<Evento>(listaDeSalas);
		
	}

	public int getRowCount() {
		return linhas.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {

		switch (columnIndex) {

		case id:
			return String.class;
		case nome:
			return String.class;
		case inicio:
			return SimpleDateFormat.class;
		case fim:
			return SimpleDateFormat.class;
		case area:
			return String.class;
		case contato:
			return String.class;
		case repeticoes:
			return int.class;

			
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	

	public Object getValueAt(int rowIndex, int columnIndex) {

		Evento evento = linhas.get(rowIndex);
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		switch (columnIndex) {

		case id:
			return evento.getId();
		case nome:
			return evento.getNome();
		case inicio:
			
			return formato.format(evento.getInicio());
		case fim:
			return formato.format(evento.getFim());
		case area:
			return evento.getArea();
		case contato:
			return evento.getContato();
		case repeticoes:
			return evento.getRepeticao();

		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");

		}
	}

	public Evento getEvento(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	public void addEvento(Evento evento) {
		linhas.add(evento);

		int ultimoIndice = getRowCount();

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removerEvento(int indiceLinha) {

		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
}
