package rvega.bingo.util;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;
import rvega.bingo.dominio.Usuario;

/**
 *
 * @author rodri
 */
@Named
@SessionScoped
@Data
public class UsuarioSession implements Serializable {

    private Usuario usuario;
    private int tamanno = 30;

    public boolean isValido() {
        return usuario != null;
    }

    public String getNombre() {
        String n = "Anónimo";
        if (isValido()) {
            n = usuario.getNombre();
        }

        return n;
    }

    public String aumentarTamanno() {
        tamanno += 5;
        return "/?faces-redirect=true";
    }

    public String disminuirTamanno() {
        tamanno -= 5;
        return "/?faces-redirect=true";
    }

}
