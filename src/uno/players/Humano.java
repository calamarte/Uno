package uno.players;


import uno.Carta;
import uno.Game;

import java.util.*;

public class Humano implements Player {

    private static Scanner sc = new Scanner(System.in);

    private Game context;

    protected String name;
    protected List<Carta> cartas;


    public Humano(String name){
        this.name = name;
        cartas = new ArrayList();
    }

    @Override
    public void setGame(Game game) {
        this.context = game;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Carta> getHand() {
        return this.cartas;
    }

    @Override
    public void push(Carta... carta) {
        cartas.addAll(Arrays.asList(carta));
    }

    @Override
    public int handSize() {
        return cartas.size();
    }

    @Override
    public boolean handIsEmpty() {
        return cartas.isEmpty();
    }

    @Override
    public Carta choose(Carta stackCard, Carta.Color color, boolean rob) {
        return this.context.getView().choose(this, stackCard, color);
    }

    @Override
    public boolean remove(Carta carta) {
        return cartas.remove(carta);
    }

    @Override
    public Carta.Color chooseColor() {
        return this.context.getView().chooseColor(this);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
