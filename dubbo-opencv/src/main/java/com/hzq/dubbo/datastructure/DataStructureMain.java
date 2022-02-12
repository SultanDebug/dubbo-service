
package com.hzq.dubbo.datastructure;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/12/7 17:51
 */
public class DataStructureMain {
    public static void main(String[] args) {
        //测试提交
        /*insection sort*/
        /*int[] ins = {5,8,4,1,6,9,3,7,2};
        System.out.println(JSONObject.toJSONString(InsectionSort.sortASC(ins)));
        System.out.println(JSONObject.toJSONString(InsectionSort.sortDESC(ins)));*/

        /*merge sort*/
        /*int[] left = {5,8,4,1,6,};
        int[] right = {9,3,7,2};*/
        /*int[] left = {2,5};
        int[] right = {3};
        System.out.println(JSONObject.toJSONString(MergeSort.sort(left,right)));*/

        /*bst*/
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i = 0; i < 10; i++) {
            Integer key = new Double(Math.random()*100).intValue();
            String value = "val-"+key;
            binarySearchTree.insert(key,value);
        }

        System.out.println(JSONObject.toJSONString(binarySearchTree.getTree()));

    }
}
