package rvega.bingo.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import rvega.bingo.dominio.BingoMetadata;
import rvega.bingo.dominio.RifaMetadata;
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
    private RifaMetadata rifa;
    private BingoMetadata bingo;

    @PostConstruct
    public void init() {
        titulo = "CAMARADERIA";
        modo = TipoModo.BINGO;
        bloqueado = false;
        rifa = new RifaMetadata();
        bingo = new BingoMetadata();
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
}
