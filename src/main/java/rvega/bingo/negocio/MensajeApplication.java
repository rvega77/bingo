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
    
    private static final Mensaje MENSAJE = new Mensaje("Bingo", "Puedes enviar mensajes desde tu celular...");
    
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
    
    public void purgar() {
        lstMensaje.clear();
        agregar(new Mensaje("Bingo", "Reiniciar Tablero..."));
    }
    
    public int getCantidad() {
        return lstMensaje.size();
    }
    
    public List<Mensaje> getLstMensaje() {
        return new ArrayList<>(lstMensaje);
    }
    
}
