package rvega.bingo.presentacion;

import rvega.bingo.util.CartonFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import rvega.bingo.negocio.Bingo;
import rvega.bingo.dominio.Usuario;
import rvega.bingo.negocio.Rifa;
import rvega.bingo.util.ConfiguracionApplication;

/**
 *
 * @author rvega
 */
@Named
@ViewScoped
public class UsuariosController implements Serializable {

    @Inject
    private ConfiguracionApplication cnf;
    @Inject
    private Bingo bingo;
    @Inject
    private CartonFactory factory;
    @Inject
    private Rifa rifa;

    // lista de usuarios
    private List<Usuario> lstUsuario;

    @PostConstruct
    public void init() {
        if (cnf.isModoBingo()) {
            lstUsuario = factory.getUsuarios();
        }
        if (cnf.isModoRifa()){
            lstUsuario = rifa.getUsuarios();
        }
    }

    public Integer contarFaltantes(Usuario u) {
        return factory.getCarton(u).contarFaltantes(bingo.getListaUtilizados());
    }
    
    public Integer obtenerPosicion(Usuario u) {
        return rifa.getPosicion(u);
    }

    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

}
