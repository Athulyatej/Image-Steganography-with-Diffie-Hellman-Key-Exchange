/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steganography;

/**
 * This module consists of all the conversions used in the library.
 * 
 * @author Athulyatej, Poornima, Deepika, Priya, Athira
 */

public class Conversion {
    
    /**
     * This function converts a decimal number to a 12 bit binary number.
     * 
     * @param xyz The Decimal value
     * @return 12 bit Binary Number
     */
    public String Decto12Binary(int xyz)
    {
         StringBuilder buf1=new StringBuilder();
         StringBuilder buf2=new StringBuilder();
         while (xyz != 0){
         int digit = xyz % 2;
         buf1.append(digit); // apend 0101 order
         xyz = xyz/2;
            }
         String binary1=buf1.reverse().toString();// reverse to get binary 1010
         int length1=binary1.length();
         if(length1<12){
         while (12-length1>0){
           buf2.append("0");// add zero until length =8
           length1++;
            }
         }
         String bin=buf2.toString()+binary1;// binary string with leading 0's
         return bin;
    }
    
    /**
     * This Function converts a binary number to its corresponding decimal value.
     * 
     * @param xyz The Binary Number
     * @return The Decimal Value
     */
    public int BintoDec(int xyz[])
    {
        int y, decimal, power;
        y=xyz.length-1;
            decimal = power = 0;
            while(y>-1){
               int tmp = xyz[y];
               decimal += tmp*Math.pow(2, power);
               power++;
               y--;
            }
        return decimal;
    }
    
    /**
     * This function converts a string to another string representing the binary representation of the former string.
     * 
     * @param xyz A String
     * @return Binary String
     */
    public StringBuilder StringtoBinary(String xyz)
    {
        byte[] bytes = xyz.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte c : bytes)
           {
             int val = c;
             for (int i = 0; i < 8; i++)
              {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
              }
            }
        return binary;
    }
    
    /**
     * This fuction converts the decimal to 8 bit binary integer array.
     * 
     * @param xyz Decimal Value
     * @return 8 bit binary integer array
     */
    public int[] Pixelto8bitArray(int xyz)
    {
        int l, num;
        int xbits[] = new int[8];
        for (l = 7, num = xyz; l >= 0; l--, num >>>= 1)
           xbits[l] = num & 1;
        return xbits;
    }
}


