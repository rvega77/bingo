package rvega.bingo.presentacion;

import rvega.bingo.negocio.MensajeApplication;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.dominio.Mensaje;
import rvega.bingo.negocio.Rifa;
import rvega.bingo.dominio.Usuario;

/**
 * permite crear una rifa
 *
 * @author rvega
 */
@Named
@ApplicationScoped
public class RifaController {
    
    @Inject
    private Rifa rifa;
    @Inject
    private MensajeApplication mensajeApplication;
    
    private int filas;
    private int columnas;
    
    @PostConstruct
    public void init() {
        filas = 8;
        columnas = 6;
        rifa.crearNichos(filas * columnas);
    }
    
    public long getCantidadUsuarios() {
        return rifa.getNichos()
                .stream()
                .filter(n -> n.getUsuario() != null)
                .count();
    }
    
    public void sortear() {
        rifa.sortear();
    }
    
    public void comprar(Usuario usr, int numero) {
        rifa.comprar(numero - 1, usr);
        mensajeApplication.agregar(new Mensaje(usr.getNombre(), "Ha comprado el Número : " + numero));
    }
    
    public Rifa getRifa() {
        return rifa;
    }
    
    public void setRifa(Rifa rifa) {
        this.rifa = rifa;
    }
    
    public int getFilas() {
        return filas;
    }
    
    public void setFilas(int filas) {
        this.filas = filas;
    }
    
    public int getColumnas() {
        return columnas;
    }
    
    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    
}
