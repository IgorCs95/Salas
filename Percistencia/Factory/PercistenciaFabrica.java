package Factory;

import PercistenciasTipos.PercistenciaAlocar;
import PercistenciasTipos.PercistenciaEvento;
import PercistenciasTipos.PercistenciaSala;
import PercistenciasTipos.Percistencias;
import execoes.RoomsAllocationException;

/**
 * @author igor 
 * gerencia a criacao de objetos.
 */
public class PercistenciaFabrica extends PercistenciaFactory{

	public Percistencias criarPercistencia(String tipo) {

		switch (tipo) {
		case "Sala":
			return new PercistenciaSala();

		case "Evento":
			return new PercistenciaEvento();

		case "Alocar":
			return new PercistenciaAlocar();

		default:
			break;
		}
		
		return null;
	}

	
}
