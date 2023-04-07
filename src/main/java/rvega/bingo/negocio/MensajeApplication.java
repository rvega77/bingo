package rvega.bingo.negocio;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
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
        Map<String, String> map = new HashMap<>();
        map.put("usuario", m.getUsuario());
        map.put("texto", m.getTexto());
        pushBean.enviarMensaje(map);
    }

    public void enviarMensajeSistema(String m) {
        agregar(new Mensaje(SISTEMA, m));
    }

    public void purgar() {
        lstMensaje.clear();
    }

    public int getCantidad() {
        return lstMensaje.size();
    }

    public List<Mensaje> getLstMensaje() {
        return new ArrayList<>(lstMensaje);
    }

}
