package uno;

import java.util.Objects;

public class Carta {

    public enum Color{
        BLUE,
        RED,
        GREEN,
        YELLOW
    }

    public enum Type{
        NORMAL,
        FORBIDDEN,
        GET_TWO,
        GET_FOUR,
        SELECT_COLOR,
        CHANGE_DIRECTION
    }

    private Integer value;
    private Color color;
    private Type type;

    public Carta(Integer value, Color color, Type type) {
        this.value = value;
        this.color = color;
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return Objects.equals(value, carta.value) &&
                color == carta.color &&
                type == carta.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, color, type);
    }

    @Override
    public String toString() {
        return "uno.Carta{" +
                "value=" + value +
                ", color=" + color +
                ", type=" + type +
                '}';
    }
}
