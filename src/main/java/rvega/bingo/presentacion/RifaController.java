package rvega.bingo.presentacion;

import java.util.logging.Logger;
import rvega.bingo.negocio.MensajeApplication;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
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

    private static final Logger LOG = Logger.getLogger(RifaController.class.getName());

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

    public void adquirir(Usuario usr, int numero) {
        if (cnf.isBloqueado()) {
            throw new IllegalStateException("Rifa Bloqueada !!");
        }
        rifa.adquirir(numero, usr);
        rifa.liberar(numero, usr);
    }

}
