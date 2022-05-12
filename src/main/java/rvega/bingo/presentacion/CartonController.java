package rvega.bingo.presentacion;

import rvega.bingo.util.CartonFactory;
import rvega.bingo.dominio.Carton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import rvega.bingo.dominio.CartonLinea;
import rvega.bingo.socket.PushBean;
import rvega.bingo.util.UsuarioSession;

/**
 *
 * @author rvega77
 */
@Named
@ViewScoped
@Data
public class CartonController implements Serializable {

    @Inject
    private PushBean pushBean;
    @Inject
    private UsuarioSession usuarioSession;

    @Inject
    private DisplayController displayController;
    @Inject
    private CartonFactory factory;
    @Inject
    private RifaController rifaController;

    private Carton carton;
    //derivado para renderizar el carton
    private List<CartonLinea> lstCartonLineas;

    // numero de rifa
    private int numero;

    @PostConstruct
    public void init() {

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
        } catch (Exception ex) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        }
    }

    public void enviarMensaje() {
        FacesContext
                .getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje Enviado...", null));
    }

    public void comprar() {
        System.out.println("NUMERO : " + numero);
        try {
            rifaController.comprar(usuarioSession.getUsuario(), numero);
            pushBean.enviarJuego("");
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Número Comprado", null));
        } catch (Exception ex) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        }
    }

}
