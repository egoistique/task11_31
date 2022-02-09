package ru.vsu.cs.course1;


import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
//31. Выбрать из текста самое длинное предложение. Если в тексте несколько предложений
//одинаковой длины – выбрать самое первое. Длина предложения считается по количеству
//слов. Словом считается непрерывная последовательность символов (строчных и
//прописных) А-Я, A-Z и цифр. Концом предложения считаются символы точка, '!' и '?'.
//Началом предложения – любой символ, отличный от данных трех и пробела, первый
//после конца предыдущего предложения (или вообще первый в тексте).

    public static String longestSentence(String fullContent){
        StringBuilder sentence = new StringBuilder();
        String maxLenStr = "";
        for (char c : fullContent.toCharArray()) {
            sentence.append(c);

            if (c == '.' || c == '!' || c == '?') {
                String str = sentence.toString();
                sentence.delete(0, sentence.length());
                if (str.length() > maxLenStr.length()){
                    maxLenStr = str;
                }
            }
        }

        return maxLenStr;
    }

    public static String arrayToString(String[] text) {
        StringBuilder res = new StringBuilder();
        for (String s : text) {
            res.append(s).append("\n");
        }
        return res.toString();
    }

}
