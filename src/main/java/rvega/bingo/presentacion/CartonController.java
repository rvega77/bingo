package rvega.bingo.presentacion;

import rvega.bingo.dominio.Carton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.dominio.CartonLinea;
import rvega.bingo.dominio.Usuario;
import rvega.bingo.socket.PushBean;

/**
 *
 * @author rvega77
 */
@Named
@SessionScoped
public class CartonController implements Serializable {

    @Inject
    private PushBean pushBean;

    @Inject
    private CartonFactory factory;

    private int tamanno = 30;
    private Usuario usuario;
    private Carton carton;
    //derivado para renderizar el carton
    private List<CartonLinea> lstCartonLineas;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
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

    public void login() {
        usuario.setTiempo(new Date());
        nuevoCarton();
        //evento
        pushBean.enviarUsuario(usuario.getNombre());
    }

    public void logout() {
        usuario.setNombre(null);
    }

    public void jugar() {
        if (factory.existeUsuario(usuario)) {
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
            carton = factory.crear(usuario);
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

    public String aumentarTamanno() {
        tamanno += 5;
        return "/carton.xhtml?faces-redirect=true";
    }

    public String disminuirTamanno() {
        tamanno -= 5;
        return "/carton.xhtml?faces-redirect=true";
    }

    public List<CartonLinea> getLstCartonLineas() {
        return lstCartonLineas;
    }

    public void setLstCartonLineas(List<CartonLinea> lstCartonLineas) {
        this.lstCartonLineas = lstCartonLineas;
    }

    public CartonFactory getFactory() {
        return factory;
    }

    public void setFactory(CartonFactory factory) {
        this.factory = factory;
    }

    public int getTamanno() {
        return tamanno;
    }

    public void setTamanno(int tamanno) {
        this.tamanno = tamanno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
