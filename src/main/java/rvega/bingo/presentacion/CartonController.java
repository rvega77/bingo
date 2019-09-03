package rvega.bingo.presentacion;

import rvega.bingo.dominio.Carton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.dominio.CartonLinea;
import rvega.bingo.dominio.Usuario;

/**
 *
 * @author rvega77
 */
@Named
@SessionScoped
public class CartonController implements Serializable {

    @Inject
    private CartonFactory factory;

    private int tamanno = 30;
    private Usuario usuario;
    private String nombre;
    private Carton carton;
    private List<CartonLinea> lstCartonLineas;

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
        usuario = new Usuario(nombre);
        nuevoCarton();
    }

    public void jugar() {
        String nro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nro");
        System.out.println("NRO: " + nro);
        carton.conmutar(Integer.parseInt(nro));
    }

    public void nuevoCarton() {
        carton = factory.crear(usuario);
        convertir();
    }
    
    public String aumentarTamanno(){
        tamanno += 5;
        return "/carton.xhtml?faces-redirect=true";
    }
    
    public String disminuirTamanno(){
        tamanno -= 5;
        return "/carton.xhtml?faces-redirect=true";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

}
