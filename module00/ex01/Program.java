import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        if (args.length != 0) {
            System.err.println("Usage: java Program");
            System.exit(1);
        }

        int number = 0;
        try {
            Scanner input = new Scanner( System.in );
            System.out.print("-> ");
            number = input.nextInt();
            if (number < 0)
                throw new java.lang.NumberFormatException("");
        } catch (java.lang.NumberFormatException | java.util.InputMismatchException e) {
            System.err.println("invalid input");
            System.exit(1);
            return;
        };

//        Any number can be implemented like this
//        6k     (eliminated %2 and %3)
//        6k + 1
//        6k + 2 (eliminated %2)
//        6k + 3 (eliminated %3)
//        6k + 4 (eliminated % 2)
//        6k + 5

        int iteration = 0;
        if (number % 2 == 0 || number % 3 == 0) {
                System.out.printf("%d\nfalse %d\n", number, iteration);
                return;
        }
        for (int i = 5; i * i <= number; i += 6) {
            ++iteration;
            if (number % i == 0 || number % (i + 2) == 0) {
                System.out.printf("%d\nfalse %d\n", number, iteration);
                return;
            }
        }
        System.out.printf("%d\ntrue %d\n", number, iteration);
    }
}
