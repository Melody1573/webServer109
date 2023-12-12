package com.luo.study;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        String name = "罗1233";
        int age = 123;
        File file = new File("D:\\account\\");
        if (!file.exists()){
            file.mkdir();
        }
        File file1 = new File("D:\\account\\" + name + ".txt");

        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(name.getBytes());
        fileOutputStream.write(age);
    }
}
