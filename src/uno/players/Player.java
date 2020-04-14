package uno.players;

import uno.Carta;
import uno.Game;

import java.util.List;

public interface Player {

    String getName();
    List<Carta> getHand();
    void setGame(Game game);
    void push(Carta... carta);
    int handSize();
    boolean handIsEmpty();
    Carta choose(Carta stackCard, Carta.Color color, boolean rob);
    boolean remove(Carta carta);
    Carta.Color chooseColor();
}
