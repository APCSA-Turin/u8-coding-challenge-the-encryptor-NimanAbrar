package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
      if (messageLen == 0) {
        return 1;
      }
    return (int) Math.ceil((double) messageLen / rows); //i learned this from https://www.w3schools.com/java/ref_math_ceil.asp
 }   
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] encrypted = new String[rows][determineColumns(message.length(), rows)];
        int idx = 0; 
        for (int i = 0; i < encrypted.length; i++) {
            for (int j = 0; j < encrypted[0].length; j++) {
                if (idx < message.length()) {
                    encrypted[i][j] = String.valueOf(message.charAt(idx));
                } else {
                    encrypted[i][j] = "=";
                }
                idx++;
            }
        }
        return encrypted;
    }

    public static String encryptMessage(String message, int rows){
        String[][] encrypt = generateEncryptArray(message, rows);
        String encrypted = "";
        for (int column = encrypt[0].length - 1; column >= 0; column--) {
            for (int row = 0; row < encrypt.length; row++) {
                encrypted += encrypt[row][column];
            }
        }
        return encrypted;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        if (encryptedMessage.isEmpty() || rows <= 0) {
            return "";
        }
        int columns = determineColumns(encryptedMessage.length(),rows);
        String[][]array = new String[rows][columns];
        int idx = 0;
        if (columns * rows < encryptedMessage.length()) {
            System.out.println("Invalid row count for decryption.");
        }
        for (int col = columns - 1; col >= 0; col--) {
            for (int r =0; r < rows; r++) {
                if (idx < encryptedMessage.length()) {
                    array[r][col] = String.valueOf(encryptedMessage.charAt(idx)); // i learned this from https://www.w3schools.com/java/ref_string_valueof.asp
                    idx++;
                }
            }
        }
        String decrypt = "";
        for (int r = 0; r < rows; r++) {
            for (int col = 0; col < columns; col++) {
                if (array[r][col] != null && !array[r][col].equals("=")) {
                    decrypt+= array[r][col];
                }
            }
            }
            return decrypt;
        }

    public static void main(String[] args)  {
        String[][] array = generateEncryptArray("I like the New York Knicks", 3);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}