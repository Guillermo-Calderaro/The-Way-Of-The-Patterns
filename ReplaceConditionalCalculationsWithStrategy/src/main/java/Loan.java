import lombok.Getter;

import java.util.Date;

@Getter
public class Loan {
    private double notional;
    private double outstanding;
    private int rating;
    private Date start;
    private Date expiry;
    private Date maturity;
    private double unusedPercentage;
    private static final int MILLIS_PER_DAY = 86400000;

    public double calcCapital() {
        return riskAmount() * duration() * RiskFactor.forRiskRating(rating);
    }
    public void setOutstanding(double newOutstanding) {
        outstanding = newOutstanding;
    }
    //Helper Methods for the calculation of the capital
    private double calcUnusedRiskAmount() {
        return (notional - outstanding) * unusedPercentage;
    }
    private double duration() {
        if (expiry == null)
            //for Term Loans
            return ((maturity.getTime() - start.getTime()) / MILLIS_PER_DAY) / 365;
        else if (maturity == null)
            //for Revolver Loans
            return ((expiry.getTime() - start.getTime()) / MILLIS_PER_DAY) / 365;
        else {
            //for RCTL Loan
            long millisToExpiry = expiry.getTime() - start.getTime();
            long millisFromExpiryToMaturity = maturity.getTime() - expiry.getTime();
            double revolverDuration = (millisToExpiry / MILLIS_PER_DAY) / 365;
            double termDuration = (millisFromExpiryToMaturity / MILLIS_PER_DAY) / 365;
            return revolverDuration + termDuration;
        }
    }

    private double riskAmount() {
        if (unusedPercentage != 1.00)
            //for Revolver Loans and for RCTL Loans
            return outstanding + calcUnusedRiskAmount();
        else
            //for Term Loans
            return outstanding;
    }

    private void setUnusedPercentage() {
        if (expiry != null && maturity != null) {
            //for RCTL Loan
            if (rating > 4)
                unusedPercentage = 0.95;
            else
                unusedPercentage = 0.50;
        } else if (maturity != null) {
            unusedPercentage = 1.00;
        } else if (expiry != null) {
            //for Revolver Loan
            if (rating > 4)
                unusedPercentage = 0.75;
            else
                unusedPercentage = 0.25;
        }
    }
}
