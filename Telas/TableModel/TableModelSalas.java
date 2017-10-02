package TableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entidades.Sala;


/** Classe responsavel por tratar a tabela Salas
 * @author IgorCs 
 *
 */
@SuppressWarnings("serial")
public class TableModelSalas extends AbstractTableModel {

	private ArrayList<Sala> linhas;
	
	private String[] colunas = new String[] { "id", "capacidade","finalidade","Tipo","apelido","aberto"};
	private final static int id = 0,capacidade = 1 , finalidade = 2,tipo = 3,apelido=4,aberto=5;

	
	public TableModelSalas(ArrayList<Sala> listaDeSalas) {
		
		
		linhas = new ArrayList<Sala>(listaDeSalas);
		
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
		case capacidade:
			return int.class;
		case finalidade:
			return String.class;
		case tipo:
			return String.class;
		case apelido:
			return String.class;
		case aberto:
			return boolean.class;
			
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	

	public Object getValueAt(int rowIndex, int columnIndex) {

		Sala sala = linhas.get(rowIndex);

		switch (columnIndex) {
		case id:
			return sala.getId();
		case capacidade:
			return sala.getCapacidade();
		case finalidade:
			return sala.getFinalidade();
		case tipo:
			return sala.getTipo();
		case apelido:
			return sala.getApelido();
		case aberto:
			return sala.isDisponibilidade();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");

		}
	}

	public Sala getSala(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	public void addSala(Sala sala) {
		linhas.add(sala);

		int ultimoIndice = getRowCount();

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removerSala(int indiceLinha) {

		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
}
