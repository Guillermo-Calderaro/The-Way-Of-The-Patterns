public class CarRental {

    protected float fuelConsumed;
    protected int days;
    protected Model model;
    protected float insuranceRate;
    protected boolean hasInsurance;
    protected boolean hasRefuelOnReturn;
    protected float refuelPrice;
    public CarRental(Model m, int rentalDays) {
        model = m;
        days = rentalDays;
        hasInsurance = false;
        hasRefuelOnReturn = false;
    }
    public float calcPrice() {
        float price = (model.getPrice() * days);
        if (hasInsurance)
            price += insuranceAmount();
        if (hasRefuelOnReturn)
            price += refuelPrice();
        return price;
    }
    public int getDaysRented() {
        return days;
    }
    public Model getModel() {
        return model;
    }
    public float getFuelConsumed() {
        return fuelConsumed;
    }
    public void setFuelConsumed(float amount) {
        fuelConsumed = amount;
    }
    private float insuranceAmount() {
        return insuranceRate * getDaysRented();
    }
    public void setInsurance(float rate) {
        insuranceRate = rate;
        hasInsurance = true;
    }
    private float refuelPrice() {
        return(getModel().getFuelCapacity() - getFuelConsumed()) * refuelPrice;
    }
    public void setRefuelOnReturn(float pricePerGallon) {
        refuelPrice = pricePerGallon;
        hasRefuelOnReturn = true;
    }
}
