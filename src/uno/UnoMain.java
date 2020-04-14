package uno;

import uno.players.Brain;
import uno.players.Humano;
import uno.players.IAPlayer;
import uno.players.Player;

import java.io.IOException;

public class UnoMain {
    public static void main(String[] args) throws IOException {

        Player[] players = {
                new IAPlayer("RONALDO", Brain.Personality.CLEVER),
                new IAPlayer("PACO", Brain.Personality.CLEVER),
                new IAPlayer("BIEL", Brain.Personality.CLEVER)
        };

        Game game = new Game(players);
        //game.run();

        /*Game game = new Game(new Humano("local"), new HumanoOnline("online"));*/

        game.run();

        System.out.println(game.getHistory());

    }


}
