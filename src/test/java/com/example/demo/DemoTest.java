package com.example.demo;

public class DemoTest {
    public static void main(String[] args) {
        showWord();
    }

    public static void showWord() {
        //97-122 65-90
        StringBuffer sb = new StringBuffer();
        for (int i = 97; i < 123; i++) {
            char c = (char) i;
            sb.append("'");
            sb.append(c);
            sb.append("',");

        }
        for (int i = 65; i < 90; i++) {
            char c = (char) i;
            sb.append("'");
            sb.append(c);
            sb.append("',");

        }
        System.out.println(sb.toString());
    }
}
