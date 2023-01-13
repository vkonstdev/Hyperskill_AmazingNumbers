package com.vkonstdev.amazingnumbers;

import java.util.Scanner;

public class Main {

    private static void printInstructions() {
        System.out.println("""
                Supported requests:
                  - enter a natural number to know its properties;
                  - enter two natural numbers to obtain the properties of the list:
                    * the first parameter represents a starting number;
                    * the second parameter shows how many consecutive numbers are to be printed;
                  - two natural numbers and a property to search for;
                  - two natural numbers and two properties to search for;
                  - separate the parameters with one space;
                  - enter 0 to exit.""");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!\n");
        printInstructions();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter a request: ");
            String input = sc.nextLine().toLowerCase().trim();
            System.out.println();
            if (input.equals("0")) {
                break;
            } else if (input.isEmpty()) {
                printInstructions();
            } else {
                new Request(input);
            }
        }
        System.out.println("Goodbye!");

        /*Scanner sc = new Scanner(System.in);
        long firstParam = 0;
        long secondParam = 0;
        String thirdParam = null;
        String fourthParam = null;
        String keyWords = "buzz duck palindromic gapful spy square sunny even odd";
        System.out.println("Welcome to Amazing Numbers!");
        printInstructions();
        while (true) {
            System.out.print("\nEnter a request: ");
            String paramStr = sc.nextLine();
            if (paramStr.isEmpty()) {
                printInstructions();
                continue;
            }
            String[] params = paramStr.split(" ");
            try {
                if (params.length == 1) {
                    firstParam = Long.parseLong(params[0]);
                } else if (params.length == 2) {
                    firstParam = Long.parseLong(params[0]);
                    secondParam = Long.parseLong(params[1]);
                } else if (params.length == 3) {
                    firstParam = Long.parseLong(params[0]);
                    secondParam = Long.parseLong(params[1]);
                    thirdParam = params[2].toLowerCase();
                } else if (params.length == 4) {
                    firstParam = Long.parseLong(params[0]);
                    secondParam = Long.parseLong(params[1]);
                    thirdParam = params[2].toLowerCase();
                    fourthParam = params[3].toLowerCase();
                }
            } catch (Exception ex) {
                if (ex.getMessage().contains(params[0])) {
                    System.out.println("\nThe first parameter should be a natural number or zero.");
                } else {
                    System.out.println("\nThe second parameter should be a natural number.");
                }
                continue;
            }
            if (firstParam == 0) {
                break;
            }
            AmazingNumbers an = new AmazingNumbers(firstParam);
            if (firstParam > 0 && secondParam > 0 && thirdParam != null && fourthParam != null) {
                if (keyWords.contains(thirdParam) && keyWords.contains(fourthParam)) {
                    if (thirdParam.equals("square") && fourthParam.equals("sunny")
                            || thirdParam.equals("sunny") && fourthParam.equals("square")
                            || thirdParam.equals("even") && fourthParam.equals("odd")
                            || thirdParam.equals("odd") && fourthParam.equals("even")
                            || thirdParam.equals("duck") && fourthParam.equals("spy")
                            || thirdParam.equals("spy") && fourthParam.equals("duck")) {
                        System.out.println("The request contains mutually exclusive properties: [" +  thirdParam.toUpperCase() + ", " + fourthParam.toUpperCase() + "]");
                        System.out.println("There are no numbers with these properties.");
                    } else {
                        int count = 0;
                        while (count < secondParam) {
                            if (an.formBooleanValues().contains(thirdParam) && an.formBooleanValues().contains(fourthParam)) {
                                System.out.println(an.formBooleanValues());
                                count++;
                            }
                            an.increment();
                        }
                    }
                } else {
                    if (!keyWords.contains(thirdParam) && !keyWords.contains(fourthParam)) {
                        System.out.println("The properties [" +  thirdParam.toUpperCase() + ", " + fourthParam.toUpperCase() + "] are wrong.");
                    } else if (!keyWords.contains(thirdParam)) {
                        System.out.println("The property [" +  thirdParam.toUpperCase() + "] is wrong.");
                    } else {
                        System.out.println("The property [" +  fourthParam.toUpperCase() + "] is wrong.");
                    }
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
                }
                secondParam = 0;
                thirdParam = null;
                fourthParam = null;
            } else if (firstParam > 0 && secondParam > 0 && thirdParam != null) {
                if (keyWords.contains(thirdParam)) {
                    int count = 0;
                    while (count < secondParam) {
                        if (an.formBooleanValues().contains(thirdParam)) {
                            System.out.println(an.formBooleanValues());
                            count++;
                        }
                        an.increment();
                    }
                } else {
                    System.out.println("The property [" +  thirdParam.toUpperCase() + "] is wrong.");
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
                }
                secondParam = 0;
                thirdParam = null;
            } else if (firstParam > 0 && secondParam > 0) {
                for (int i = 0; i < secondParam; i++) {
                    String out = an.formBooleanValues();
                    System.out.println(out);
                    an.increment();
                }
                firstParam = 0;
                secondParam = 0;
            } else if (firstParam > 0 && secondParam < 0) {
                System.out.println("\nThe second parameter should be a natural number.");
                secondParam = 0;
            } else if (firstParam > 0) {
                System.out.println("\nProperties of " + an.getNumber());
                System.out.println("       buzz: " + an.isBuzz());
                System.out.println("       duck: " + an.isDuck());
                System.out.println("palindromic: " + an.isPalindromic());
                System.out.println("     gapful: " + an.isGapful());
                System.out.println("        spy: " + an.isSpy());
                System.out.println("     square: " + an.isSquare());
                System.out.println("      sunny: " + an.isSunny());
                System.out.println("       even: " + an.isEven());
                System.out.println("        odd: " + an.isOdd());

            } else {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            }
        }
        System.out.println("\nGoodbye!");*/
    }
}
