package rvega.bingo.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 *
 * @author rodri
 */
@Named
@ApplicationScoped
public class ColorFactory {

//    private static final String[] COLORES = {
//        "#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3", "#fdb462", "#b3de69", "#fccde5", "#d9d9d9", "#bc80bd", "#ccebc5", "#ffed6f"
//    };
    private static final String[] COLORES = {
        "#8dd3c7", "#ffffb3", "#bebada", "#fb8072", "#80b1d3", "#fdb462", "#b3de69", "#fccde5", "#d9d9d9", "#bc80bd", "#ccebc5", "#ffed6f"
    };

    private int idx = 0;

    public String get() {
        if (idx >= COLORES.length) {
            idx = 0;
        }
        String color = COLORES[idx++];
        return color;
    }

}
