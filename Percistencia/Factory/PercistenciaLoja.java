package Factory;

import PercistenciasTipos.Percistencias;

/**
 * @author igor
 * LOja que ira gerenciar as fabricas.
 */
public class PercistenciaLoja {
	
	PercistenciaFactory fabrica;
	
	public PercistenciaLoja(PercistenciaFactory fabrica) {
		this.fabrica = fabrica;
	}
	
	@SuppressWarnings("rawtypes")
	public Percistencias pedirObjeto(String tipo){
		return fabrica.pedir(tipo);
		
	}

}
