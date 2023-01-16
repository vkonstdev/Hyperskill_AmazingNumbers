package com.vkonstdev.amazingnumbers;

import java.util.Arrays;
import java.util.Objects;

public class AmazingNumbers {
    private long number;

    AmazingNumbers(long number) {
        this.number = number;
    }

    public void increment() {
        ++number;
    }

    public boolean isEven() {
        return number % 2 == 0;
    }

    public boolean isOdd() {
        return !isEven();
    }

    public boolean isBuzz() {
        return number % 7 == 0 || number % 10 == 7;
    }

    public boolean isDuck() {
        return String.valueOf(number).contains("0");
    }

    public boolean isPalindromic() {
        String[] letters = String.valueOf(number).split("");
        for (int i = 0; i < letters.length / 2; i++) {
            if (!Objects.equals(letters[i], letters[letters.length - 1 - i])) {
                return false;
            }
        }
        return true;
    }

    public boolean isGapful() {
        if (number < 100) {
            return false;
        }
        String[] letters = String.valueOf(number).split("");
        return number % Long.parseLong(letters[0] + letters[letters.length - 1]) == 0;
    }

    public boolean isSpy() {
        String[] letters = String.valueOf(number).split("");
        return Arrays.stream(letters).map(Integer::parseInt).reduce(0, Integer::sum) ==
                Arrays.stream(letters).map(Integer::parseInt).reduce(1, (x, y) -> (x * y));
    }

    public boolean isSquare() {
        return number == ((long) (Math.sqrt(number)) * (long) (Math.sqrt(number)));
    }

    public boolean isSunny() {
        return number == ((long) (Math.sqrt(number + 1)) * (long) (Math.sqrt(number + 1)) - 1);
    }

    private boolean isJumping() {
        String[] letters = String.valueOf(number).split("");
        for (int i = 1; i < letters.length; i++) {
            if (!((Long.parseLong(letters[i]) - Long.parseLong(letters[i - 1]) == 1) || (Long.parseLong(letters[i]) - Long.parseLong(letters[i - 1]) == -1))) {
                return false;
            }
        }
        return true;
    }

    private boolean isHappy() {
        long res = 0;
        long current = number;
        do {
            String[] letters = String.valueOf(current).split("");
            for (String letter : letters) {
                res += Long.parseLong(letter) * Long.parseLong(letter);
            }
            current = res;
            res = 0;
        } while (current > 9);
        return current == 1;
    }

    private boolean isSad() {
        return !isHappy();
    }

    public String formBooleanValues() {
        StringBuilder sb = new StringBuilder(this.number + " is ");
        sb.append(isBuzz() ? "buzz, " : "");
        sb.append(isDuck() ? "duck, " : "");
        sb.append(isGapful() ? "gapful, " : "");
        sb.append(isPalindromic() ? "palindromic, " : "");
        sb.append(isSpy() ? "spy, " : "");
        sb.append(isSquare() ? "square, " : "");
        sb.append(isSunny() ? "sunny, " : "");
        sb.append(isJumping() ? "jumping, " : "");
        sb.append(isHappy() ? "happy, " : "");
        sb.append(isSad() ? "sad, " : "");
        sb.append(isEven() ? "even" : "odd");

        /*String out = String.join("", this.number + " is "
                + (isBuzz() ? "buzz, " : "")
                + (isDuck() ? "duck, " : "")
                + (isGapful() ? "gapful, " : "")
                + (isEven() ? "even, " : "")
                + (isOdd() ? "odd, " : "")
                + (isPalindromic() ? "palindromic, " : "")
                + (isSpy() ? "spy, " : "")
                + (isSquare() ? "square, " : "")
                + (isSunny() ? "sunny, " : "")
                + (isJumping() ? "jumping, " : ""));
        out = out.substring(0, out.length() - 2);*/
        return sb.toString();
    }

    public void printProperties() {
        System.out.println("Properties of " + this.number);
        System.out.println("       buzz: " + isBuzz());
        System.out.println("       duck: " + isDuck());
        System.out.println("palindromic: " + isPalindromic());
        System.out.println("     gapful: " + isGapful());
        System.out.println("        spy: " + isSpy());
        System.out.println("     square: " + isSquare());
        System.out.println("      sunny: " + isSunny());
        System.out.println("       even: " + isEven());
        System.out.println("        odd: " + isOdd());
        System.out.println("    jumping: " + isJumping());
        System.out.println("      happy: " + isHappy());
        System.out.println("        sad: " + isSad());
    }
}
