package rvega.bingo.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
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
    private String url;
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

    public void crearUrl() {
        // crear url
        if (url == null) {
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            url = ext.getRequestScheme() + "://" + ext.getRequestServerName() + ":" + ext.getRequestServerPort() + ext.getRequestContextPath();
        }
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
