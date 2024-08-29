package org.example;

import java.util.Arrays;
import java.util.Scanner;

//input: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42

public class Main {

    public static void main(String[] args) {
        char[] listOfChars = getListOfChars();

        // Count occurrences
        int[] occurrences = new int[Character.MAX_VALUE + 1];
        for (char c : listOfChars) {
            occurrences[c]++;
        }

        // Create result arrays
        char[] chars = new char[listOfChars.length];
        int[] counts = new int[listOfChars.length];
        int uniqueCount = 0;

        // Fill result arrays
        for (char c : listOfChars) {
            if (occurrences[c] > 0) {
                chars[uniqueCount] = c;
                counts[uniqueCount] = occurrences[c];
                uniqueCount++;
                occurrences[c] = 0; // Pop it from the array
            }
        }

        // Trim arrays to actual size
        chars = Arrays.copyOf(chars, uniqueCount);
        counts = Arrays.copyOf(counts, uniqueCount);

        // sort
        sortArrays(chars, counts);

        // Truncate
        chars = Arrays.copyOf(chars, 10);
        counts = Arrays.copyOf(counts, 10);
        int[] barHeight = getBarHeight(counts, 10);
        int[] numberOfSpaces = getNumberOfSpaces(barHeight);

        String[][] matrix = getMatrix(chars, counts, barHeight, numberOfSpaces);
        for (int col = 0; col < matrix[0].length; col++) {
            for (String[] strings : matrix) {
                System.out.print(strings[col] + " ");
            }
            System.out.println();
        }    }

    private static int[] getNumberOfSpaces(int[] barHeight) {
        int[] result = new int[barHeight.length];
        for (int i = 0; i < barHeight.length; i++) {
            result[i] = 10 - barHeight[i];
        }
        return result;
    }

    private static String[][] getMatrix(char[] chars, int[] counts, int[] barHeight, int[] numberOfSpaces) {
        String[][] matrix = new String[10][12];

        for (int i = 0; i < 10; i++) {
            int counter = 0;
            if (numberOfSpaces[i] > 0) {
                String[] spaces = " ".repeat(numberOfSpaces[i]).split("");
                for (int space = 0; space < spaces.length; space++) {
                    matrix[i][counter++] = spaces[space];
                }
            }

            String count = String.valueOf(counts[i]);
            matrix[i][counter++] = count;

            if (barHeight[i] > 0) {
                String[] hashes = "#".repeat(barHeight[i]).split("");
                for (int hash = 0; hash < hashes.length; hash++) {
                    matrix[i][counter++] = hashes[hash];
                }
            }

            String character = String.valueOf(chars[i]);
            matrix[i][counter] = character;
        }
        return matrix;
    }

    private static int[] getBarHeight(int[] counts, int barHeight) {
        int maxElement = counts[0];
        int[] result = new int[counts.length];
        for (int i = 0; i < counts.length; i++) {
            result[i] = (int)((float) (counts[i] * barHeight) / maxElement);
        }
        return result;
    }

    private static void printResults(char[] chars, int[] counts) {
        int maxElement = counts[0];
        String[] output = new String[12];
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 10; y++) {
                int numberOfCounts = counts[y];
                int barHeight = numberOfCounts * 10 / maxElement;
            }

        }
    }

    private static void sortArrays(char[] chars, int[] counts) {
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (counts[j] > counts[i]) { // sort occurrences
                    char tempCj = chars[j];
                    int tempNj = counts[j];
                    counts[j] = counts[i];
                    chars[j] = chars[i];
                    chars[i] = tempCj;
                    counts[i] = tempNj;
                    continue;
                }
                if (counts[j] == counts[i] && chars[j] < chars[i]) { // sort lexicographically
                    char tempCj = chars[j];
                    chars[j] = chars[i];
                    chars[i] = tempCj;
                }
            }
        }
    }

    private static char[] getListOfChars() {
        try(Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine().toCharArray();
        }
    }
}

