package rvega.bingo.socket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

/**
 *
 * @author rvega
 */
@Named
@ApplicationScoped
public class PushBean {

    @Inject
    @Push(channel = "bingoUsuarioChannel")
    private PushContext usuarioChannel;

    @Inject
    @Push(channel = "bingoJuegoChannel")
    private PushContext juegoChannel;

    @Inject
    @Push(channel = "bingoMensajeChannel")
    private PushContext mensajeChannel;

    @Inject
    @Push(channel = "bingoCartonChannel")
    private PushContext cartonChannel;

    public void enviarUsuario(String data) {
        usuarioChannel.send(data);
    }

    public void enviarJuego(String data) {
        juegoChannel.send(data);
    }

    public void enviarMensaje(String data) {
        mensajeChannel.send(data);
    }

    public void enviarCarton(String data) {
        cartonChannel.send(data);
    }
}
