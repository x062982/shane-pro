package com.shanezhou.pro.huffman;

import java.io.*;
import java.util.*;

/**
 * @author ShaneZhou
 * @since 2020/11/11 周三 19:40:00
 */
public class HuffmanCodeTree {

    private static StringBuilder sb = new StringBuilder();
    private static Map<Byte, String> codes = new HashMap<>();

    public static void main(String[] args) throws IOException {
        //String str = "can you can a can as a can canner can a can.";
        //String str = "哈哈哈哈哈哈";
        //byte[] bytes = str.getBytes();
        //System.out.println(bytes.length);

        File file = new File("C:\\Users\\shanezhou\\Desktop\\finish.pdf");
        InputStream is = new FileInputStream(file);
        byte[] b = new byte[10];
        is.read(b);
        is.close();
        byte[] zipByte = zipData(b);
        System.out.println(zipByte.length);
    }

    private static void calcNumber(Map<Byte, Integer> map, byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            map.merge(bytes[i], 1, Integer::sum);
        }
    }

    static HuffmanCodeNode createHuffmanCodeTree(List<HuffmanCodeNode> nodes) {
        while (nodes.size() > 1) {
            // 1`.排序
            Collections.sort(nodes, (i, j) -> i.getValue() - j.getValue());
            // 2.获取最小的两个生成树
            HuffmanCodeNode leftNode = nodes.get(0);
            HuffmanCodeNode rightNode = nodes.get(1);
            HuffmanCodeNode parent = new HuffmanCodeNode(null, leftNode.getValue() + rightNode.getValue());
            parent.leftNode = leftNode;
            parent.rightNode = rightNode;
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(parent);
            // 3.循环直到生成一棵树
        }
        return nodes.get(0);
    }

    private static byte[] zipData(byte[] bytes) {

        // 计算字符串中每个字符出现的次数
        Map<Byte, Integer> map = new HashMap<>(16);
        calcNumber(map, bytes);
        List<HuffmanCodeNode> nodes = new ArrayList<>();
        map.forEach((k, v) -> {
            nodes.add(new HuffmanCodeNode(k, v));
        });

        // 创建一颗Huffman编码树
        HuffmanCodeNode huffmanTree = createHuffmanCodeTree(nodes);
        Map<Byte, String> codes = createCodes(huffmanTree);

        byte[] b = zip(bytes, codes);
        return b;
    }

    private static byte[] zip(byte[] bytes, Map<Byte, String> codes) {
        StringBuilder s = new StringBuilder();
        for (byte b : bytes) {
            s.append(codes.get(b));
        }
        int len;
        int sLen = s.length();
        if (sLen % Byte.SIZE == 0) {
            len = sLen >> 3;
        } else {
            len = (sLen >> 3) + 1;
        }
        byte[] zipData = new byte[len];
        for (int i = 0, index = 0; i < s.length(); i += Byte.SIZE, index++) {
            String str;
            if ((i + Byte.SIZE) > s.length()) {
                str = s.substring(i);
            } else {
                str = s.substring(i, i + Byte.SIZE);
            }
            zipData[index] = (byte)Integer.parseInt(str, 2);
        }
        return zipData;
    }

    private static Map<Byte, String> createCodes(HuffmanCodeNode huffmanTree) {
        if (huffmanTree == null) {
            return new HashMap<>(0);
        }

        getCodes(huffmanTree.leftNode, "0", sb);
        getCodes(huffmanTree.rightNode, "1", sb);

        return codes;
    }

    private static void getCodes(HuffmanCodeNode node, String s, StringBuilder sb) {
        StringBuilder code = new StringBuilder(sb);
        code.append(s);
        if (node.data == null) {
            getCodes(node.leftNode, "0", code);
            getCodes(node.rightNode, "1", code);
        } else {
            codes.put(node.data, code.toString());
        }
    }
}
