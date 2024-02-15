import java.util.Random;

public class RandomPasswordGenerator
{
    public static void main(String[] args)
    {
        String password;
        boolean valid;
        for (int i=0; i < 20; i++)
        {
            password = generatePassword(10, 4, 2,2,"&!#@");
            valid = checkPassword(8, 4, 2,2,"&!#@", password);
            System.out.println(password+" "+valid);
        }
    }

    public static String generatePassword(int minLength, int minUpper, int minDigits,int minSpecial, String specialCharacters)
    {
        Random random = new Random();
        int actualLength = random.nextInt(5) + minLength;
        char[] generatedPasswordArr = new char[actualLength];

        final String UPPER_CASES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWER_CASES = "abcdefghijklmnopqrstuvwxyz";
        final String DIGITS = "123456789";

        int temp;

        //adding special characters
        for (int i=0; i < minSpecial; i++)
        {
            temp = generateIndex(generatedPasswordArr);
            generatedPasswordArr[temp] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        }

        //adding digits
        for (int i=0; i < minDigits; i++)
        {
            temp = generateIndex(generatedPasswordArr);
            generatedPasswordArr[temp] = DIGITS.charAt(random.nextInt(DIGITS.length()));
        }

        //adding uppercase
        for (int i=0; i < minUpper; i++)
        {
            temp = generateIndex(generatedPasswordArr);
            generatedPasswordArr[temp] = UPPER_CASES.charAt(random.nextInt(UPPER_CASES.length()));
        }

        //adding lowercase
        int remaining = actualLength - (minUpper + minDigits + minSpecial);
        for (int i=0; i < remaining; i++)
        {
            temp = generateIndex(generatedPasswordArr);
            generatedPasswordArr[temp] = LOWER_CASES.charAt(random.nextInt(LOWER_CASES.length()));
        }

        String password = "";
        for (char character: generatedPasswordArr) password += character;

        return password;
    }

    public static boolean checkPassword(int minLength, int minUpper, int minDigits,int minSpecial, String specialCharacters, String password)
    {
        final String DIGITS = "123456789";
        final String UPPER_CASES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int specialCount = 0;
        int upperCount = 0;
        int digitsCount = 0;

        if (password.length() < minLength) return false;

        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            //check for special character
            if (specialCharacters.indexOf(character) != -1) specialCount++;

            //check for uppercase
            else if (UPPER_CASES.indexOf(character) != -1) upperCount++;

            //check for digits
            else if (DIGITS.indexOf(character) != -1) digitsCount++;

        }

        return specialCount >= minSpecial && upperCount >= minUpper && digitsCount >= minDigits;
    }

    private static int generateIndex(char[] passwordArr)
    {
        Random rand = new Random();

        int index;
        int target;
        do {
            target = rand.nextInt(passwordArr.length);
            index = passwordArr[target];
        } while (index != 0);

        return target;
    }
}

