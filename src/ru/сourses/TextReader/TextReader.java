package ru.сourses.TextReader;

import java.io.*;

public class TextReader {
    public static void main(String[] args) {
        try {
            FileReader fileReader= new FileReader("C:\\Users\\lchurkina\\ru.сourses.rest.Calculator\\src\\ru.сourses.Points.TextReader\\access.log");
            BufferedReader reader=new BufferedReader(fileReader);
            String s;
            int count=0;
            int maxLength=0;
            int minLength= Integer.MAX_VALUE;
            while ((s= reader.readLine())!=null){
                if (s.length()>1024){
                    throw new RuntimeException("Слишком длинная строка");
                }
                count++;
                //System.out.println(s);
                if (s.length()>maxLength){
                    maxLength=s.length();
                }
                if (s.length()<minLength){
                    minLength=s.length();
                }
            }
            System.out.println("Количество строк в файле: "+ count );
            System.out.println("Максимальная длина: "+ maxLength);
            System.out.println("Минимальная длина: "+ minLength);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
