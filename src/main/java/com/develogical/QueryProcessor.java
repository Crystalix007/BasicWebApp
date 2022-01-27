package com.develogical;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryProcessor {

    public String process(String query) {
        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        if (query.toLowerCase().contains("imperial")) {
            return "Imperial College is a university in London";
        }

        if (query.toLowerCase().contains("name")) {
            return "FathomlessDepths";
        }

        boolean isLargest = query.toLowerCase().contains("largest");
        boolean isSquareAndCube = query.toLowerCase().contains("square and a cube");
        boolean isPrime = query.toLowerCase().contains("prime");
        if (isLargest || isSquareAndCube || isPrime) {
            String[] numbers = query.split(":")[2].split(",");
            int value = 0;

            for (String number : numbers) {
                int num = Integer.parseInt(number.trim());
                if (isLargest) {
                    value = Math.max(value, num);
                } else if (isSquareAndCube) {
                    int sqrt = (int)Math.sqrt(num);
                    int cubeRoot = (int)Math.pow(num, 1/3d);

                    if (Math.abs(Math.pow(sqrt, 2) - num) < 0.01 && Math.abs(Math.pow(cubeRoot, 3) - num) < 0.01) {
                        value = num;
                    }
                } else if (isPrime) {
                    if (valueIsPrime(num)) {
                        value = num;
                    }
                }
            }

            return String.valueOf(value);
        }

        if (query.toLowerCase().contains("plus")) {
            Pattern pattern = Pattern.compile("what is (\\d) plus (\\d)");
            Matcher matcher = pattern.matcher(query.toLowerCase());

            if (matcher.find()) {
                int result = Integer.parseInt(matcher.group(1)) + Integer.parseInt(matcher.group(2));
                return String.valueOf(result);
            }

            return "No idea";
        }

        return "";
    }

    private boolean valueIsPrime(int value) {
        for (int i = 2; i <= Math.ceil(Math.sqrt(value)); i++) {
            if ((value % i) == 0) return false;
        }

        return true;
    }
}
