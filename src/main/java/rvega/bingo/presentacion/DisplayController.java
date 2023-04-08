package rvega.bingo.presentacion;

import rvega.bingo.negocio.MensajeApplication;
import rvega.bingo.util.CartonFactory;
import java.util.ArrayDeque;
import rvega.bingo.negocio.Bingo;
import rvega.bingo.dominio.BingoLinea;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;
import lombok.Data;
import rvega.bingo.dominio.Carton;
import rvega.bingo.dominio.Numero;
import rvega.bingo.socket.PushBean;
import rvega.bingo.util.ConfiguracionApplication;
import rvega.bingo.util.TombolaApplication;

/**
 *
 * @author rvega77
 */
@Named
@ApplicationScoped
@Data
public class DisplayController {

    private static final Logger LOG = Logger.getLogger(DisplayController.class.getName());

    @Inject
    private ConfiguracionApplication cnf;
    @Inject
    private MensajeApplication mensajeApplication;
    @Inject
    private TombolaApplication tombolaApplication;
    @Inject
    private PushBean pushBean;
    @Inject
    private Bingo bingo;
    @Inject
    private CartonFactory factory;

    private Numero numeroCarton;
    private List<BingoLinea> lstBingoLinea;
    private List<Integer> lstColumnas;
    private int cantidadCartonesPorGanar;
    // ultimos numeros jugados
    private Deque<Numero> lstUltimosNumeros;

    @PostConstruct
    public void init() {
        bingo.init();
        tombolaApplication.inicializar(bingo.getTotalNumeros());
        actualizarTablero();
        numeroCarton = null;
        cantidadCartonesPorGanar = 0;
        lstUltimosNumeros = new ArrayDeque<>();
    }

    private void actualizarTablero() {
        lstBingoLinea = new ArrayList<>();
        int nroLinea = 0;
        for (char letra : cnf.getBingo().getLetras()) {
            List<Numero> lst = new ArrayList<>();
            for (int idx = 1; idx <= cnf.getBingo().getCantidadPorLinea(); idx++) {
                int posicion = idx + (nroLinea * cnf.getBingo().getCantidadPorLinea());
                lst.add(bingo.getNumero(posicion));
            }
            nroLinea++;
            lstBingoLinea.add(new BingoLinea(Character.toString(letra), lst));
        }
        lstColumnas = new ArrayList<>();
        for (int i = 1; i <= cnf.getBingo().getCantidadPorLinea(); i++) {
            lstColumnas.add(i);
        }

    }

    public Numero get(BingoLinea bl, Integer nro) {
        Numero n = null;
        if (bl != null && nro != null) {
            n = bl.getLstNumeros().get(nro - 1);
        }
        return n;
    }

    public void sacarNumero() {
        if (tombolaApplication.isDisponibles()) {
            if (numeroCarton != null) {
                agregarUltimosUtilizados();
            }
            int numero = tombolaApplication.sacarNumero();
            numeroCarton = bingo.getMap().get(numero);
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
        List<Integer> numeros = tombolaApplication.getListaUtilizados();
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

}
