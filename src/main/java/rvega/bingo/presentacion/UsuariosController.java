package rvega.bingo.presentacion;

import rvega.bingo.util.CartonFactory;
import java.io.Serializable;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import lombok.Data;
import rvega.bingo.dominio.Usuario;
import rvega.bingo.negocio.Rifa;
import rvega.bingo.util.ConfiguracionApplication;
import rvega.bingo.util.TombolaApplication;

/**
 *
 * @author rvega
 */
@Named
@ViewScoped
@Data
public class UsuariosController implements Serializable {

    @Inject
    private ConfiguracionApplication cnf;
    @Inject
    private TombolaApplication tombolaApplication;
    @Inject
    private CartonFactory factory;
    @Inject
    private Rifa rifa;

    // lista de usuarios
    private List<Usuario> lstUsuario;

    @PostConstruct
    public void init() {
        lstUsuario = new ArrayList<>();
        if (cnf.isModoBingo()) {
            lstUsuario = factory.getUsuarios();
        }
        if (cnf.isModoRifa()) {
            lstUsuario = rifa.getUsuarios();
        }
    }

    public Integer contarFaltantes(Usuario u) {
        return factory.getCarton(u).contarFaltantes(tombolaApplication.getListaUtilizados());
    }

    public Integer obtenerPosicion(Usuario u) {
        return rifa.getPosicion(u);
    }

}
