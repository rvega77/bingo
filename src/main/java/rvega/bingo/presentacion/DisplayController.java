package rvega.bingo.presentacion;

import java.util.ArrayDeque;
import rvega.bingo.dominio.Bingo;
import rvega.bingo.dominio.BingoLinea;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.dominio.Carton;
import rvega.bingo.dominio.Mensaje;
import rvega.bingo.dominio.Numero;
import rvega.bingo.dominio.Usuario;
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
    @Inject
    private MensajeApplication mensajeApplication;

    private Random rnd;
    private Numero numeroCarton;
    private List<BingoLinea> lstBingoLinea;
    private String titulo = "BINGO";
    private int cantidadCartonesPorGanar;
    // ultimos numeros jugados
    private Deque<Numero> lstUltimosNumeros;

    @PostConstruct
    public void init() {
        rnd = new Random();
        bingo.init();
        actualizarTablero();
        numeroCarton = null;
        cantidadCartonesPorGanar = 0;
        lstUltimosNumeros = new ArrayDeque<>();
    }

    private void actualizarTablero() {
        lstBingoLinea = new ArrayList<>();
        lstBingoLinea.add(new BingoLinea("B", bingo.getListaB()));
        lstBingoLinea.add(new BingoLinea("I", bingo.getListaI()));
        lstBingoLinea.add(new BingoLinea("N", bingo.getListaN()));
        lstBingoLinea.add(new BingoLinea("G", bingo.getListaG()));
        lstBingoLinea.add(new BingoLinea("O", bingo.getListaO()));
    }

    public void nuevoUsuario(Usuario usuario) {
        //String usr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("usr");
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BIENVENIDA: " + usr, usr);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        Mensaje m = new Mensaje();
        m.setUsuario("Bingo");
        m.setTexto("Nuevo jugador: " + usuario.getNombre());
        mensajeApplication.agregar(m);

    }

    public void sacarNumero() {
        if (bingo.disponibles()) {
            if (numeroCarton != null) {
                agregarUltimosUtilizados();
            }
            do {
                int numero = rnd.nextInt(75) + 1;
                numeroCarton = bingo.getMapTotal().get(numero);
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

    private void agregarUltimosUtilizados() {
        lstUltimosNumeros.addFirst(numeroCarton);
        if (lstUltimosNumeros.size() > 3) {
            lstUltimosNumeros.removeLast();
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
        //System.out.println("Por Ganar : " + cantidadCartonesPorGanar);
    }

    public boolean isExistenPorGanar() {
        return cantidadCartonesPorGanar > 0;
    }

    public List<Numero> getLstUltimosNumeros() {
        return new ArrayList<>(lstUltimosNumeros);
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
