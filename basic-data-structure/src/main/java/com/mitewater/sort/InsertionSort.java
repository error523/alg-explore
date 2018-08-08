package com.mitewater.sort;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;

public class InsertionSort {

    public static int[] insertionSort(int[] array){
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(array, array.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }

        }
        return arr;
    }

    public static int[] insertionSort2(int[] array){
        // 数组长度
        int length = array.length;
        // 默认角标0为已排序
        for(int i=1;i<length;i++){
            // 从已排序最右元素开始比较
            for (int j=i;j>0;j--){
                int tmp = array[j];
                int sortRightEle = array[j-1];
                if(tmp<sortRightEle){
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                }else{
                    break;
                }
            }
        }
        return array;
    }

    public static int[] insertionSort3(int[] array){
        // 数组长度
        int length = array.length;
        // 默认角标0为已排序
        for(int i=1;i<length;i++){
            int tmp = array[i];
            // 记录搜索范围的左边界
            int low = 0;
            // 记录搜索范围的右边界
            int high = i - 1;
            while (low <= high) {
                // 记录中间位置
                int mid = (low + high) / 2;
                // 比较中间位置数据和i处数据大小，以缩小搜索范围
                if (array[mid] < tmp) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            //将数据整体向后移动1位
            for (int j = i; j > low; j--) {
                array[j] = array[j - 1];
            }
            array[low] = tmp;

        }
        return array;
    }

    public static void main(String[] args) {
        int length = 100000;
        long min = System.currentTimeMillis();
        insertionSort(randomArray(length));
        long max = System.currentTimeMillis();
        System.out.println();
        System.out.println("\n 排序方法1耗费时间为: "  + (max - min) + " ms");
        min = System.currentTimeMillis();
        insertionSort2(randomArray(length));
        max = System.currentTimeMillis();
        System.out.println();
        System.out.println("\n 排序方法2耗费时间为: "  + (max - min) + " ms");
        min = System.currentTimeMillis();
        insertionSort3(randomArray(length));
        max = System.currentTimeMillis();
        System.out.println();
        System.out.println("\n 排序方法3耗费时间为: "  + (max - min) + " ms");
    }

    public static int[] randomArray(int length){
        long min = System.currentTimeMillis();
        int[] array = new int[length];
        for(int i=0;i<length;i++){
            array[i] = RandomUtil.randomInt(0, 10000000);
        }
        long max = System.currentTimeMillis();
        System.out.println("产生数据时间为: "  + (max - min) + " ms");
        return array;
    }

}
