/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steganography;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * This module generates the key for encryption of the text that we would be
 * embedding into the image. As here we use the Diffie Hellman key exchange
 * algorithm which has a symmetric key approach, there must be a public key
 * made by both encoder and decoder from which they make their shared secret
 * key for encryption and decryption of text message.
 * 
 * @author Athulyatej, Poornima, Deepika, Priya, Athira
 */
public class Key {
    private double Pkey;
    
    /**
     * This function takes the prime number and its primitive root modulo as the
     * input parameters. Firstly, it  generates a random private key for the user
     * and then his public key is generated of the form:
     * Public key = (root^privateKey)%primeNo. This is then written back into a
     * file for the reference of the user at other end.
     * 
     * @param PrimeNo Prime Number
     * @param PrimitiveRoot Primitive Root of that Prime Number
     * @param Path The Path to which the public key should be saved
     * @return The Public Key
     * @throws IOException
     */
    
    public int pubkey(int PrimeNo , int PrimitiveRoot, String Path ) throws IOException
    {   
       Scanner sc= new Scanner(System.in);
       Random randomGenerator = new Random();
        int a=randomGenerator.nextInt(10); 
        Pkey=(Math.pow(PrimitiveRoot,a)%PrimeNo);
        try (FileWriter fos1 = new FileWriter(Path)) {
            fos1.write((int)Pkey);
        }
         return a;
      }
    
    /**
     * This function creates the shared secret key for the user by reading the 
     * public key of the other  user from his file and use the prime number and
     * the private key of the user received as input parameters. The shared 
     * secret key is of the form: key = (public key^privateKey)%primeNo. This 
     * value is then returned back by the function.
     * 
     * @param PrimeNo Prime Number
     * @param PrivateKey The Private Key of Encoder/Decoder
     * @param Path The Path where the Public Key is saved
     * @return The Shared Secret Key
     * @throws FileNotFoundException
     * @throws IOException
     */
    
    public int Skey(int PrimeNo, int PrivateKey, String Path) throws FileNotFoundException, IOException
      {
          Scanner sc= new Scanner(System.in);
          FileReader is = new FileReader(Path);
          Pkey=is.read();
          double k =(Math.pow(Pkey,PrivateKey)%PrimeNo);
          return (int)k;

        }
}
