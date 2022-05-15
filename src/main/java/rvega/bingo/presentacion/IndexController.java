package rvega.bingo.presentacion;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import rvega.bingo.dominio.Usuario;
import rvega.bingo.negocio.MensajeApplication;
import rvega.bingo.socket.PushBean;
import rvega.bingo.util.UsuarioSession;

/**
 *
 * @author rodri
 */
@Named
@RequestScoped
@Data
public class IndexController {

    private static final Logger LOG = Logger.getLogger(IndexController.class.getName());

    @Inject
    private PushBean pushBean;
    @Inject
    private MensajeApplication mensajeApplication;
    @Inject
    private UsuarioSession usuarioSession;

    private String nick;

    public void login() {
        LOG.info("LOGIN : " + nick);
        Usuario usuario = new Usuario();
        usuario.setNombre(nick);
        usuarioSession.setUsuario(usuario);
        //evento
        pushBean.enviarUsuario(usuario.getNombre());
        String m = "Nuevo jugador: " + usuario.getNombre();
        mensajeApplication.enviarMensajeSistema(m);
    }

    public void logout() {
        usuarioSession.setUsuario(null);
    }

}
