package a4;

/**
 * Rules that have been used *
 * 3 - Naming Conventions
 *  2 - Names representing packages should be in all lower case.
 *  3 - Names representing types must be nouns and written in mixed case starting with upper case.
 *  4 - Variable names must be in mixed case starting with lower case.
 *  6 - Names representing methods must contain a verb and written in mixed case starting with lower case.
 *  10 - All names should be written in English.
 *  14 - is prefix should be used for boolean variables and methods. ('include' has been used for 'is')
 *  15 - The term compute can be used in methods where something is computed.
 *  16 - The term find can be used in methods where something is looked up.
 *  24 - Abbreviations in names should be avoided.
 * 5.1 - Package and Import Statements
 *  37 - The package statement must be the first statement of the file. All files should belong to a specific package.
 *  38 - The import statements must follow the package statement. import statements.
 *  39 - Imported classes should always be listed explicitly.
 * 5.4 - Types
 *  42 - Type conversions must always be done explicitly. Never rely on implicit type conversion.
 * 5.5 - Variables
 *  44 - Variables should be initialized where they are declared and they should be declared in the smallest scope possible.
 *  45 - Variables must never have dual meaning.
 *  48 - Variables should be kept alive for as short a time as possible.
 * 5.6 - Loops
 *  49 - Only loop control statements must be included in the for() construction.
 *  51 - The use of do-while loops can be avoided.
 * 5.7 - Conditionals
 *  53 - Complex conditional expressions must be avoided. Introduce temporary boolean variables instead.
 * 6.1 - Layout
 *  61,62,63,64,65,66,67 - looping layouts
 * 6.2 - White Space
 * 6.3 - Comments
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * @author Thomas Ruiz
 */
public final class A4 {

    /**
     * This method will calculate the average of a given values list.
     * The includeNegative argument will define whether to include the negative
     * values during the calculation if exists in the arrayList.
     * This method will return a double for any type of input list.
     * 
     * @param <T> anyType of number
     * @param valueList the list of values to be used to calculate the average
     * @param includeNegative whether to include negative values in the calculation
     * @return the average of the given values list
     */
    public static <T extends Number> double computeAverage(ArrayList<T> valueList, boolean includeNegative) {
        double sum = computeSum(valueList, includeNegative); // calculate the sum of the values list
        int count = 0; // keeps count of included values from the arrayList
        for (int index = 0; index < valueList.size(); index++) {
            if (includeNegative || valueList.get(index).doubleValue() >= 0) {  // skips negative values if not to include them in the calculation
                count++;
            }
        }
        if (count == 0) { // throws an exception if all the values are negative
            throw new IllegalArgumentException("no values are > 0");
        }
        double average = sum / count; // calculates the average
        return average;
    }

    /**
     * This method will calculate the sum of a given values list.
     * The includeNegative argument will define whether to include the negative
     * values during the calculation if exists in the arrayList.
     * This method will return a double for any type of input list.
     * 
     * @param <T> anyType of number
     * @param valueList the list of values to be used to calculate the sum
     * @param includeNegative whether to include negative values in the calculation
     * @return the summation of the given values list
     */
    public static <T extends Number> double computeSum(ArrayList<T> valueList, boolean includeNegative) {
        if (valueList.size() < 0) { // throws an exception if the arrayList is empty
            throw new IllegalArgumentException("x cannot be empty");
        }

        double sum = 0.0;
        for (T currentValue : valueList) { // add each value in the arrayList to the sum
            double value = currentValue.doubleValue();
            if (includeNegative || value >= 0) { // excludes the values if it has to be ignored as a negative value 
                sum += value;
            }
        }
        return sum;
    }

    /**
     * This method will find the median of a given values list.
     * This method will return a double for any type of input list.
     * 
     * @param <T> anyType of number
     * @param valueList the list of values to be used to find the median
     * @return the median of the given values list
     */
    public static <T extends Number & Comparable> double findMedian(ArrayList<T> valueList) {
        if (valueList.isEmpty()) { // throws an exception if the arrayList is empty
            throw new IllegalArgumentException("Size of array must be greater than 0");
        }

        Collections.sort(valueList); // sort the input arrayList

        double median = valueList.get(valueList.size() / 2).doubleValue(); // get the middle value of the sorted values list
        if (valueList.size() % 2 == 0) { // if the number of data inside the arrayList is even
            double leftMostValue = valueList.get(valueList.size() / 2).doubleValue();
            double rightMostValue = valueList.get(valueList.size() / 2 - 1).doubleValue();
            median = (leftMostValue + rightMostValue) / 2; // get the average of two middle values
        }

        return median;
    }

    /**
     * This method will find the standard deviation of a given values list.
     * This method will return a double for any type of input list.
     * 
     * @param <T> anyType of number
     * @param valueList the list of values to be used to find the standard deviation
     * @return the standard deviation of the given values list
     */
    public static <T extends Number> double computeStandardDeviation(ArrayList<T> valueList) {
        if (valueList.size() <= 1) { // throws an exception if the arrayList has less than two elements
            throw new IllegalArgumentException("Size of array must be greater than 1");
        }

        int listSize = valueList.size();
        double sum = 0;
        double avareage = computeAverage(valueList,true); // calculate the average of the values list
        
        for (int index = 0; index < listSize; index++) { // add the squared value of the dofference of each value and the average to the sum
            double doubleValue = valueList.get(index).doubleValue();
            sum += Math.pow(doubleValue - avareage,2);
        }
        double standardDeviation = Math.sqrt(sum / (listSize-1)); // get the square root
        return standardDeviation;
    }

    // Simple set of tests that confirm that functions operate correctly
    public static void main(String[] args) {
        ArrayList<Integer> testIntegerDataSet = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        ArrayList<Double> testDoubleDataSet = new ArrayList<>(Arrays.asList(2.2, 3.3, 66.2, 17.5, 30.2, 31.1));

        System.out.printf("The sum of the Integer array = %.0f\n", computeSum(testIntegerDataSet, true));

        System.out.printf("The average of the Integer test set = %.0f\n", computeAverage(testIntegerDataSet, true));
        System.out.printf("The average of the Double test set = %.2f\n", computeAverage(testDoubleDataSet, true));

        System.out.printf("The median value of the Integer data set = %.1f\n", findMedian(testIntegerDataSet));
        System.out.printf("The median value of the Double data set = %.1f\n", findMedian(testDoubleDataSet));
        
        System.out.printf("The sample standard deviation of the Integer test set = %.2f\n", computeStandardDeviation(testIntegerDataSet));
        System.out.printf("The sample standard deviation of the Double test set = %.2f\n", computeStandardDeviation(testDoubleDataSet));
    }
}

