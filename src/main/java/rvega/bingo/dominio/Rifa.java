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
    
    public synchronized void comprar(int idx, Usuario usr) {
        RifaNicho n = nichos.get(idx);
        if (n.getUsuario() != null){
            throw new IllegalStateException("Ya fue comprado por : " + n.getUsuario().getNombre());
        }
        n.setUsuario(usr);        
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
