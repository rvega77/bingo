package rvega.bingo.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 *
 * @author rodri
 */
@Named("imagenUtil")
@ApplicationScoped
public class ImagenEsperaApplication {

    private int idx;

    public String getNombre() {
        if (idx >= DATA.length) {
            idx = 0;
        }

        return DATA[idx++];
    }

    private static final String[] DATA = {
        "gato.gif",
        "hourglass.gif",
        "molino.gif",
        "planta.gif",
        "spinner-3.gif",
        "skateboarding.gif",
        "rueda-gobierno.gif",
        "spinner.gif"
    };
}
