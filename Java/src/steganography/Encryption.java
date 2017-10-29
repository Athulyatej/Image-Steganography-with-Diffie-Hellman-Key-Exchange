/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steganography;

import java.io.IOException;
import java.util.Scanner;

/**
 * This module encrypt the text message to be embedded into the carrier image
 * using the Diffie Hellman generated key for encryption.
 * 
 * @author Athulyatej, Poornima, Deepika, Priya, Athira
 */

public class Encryption {
        
    /**
     * In this function, the message is encrypted using the shared secret key.
     * The encrypted message is returned back by the function.
     * 
     * @param Message The Message to be encrypted
     * @param PrimeNo Prime Number
     * @param DecoderKeyPath The Path where the Public Key of Decoder is saved
     * @return Encrypted Message
     * @throws IOException
     */
    public char[] Encrypt(String Message, int PrimeNo, String DecoderKeyPath) throws IOException
        {
            Scanner sc = new Scanner(System.in);
            Key key1 = new Key();
            char[] msgg = new char[Message.length()];
            int s, x;
            System.out.println("Enter Your Secret Key to Encode ");
            x=sc.nextInt();
            int key = key1.Skey(PrimeNo, x, DecoderKeyPath);
            char s1;
            int res=0;
            for(int i=0;i<Message.length();i++)
            {
                res=i%key;
                s=Message.charAt(i);
                s+=res;
                s1= (char) s;
                msgg[i]=s1;
            }
            return msgg;
            }
    }