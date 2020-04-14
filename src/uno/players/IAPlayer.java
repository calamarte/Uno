package uno.players;

import uno.Carta;
import uno.Game;

import java.util.*;

public class IAPlayer implements Player {



    private static String IA_PREFIX = "IA_";
    private String name;
    private Brain brain;

    private List<Carta> cartas;

    public IAPlayer(String name){
        this(name, Brain.Personality.PASSIVE);
    }

    public IAPlayer(String name, Brain.Personality mind) {
        if(name != null && name.length() > 0){
            this.name = IA_PREFIX + name;
        }else{
            this.name = IA_PREFIX + "default";
        }
        this.brain = new Brain(mind);
        cartas = new ArrayList();
    }

    @Override
    public void setGame(Game game) {
        brain.setContext(game);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Carta> getHand() {
        return cartas;
    }

    @Override
    public void push(Carta... carta) { cartas.addAll(Arrays.asList(carta)); }

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

        List<Carta> posibilities = new ArrayList();
        for (Carta carta: cartas){
            if(brain.isValid(carta, color)){
                posibilities.add(carta);
            }
        }

        if(posibilities.isEmpty()){
            return null;
        }

        return brain.think(posibilities, color);
    }

    @Override
    public boolean remove(Carta carta) {
        return cartas.remove(carta);
    }

    @Override
    public Carta.Color chooseColor() {
        Map<Carta.Color, Integer> group = new HashMap();

        for (Carta c :cartas){
            Integer count = group.get(c.getColor());

            if (count == null){
                count = 0;
            }

            group.put(c.getColor(), ++count);
        }

        Map.Entry<Carta.Color, Integer> max = null;
        for (Map.Entry<Carta.Color, Integer> entry : group.entrySet()){
            if(max == null || max.getValue() < entry.getValue()){
                max = entry;
            }

        }

        if(max == null){
            return Carta.Color.BLUE;
        }

        return max.getKey();
    }

   /* private boolean isValid(Carta stackCarta, Carta.Color stackColor, Carta carta, boolean rob){

        if(rob){
            switch (stackCarta.getType()){
                case GET_TWO:
                case GET_FOUR: {
                    return stackCarta.getType().equals(carta.getType());
                }
                default:return false;
            }
        }

        switch (carta.getType()){
            case GET_FOUR:
            case SELECT_COLOR:{
                return true;
            }
            case GET_TWO:
            case FORBIDDEN:
            case CHANGE_DIRECTION:
                return carta.getColor().equals(stackColor)
                        || carta.getType().equals(stackCarta.getType());
            case NORMAL:{
                return carta.getColor().equals(stackColor)
                        || carta.getValue().equals(stackCarta.getValue());
            }
        }
        return false;

    }*/

    @Override
    public String toString() {
        return name;
    }
}
