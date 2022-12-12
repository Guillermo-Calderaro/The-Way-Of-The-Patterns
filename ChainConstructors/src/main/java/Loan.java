import java.util.Date;

public class Loan {
    private final CapitalStrategy strategy;
    private float notional;
    private float outstanding;
    private int rating;
    private Date start;
    private Date expiry;
    private Date maturity;

    //This is the constructor for the Term Loan. It takes 2 parameters
    public Loan(float notional, float outstanding, int rating, Date expiry) {
        this.strategy = new TermLoanCapital();
        this.notional = notional;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
    }

    //This is the constructor for the Revolver Loan. It takes 3 parameters
    public Loan(float notional, float outstanding, int rating, Date start, Date expiry) {
        this.strategy = new RevolverCapital();
        this.notional = notional;
        this.outstanding = outstanding;
        this.rating = rating;
        this.start = start;
        this.expiry = expiry;
    }

    //This is the constructor for the RCTL Loan. It takes 3 parameters
    public Loan(float notional, float outstanding, int rating, Date start, Date expiry, Date maturity) {
        this.strategy = new RCTLCapital();
        this.notional = notional;
        this.outstanding = outstanding;
        this.rating = rating;
        this.start = start;
        this.expiry = expiry;
        this.maturity = maturity;
    }

    //TODO: Add a description for this constructor
    public Loan(CapitalStrategy strategy, float notional, float outstanding,
                int rating, Date start, Date expiry, Date maturity) {
        this.strategy = strategy;
        this.notional = notional;
        this.outstanding = outstanding;
        this.rating = rating;
        this.start = start;
        this.expiry = expiry;
        this.maturity = maturity;
    }
}