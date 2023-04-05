package rvega.bingo.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
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
        modo = TipoModo.CONFIG;
        bloqueado = false;
        columnas = 6;
        filas = 8;
    }

    public boolean isModoConfig() {
        return modo == TipoModo.CONFIG;
    }

    public boolean isModoBingo() {
        return modo == TipoModo.BINGO;
    }

    public boolean isModoRifa() {
        return modo == TipoModo.RIFA;
    }

    public int getMaxNichos() {
        return filas * columnas;
    }
}
