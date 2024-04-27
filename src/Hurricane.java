import java.io.Serializable;
import java.util.Objects;

public class Hurricane extends NaturalDisaster implements Serializable {

    private int maxWindSpeed;

    public Hurricane(String name, int year, String location, double cost, int maxWindSpeed) {
        super(name, year, location, cost);
        this.maxWindSpeed = maxWindSpeed;
    }

    public int getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(int maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hurricane hurricane = (Hurricane) o;
        return maxWindSpeed == hurricane.maxWindSpeed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxWindSpeed);
    }

    @Override
    public String toString() {
        return "Hurricane [" + name +
                ", " + year + ", " +
                location + ", " +
                currency.format(cost) +
                ", " + maxWindSpeed + " mph]";
    }
}
