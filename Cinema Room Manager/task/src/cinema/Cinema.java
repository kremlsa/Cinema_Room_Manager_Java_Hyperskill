package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {
    static String[][] cinema;
    static int rows = 0;
    static int seats = 0;
    static int tickets = 0;
    static int income = 0;
    static Boolean isRun = true;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input;
        System.out.println("Enter the number of rows:");
        input = scanner.nextLine();
        rows = Integer.parseInt(input);
        System.out.println("Enter the number of seats in each row:");
        input = scanner.nextLine();
        seats = Integer.parseInt(input);
        setCinema();
        while (isRun) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                        printCinema();
                        break;
                case "2":
                    buyTicket();
                    break;
                case "3":
                    stats();
                    break;
                case "0":
                    isRun = false;
                    break;
            }
        }

    }

    public static void setCinema() {
        cinema = new String[rows][seats];
        for (int y = 0; y < cinema.length; y++) {
            for (int x = 0; x < cinema[y].length; x++) {
                cinema[y][x] = "S";
            }
        }
    }

    public static void printCinema() {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int y = 0; y < cinema.length; y++) {
            System.out.print(y + 1);
            for (int x = 0; x < cinema[y].length; x++) {
                System.out.print(" " + cinema[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int calcProfit() {
        if (seats * rows <= 60) {
            return seats * rows * 10;
        } else {
            return rows % 2 == 0 ? seats * rows * 9 : seats * (rows / 2) * 10 + seats * (1 + rows / 2) * 8;
        }
    }

    public static void stats() {
        int total = calcProfit();
        double perc = tickets * 100.0 / (seats * rows);
        perc = Math.round(perc*100)/100;
        System.out.println("Number of purchased tickets: "+ tickets +"\n" +
                "Percentage: " + String.format("%.2f", perc) + "%\n" +
                "Current income: $" + income + "\n" +
                "Total income: $" + total + "\n");
    }

    public static void buyTicket() {
        String input = "";
        Boolean isValid = false;
        int rowsBuy = 0;
        int seatsBuy = 0;
        while (!isValid) {
            System.out.println("Enter a row number:");
            input = scanner.nextLine();
            rowsBuy = Integer.parseInt(input);
            System.out.println("Enter a seat number in that row:");
            input = scanner.nextLine();
            seatsBuy = Integer.parseInt(input);
            if (rowsBuy > rows || seatsBuy > seats) {
                System.out.println("\nWrong input!\n");
            } else if (cinema[rowsBuy - 1][seatsBuy - 1] != "B") {
                isValid = true;
            } else {
                System.out.println("\nThat ticket has already been purchased!\n");
            }
        }
        if (seats * rows <= 60) {
            System.out.println("Ticket price: $10");
            tickets++;
            income += 10;
        } else {
            System.out.println("Ticket price: $" +
                    (rowsBuy <= rows / 2 ? "10" : "8"));
            tickets++;
            income += (rowsBuy <= (rows / 2)) ? 10 : 8;
        }
        cinema[rowsBuy - 1][seatsBuy - 1] = "B";
        //printCinema();
    }
 }