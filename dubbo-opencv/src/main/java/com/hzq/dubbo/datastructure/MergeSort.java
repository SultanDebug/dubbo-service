/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.datastructure;

import java.util.Arrays;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/12/8 15:16
 */
public class MergeSort {
    public static int[] sort(int[] left, int[] right) {
        if (left.length == 2 && left[0] > left[1]) {
            int tmp = left[0];
            left[0] = left[1];
            left[1] = tmp;
        } else if (left.length > 2) {
            int n = left.length / 2;
            left = sort(Arrays.copyOfRange(left, 0, n), Arrays.copyOfRange(left, n , left.length));
        }

        if (right.length == 2 && right[0] > right[1]) {
            int tmp = right[0];
            right[0] = right[1];
            right[1] = tmp;
        } else if (right.length > 2) {
            int n = right.length / 2;
            right = sort(Arrays.copyOfRange(right, 0, n), Arrays.copyOfRange(right, n, right.length));
        }

        int[] target = new int[left.length + right.length];

        merge(left,right,target);

        return target;
    }

    private static void merge(int[] left, int[] right,int[] target){
        int i = 0, j = 0;
        boolean l = false , r = false;
        for (int k = 0; k < target.length; k++) {
            if(i==left.length-1 && j==right.length-1){
                //结束
                if (left[i] <= right[j]) {
                    target[k] = left[i];
                    target[k+1] = right[j];
                }else{
                    target[k] = right[j];
                    target[k+1] = left[i];
                }
                break;
            }else {
                if (left[i] <= right[j]) {
                    if(l){
                        target[k] = right[j];
                        if(j<right.length-1)j++;
                    }else{
                        target[k] = left[i];
                        if(i<left.length-1)i++;
                    }

                }else{
                    if(r){
                        target[k] = left[i];
                        if(i<left.length-1)i++;
                    }else{
                        target[k] = right[j];
                        if(j<right.length-1)j++;
                    }
                }

                //标记数组结束
                if(i==left.length-1){l=true;}
                if(j==right.length-1){r=true;}
            }

        }
    }


}
