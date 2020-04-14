package uno;

import uno.players.Player;

public class Event {
    public enum Verb{
        CHOOSE, //info -> Card or Color
        PLAY,   //info -> Card
        ROB     //info -> List<Card>
    }

    private Player player;
    private Verb verb;
    private Object info;

    public Event(Player player, Verb verb, Object info) {
        this.player = player;
        this.verb = verb;
        this.info = info;
    }

    public Player getPlayer() {
        return player;
    }

    public Verb getVerb() {
        return verb;
    }

    public Object getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return player + " " + verb + " " + info;
    }
}
