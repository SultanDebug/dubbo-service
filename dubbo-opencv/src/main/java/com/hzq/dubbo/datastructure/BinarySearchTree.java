
package com.hzq.dubbo.datastructure;

import lombok.Data;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/12/8 17:09
 */
public class BinarySearchTree {
    // 树中的节点为私有的类, 外界不需要了解二分搜索树节点的具体实现
    @Data
    public static class Node {
        private Integer key;
        private String value;
        private Node left, right;

        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    // 根节点
    private Node root = null;
    // 树种的节点个数
    private int count = 0;

    public void insert(Integer key, String value){
        this.insert(root,key,value);
    }

    public Node getTree(){
        return root;
    }

    private void insert(Node node,Integer key, String value){
        if(root == null){
            root = new Node(key,value);
            return;
        }
        if(node.key==key){
            node.value = value;
        }else if(node.key>key){
            if(node.left!=null){
                insert(node.left,key,value);
            }else{
                node.left = new Node(key,value);
            }
        }else{
            if(node.right!=null){
                insert(node.right,key,value);
            }else{
                node.right = new Node(key,value);
            }
        }

    }
}
