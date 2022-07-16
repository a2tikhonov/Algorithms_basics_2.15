package ru.atikhonov;

import java.util.Arrays;
import java.util.Random;

public class Main {

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr1 = new int[100_000];
        int[] arr2;
        int[] arr3;
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = random.nextInt();
        }
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        long start = System.currentTimeMillis();
        sortInsertion(arr1);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        sortSelection(arr2);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        sortBubble(arr3);
        System.out.println(System.currentTimeMillis() - start);
    }
}