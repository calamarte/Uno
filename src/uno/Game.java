package uno;

import uno.players.Player;
import uno.views.GUI.GUI;
import uno.views.View;
import uno.views.terminal.Terminal;

import java.util.*;

public class Game {

    private View view = new Terminal();

    private LinkedList<Event> history;

    private LinkedList<Carta> inGame;
    private List<Carta> robList;
    private Player[] players;
    private Baraja baraja;
    private boolean running = false;
    private int turno = 0;
    private boolean rightDirection = true;

    public Game(Player... players){
        this.history = new LinkedList();
        this.inGame = new LinkedList();
        this.robList = new ArrayList();
        this.players = players;
        this.baraja = new Baraja();
    }

    //Getters
    public View getView() {
        return view;
    }

    public LinkedList<Event> getHistory() {
        return history;
    }

    public LinkedList<Carta> getInGame() {
        return inGame;
    }

    public List<Carta> getRobList() {
        return robList;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Baraja getBaraja() {
        return baraja;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isRightDirection() {
        return rightDirection;
    }

    public void run(){
        view.start();

        baraja.shuffle();

        inGame.push(baraja.next());
        while (!inGame.peek().getType().equals(Carta.Type.NORMAL)){
            inGame.push(baraja.next());
        }

        setUpPlayers();

        this.running = true;
        Player player;
        Carta choosed = null;
        Carta.Type type;
        boolean isValid, rob;
        Carta.Color currentColor = inGame.peek().getColor();
        while (this.running){
            isValid = false;
            rob = false;



            player = nextPlayer();


            boolean jumpTourn = false;
            while (!isValid) {
                choosed = player.choose(inGame.peek(), currentColor, !robList.isEmpty());
                history.push(new Event(player, Event.Verb.CHOOSE, choosed));

                if(null == choosed && !rob){

                    if(!robList.isEmpty()){
                        robAll(player);
                        jumpTourn = true;
                        break;
                    }

                    player.push(baraja.next());
                    rob = true;
                    continue;
                }else if (choosed == null){
                    jumpTourn = true;
                    break;
                }

                isValid = isValidCard(choosed, currentColor);
            }

            if(jumpTourn){
                continue;
            }


            player.remove(choosed);
            history.push(new Event(player, Event.Verb.PLAY, choosed));

            if(player.handIsEmpty()){

                win(player);
                running = false;
                break;
            }

            inGame.push(choosed);

            type = choosed.getType();
            boolean chooseColor = false;
            switch (type){
                case CHANGE_DIRECTION:{
                    rightDirection = !rightDirection;
                    view.changeDirection(player);
                    break;
                }
                case FORBIDDEN:{
                    view.jump(nextPlayer());
                    break;
                }
                case GET_TWO:{
                    for (int i = 0; i < 2; i++) {
                        robList.add(baraja.next());
                    }
                    break;
                }
                case GET_FOUR:{
                    for (int i = 0; i < 4; i++) {
                        robList.add(baraja.next());
                    }

                }
                case SELECT_COLOR:{
                    chooseColor = true;
                    break;
                }
            }

            if(chooseColor){
                currentColor = player.chooseColor();
                history.push(new Event(player, Event.Verb.CHOOSE, currentColor));
            }else {
                currentColor = choosed.getColor();
            }

        }

    }

    private void win(Player player) {
        view.win(player);
    }

    private void setUpPlayers(){
        view.setUpPlayers();

        Player current;
        for (int i = 0; i < players.length; i++) {
            current = players[i];
            current.setGame(this);

            for (int j = 0; j < 7; j++) {
                current.push(baraja.next());
            }
        }

    }

    private void robAll(Player player){
        for (Carta c : robList) {
            player.push(c);
        }


        history.push(new Event(player, Event.Verb.ROB, robList));
        view.rob(player, robList);

        robList.clear();
    }

    public boolean isValidCard(Carta carta, Carta.Color color){
        if(null == carta)return true;

        Carta inGameCard = inGame.peek();

        if(!robList.isEmpty() && (inGameCard.getType() == Carta.Type.GET_FOUR || inGameCard.getType() == Carta.Type.GET_TWO)){
            return carta.getType() == Carta.Type.GET_FOUR || carta.getType() == Carta.Type.GET_TWO;
        }

        if (null == color){
            color = carta.getColor();
        }

        Carta.Type tipo = carta.getType();


        switch (tipo){
            case GET_FOUR:
            case SELECT_COLOR:{
                return true;
            }
            case GET_TWO:
            case FORBIDDEN:
            case CHANGE_DIRECTION:
                return carta.getColor().equals(color)
                        || carta.getType().equals(inGameCard.getType());
            case NORMAL:{
                return carta.getColor().equals(color)
                        || carta.getValue().equals(inGameCard.getValue());
            }
        }
        return false;
    }

    public Player whoIsNext(){
        int turno = this.turno;
        if(rightDirection) {
            turno = turno < players.length - 1 ? turno + 1 : 0;
        }else {
            turno = turno > 0 ? turno - 1 : players.length - 1;
        }

        return players[turno];

    }

    private Player nextPlayer(){
        if(rightDirection) {
            turno = turno < players.length - 1 ? turno + 1 : 0;
        }else {
            turno = turno > 0 ? turno - 1 : players.length - 1;
        }

        return players[turno];
    }
}
