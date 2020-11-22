package com.ntustars.controller;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextReaderWriter{
    // an example of reading
    /** Write fixed content to the given file. */
    public static void writetxt(String fileName, List data){
        try{
            PrintWriter out = new PrintWriter(new FileWriter(fileName));

            try {
                for (int i =0; i < data.size() ; i++) {
                    out.println((String)data.get(i));
                }
            }
            finally {
                out.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /** Read the contents of the given file. */
    public static List readtxt(String fileName){
        List data = new ArrayList() ;
        try {
            Scanner scanner = new Scanner(new FileInputStream(fileName));
            try {
                while (scanner.hasNextLine()){
                    data.add(scanner.nextLine());
                }
            }
            finally{
                scanner.close();
            }
            return data;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
