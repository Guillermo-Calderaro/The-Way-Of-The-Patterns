import lombok.Getter;

@Getter
public class Model {
    private float fuelCapacity;
    private float price;
    private String name;
    public Model(float fuelCapacity, float price, String name) {
        this.fuelCapacity = fuelCapacity;
        this.price = price;
        this.name = name;
    }
}
