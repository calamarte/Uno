package uno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Baraja {

    private final List<Carta> values;


    public Baraja(){
        this.values = new ArrayList();
        setUp();
    }

    public void shuffle(){
        System.out.println("Barajando " + values.size() + " cartas");

        Random random = new Random();

        int rIndex;
        for (int i = 0; i < values.size(); i++) {
            rIndex = random.nextInt(values.size() - 1);
            values.add(rIndex, values.remove(i));
        }
    }

    public Carta next(){
        return !values.isEmpty() ? values.remove(0) : null;
    }

    public Carta nextRandom(){
        if(values.isEmpty()){
           return null;
        }

        Random r = new Random();
        return values.remove(r.nextInt(values.size() - 1));
    }

    public void setUp(){
        if(!values.isEmpty()){
            values.clear();
        }

        Carta.Color[] colores = Carta.Color.values();

        Carta prohibido, sentido, getTwo;
        for (int i = 0; i < colores.length; i++) {

            Carta normal;
            for (int j = 0; j <= 9; j++) {
                normal = new Carta(j, colores[i], Carta.Type.NORMAL);

                if(j > 0) {
                    values.add(normal);
                }

                values.add(normal);
            }

            prohibido = new Carta(null, colores[i], Carta.Type.FORBIDDEN);
            values.add(prohibido);
            values.add(prohibido);

            sentido = new Carta(null, colores[i], Carta.Type.CHANGE_DIRECTION);
            values.add(sentido);
            values.add(sentido);

            getTwo = new Carta(null, colores[i], Carta.Type.GET_TWO);
            values.add(getTwo);
            values.add(getTwo);
        }

        Carta getFour = new Carta(null, null, Carta.Type.GET_FOUR);
        Carta chageColor = new Carta(null, null, Carta.Type.SELECT_COLOR);
        for (int i = 0; i < 4; i++) {
            values.add(getFour);
            values.add(chageColor);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Iterator it = values.iterator();

        while (it.hasNext()){
            sb.append(it.next() + "\n");
        }

        return sb.toString();
    }
}
