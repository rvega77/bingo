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
import rvega.bingo.dominio.Numero;

/**
 *
 * @author rvega77
 */
@Named
@ApplicationScoped
public class DisplayController {

    @Inject
    private Bingo bingo;
    private Random rnd;
    private Numero numeroCarton;
    private List<BingoLinea> lstBingoLinea;
    private String titulo = "BINGO";

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

    public void sacarNumero() {
        if (bingo.disponibles()) {
            do {
                int numero = rnd.nextInt(75) + 1;
                numeroCarton = bingo.getMapTotal().get(numero);
                System.out.println(numero + " : " + numeroCarton.isUtilizado());
            } while (numeroCarton.isUtilizado());
            numeroCarton.setUtilizado(true);
            actualizarTablero();
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "TABLERO LLENO", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
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

}
