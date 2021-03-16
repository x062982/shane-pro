package com.shanezhou.pro.huffman;

import java.util.List;

/**
 * @author ShaneZhou
 * @since 2020/11/11 周三 13:38:13
 */
public class Node {

    private Integer value;

    private Node leftNode;

    private Node rightNode;

    public void preSearch() {
        preSearch(this);
    }
    /**
     * 先序遍历
     * @param root
     */
    public void preSearch(Node root) {
        System.out.print(root.value + " \t");
        if (root.leftNode != null) {
            preSearch(root.leftNode);
        }
        if (root.rightNode != null) {
            preSearch(root.rightNode);
        }
    }


    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    public Node(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}
