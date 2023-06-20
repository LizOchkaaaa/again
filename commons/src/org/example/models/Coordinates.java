package org.example.models;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Comparable<Coordinates>, Serializable {
    public Coordinates(){}
    private Double x; //Значение поля должно быть больше -564
    private Integer y; //Максимальное значение поля: 441

    public Coordinates(Double x , Integer y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    @Override
    public int compareTo(Coordinates o) {
        if (o.getX() - this.x != 0) return (int)(o.getX() - this.x);
        if (o.getY() - this.y != 0) return o.getY() - this.y;
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
