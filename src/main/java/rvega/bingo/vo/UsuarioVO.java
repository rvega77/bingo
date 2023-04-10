package rvega.bingo.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rvega.bingo.dominio.Usuario;

/**
 *
 * @author rodri
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioVO implements Serializable {

    private Usuario usuario;
    private int cantidad;
}
