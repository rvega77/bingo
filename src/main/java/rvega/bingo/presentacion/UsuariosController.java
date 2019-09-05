package rvega.bingo.presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.dominio.Bingo;
import rvega.bingo.dominio.Usuario;

/**
 *
 * @author rvega
 */
@Named
@ViewScoped
public class UsuariosController implements Serializable {

    @Inject
    private Bingo bingo;
    @Inject
    private CartonFactory factory;

    // lista de usuarios
    private List<Usuario> lstUsuario;

    @PostConstruct
    public void init() {
        lstUsuario = factory.getUsuarios();
    }

    public Integer contarFaltantes(Usuario u) {
        return factory.getCarton(u).contarFaltantes(bingo.getListaUtilizados());
    }

    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

}
