package com.shanezhou.pro.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ShaneZhou
 * @since 2020/11/11 周三 13:40:45
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {12, 321, 43, 41, 12, 3, 14};
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }
        Node root = createHuffmanTree(nodes);
        root.preSearch();
    }

    static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 1`.排序
            Collections.sort(nodes, (i, j) -> i.getValue() - j.getValue());
            // 2.获取最小的两个生成树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeftNode(leftNode);
            parent.setRightNode(rightNode);
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(parent);
        // 3.循环直到生成一棵树
        }
        return nodes.get(0);
    }


}
