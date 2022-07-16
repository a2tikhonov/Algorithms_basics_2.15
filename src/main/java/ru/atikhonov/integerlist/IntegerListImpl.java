package ru.atikhonov.integerlist;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] arr;

    private int index = 0;

    private final int initialLength;

    public IntegerListImpl(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        initialLength = length;
        this.arr = new Integer[length];
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (index == arr.length) {
            expandArr();
        }
        arr[index++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (index != 0 && index >= this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int indexOfNull = 0;
        if (index == 0 && this.index == 0 && arr[0] == null) {
            arr[index] = item;
            this.index++;
        } else if (index < this.index && arr[index] != null) {
            for (int i = index + 1; i < arr.length; i++) {
                if (arr[i] == null) {
                    indexOfNull = i;
                    break;
                }
            }
            if (indexOfNull > 0) {
                shiftRight(index, indexOfNull);
                arr[index] = item;
                this.index++;
            } else {
                indexOfNull = this.index;
                expandArr();
                shiftRight(index, indexOfNull);
                arr[index] = item;
                this.index++;
            }
        }
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index < this.index && item != null) {
            arr[index] = item;
            return item;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public Integer remove(Integer item) {
        boolean hasItem = false;
        if (this.index == 1 && arr[0].equals(item)) {
            hasItem = true;
            arr[0] = null;
            this.index--;
        } else {
            for (int i = 0; i < index; i++) {
                if (arr[i].equals(item)) {
                    hasItem = true;
                    shiftLeft(i, this.index);
                    index--;
                }
            }
        }
        if (this.index != 0 && this.index == arr.length - initialLength) {
            reduceArr();
        }
        if (!hasItem) {
            throw new IllegalArgumentException();
        }
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index >= this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (arr[index] == null) {
            throw new IllegalArgumentException();
        }
        Integer item;
        if (arr[index] == null) {
            throw new IllegalArgumentException();
        } else if (this.index == 1 && index == 0) {
            item = arr[0];
            arr[0] = null;
            this.index--;
        } else {
            item = arr[index];
            shiftLeft(index, this.index);
            this.index--;
        }
        if (this.index != 0 && this.index == arr.length - initialLength) {
            reduceArr();
        }
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        int min = 0;
        int max = index - 1;
        sort();
        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.equals(arr[mid])) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        int index = -1;
        for (int i = 0; i < this.index; i++) {
            if (arr[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item) {
        int index = -1;
        for (int i = this.index - 1; i >= 0; i--) {
            if (arr[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Integer get(int index) {
        if (index >= this.index) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return arr[index];
        }
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        int size = index;
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = true;
        for (int i = 0; i < index; i++) {
            if (arr[i] != null) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    @Override
    public void clear() {
        int length = index;
        if (!isEmpty()) {
            for (int i = 0; i < length; i++) {
                arr[i] = null;
                index--;
            }
            arr = new Integer[initialLength];
        }
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(arr, index);
    }

    private void expandArr() {
        Integer[] tmp = new Integer[index];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = arr[i];
        }
        arr = new Integer[index + initialLength];
        for (int i = 0; i < tmp.length; i++) {
            arr[i] = tmp[i];
        }
    }

    private void reduceArr() {
        Integer[] tmp = new Integer[index];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = arr[i];
        }
        arr = new Integer[arr.length - initialLength];
        for (int i = 0; i < tmp.length; i++) {
            arr[i] = tmp[i];
        }
    }

    private void shiftRight(int from, int to) {
        for (int i = to; i > from; i--) {
            arr[i] = arr[i - 1];
            arr[i - 1] = null;
        }
    }

    private void shiftLeft(int to, int from) {
        for (int i = to + 1; i < from; i++) {
            arr[i - 1] = arr[i];
            arr[i] = null;
        }
    }

    private void sort() {
        for (int i = 1; i < index; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

/*    private void sortSelection() {
        for (int i = 0; i < index - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < index; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minElementIndex];
            arr[minElementIndex] = tmp;
        }
    }*/

}


