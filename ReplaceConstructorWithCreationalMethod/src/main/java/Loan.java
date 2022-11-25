import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Loan {

    private static String TERM_LOAN = "TL";
    private static String REVOLVER = "RC";
    private static String RCTL = "RCTL";
    private String type;
    private CapitalStrategy capitalStrategy;
    private float notional;
    private float outstanding;
    private int customerRating;
    private LocalDateTime maturity;
    private LocalDateTime expiry;

    public Loan(float notional, float outstanding, int customerRating, LocalDateTime expiry) {
        this(TERM_LOAN, new TermROC(), notional, outstanding,
                customerRating, expiry, null);
    }
    public Loan(float notional, float outstanding, int customerRating, LocalDateTime expiry,
                LocalDateTime maturity) {
        this(RCTL, new RevolvingTermROC(), notional, outstanding, customerRating,
                expiry, maturity);
    }
    public Loan(CapitalStrategy strategy, float notional, float outstanding,
                int customerRating, LocalDateTime expiry, LocalDateTime maturity) {
        this(RCTL, strategy, notional, outstanding, customerRating,
                expiry, maturity);
    }
    public Loan(String type, CapitalStrategy strategy, float notional,
                float outstanding, int customerRating, LocalDateTime expiry) {
        this(type, strategy, notional, outstanding, customerRating, expiry, null);
    }
    public Loan(String type, CapitalStrategy capitalStrategy, float notional,
                float outstanding, int customerRating, LocalDateTime expiry, LocalDateTime maturity) {
        this.type = type;
        this.capitalStrategy = capitalStrategy;
        this.notional = notional;
        this.outstanding = outstanding;
        this.customerRating = customerRating;
        this.expiry = expiry;
        if (RCTL.equals(type))
            this.maturity = maturity;
    }
}