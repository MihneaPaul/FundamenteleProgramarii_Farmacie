package Domain;

/**
 * Created by Mihnea on 29.10.2017.
 */
public class Medicament {
    private String name;
    private int price;

    public Medicament(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicament that = (Medicament) o;

        if (price != that.price) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
