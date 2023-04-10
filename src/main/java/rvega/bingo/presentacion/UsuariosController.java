package rvega.bingo.presentacion;

import rvega.bingo.util.CartonFactory;
import java.io.Serializable;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import lombok.Data;
import rvega.bingo.dominio.Usuario;
import rvega.bingo.negocio.Rifa;
import rvega.bingo.util.ConfiguracionApplication;
import rvega.bingo.util.TombolaApplication;
import rvega.bingo.vo.UsuarioVO;

/**
 *
 * @author rvega
 */
@Named
@RequestScoped
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
    private List<UsuarioVO> lstUsuario;

    @PostConstruct
    public void init() {
        List<Usuario> lst = new ArrayList<>();
        if (cnf.isModoBingo()) {
            lst = factory.getUsuarios();
        }
        if (cnf.isModoRifa()) {
            lst = rifa.getUsuarios();
        }
        lstUsuario = new ArrayList<>();
        lst.forEach(u -> {
            int cantidad = 0;
            if (cnf.isModoBingo()) {
                cantidad = factory.getCarton(u).contarFaltantes(tombolaApplication.getListaUtilizados());
            }
            if (cnf.isModoRifa()) {
                cantidad = rifa.getPosicion(u);
            }
            lstUsuario.add(new UsuarioVO(u, cantidad));
        });
        // ordenar
        Collections.sort(lstUsuario, (u1, u2) -> u1.getCantidad() - u2.getCantidad());

    }

    public String getNombreColumna() {
        String n = "CANTIDAD";
        if (cnf.isModoBingo()) {
            n = "FALT. PARA GANAR";
        }
        if (cnf.isModoRifa()) {
            n = "NICHO ADQUIRIDO";
        }
        return n;
    }
}
