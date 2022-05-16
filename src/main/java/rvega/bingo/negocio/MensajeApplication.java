package rvega.bingo.negocio;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.dominio.Mensaje;
import rvega.bingo.socket.PushBean;

/**
 *
 * @author rvega77
 */
@Named
@ApplicationScoped
public class MensajeApplication {

    public static final String SISTEMA = "🤖";
    private static final Mensaje MENSAJE = new Mensaje(SISTEMA, "Puedes enviar mensajes desde tu celular...");

    @Inject
    private PushBean pushBean;
    private final Queue<Mensaje> lstMensaje = Collections.asLifoQueue(new ArrayDeque<>());

    @PostConstruct
    public void init() {
        lstMensaje.add(MENSAJE);
    }

    public void agregar(Mensaje m) {
        lstMensaje.add(m);
        pushBean.enviarMensaje(m.toString());
    }

    public void enviarMensajeSistema(String m) {
        agregar(new Mensaje(SISTEMA, m));
    }

    public void purgar() {
        lstMensaje.clear();
        enviarMensajeSistema("Reiniciar Tablero...");
    }

    public int getCantidad() {
        return lstMensaje.size();
    }

    public List<Mensaje> getLstMensaje() {
        return new ArrayList<>(lstMensaje);
    }

}
