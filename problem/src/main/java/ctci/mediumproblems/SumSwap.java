package ctci.mediumproblems;

import java.util.Arrays;
import java.util.HashSet;

public class SumSwap {

    int[] getValuesForSwap(int[] arr1, int[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) {
            return null;
        }

        Integer target = getTargetValue(arr1, arr2);
        if (target == null) {
            return null;
        }

        HashSet<Integer> arr2Hash = getArray2Hash(arr2);

        for(int element : arr1) {
            int diff = element - target;
            if (arr2Hash.contains(diff)) {
                return new int[]{element, diff};
            }
        }

        return null;
    }

    private HashSet<Integer> getArray2Hash(int[] arr2) {
        HashSet<Integer> arr2Hash = new HashSet<>();
        for(int i : arr2) {
            arr2Hash.add(i);
        }
        return arr2Hash;
    }

    Integer getTargetValue (int[] arr1, int[] arr2) {
        int arr1Sum = sum(arr1);
        int arr2Sum = sum(arr2);

        if (((arr1Sum - arr2Sum) % 2) != 0) {
            return null;
        }

        return (arr1Sum - arr2Sum) / 2;
    }

    int sum(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}