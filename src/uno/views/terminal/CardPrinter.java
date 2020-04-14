package uno.views.terminal;

import uno.Carta;

import java.util.List;
import java.util.StringJoiner;

public class CardPrinter {

    private static final String CARD_MODEL =
            "{an} _____ \n" +
            "{an}|  .  |\n" +
            "{an}| / \\ |\n" +
            "{an}|(lcr)|\n" +
            "{an}| \\./ |\n" +
            "{an}|____ |\n" +
            "{an}1234567 \u001b[0m";



    public static String cards(List<Carta> cartas){
        if(cartas == null || cartas.isEmpty()){
            return "";
        }else if(cartas.size() == 1){
            return card(cartas.get(0));
        }

        String[] formatedCards = new String[cartas.size()];

        for (int i = 0; i < cartas.size(); i++) {
            formatedCards[i] = card(cartas.get(i));
        }

        StringJoiner[] base = new StringJoiner[formatedCards[0].split("\n").length];


        String[] split;
        for (int i = 0; i < formatedCards.length; i++) {
            split = formatedCards[i].split("\n");
            for (int j = 0; j < base.length; j++) {
                if (base[j] == null) {
                    base[j] = new StringJoiner(" ", "", "");
                }

                base[j].add(split[j]);
            }
        }

        StringJoiner totalJoiner = new StringJoiner("\n", "", "");

        for (int i = 0; i < base.length; i++) {
            totalJoiner.add(base[i].toString());
        }

        return totalJoiner.toString();
    }

    public static String card(Carta carta){
        String left = " ", center = " ", right = " ", color = "       ", ansi = "\u001b[0m";

        switch (carta.getType()){
            case NORMAL:{
                center = String.valueOf(carta.getValue());
                break;
            }
            case FORBIDDEN:{
                center = "X";
                break;
            }
            case CHANGE_DIRECTION:{
                left = "<";
                center =  "-";
                right = ">";
                break;
            }
            case GET_TWO:{
                left = "+";
                center = "2";
                break;
            }
            case GET_FOUR:{
                left = "+";
                center = "4";
                break;
            }
            case SELECT_COLOR:{
                left = "R";
                center = "G";
                right = "B";
                break;
            }

        }

        if(carta.getColor() != null) {
            switch (carta.getColor()) {
                case RED: {
                    color = " rojo ";
                    ansi = "\u001b[31m";
                    break;
                }
                case BLUE: {
                    color = " azul ";
                    ansi = "\u001b[34m";
                    break;
                }
                case GREEN: {
                    color = "verde ";
                    ansi = "\u001b[32m";
                    break;
                }
                case YELLOW: {
                    color = "amrll ";
                    ansi = "\u001b[33m";
                    break;
                }
            }
        }

        return format(left, center, right, color, ansi);

    }

    private static String format(String left, String center, String right, String color, String ansi){
        return CARD_MODEL
                .replace("{an}", ansi)
                .replace("l", left)
                .replace("c", center)
                .replace("r", right)
                .replace("1234567", color);
    }

}
