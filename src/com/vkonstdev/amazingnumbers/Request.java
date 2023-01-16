package com.vkonstdev.amazingnumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Request {

    public static final String propertiesString = "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD, JUMPING, HAPPY, SAD, -BUZZ, -DUCK, -PALINDROMIC, -GAPFUL, -SPY, -SQUARE, -SUNNY, -EVEN, -ODD, -JUMPING, -HAPPY, -SAD]";

    private final String[] params;

    public Request(String input) {
        this.params = input.split("\\s+");
        switch (this.params.length) {
            case 1 -> oneNumber();
            case 2 -> listNumbers();
            //case 3 -> oneProperty();
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
        ArrayList<String> propertiesList = new ArrayList<>(List.of(properties));
        List<String> rightProperties = new ArrayList<>();
        List<String> excludeProperties = new ArrayList<>();
        List<String> wrongProperties = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String property : properties) {
            if (!isValidProperty(property)) {
                wrongProperties.add(property);
            } else if (isExcludeProperty(property)) {
                excludeProperties.add(property);
            } else {
                rightProperties.add(property);
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
            }
        } else if (firstParam >= 0 && secondParam > 0 && (rightProperties.size() != 0 || excludeProperties.size() != 0) && !mutuallyExclusiveProperties(propertiesList)) {
            AmazingNumbers an = new AmazingNumbers(firstParam);
            int count = 0;
            int countContains = 0;
            while (count < secondParam) {
                String currentString = an.formBooleanValues();
                for (String rightProperty : rightProperties) {
                    if (currentString.contains(rightProperty)) {
                        countContains++;
                    }
                }
                for (String excludeProperty : excludeProperties) {
                    if (!currentString.contains(excludeProperty.substring(1)) ) {
                        countContains++;
                    }
                }
                if (countContains == rightProperties.size() + excludeProperties.size()) {
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
                if (propertiesList.contains("even") && propertiesList.contains("odd")) {
                    sb.append("EVEN, ODD");
                } else if (propertiesList.contains("-even") && propertiesList.contains("-odd")) {
                    sb.append("-EVEN, -ODD");
                } else if (propertiesList.contains("duck") && propertiesList.contains("spy")) {
                    sb.append("DUCK, SPY");
                } else if (propertiesList.contains("-duck") && propertiesList.contains("-spy")) {
                    sb.append("-DUCK, -SPY");
                } else if (propertiesList.contains("happy") && propertiesList.contains("sad")) {
                    sb.append("HAPPY, SAD");
                } else if (propertiesList.contains("-happy") && propertiesList.contains("-sad")) {
                    sb.append("-HAPPY, -SAD");
                } else if (propertiesList.contains("square") && propertiesList.contains("sunny")) {
                    sb.append("SQUARE, SUNNY");
                } else if (propertiesList.contains("-square") && propertiesList.contains("-sunny")) {
                    sb.append("-SQUARE, -SUNNY");
                } else if (propertiesList.contains("even") && propertiesList.contains("-even")) {
                    sb.append("EVEN, -EVEN");
                } else if (propertiesList.contains("odd") && propertiesList.contains("-odd")) {
                    sb.append("ODD, -ODD");
                } else if (propertiesList.contains("duck") && propertiesList.contains("-duck")) {
                    sb.append("DUCK, -DUCK");
                } else if (propertiesList.contains("spy") && propertiesList.contains("-spy")) {
                    sb.append("SPY, -SPY");
                } else if (propertiesList.contains("happy") && propertiesList.contains("-happy")) {
                    sb.append("HAPPY, -HAPPY");
                } else if (propertiesList.contains("sad") && propertiesList.contains("-sad")) {
                    sb.append("SAD, -SAD");
                } else if (propertiesList.contains("square") && propertiesList.contains("-square")) {
                    sb.append("SQUARE, -SQUARE");
                } else if (propertiesList.contains("sunny") && propertiesList.contains("-sunny")) {
                    sb.append("SUNNY, -SUNNY");
                } else if (propertiesList.contains("buzz") && propertiesList.contains("-buzz")) {
                    sb.append("BUZZ, -BUZZ");
                } else if (propertiesList.contains("palindromic") && propertiesList.contains("-palindromic")) {
                    sb.append("PALINDROMIC, -PALINDROMIC");
                } else if (propertiesList.contains("jumping") && propertiesList.contains("-jumping")) {
                    sb.append("JUMPING, -JUMPING");
                } else {
                    sb.append("GAPFUL, -GAPFUL");
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

    private boolean isExcludeProperty(String input) {
        return input.toUpperCase().startsWith("-");
    }

    private boolean mutuallyExclusiveProperties(List<String> props) {
        return props.contains("even") && props.contains("odd") ||
               props.contains("-even") && props.contains("-odd") ||
               props.contains("duck") && props.contains("spy") ||
               props.contains("-duck") && props.contains("-spy") ||
               props.contains("square") && props.contains("sunny") ||
               props.contains("-square") && props.contains("-sunny") ||
               props.contains("happy") && props.contains("sad") ||
               props.contains("-happy") && props.contains("-sad") ||
               props.contains("even") && props.contains("-even") ||
               props.contains("odd") && props.contains("-odd") ||
               props.contains("duck") && props.contains("-duck") ||
               props.contains("spy") && props.contains("-spy") ||
               props.contains("square") && props.contains("-square") ||
               props.contains("sunny") && props.contains("-sunny") ||
               props.contains("happy") && props.contains("-happy") ||
               props.contains("sad") && props.contains("-sad") ||
               props.contains("buzz") && props.contains("-buzz") ||
               props.contains("palindromic") && props.contains("-palindromic") ||
               props.contains("jumping") && props.contains("-jumping") ||
               props.contains("gapful") && props.contains("-gapful");

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
