package rvega.bingo.presentacion;

import rvega.bingo.dominio.Bingo;
import rvega.bingo.dominio.BingoLinea;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.dominio.Carton;
import rvega.bingo.dominio.Numero;
import rvega.bingo.socket.PushBean;

/**
 *
 * @author rvega77
 */
@Named
@ApplicationScoped
public class DisplayController {
    
    @Inject
    private PushBean pushBean;
    @Inject
    private Bingo bingo;
    @Inject
    private CartonFactory factory;
    
    private Random rnd;
    private Numero numeroCarton;
    private List<BingoLinea> lstBingoLinea;
    private String titulo = "BINGO";
    private int cantidadCartonesPorGanar;
    
    @PostConstruct
    public void init() {
        rnd = new Random();
        bingo.init();
        actualizarTablero();
        numeroCarton = null;
    }
    
    private void actualizarTablero() {
        lstBingoLinea = new ArrayList<>();
        lstBingoLinea.add(new BingoLinea("B", bingo.getListaB()));
        lstBingoLinea.add(new BingoLinea("I", bingo.getListaI()));
        lstBingoLinea.add(new BingoLinea("N", bingo.getListaN()));
        lstBingoLinea.add(new BingoLinea("G", bingo.getListaG()));
        lstBingoLinea.add(new BingoLinea("O", bingo.getListaO()));
    }
    
    public void nuevoUsuario() {
        String usr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("usr");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BIENVENIDA: " + usr, usr);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void sacarNumero() {
        if (bingo.disponibles()) {
            do {
                int numero = rnd.nextInt(75) + 1;
                numeroCarton = bingo.getMapTotal().get(numero);
                System.out.println(numero + " : " + numeroCarton.isUtilizado());
            } while (numeroCarton.isUtilizado());
            numeroCarton.setUtilizado(true);
            actualizarTablero();
            calcularCartonesPorGanar();
            pushBean.enviarJuego(numeroCarton.toFmtString());
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "TABLERO LLENO", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    private void calcularCartonesPorGanar() {
        cantidadCartonesPorGanar = 0;
        List<Integer> numeros = bingo.getListaUtilizados();
        for (Carton c : factory.getCartones()) {
            if (c.contarFaltantes(numeros) <= 1) {
                cantidadCartonesPorGanar++;
            }
        }
        System.out.println("Por Ganar : " + cantidadCartonesPorGanar);
    }
    
    public boolean isExistenPorGanar() {
        return cantidadCartonesPorGanar > 0;
    }
    
    public Bingo getBingo() {
        return bingo;
    }
    
    public void setBingo(Bingo bingo) {
        this.bingo = bingo;
    }
    
    public List<BingoLinea> getLstBingoLinea() {
        return lstBingoLinea;
    }
    
    public Numero getNumeroCarton() {
        return numeroCarton;
    }
    
    public void setNumeroCarton(Numero numeroCarton) {
        this.numeroCarton = numeroCarton;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public int getCantidadCartonesPorGanar() {
        return cantidadCartonesPorGanar;
    }
    
    public void setCantidadCartonesPorGanar(int cantidadCartonesPorGanar) {
        this.cantidadCartonesPorGanar = cantidadCartonesPorGanar;
    }
    
}
