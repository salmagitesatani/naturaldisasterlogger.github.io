import java.io.Serializable;
import java.util.Objects;

public class Earthquake extends NaturalDisaster implements Serializable {

    private double magnitude;

    public Earthquake(String name, int year, String location, double cost, double magnitude) {
        super(name, year, location, cost);
        this.magnitude = magnitude;
    }
    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Earthquake that = (Earthquake) o;
        return Double.compare(magnitude, that.magnitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), magnitude);
    }

    @Override
    public String toString() {
        return "Earthquake [" + name + ", " +
                year + ", " + location +
                ", " + currency.format(cost) + ", "
                + magnitude + " magnitude]";
    }
}

