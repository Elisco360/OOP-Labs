public class TreasuryBillYields
{
    public static void main(String[] args)
    {
        System.out.println(computeInvestmentValue(546790234, 100.85962, 12, -56789, 120));
    }

    /**
     * Computes the future value of an investment with regular deposits.
     *
     * @param principal The initial amount of money invested.
     * @param rate The annual interest rate (as a decimal, e.g., 0.05 for 5%).
     * @param periodsPerYear The number of times interest is compounded per year.
     * @param deposit The amount deposited at the beginning of each period after the initial investment.
     * @param totalPeriods The total number of periods (years * periodsPerYear) for the investment.
     * @return The future value of the investment.
     */
    public static double computeInvestmentValue(double principal, double rate, int periodsPerYear, int deposit, int totalPeriods)
{
        double PERIODIC_RATES = (rate/periodsPerYear)/100.0;
        double investment = principal;

        double interest;

        do {
            deposit = totalPeriods == 1 ? 0 : deposit;

            interest = PERIODIC_RATES * investment;
            investment += interest + deposit;
            --totalPeriods;

        } while(totalPeriods > 0);

        return Math.round(investment*100)/100.00;
    }
}
