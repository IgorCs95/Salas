package TableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entidades.Alocar;

/** Classe responsavel por tratar a tabela Alcoar
 * @author IgorCs 
 *
 */
@SuppressWarnings("serial")
public class TableModelAlocar extends AbstractTableModel {

	private ArrayList<Alocar> linhas;
	
	private String[] colunas = new String[] { "ID Sala", "ID Evento"};
	private final static int idSala = 0,idEvento = 1;

	
	public TableModelAlocar(ArrayList<Alocar> listaDeSalas) {
		
		
		linhas = new ArrayList<Alocar>(listaDeSalas);
		
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

		case idSala:
			return String.class;
		case idEvento:
			return String.class;
	
			
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}

	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	

	public Object getValueAt(int rowIndex, int columnIndex) {

		Alocar sala = linhas.get(rowIndex);

		switch (columnIndex) {
		case idSala:
			return sala.getIdSala();
		case idEvento:
			return sala.getIdEvento();
		
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");

		}
	}

	public Alocar getAlocar(int indiceLinha) {
		return linhas.get(indiceLinha);
	}

	public void addAlocar(Alocar sala) {
		linhas.add(sala);

		int ultimoIndice = getRowCount();

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removerAlocar(int indiceLinha) {

		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
}
