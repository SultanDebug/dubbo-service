/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.datastructure;

/**
 * 插入排序
 * 确保遍历每个
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/12/7 17:50
 */
public class InsectionSort {
    public static int[] sortASC(int[] source){
        if(source == null || source.length == 0 || source.length == 1){
            return source;
        }
        /*顺序遍历*/
        /*for (int i = 1; i < source.length; i++) {
            for (int j = 0; j < i; j++) {
                if(source[j] > source[i]){
                    int tmp = source[j];
                    source[j] = source[i];
                    source[i] = tmp;
                }
            }
        }*/
        /*倒叙遍历*/
        for (int i = 1; i < source.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if(source[j] > source[j+1]){
                    int tmp = source[j];
                    source[j] = source[j+1];
                    source[j+1] = tmp;
                }
            }
        }
        return source;
    }

    public static int[] sortDESC(int[] source){
        if(source == null || source.length == 0 || source.length == 1){
            return source;
        }
        /*顺序遍历*/
        /*for (int i = 1; i < source.length; i++) {
            for (int j = 0; j < i; j++) {
                if(source[j] < source[i]){
                    int tmp = source[j];
                    source[j] = source[i];
                    source[i] = tmp;
                }
            }
        }*/
        /*倒叙遍历*/
        for (int i = 1; i < source.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if(source[j] < source[j+1]){
                    int tmp = source[j];
                    source[j] = source[j+1];
                    source[j+1] = tmp;
                }
            }
        }
        return source;
    }
}
