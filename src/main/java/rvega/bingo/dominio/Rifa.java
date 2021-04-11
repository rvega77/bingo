package rvega.bingo.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author rvega
 */
@Named
@ApplicationScoped
public class Rifa {
    
    private List<RifaNicho> nichos;
    private int maxNichos;
    
    @PostConstruct
    public void init() {
    }
    
    public void testGanadores() {
        Random rnd = new Random();
        
        comprar(rnd.nextInt(maxNichos), new Usuario("URS TEST 1"));
        comprar(rnd.nextInt(maxNichos), new Usuario("URS TEST 2"));
        comprar(rnd.nextInt(maxNichos), new Usuario("URS TEST 3"));
        comprar(rnd.nextInt(maxNichos), new Usuario("URS TEST 4"));
        comprar(rnd.nextInt(maxNichos), new Usuario("URS TEST 5"));
        comprar(rnd.nextInt(maxNichos), new Usuario("URS TEST 6"));
        comprar(rnd.nextInt(maxNichos), new Usuario("URS TEST 7"));
        comprar(rnd.nextInt(maxNichos), new Usuario("URS TEST 8"));
    }
    
    public void comprar(int idx, Usuario usr) {
        //todo logica para comprar
        nichos.get(idx).setUsuario(usr);
        
    }
    
    public void crearNichos(int max) {
        maxNichos = max;
        
        nichos = new ArrayList<>();
        for (int i = 0; i < maxNichos; i++) {
            nichos.add(new RifaNicho(i + 1));
        }
    }
    
    public void sortear() {
        nichos.forEach(n -> n.setGanador(false));
        
        Random rnd = new Random();
        nichos.get(rnd.nextInt(maxNichos)).setGanador(true);
        
    }
    
    public List<RifaNicho> getNichos() {
        return nichos;
    }
    
    public void setNichos(List<RifaNicho> nichos) {
        this.nichos = nichos;
    }
    
}
