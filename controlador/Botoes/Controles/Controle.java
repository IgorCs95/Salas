package Botoes.Controles;

import java.util.ArrayList;
import java.util.Stack;

import Botoes.XmlCommand;
import execoes.RoomsAllocationException;

/**
 * Classe responsavel por gerenciar os botoes;
 * 
 * @author Igor
 *
 */
public class Controle {

	private ArrayList<XmlCommand> lista;
	private Stack<XmlCommand> acionados;
	private int apertados = 0;

	private String print = "";

	/**
	 * Metodo construtor da classe aqui � intaciado a lista dos botoes.
	 */
	public Controle() {
		lista = new ArrayList<>();
		acionados = new Stack<>();
	}

	/**
	 * Adicionada um botao command na lista de commands
	 * 
	 * @param posicao
	 *            a posi��o que deve ser adicionado o botao;
	 * @param c
	 *            O botao adicionado
	 */
	public void adicionarCommando(int posicao, XmlCommand c) {
		lista.add(posicao, c);
	}

	/**
	 * Cabe a este metodo acionar a a�ao do command.
	 * 
	 * @param index
	 *            posi��o do botao que deve ser acionado.
	 * @throws RoomsAllocationException
	 *             retorna erro ocorrido na manipula��o do banco.
	 */
	public void precionarCommando(int index) throws RoomsAllocationException {
		acionados.push(lista.get(index));
		if (apertados < 3) {
			apertados++;
		} else {
			apertados = 3;
		}

		lista.get(index).executar();
		print += "[Slot " + index + "]" + lista.get(index).print() + "\n";
	}

	/**
	 * Desfaz a ultima a��o realizada, quando um botao � precionado ele tamben �
	 * adicionado em uma pilha que assim podemos recuperar o botao para
	 * conseguir desfazer sua a��o
	 * 
	 * @throws RoomsAllocationException
	 *             retorna erro ocorrido na manipula��o do banco.
	 */
	public void desfazer() throws RoomsAllocationException {
		if (apertados > 0) {
			acionados.pop().desfazer();
			apertados--;

		}
	}

	public String print() {
		return print;
	}

}
