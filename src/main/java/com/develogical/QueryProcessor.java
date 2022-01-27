package com.develogical;

import java.util.ArrayList;
import java.util.List;
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
        boolean isPrime = query.toLowerCase().contains("primes");
        if (isLargest || isSquareAndCube || isPrime) {
            String[] numbers = query.split(":")[2].split(",");

            List<Integer> values = new ArrayList<Integer>();

            if (isLargest || isSquareAndCube) {
                values.add(0);
            }

            for (String number : numbers) {
                int num = Integer.parseInt(number.trim());
                if (isLargest) {
                    values.set(0, Math.max(values.get(0), num));
                } else if (isSquareAndCube) {
                    int sqrt = (int)Math.round(Math.pow(num, 0.5));
                    int cubeRoot = (int)Math.round(Math.pow(num, 0.33333333));

                    if (Math.abs(Math.pow(sqrt, 2) - num) < 0.01 && Math.abs(Math.pow(cubeRoot, 3) - num) < 0.01) {
                        values.set(0, num);
                    }
                } else if (isPrime) {
                    if (valueIsPrime(num)) {
                        values.add(num);
                    }
                }
            }

            String result = "";

            for (int value : values) {
                result += ", " + String.valueOf(value);
            }

            return result.substring(2);
        }

        if (query.toLowerCase().contains("plus")) {
            Pattern pattern = Pattern.compile("what is (\\d+) plus (\\d+)");
            Matcher matcher = pattern.matcher(query.toLowerCase());

            if (matcher.find()) {
                int result = Integer.parseInt(matcher.group(1)) + Integer.parseInt(matcher.group(2));
                return String.valueOf(result);
            }

            return "No idea";
        }

        if (query.toLowerCase().contains("multiplied by")) {
            Pattern pattern = Pattern.compile("what is (\\d+) multiplied by (\\d+)");
            Matcher matcher = pattern.matcher(query.toLowerCase());

            if (matcher.find()) {
                int result = Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                return String.valueOf(result);
            }

            return "No idea";
        }

        if (query.toLowerCase().contains("theresa may elected as the prime minister")) {
            return "2016";
        }

        if (query.toLowerCase().contains("minus")) {
            Pattern pattern = Pattern.compile("what is (\\d+) minus (\\d+)");
            Matcher matcher = pattern.matcher(query.toLowerCase());

            if (matcher.find()) {
                int result = Integer.parseInt(matcher.group(1)) - Integer.parseInt(matcher.group(2));
                return String.valueOf(result);
            }

            return "No idea";
        }

        if (query.toLowerCase().contains("power")) {
            Pattern pattern = Pattern.compile("what is (\\d+) to the power of (\\d+)");
            Matcher matcher = pattern.matcher(query.toLowerCase());

            if (matcher.find()) {
                long result = (long) Math.pow(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                return String.valueOf(result);
            }
        }

        if (query.toLowerCase().contains("fibonacci sequence")) {
            Pattern pattern = Pattern.compile("what is the (\\d+)(rd|th|st) number in the fibonacci sequence");
            Matcher matcher = pattern.matcher(query.toLowerCase());

            if (matcher.find()) {
                int result = Integer.parseInt(matcher.group(1));
                if (result == 1 || result == 2) {
                    return String.valueOf(1);
                }

                int a = 1;
                int fib = 1;

                for (int i = 2; i < result; i++) {
                    fib += a;
                    a = fib;
                }

                return String.valueOf(fib);
            }

            return "No idea";
        }

        if (query.toLowerCase().contains("james bond in the film dr no")) {
            return "Sean Connery";
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
