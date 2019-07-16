package ir.ac.kntu;

import java.util.Scanner;

public class FariborzEscape {
    private static int[][] board;
    private static int m, n;
    private static int[] answer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        scanner.close();
        answer = new int[2 * m * n];


        board = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 0;
            }
        }

        check(n/2,m/2, 0);

        boolean check = true;
        for (int i = 0; i < m && check; i++) {
            for (int j = 0; j < n && check; j++) {
                if (board[i][j] == 0) {
                    check = false;
                }
            }
        }

        if (!check) {
            System.out.println("RIP FARIBORZ!");
        } else {
            printAnswer();
        }

    }

    private static void printAnswer() {
        System.out.println("POSSIBLE");
        for (int count = 0; count < answer.length; count++) {
            if (count % 2 == 0) {
                System.out.print((answer[count] + 1) + " ");
            } else {
                System.out.print((answer[count] + 1));
                System.out.println();
            }

        }
    }

    private static void check(int x, int y, int count) {
        if (count >= answer.length) {
            return;
        }
        board[y][x] = 1;
        answer[count] = y;
        count++;
        answer[count] = x;
        count++;
        int[][] available = new int[m][n];

        available = setAvailable(available, x, y);

        boolean check = false;
        for (int i = 0; i < m && !check; i++) {
            for (int j = 0; j < n && !check; j++) {
                if (board[i][j] == 0 &&
                        available[i][j] == 0) {
                    x = j;
                    y = i;
                    check = true;
                    check(x, y, count);
                }
            }
        }
    }

    private static int[][] setAvailable(int[][] available, int x, int y) {
        available[y][x] = 1;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= +1; j++) {
                if (i != 0 || j != 0) {
                    int tempX = x, tempY = y;
                    if (tempX + j >= 0 && tempX + j < n &&
                            tempY + i >= 0 && tempY + i < m) {
                        tempX += j;
                        tempY += i;
                        available[tempY][tempX] = 1;
                        while (tempX + j >= 0 && tempX + j < n &&
                                tempY + i >= 0 && tempY + i < m) {
                            tempX += j;
                            tempY += i;
                            available[tempY][tempX] = 1;
                        }
                    }
                }
            }
        }
        return available;
    }
}