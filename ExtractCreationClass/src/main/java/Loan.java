import java.util.Date;

public class Loan {

    private double notional;
    private double outstanding;
    private int rating;
    private Date start;
    private CapitalStrategy capitalStrategy;
    private Date expiry;
    private Date maturity;


    protected Loan(double notional, Date start, Date expiry,
                   Date maturity, int riskRating, CapitalStrategy strategy) {
        this.notional = notional;
        this.start = start;
        this.expiry = expiry;
        this.maturity = maturity;
        this.rating = riskRating;
        this.capitalStrategy = strategy;
    }
    public double calcCapital() {
        return capitalStrategy.calc(this);
    }
    public void setOutstanding(double newOutstanding) {
        outstanding = newOutstanding;
    }

    // ... even more methods for dealing with the primary responsibility of a Loan, not shown here

    public static Loan newAdvisor(double notional, Date start,
                                  Date maturity, int rating){
        return new Loan(notional, start, null, maturity, rating, new TermLoanCapital());
    }
    public static Loan newLetterOfCredit(double notional, Date start,
                                         Date maturity, int rating) {
        return new Loan(notional, start, null, maturity, rating, new TermLoanCapital());
    }
    public static Loan newRCTL(double notional, Date start,
                               Date expiry, Date maturity, int rating) {
        return new Loan(notional, start, expiry, maturity, rating, new RCTLCapital());
    }
    public static Loan newRevolver(double notional, Date start,
                                   Date expiry, int rating) {
        return new Loan(notional, start, expiry, null, rating, new RevolverCapital());
    }
    public static Loan newSPLC(double notional, Date start,
                               Date maturity, int rating) {
        return new Loan(notional, start, null, maturity, rating, new TermLoanCapital());
    }
    public static Loan newTermLoan(double notional, Date start,
                                   Date maturity, int rating) {
        return new Loan(notional, start, null, maturity, rating, new TermLoanCapital());
    }
    public static Loan newVariableLoan(double notional, Date start,
                                       Date expiry, Date maturity, int rating) {
        return new Loan(notional, start, expiry, maturity, rating, new RCTLCapital());
    }
    }
