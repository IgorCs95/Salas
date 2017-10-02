package PercistenciasTipos;

import java.util.ArrayList;

/**
 * @author igor
 *
 * @param <t> tipo generico de Classe que sera usada
 */
public interface Percistencias<t> {

	/**
	 * @param objeto a ser salvo no banco de dados
 	 */
	public void salvar(t objeto);

	/**
	 * @param IdObjeto id do objeto que sera excluido.
	 */
	public void excluir(String IdObjeto);
	
	/**
	 * @param IdObjeto ecluir alocar pela sala.
	 */
	public void excluirAlocarSala(String IdObjeto);
	
	/**
	 * @param IdObjeto excluir alocar pelo evento.
	 */
	public void excluirAlocarEvento(String IdObjeto);

	/**
	 * @return retorna todos os objetos salvos.
	 */
	public ArrayList<t> todos();

	/**
	 * @param IdObjeto id do objeto procurado.
	 * @return o objeto que foi buscado.
	 */
	public t recuperar(String IdObjeto);

	/**
	 * apaga todos os dados do banco.
	 */
	public void zerar();
}
