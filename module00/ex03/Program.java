import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WeekInfo {
    private final int weekNumber;
    private final int averageResult;

    public WeekInfo(int weekNumber, int averageResult) {
        this.weekNumber = weekNumber;
        this.averageResult = averageResult;
    }

    @Override
    public String toString() {
        return String.format("Week %d %s%n", weekNumber, createProgressBar());
    }

    private String createProgressBar() {
        return "=".repeat(Math.max(0, averageResult - 1)) + ">";
    }
}

public class Program {
    private static final int MAX_WEEKS = 18;
    private static final int DAYS_PER_WEEK = 5;
    private static final String EXIT_COMMAND = "42";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<WeekInfo> weekResults = new ArrayList<>();
        int currentWeek = 1;

        while (currentWeek <= MAX_WEEKS) {
            String input = promptForInput();

            if (EXIT_COMMAND.equals(input)) {
                break;
            }

            if (!input.equalsIgnoreCase("Week")) {
                exitWithError("IllegalArgument: Expected 'Week'");
            }

            int inputWeekNumber = readIntegerInput();
            if (inputWeekNumber != currentWeek) {
                exitWithError("IllegalArgument: Incorrect week number");
            }

            int averageResult = processWeekResults();
            weekResults.add(new WeekInfo(currentWeek, averageResult));
            currentWeek++;
        }

        printResults(weekResults);
    }

    private static String promptForInput() {
        System.out.print("->" + " ");
        return scanner.next();
    }

    private static int readIntegerInput() {
        return scanner.nextInt();
    }

    private static int processWeekResults() {
        int sum = 0;
        for (int day = 0; day < DAYS_PER_WEEK; day++) {
            int number = readIntegerInput();
            validateDayResult(number);
            sum += number;
        }
        return sum / DAYS_PER_WEEK;
    }

    private static void validateDayResult(int number) {
        if (number < 0 || number > 9) {
            exitWithError("IllegalArgument: Day result must be between 0 and 9");
        }
    }

    private static void printResults(List<WeekInfo> weekResults) {
        for (WeekInfo week : weekResults) {
            System.out.print(week);
        }
    }

    private static void exitWithError(String errorMessage) {
        System.err.println(errorMessage);
        System.exit(-1);
    }
}
