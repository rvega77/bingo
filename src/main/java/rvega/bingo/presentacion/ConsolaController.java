    package rvega.bingo.presentacion;

import rvega.bingo.dominio.Bingo;
import java.io.Serializable;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import rvega.bingo.dominio.Numero;

@ManagedBean(name = "consola")
@ApplicationScoped
public class ConsolaController implements Serializable {

    private Random rnd;
    @ManagedProperty("#{bingo}")
    private Bingo bingo;
    private Integer numero;
    private String letra;

    @PostConstruct
    private void iniciar() {
        rnd = new Random();
    }

    public void numeroAleatorio() {
        Numero numeroCarton = null;
        do {
            numero = rnd.nextInt(75) + 1;
            numeroCarton = bingo.getMapTotal().get(numero);
            System.out.println(numero + " : " + numeroCarton.isUtilizado());
        } while (numeroCarton.isUtilizado());

        numeroCarton.setUtilizado(true);
        letra = numeroCarton.getLetra();
    }

    public Bingo getBingo() {
        return bingo;
    }

    public void setBingo(Bingo bingo) {
        this.bingo = bingo;
    }

    
    
    // accesadores
    public Integer getNumero() {
        return numero;
    }

    public String getLetra() {
        return letra;
    }

}
