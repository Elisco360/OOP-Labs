public class IncomeTaxCalculator {
    public static void main(String[] args) {
        double netIncome = computeTax(2000);
        System.out.println(netIncome);
    }

    public static double computeTax(double grossIncome){
        // Initialize variables
        double taxableAmount = grossIncome;
        double taxPayable = 0;

        // Check income against different tax brackets and calculate tax payable
        // First 402: 0% on the first 402 and below
        if (taxableAmount >= 402) {
            taxableAmount -= 402;

            // Next 110: 5% on the first 110
            if (taxableAmount >= 110) {
                taxPayable += 0.05 * 110;
                taxableAmount -= 110;

                // Next 130: 10% on the next 130
                if (taxableAmount >= 130) {
                    taxPayable += 0.1 * 130;
                    taxableAmount -= 130;

                    // Third 130: 17.5% on the next 3000
                    if (taxableAmount >= 3000) {
                        taxPayable += 0.175 * 3000;
                        taxableAmount -= 3000;

                        // Fourth bracket: 25% on the next 16395
                        if (taxableAmount >= 16395) {
                            taxPayable += 0.25 * 16395;
                            taxableAmount -= 16395;

                            // Fifth bracket: 30% on the next 29963
                            if (taxableAmount >= 29963) {
                                taxPayable += 0.3 * 29963;
                                taxableAmount -= 29963;

                                // Sixth bracket: 35% on the remaining amount if more than 50000
                                if (taxableAmount >= 50000) {
                                    taxPayable += 0.35 * 50000;
                                    taxableAmount -= 50000;
                                } else if (taxableAmount != 0) {
                                    // If remaining amount is less than 50000, apply 35% on the remaining amount
                                    taxPayable += 0.35 * taxableAmount;
                                }

                            } else if (taxableAmount != 0) {
                                // If remaining amount is less than 29963, apply 30% on the remaining amount
                                taxPayable += 0.3 * taxableAmount;
                            }

                        } else if (taxableAmount != 0) {
                            // If remaining amount is less than 16395, apply 25% on the remaining amount
                            taxPayable += 0.25 * taxableAmount;
                        }

                    } else if (taxableAmount != 0) {
                        // If remaining amount is less than 3000, apply 17.5% on the remaining amount
                        taxPayable += 0.175 * taxableAmount;
                    }
                } else if (taxableAmount != 0) {
                    // If remaining amount is less than 130, apply 10% on the remaining amount
                    taxPayable += 0.1 * taxableAmount;
                }
            } else if (taxableAmount != 0) {
                // If remaining amount is less than 110, apply 5% on the remaining amount
                taxPayable += 0.05 * taxableAmount;
            }
        }

        return grossIncome - taxPayable;
    }
}