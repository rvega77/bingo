package rvega.bingo.presentacion;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rvega.bingo.dominio.Rifa;

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

    private int filas;
    private int columnas;

    @PostConstruct
    public void init() {
        filas = 8;
        columnas = 6;
        rifa.crearNichos(filas * columnas);
        rifa.testGanadores();
    }

    public long getCantidadUsuarios() {
        return rifa.getNichos()
                .stream()
                .filter(n -> n.getUsuario() != null)
                .count();
    }
    
    public void sortear(){
        rifa.sortear();
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
