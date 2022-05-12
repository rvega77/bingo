package rvega.bingo.util;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import lombok.Data;
import rvega.bingo.dominio.TipoModo;

/**
 *
 * @author rodri
 */
@Named("cnf")
@ApplicationScoped
@Data
public class ConfiguracionApplication {

    private String titulo;
    private TipoModo modo;
    private boolean bloqueado;
    private int columnas;
    private int filas;

    @PostConstruct
    public void init() {
        titulo = "CAMARADERIA";
        modo = TipoModo.BINGO;
        bloqueado = false;
        columnas = 6;
        filas = 8;
    }

    public boolean isModoBingo() {
        return modo == TipoModo.BINGO;
    }

    public boolean isModoRifa() {
        return modo == TipoModo.RIFA;
    }
}
