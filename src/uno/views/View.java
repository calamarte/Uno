package uno.views;

import uno.Carta;
import uno.players.Player;

import java.util.List;

public interface View {

    void start();
    void setUpPlayers();
    void stop();
    Carta choose(Player player, Carta stackCard, Carta.Color gameColor);
    Carta.Color chooseColor(Player player);
    void rob(Player target, Carta carta);
    void rob(Player target, List<Carta> cartas);
    void jump(Player target);
    void changeDirection(Player who);
    void win(Player winner);
}
