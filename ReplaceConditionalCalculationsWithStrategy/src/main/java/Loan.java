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
    private double duration() {
        if (this.getExpiry() == null)
            //for Term Loans
            return ((this.getMaturity().getTime() - this.getStart().getTime()) / MILLIS_PER_DAY) / 365;
        else if (this.getMaturity() == null)
            //for Revolver Loans
            return ((this.getExpiry().getTime() - this.getStart().getTime()) / MILLIS_PER_DAY) / 365;
        else {
            //for RCTL Loan
            long millisToExpiry = this.getExpiry().getTime() - this.getStart().getTime();
            long millisFromExpiryToMaturity = this.getMaturity().getTime() - this.getExpiry().getTime();
            double revolverDuration = (millisToExpiry / MILLIS_PER_DAY) / 365;
            double termDuration = (millisFromExpiryToMaturity / MILLIS_PER_DAY) / 365;
            return revolverDuration + termDuration;
        }
    }

    private double riskAmount() {
        if (this.getUnusedPercentage() != 1.00)
            //for Revolver Loans and for RCTL Loans
            return this.getOutstanding() + calcUnusedRiskAmount();
        else
            //for Term Loans
            return this.getOutstanding();
    }

    private void setUnusedPercentage() {
        if (this.getExpiry() != null && this.getMaturity() != null) {
            //for RCTL Loan
            if (this.getRating() > 4)
                unusedPercentage = 0.95;
            else
                unusedPercentage = 0.50;
        } else if (this.getMaturity() != null) {
            unusedPercentage = 1.00;
        } else if (this.getExpiry() != null) {
            //for Revolver Loan
            if (this.getRating() > 4)
                unusedPercentage = 0.75;
            else
                unusedPercentage = 0.25;
        }
    }
    // for Revolver Loans and RCTL Loan
    private double calcUnusedRiskAmount() {
        return (this.getNotional() - this.getOutstanding()) * this.getUnusedPercentage();
    }
}
