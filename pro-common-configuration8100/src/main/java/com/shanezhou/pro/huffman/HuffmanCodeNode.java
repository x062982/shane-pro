package com.shanezhou.pro.huffman;

import java.util.List;

/**
 * @author ShaneZhou
 * @since 2020/11/11 周三 19:38:52
 */
public class HuffmanCodeNode extends Node {

    public Byte data;
    public HuffmanCodeNode leftNode;
    public HuffmanCodeNode rightNode;

    public HuffmanCodeNode(Byte data, int value) {
        this(value);
        this.data = data;
    }

    public HuffmanCodeNode(int value) {
        super(value);
    }

}
