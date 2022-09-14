import java.lang.Math;

public class GeneralAlgorithms
{
    public static void main(String[] args)
    {
        // Test the `getSuffixGeneral` for 100 different integers
        for (int i = 0; i < 100; i++) {
            int randomInt = (int)(Math.random() * 200); // random integer between 0 and 200
            System.out.printf("%d%s\n", randomInt, getSuffixGeneral(randomInt));
        }    

        System.out.println("--------------------");
        
        // Test the `roundToN` for different number of decimals
        double testValue = 100.123456789;
        System.out.println(roundToN(testValue, 2));
        System.out.println(roundToN(testValue, 5));
        System.out.println(roundToN(testValue, 10));
        System.out.println(roundToN(testValue, 12));
        System.out.println(roundToN(testValue, 0));
        System.out.println(roundToN(testValue, 1));
    }
    
    // ------------------------------------------------------------------------------------------------------------------------------------------------
    
    /** 
     * @param number
     * @return String
     */
    public static String getSuffixGeneral(int number) 
    {
        int lastTwoDigits = number % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 19) {
            return "th";
        }

        int lastDigit = number % 10;
        switch (lastDigit) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    /** 
     * @param decimalNumber
     * @param decimalsToRound
     * @return double
     */
    public static double roundToN(double decimalNumber, int decimalsToRound)
    {
        double factor = (double) Math.pow(10, decimalsToRound); // power of 10
        return Math.round(decimalNumber * factor) / factor;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------
}
