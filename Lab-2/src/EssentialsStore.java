public class EssentialsStore
{
    public static void main(String[] args)
    {
        System.out.println(computeChangeBreakdown(999999.99, 1000000.00));
        //System.out.println(computeChange(53.88, 70, new double[] {5,0.2,0.01}));
    }

    public static double computeChangeBreakdown(double totalCost, double amountPaid)
    {
        // calculate the balance
        double balance = (amountPaid - totalCost)*100/100.00;
        double change = balance;

        int[] denominationCounts = new int[11];
        double [] denominations = {200,100,50,20,10,5,2,1,0.5,0.2,0.1};

        // repetitions
        do {
            // split according to denominations
            if (balance >= 200)
            {
                balance -= 200;
                denominationCounts[0]++;
            }
            else if (balance < 200 && balance >= 100)
            {
                balance -= 100;
                denominationCounts[1]++;
            }
            else if (balance < 100 && balance >= 50)
            {
                balance -= 50;
                denominationCounts[2]++;
            }
            else if (balance < 50 && balance >= 20)
            {
                balance -= 20;
                denominationCounts[3]++;
            }
            else if (balance < 20 && balance >= 10)
            {
                balance -= 10;
                denominationCounts[4]++;
            }
            else if (balance < 10 && balance >= 5)
            {
                balance -= 5;
                denominationCounts[5]++;
            }
            else if (balance < 5 && balance >= 2)
            {
                balance -= 2;
                denominationCounts[6]++;
            }
            else if (balance < 2 && balance >= 1)
            {
                balance -= 1;
                denominationCounts[7]++;
            }
            else if (balance < 1 && balance >= 0.5)
            {
                balance -= 0.5;
                denominationCounts[8]++;
            }
            else if (balance < 0.5 && balance >= 0.2)
            {
                balance -= 0.2;
                denominationCounts[9]++;
            }
            else if (balance < 0.2 && balance >= 0.1)
            {
                balance -= 0.1;
                denominationCounts[10]++;
            }

            balance = Math.round(balance*100)/100.00;
            //System.out.println(balance);
        } while (balance >= 0.1);

        System.out.println(printReceipt(denominations, denominationCounts));
        return Math.round(change*100)/100.00;
    }

    private static String printReceipt(double[] denominations, int[] denominationCounts)
    {
        String receipt = "";
        String currency;

        for (int i = 0; i < denominations.length; i++)
        {
            if (denominationCounts[i] == 0)
            {
                continue;
            }
            if (denominations[i] <= 0.5)
            {
                currency = "coins";
                if (denominationCounts[i] == 1) currency = "coin";

                receipt += String.format("%d %.0fp %s%n", denominationCounts[i], denominations[i] * 100, currency);
            } else
            {
                currency = "notes";
                if (denominationCounts[i] == 1) currency = "note";

                receipt += String.format("%d \u20b5%d %s%n", denominationCounts[i], (int) denominations[i], currency);
            }
        }

        return receipt;
    }

    public static double computeChange(double totalCost, double amountPaid, double[] denominations)
    {
        // calculate the balance
        double balance = Math.round((amountPaid - totalCost)*100)/100.00;
        double change = balance;

        int[] denominationCounts = new int[denominations.length];
        int position = 0;

        for(double denomination: denominations)
        {
            while (balance > denomination)
            {
                balance -= denomination;
                balance = Math.round(balance*100)/100.00;

                denominationCounts[position]++;

            }
            position++;
        }

        System.out.println(printReceipt(denominations, denominationCounts));
        return change;
    }
}
