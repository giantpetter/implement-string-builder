package com.github.hcsp.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class MyStringBuilder {
    private char[] value;
    private int count;

    public MyStringBuilder() {
        int DEFAULT_CAPACITY = 16;
        value = new char[DEFAULT_CAPACITY];
        count = 0;
    }

    // 在末尾添加一个字符
    public MyStringBuilder append(char ch) {
        ensureCapacity(count + 1);
        value[count++] = ch;
        return this;
    }

    // 在末尾添加一个字符串，其数据需要从bytes字节数组中按照charsetName字符集解码得到
    // 请思考一下字节和字符串（字符串本质上是字节数组）之间d关系
    // 并查找相关API
    public MyStringBuilder append(byte[] bytes, String charsetName) {

        try {
            String appendString = new String(bytes, charsetName);
            ensureCapacity(count + appendString.length());
            for (int i = count; i < appendString.length() + count; i++) {
                value[i] = appendString.charAt(i - count);
            }
            count += appendString.length();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    // 在index指定位置添加一个字符ch
    public MyStringBuilder insert(int index, char ch) {
        ensureCapacity(count + 1);
        System.arraycopy(value, index, value, index + 1, count - index);
        value[index] = ch;
        count += 1;
        return this;
    }

    // 删除位于index处的字符
    public MyStringBuilder deleteCharAt(int index) {
        if (count - index >= 0) {
            System.arraycopy(value, index + 1, value, index, count - index);
        }
        count--;
        return this;
    }

    @Override
    public String toString() {

        return new String(value, 0, count);
    }

    private void ensureCapacity(int miniCapacity) {
        if ((miniCapacity - value.length) > 0) {
            value = Arrays.copyOf(value,
                    miniCapacity << 1 + 2);
        }
    }
}
