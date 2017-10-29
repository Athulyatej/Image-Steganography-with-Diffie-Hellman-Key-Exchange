/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steganography;

import java.io.IOException;
import java.util.Scanner;

/**
 * This module decrypt the extracted message from the stego image and form the 
 * original message that has been sent by the encoder through steganographic 
 * means. The same key used for encryption of the message is employed for the 
 * decryption process also.
 * 
 * @author Athulyatej, Poornima, Deepika, Priya, Athira
 */

public class Decryption {
    
    /**
     * Using this function the message is decrypted back using the shared secret
     * key and returned to the decoder.
     * 
     * @param Message The Message to be Decrypted
     * @param PrimeNo Prime Number
     * @param EncoderKeyPath The Path where the Public Key of Encoder is saved
     * @return Decrypted Message
     * @throws IOException
     */
    public String Decrypt(String Message, int PrimeNo, String EncoderKeyPath) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        Key key1 = new Key();
        char[] msg=new char[Message.length()];
        int s, x;
        System.out.println("Enter Your Secret Key To Decode ");
        x=sc.nextInt();
        int key = key1.Skey(PrimeNo, x, EncoderKeyPath);
        char s1;
        int len=Message.length();
        int res=0;
	for(int i=0;i<len;i++)
	{
            res=i%key;
            s=Message.charAt(i);
            s-=res;
            s1= (char) s;
            msg[i]=s1;	
	}
        String msg1 = new String(msg);
        return msg1;    
    }
}