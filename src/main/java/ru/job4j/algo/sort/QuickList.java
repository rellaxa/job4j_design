package ru.job4j.algo.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickList {

    public static <T> void quickSort(List<T> sequence, Comparator<T> comparator) {
        quickSort(sequence, 0, sequence.size() - 1, comparator);
    }

    private static <T> void quickSort(List<T> sequence, Integer start, Integer end, Comparator<T> comparator) {
        if (start >= end) {
            return;
        }
        int h = breakPartition(sequence, start, end, comparator);
        quickSort(sequence, start, h - 1, comparator);
        quickSort(sequence, h + 1, end, comparator);
    }

    private static <T> int breakPartition(List<T> sequence, Integer start, Integer end, Comparator<T> comparator) {
        int left = start + 1;
        T supportElement = sequence.get(start);
        int right = end;
        while (true) {
            while (left < right && comparator.compare(sequence.get(left), supportElement) < 0) {
                left++;
            }
            while (comparator.compare(sequence.get(right), supportElement) > 0) {
                right--;
            }
            if (left >= right) {
                break;
            }
            swap(sequence, left++, right--);
        }
        swap(sequence, start, right);
        return right;
    }

    private static <T> void swap(List<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(0, 5, -2, 7, 3, -2));
        quickSort(list, Comparator.naturalOrder());
        System.out.println(list);
    }
}
