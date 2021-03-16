package com.shanezhou.pro.algorithm;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ShaneZhou
 * @since 2020/11/11 周三 17:42:22
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,4,5,6};
        System.out.println(Arrays.toString(arr));
        //bubbleSort(arr);
        //selectionSortDesc(arr, 0, arr.length);
        //insertSort(arr);
        //shellSort(arr);
        //mergeSort(arr, 0, arr.length);
        //quicklySort(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));
        //System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(1605858206481L), ZoneId.systemDefault()));
        //long sum = 0;
        //for (int i = 0; i < 10; i++) {
        //    sum += compareSpeed();
        //}
        //System.out.println(sum / 10);

        int[][] arr1 = new int[][]{{2,5,8},{4,0,-1}};
        //String s = "ababcaababcaabc";
        //System.out.println(s);
        Sort sort = new Sort();
        //char[] chars = "abcdefg".toCharArray();
        //sort.method02(3);
        sort.method01(arr, 4);
    }

    /**
     * 比较速度
     */
    public static long compareSpeed() {
        int length = 10000;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new Random().nextInt(length);
        }
        //System.out.println(Arrays.toString(arr));
        long start = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        //selectionSortAsc(arr, 0, arr.length - 1);
        shellSort(arr);

        long end = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        //System.out.println(Arrays.toString(arr));
        return (end - start);
    }

    /**
     * 选择排序
     * 从前往后遍历，记录最小数的下标，和遍历开始位置交换
     * @param arr
     */
    public static void selectionSortAsc(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            int index = i;
            int min = arr[i];
            for (int j = i + 1; j < end; j++) {
                if (min > arr[j]) {
                    index = j;
                    min = arr[index];
                }
            }
            // 交换位置
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    public static void selectionSortDesc(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            int index = i;
            int max = arr[i];
            for (int j = i + 1; j < end; j++) {
                if (max < arr[j]) {
                    index = j;
                    max = arr[index];
                }
            }
            // 交换位置
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    /**
     * 从前往后遍历，每一个跟后一个比较，
     * 如果比后面一个大，则移动位置。
     * @param arr
     */
    public static void bubbleSort(int[] arr) {

        // 遍历次数
        for (int i = 0; i < arr.length; i++) {
            // 遍历
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 交换位置
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * 从前往后遍历，将当前这个元素一直往前比较，
     * 直到前面没有比当前数更大的元素，放入该元素
     * @param arr
     */
    public static void insertSort(int[] arr) {
        // 从前往后遍历
        for (int i = 0; i < arr.length; i++) {
            // 从当前的下标往前遍历
            int j = i;
            int temp = arr[i];
            while ( j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            if (j != i) {
                arr[j] = temp;
            }
        }
    }

    /**
     * 希尔排序
     * 1、定义步长
     * 2、根据步长比较对应元素的大小排序
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int length = arr.length;
        int stride = length;

        // 根据步长比较对应的元素大小
        while((stride /= 2) != 0) {
            for (int i = stride; i < length; i++) {
                int temp = arr[i];
                int j = i - stride;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + stride] = arr[j];
                    j -= stride;
                }
                arr[j + stride] = temp;
            }
        }
    }

    ////////////////// 归并///////////////////////////
    public static void merge(int[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        if (j == arr.length) {
            return;
        }
        int[] temp = new int[end - start + 1];
        int index = 0;

        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
            if (i > mid) {
                while (j <= end) {
                    temp[index++] = arr[j++];
                }
            }
            if (j > end) {
                while (i <= mid) {
                    temp[index++] = arr[i++];
                }
            }
        }
        for (int k = start, m = 0; m < temp.length; k++, m++) {
            arr[k] = temp[m];
        }
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (start >= end){
            return;
        }
        // 分解
        int mid = start + ((end - start) >> 1);
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        merge(arr, start, mid, end);
    }
    /////////////////////////////////////////////////////////////////

    ///////快速排序///////////////////////////////////////////////

    public static void quicklySort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int low = start;
        int high = end;
        int standard = arr[start];

        while (low < high) {
            while (low < high && arr[high] >= standard) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= standard) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = standard;
        System.out.println(Arrays.toString(arr));
        quicklySort(arr, start, low);
        quicklySort(arr, low + 1, end);
    }

    /////////////////////////////////////////////////////////////
    public int badStr(String haystack, String needle, int i, int j, int k) {
        int m = 0;
        for (m = i - 1; m >= 0; m--) {
            if (haystack.charAt(k) == needle.charAt(m)) {
                return k - m;
            }
        }
        if (m < 0) {
            j += i + 1;
        }
        return j;
    }

    public void method01(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        int count = 0;
        for (int i = 0; count < len; i++) {
            int current = i;
            int pre = nums[current];
            do {
                int next = (current + k) % len;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                current = next;
                count++;
            } while (i != current);

        }

    }

    public List<Integer> method02(int numRows) {

        return getList(numRows);
    }
    public List<Integer> getList(int rowIndex) {
        int num = rowIndex + 1;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 1; i < num; i++) {
            long temp = (long)((long)(list.get(i - 1)) * (num - i)) / i;
            list.add((int)temp);
        }
        return list;
    }
    public int fineSuffix(String haystack, String needle, int i, int j, int k) {
        int index = 0;
        int nLen = needle.length();
        while (index < i && nLen - i > 1) {
            String suffix = haystack.substring(k + 1 + index, nLen - i + k);
            for (int m = i; m > index; m--) {
                if (m - nLen + i + 2 + index >= 0 &&
                        needle.substring(m - nLen + i + 2 + index, m + 1)
                                .equals(suffix)) {
                    return i - m + 1;
                }
            }
            index++;
        }
        return j + 1;
    }
}
