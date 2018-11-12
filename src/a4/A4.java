package a4;

/**
 * Library of statistical functions using Generics for different statistical
 * calculations.
 * 
 * see http://www.calculator.net/standard-deviation-calculator.html
 * for sample standard deviation calculations
 *
 * @author Joey Programmer
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class A4 {

    public static <T extends Number> double avg(ArrayList<T> x, boolean incneg) {
        double s = sum(x, incneg);
        int c = 0;
        for (int i = 0; i < x.size(); i++) {
            if (incneg || x.get(i).doubleValue() >= 0) {
                c++;
            }
        }
        if (c == 0) {
            throw new IllegalArgumentException("no values are > 0");
        }
        return s / c;
    }

    public static <T extends Number> double sum(ArrayList<T> x, boolean incneg) {
        if (x.size() < 0) {
            throw new IllegalArgumentException("x cannot be empty");
        }

        double sum = 0.0;
        for (T val : x) {
            double value = val.doubleValue();
            if (incneg || value >= 0) {
                sum += value;
            }
        }
        return sum;
    }

    public static <T extends Number & Comparable> double median(ArrayList<T> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Size of array must be greater than 0");
        }

        Collections.sort(data);

        double median = data.get(data.size() / 2).doubleValue();
        if (data.size() % 2 == 0) {
            median = (data.get(data.size() / 2).doubleValue() + data.get(data.size() / 2 - 1).doubleValue()) / 2;
        }

        return median;
    }

    public static <T extends Number> double calculateStandardDeviation(ArrayList<T> data) {
        if (data.size() <= 1) {
            throw new IllegalArgumentException("Size of array must be greater than 1");
        }

        int n = data.size();
        double s = 0;
        double a = avg(data,true);
        
        for (int i = 0; i < n; i++) {
            double v = data.get(i).doubleValue();
            s += Math.pow(v - a,2);
        }
        double stdev = Math.sqrt(s / (n-1));
        return stdev;
    }

    // Simple set of tests that confirm that functions operate correctly
    public static void main(String[] args) {
        ArrayList<Integer> testDataI = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        ArrayList<Double> testDataD = new ArrayList<>(Arrays.asList(2.2, 3.3, 66.2, 17.5, 30.2, 31.1));

        System.out.printf("The sum of the Integer array = %.0f\n", sum(testDataI, true));

        System.out.printf("The average of the Integer test set = %.0f\n", avg(testDataI, true));
        System.out.printf("The average of the Double test set = %.2f\n", avg(testDataD, true));

        System.out.printf("The median value of the Integer data set = %.1f\n", median(testDataI));
        System.out.printf("The median value of the Double data set = %.1f\n", median(testDataD));
        
        System.out.printf("The sample standard deviation of the Integer test set = %.2f\n", calculateStandardDeviation(testDataI));
        System.out.printf("The sample standard deviation of the Double test set = %.2f\n", calculateStandardDeviation(testDataD));
    }
}

