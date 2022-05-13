package rvega.bingo.presentacion;

import rvega.bingo.negocio.MensajeApplication;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import rvega.bingo.dominio.Mensaje;
import rvega.bingo.negocio.Rifa;
import rvega.bingo.dominio.Usuario;
import rvega.bingo.util.ConfiguracionApplication;

/**
 * permite crear una rifa
 *
 * @author rvega
 */
@Named
@ApplicationScoped
@Data
public class RifaController {

    @Inject
    private ConfiguracionApplication cnf;
    @Inject
    private Rifa rifa;
    @Inject
    private MensajeApplication mensajeApplication;

    @PostConstruct
    public void init() {
        rifa.crearNichos(cnf.getMaxNichos());
    }

    public long getCantidadUsuarios() {
        return rifa.getNichos()
                .stream()
                .filter(n -> n.getUsuario() != null)
                .count();
    }

    public void sortear() {
        rifa.sortear();
    }

    public void adquirir(Usuario usr, int numero) {
        rifa.adquirir(numero, usr);
        rifa.liberar(numero, usr);
        mensajeApplication.agregar(new Mensaje(usr.getNombre(), "rifa # " + numero));
    }

}
