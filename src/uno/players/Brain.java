package uno.players;

import uno.Carta;
import uno.Game;

import java.util.*;
import java.util.stream.Collectors;

public class Brain {

    public enum Personality {

        PASSIVE,     //Se limita a jugar con lo que tiene
        AGGRESIVE,   //Ataca cuando puede
        SAVER,       //Guarda las cartas de ataque
        COLOR_FULL,  //Siempre intenta cambia de color
        CLEVER       //El más listo
    }

    //Only use on mind == CLEVER
    private static final int AGGRESSIVE_LINE = 3; //Limite de cartas para ser agresivo

    private Personality mind;
    private Game context;

    Brain(Personality mind) {
        this.mind = mind;
    }

    void setContext(Game context) {
        this.context = context;
    }

    Carta think(List<Carta> posibilities, Carta.Color color){
        return think(posibilities, color, this.mind);
    }

    private Carta think(List<Carta> posibilities, Carta.Color color, Personality mind) {
        switch (mind) {
            case PASSIVE:
                return posibilities.get(0);
            case AGGRESIVE: {
                List<Carta> aggCartas = posibilities.stream()
                        .filter(p -> p.getType() != Carta.Type.NORMAL && p.getType() != Carta.Type.SELECT_COLOR)
                        .collect(Collectors.toList());

                if (aggCartas.isEmpty()) {
                    return posibilities.get(0);
                }

                Carta.Type[] tipos = {
                        Carta.Type.GET_FOUR, Carta.Type.GET_TWO,
                        Carta.Type.FORBIDDEN, Carta.Type.CHANGE_DIRECTION
                };

                for (int i = 0; i < tipos.length; i++) {
                    for (Carta c : aggCartas) {
                        if (tipos[i].equals(c.getType())) {
                            return c;
                        }
                    }
                }
            }
            case SAVER:{
                Optional<Carta> optional = posibilities.stream()
                        .filter(p -> p.getType() == Carta.Type.NORMAL)
                        .findFirst();

                if(optional.isPresent()){
                    return optional.get();
                }

                Carta.Type[] priority = {
                        Carta.Type.SELECT_COLOR, Carta.Type.CHANGE_DIRECTION,
                        Carta.Type.FORBIDDEN, Carta.Type.GET_TWO, Carta.Type.GET_FOUR
                };

                for (int i = 0; i < priority.length; i++) {
                    for (Carta c: posibilities){
                        if(priority[i] == c.getType()){
                            return c;
                        }
                    }
                }

            }
            case COLOR_FULL: {
                Optional<Carta> optional = posibilities.stream()
                        .filter(p -> p.getColor() != color)
                        .findFirst();

                return optional.isPresent() ? optional.get() : posibilities.get(0);
            }
            case CLEVER: {
                Player next = context.whoIsNext();

                if(next.handSize() <= AGGRESSIVE_LINE){
                    return think(posibilities, color, Personality.AGGRESIVE);
                }

                return think(posibilities, color, Personality.SAVER);
            }
        }

        return null;
    }


    boolean isValid(Carta carta, Carta.Color color){
        return context.isValidCard(carta, color);
    }

}
