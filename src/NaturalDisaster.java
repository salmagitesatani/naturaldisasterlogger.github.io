import java.io.Serializable;
import java.util.Objects;
import java.text.NumberFormat;

public abstract class NaturalDisaster implements Serializable {

    protected String name;
    protected int year;
   protected  double cost;
   protected String location;

    protected NumberFormat currency = NumberFormat.getCurrencyInstance();

    protected NaturalDisaster(String name, int year, String location, double cost) {
        this.name = name;
        this.year = year;
        this.location = location;
        this.cost = cost;
    }

    public NaturalDisaster(String name) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NaturalDisaster that = (NaturalDisaster) o;
        return year == that.year && Double.compare(cost, that.cost) == 0 && Objects.equals(name, that.name) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, cost, location);
    }
}

