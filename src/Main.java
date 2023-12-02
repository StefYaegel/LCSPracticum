import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        System.out.println("Bottom-Up LCS-LENGTH(sequence1, sequence2)");
        System.out.println(" ");

        // first running program on two example DNA sequences of length 6
        String sequence1 = "ACGGAT";
        String sequence2 = "CCGCTT";
        findLCS(args, sequence1, sequence2);

        // enters loop to check for user input
        while (loop) {
            System.out.print("Would you like to test another example? (Y/N) ");
            String response = scanner.nextLine();
            if (response.equals("Y") || response.equals("y")) {
                sequence1 = "";
                sequence2 = "";
                findLCS(args, sequence1, sequence2);
            } else {
                System.out.println("Program Finished.");
                loop = false;
            }
        }
    }

    public static void findLCS(String[] args, String sequence1, String sequence2) {

        Scanner scanner = new Scanner(System.in);

        // user can modify the two sequences to test other examples
        if (sequence1.equals("") && sequence2.equals("")) {
            System.out.print("Enter sequence 1: ");
            sequence1 = scanner.nextLine();
            System.out.print("Enter sequence 2: ");
            sequence2 = scanner.nextLine();
            System.out.println("Testing another example...");
        }

        // get size of the table mxn from sequence lengths
        int m = sequence1.length();
        int n = sequence2.length();

        int[][] table = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (sequence1.charAt(i - 1) == sequence2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }

                // illustrate the process of filling up the table
                // print out the program state at each loop iteration
                System.out.println("LCS Table");
                System.out.println("----------------------------");

                for (int[] row : table) {
                    for (int num : row) {
                        System.out.printf("%3d ", num);
                    }
                    System.out.println();
                }

                System.out.println(" ");
            }
        }

        int index = table[m][n];
        char[] lcs = new char[index];
        int k = index - 1;
        int i = m;
        int j = n;

        while (i > 0 && j > 0) {
            if (sequence1.charAt(i - 1) == sequence2.charAt(j - 1)) {
                lcs[k] = sequence1.charAt(i - 1);
                i--;
                j--;
                k--;
            } else if (table[i - 1][j] > table[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        String lcsString = new String(lcs);

        // Print user input and LCS results
        System.out.println("Sequence 1: " + sequence1);
        System.out.println("Sequence 2: " + sequence2);
        System.out.println("LCS: " + lcsString);
        System.out.println(" ");
    }
}


