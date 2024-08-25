import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner input = new Scanner( System.in );
        int validCoffeeCounter = 0;

        while (true) {
            System.out.print("-> ");
            int number = input.nextInt();
            if (number == 42) {
                break;
            }
            if (number <= 1) {
                continue;
            }
            int sum = getSumNumber(number);
            if (isCoffeeRelated(sum)) {
                validCoffeeCounter++;
            }
        }
        input.close();
        System.out.printf("Count of coffee-request : %d\n", validCoffeeCounter);
    }

    private static int getSumNumber(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    private static boolean isCoffeeRelated(int number) {
        return isPrimeNumber(number);
    }
    
    private static boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }}
