package rvega.bingo.presentacion;

import rvega.bingo.util.CartonFactory;
import rvega.bingo.dominio.Carton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import rvega.bingo.dominio.CartonLinea;
import rvega.bingo.negocio.MensajeApplication;
import rvega.bingo.util.UsuarioSession;

/**
 *
 * @author rvega77
 */
@Named
@SessionScoped
@Data
public class CartonController implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(CartonController.class.getName());
    
    @Inject
    private MensajeApplication mensajeApplication;
    @Inject
    private UsuarioSession usuarioSession;
    @Inject
    private CartonFactory factory;
    
    private Carton carton;
    //derivado para renderizar el carton
    private List<CartonLinea> lstCartonLineas;
    
    @PostConstruct
    public void init() {
        nuevoCarton();
    }
    
    private void convertir() {
        lstCartonLineas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CartonLinea cl = new CartonLinea();
            cl.setB(carton.getMapColumnas().get("B").get(i));
            cl.setI(carton.getMapColumnas().get("I").get(i));
            cl.setN(carton.getMapColumnas().get("N").get(i));
            cl.setG(carton.getMapColumnas().get("G").get(i));
            cl.setO(carton.getMapColumnas().get("O").get(i));
            lstCartonLineas.add(cl);
        }
        
    }
    
    public void jugar() {
        if (factory.existeUsuario(usuarioSession.getUsuario())) {
            String nro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nro");
            carton.conmutar(Integer.parseInt(nro));
        } else {
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Cartón Inválido", null));
            
        }
    }
    
    public void nuevoCarton() {
        try {
            carton = factory.crear(usuarioSession.getUsuario());
            convertir();
            mensajeApplication.enviarMensajeSistema(usuarioSession.getNombre() + " ha solicitado un nuevo Cartón.");
        } catch (Exception ex) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        }
    }
    
}
