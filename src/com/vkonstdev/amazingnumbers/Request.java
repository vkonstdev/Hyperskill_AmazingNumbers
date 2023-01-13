package com.vkonstdev.amazingnumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Request {

    public static final String propertiesString = "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD, JUMPING]";
    private final String[] params;

    public Request(String input) {
        this.params = input.split("\\s+");
        switch (this.params.length) {
            case 1 -> oneNumber();
            case 2 -> listNumbers();
            case 3 -> oneProperty();
            //case 4 -> twoProperties();
            default -> manyProperties();
        }
    }

    private void oneNumber() {
        long firstParam = getNumber(this.params[0]);
        if (firstParam > 0) {
            new AmazingNumbers(firstParam).printProperties();
        } else {
            System.out.println("The first parameter should be a natural number or zero.");
        }
    }

    private void listNumbers() {
        long firstParam = getNumber(this.params[0]);
        long secondParam = getNumber(this.params[1]);
        if (firstParam >= 0 && secondParam > 0) {
            AmazingNumbers an = new AmazingNumbers(firstParam);
            for (int i = 0; i < secondParam; i++) {
                System.out.println(an.formBooleanValues());
                an.increment();
            }
        } else if (firstParam < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else {
            System.out.println("The second parameter should be a natural number.");
        }
    }

    private void oneProperty() {
        long firstParam = getNumber(this.params[0]);
        long secondParam = getNumber(this.params[1]);
        if (firstParam >= 0 && secondParam > 0 && isValidProperty(this.params[2])) {
            printParams(firstParam, secondParam, this.params[2]);
        } else if (firstParam < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else if (secondParam < 1) {
            System.out.println("The second parameter should be a natural number.");
        } else {
            System.out.printf("The property [%s] is wrong.\n", params[2].toUpperCase());
            System.out.println(propertiesString);
        }
    }

    /*private void twoProperties() {
        long firstParam = getNumber(this.params[0]);
        long secondParam = getNumber(this.params[1]);
        if (firstParam >= 0 && secondParam > 0 && isValidProperty(this.params[2]) && isValidProperty(this.params[3])
                && !mutuallyExclusiveProperties()) {
            printParams(firstParam, secondParam, this.params[2], this.params[3]);
        } else if (firstParam < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else if (secondParam < 1) {
            System.out.println("The second parameter should be a natural number.");
        } else if (!(isValidProperty(this.params[2]) || isValidProperty(this.params[3]))) {
            System.out.printf("The properties [%s, %s] are wrong.\n", this.params[2].toUpperCase(), this.params[3].toUpperCase());
            System.out.println(propertiesString);
        } else if (!isValidProperty(this.params[2])) {
            System.out.printf("The property [%s] is wrong.\n", this.params[2].toUpperCase());
            System.out.println(propertiesString);
        } else if (!isValidProperty(this.params[3])) {
            System.out.printf("The property [%s] is wrong.\n", this.params[3].toUpperCase());
            System.out.println(propertiesString);
        } else {
            System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n",
                    this.params[2].toUpperCase(), this.params[3].toUpperCase());
            System.out.println("There are no numbers with these properties.");
        }
    }*/

    private void manyProperties() {
        long firstParam = getNumber(this.params[0]);
        long secondParam = getNumber(this.params[1]);
        String[] properties = Arrays.copyOfRange(params, 2, params.length);
        List<String> rightProperties = new ArrayList<>();
        List<String> wrongProperties = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String property : properties) {
            if (isValidProperty(property)) {
                rightProperties.add(property);
            } else {
                wrongProperties.add(property);
            }
        }
        if (wrongProperties.size() != 0) {
            if (wrongProperties.size() == 1) {
                System.out.printf("The property [%s] is wrong.\n", wrongProperties.get(0).toUpperCase());
                System.out.println(propertiesString);
            } else {
                sb.append("The properties [");
                for (int i = 0; i < wrongProperties.size(); i++) {
                    sb.append(wrongProperties.get(i).toUpperCase());
                    if (i != wrongProperties.size() - 1) {
                        sb.append(", ");
                    }
                }
                sb.append("] are wrong.");
                System.out.println(sb);
                System.out.println(propertiesString);
                sb = null;
            }
        } else if (firstParam >= 0 && secondParam > 0 && rightProperties.size() != 0 && !mutuallyExclusiveProperties(rightProperties)) {
            AmazingNumbers an = new AmazingNumbers(firstParam);
            int count = 0;
            int countContains = 0;
            while (count < secondParam) {
                for (String rightProperty : rightProperties) {
                    if (an.formBooleanValues().contains(rightProperty)) {
                        countContains++;
                    }
                }
                if (countContains == rightProperties.size()) {
                    System.out.println(an.formBooleanValues());
                    count++;
                }
                an.increment();
                countContains = 0;
            }
        } else if (firstParam < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else if (secondParam < 1) {
            System.out.println("The second parameter should be a natural number.");
        } else {
            sb.append("The request contains mutually exclusive properties: [");
                if (rightProperties.contains("even") && rightProperties.contains("odd")) {
                    sb.append("EVEN, ODD");
                } else if (rightProperties.contains("duck") && rightProperties.contains("spy")) {
                    sb.append("DUCK, SPY");
                } else {
                    sb.append("SQUARE, SUNNY");
                }
            sb.append("]");
            System.out.println(sb);
            System.out.println("There are no numbers with these properties.");
        }
    }

    private long getNumber(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isValidProperty(String input) {
        return propertiesString.contains(input.toUpperCase());
    }

    private boolean mutuallyExclusiveProperties(List<String> props) {
        return props.contains("even") && props.contains("odd") ||
               props.contains("duck") && props.contains("spy") ||
               props.contains("square") && props.contains("sunny");

        /*return "even".equals(this.params[2]) && "odd".equals(this.params[3]) ||
               "odd".equals(this.params[2]) && "even".equals(this.params[3]) ||
               "duck".equals(this.params[2]) && "spy".equals(this.params[3]) ||
               "spy".equals(this.params[2]) && "duck".equals(this.params[3]) ||
               "square".equals(this.params[2]) && "sunny".equals(this.params[3]) ||
               "sunny".equals(this.params[2]) && "square".equals(this.params[3]);*/
    }

    private void printParams(long param1, long param2, String... props) {
        AmazingNumbers an = new AmazingNumbers(param1);
        int count = 0;
        while (count < param2) {
            if (this.params.length == 3 && an.formBooleanValues().contains(props[0]) ||
                this.params.length == 4 && an.formBooleanValues().contains(props[0]) && an.formBooleanValues().contains(props[1])) {
                    System.out.println(an.formBooleanValues());
                    count++;
            }
            an.increment();
        }
    }
}
