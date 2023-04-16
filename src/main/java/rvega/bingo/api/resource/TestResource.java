package rvega.bingo.api.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicInteger;
import rvega.bingo.dominio.Carton;
import rvega.bingo.dominio.Usuario;
import rvega.bingo.negocio.MensajeApplication;
import rvega.bingo.socket.PushBean;
import rvega.bingo.util.CartonFactory;

/**
 *
 * @author rodri
 */
@Path("test")
public class TestResource {
    
    private static final AtomicInteger contador = new AtomicInteger(0);
    
    @Inject
    private PushBean pushBean;
    @Inject
    private MensajeApplication mensaje;
    @Inject
    private CartonFactory factory;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Carton getCarton() {
        Usuario usr = new Usuario();
        usr.setNombre("USR." + contador.incrementAndGet());
//        pushBean.enviarUsuario(usr.getNombre());
//        mensaje.enviarMensajeSistema(LocalDateTime.now() + " : " + usr.getNombre());
        return factory.crear(usr);
    }
    
}
