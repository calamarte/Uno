package uno.views.terminal;

import uno.Carta;
import uno.players.Player;
import uno.views.View;

import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Terminal implements View {

    private static Scanner sc = new Scanner(System.in);

    @Override
    public void start() {
        System.out.println("¡Iniciando UNO!");
    }

    @Override
    public void setUpPlayers() {
        System.out.println("Repartiendo cartas");
    }

    @Override
    public void stop() {
        System.out.println("Finalizando juego...");
    }

    @Override
    public Carta choose(Player player, Carta stackCard, Carta.Color gameColor) {
        List<Carta> cartas = player.getHand();

        System.out.println("######### " + player.getName() + " #########");
        System.out.println("La última carta es :\n" + CardPrinter.card(stackCard) + "\n");
        System.out.println("Color de juego: " + gameColor);
        System.out.println("Seleccione una opción " + player.getName()+":");
        System.out.println("r  --> Robar carta");

        StringJoiner sj = new StringJoiner("      ", "  ", "");

        for (int i = 0; i < cartas.size(); i++) {
            sj.add(i > 9 ? "" : " " + String.valueOf(i));
        }

        System.out.println(CardPrinter.cards(cartas));
        System.out.println("\n" + sj.toString());

        String choose = sc.next();

        if("r".equals(choose)){
            return null;
        }

        int numberChoose;
        try {
            numberChoose = Integer.parseInt(choose);
        }catch (NumberFormatException e){
            return choose(player, stackCard, gameColor);
        }

        if(numberChoose >= cartas.size() || numberChoose < 0){
            choose(player, stackCard, gameColor);
        }

        return cartas.get(numberChoose);
    }

    @Override
    public Carta.Color chooseColor(Player player) {
        Carta.Color[] colores = Carta.Color.values();

        System.out.println("Elige un color: ");
        for (int i = 0; i < colores.length; i++) {
            System.out.println(i + "  --> " + colores[i]);
        }

        int numberChoosed;
        try {
            numberChoosed = Integer.parseInt(sc.next());
        }catch (NumberFormatException e){
            return chooseColor(player);
        }

        if (numberChoosed >= colores.length){
            return chooseColor(player);
        }

        return colores[numberChoosed];
    }

    @Override
    public void rob(Player target, Carta carta) {
        System.out.println(target.getName() + " roba una carta");
    }

    @Override
    public void rob(Player target, List<Carta> cartas) {
        System.out.println(target.getName() + " roba " + cartas.size() + " cartas");
    }

    @Override
    public void jump(Player target) {
        System.out.println("Se ha saltado el turno del jugador " + target.getName());
    }

    @Override
    public void changeDirection(Player who) {
        System.out.println("Se ha cambiado la dirección de juego");
    }

    @Override
    public void win(Player winner) {
        System.out.println(winner.getName() + " ha ganado la partida!");
    }
}
