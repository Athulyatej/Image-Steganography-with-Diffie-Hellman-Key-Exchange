/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steganography;

/**
 *
 * @author Athulyatej
 */


import java.io.IOException;
import java.util.Scanner;



/**
 * This module provides the functionalities to the user.
 * 
 * @author Athulyatej, Poornima, Deepika, Priya, Athira
 */

public class ImageSteganography {
   
    private String Encoder, Decoder;
    int Prime;
    
    /**
     * This function generates the key for encryption of the text that we would be
     * embedding into the image. As here we use the Diffie Hellman key exchange
     * algorithm which has a symmetric key approach, there must be a public key
     * made by both encoder and decoder from which they make their shared secret
     * key for encryption and decryption of text message.
     * 
     * @param PrimeNo Prime Number
     * @param PrimitiveRoot Primitive Root of that Prime Number
     * @param EncoderKeyPath The path to where the Public Key of the Encoder to be saved
     * @param DecoderKeyPath The path to where the Public Key of the Decoder to be saved
     * @throws IOException
     */
    public void GenerateKey(int PrimeNo, int PrimitiveRoot, String EncoderKeyPath, String DecoderKeyPath ) throws IOException
        {
            
            Encoder = EncoderKeyPath;
            Decoder = DecoderKeyPath;
            Prime = PrimeNo;
            int a,b;
            Scanner sc= new Scanner(System.in);
            Key key= new Key();
            System.out.println("Writing The Public Keys Into Corresponding Files....");
            System.out.println();
            a=key.pubkey(PrimeNo, PrimitiveRoot, EncoderKeyPath);
            System.out.println("Public Key Containing File Of Encorder Is Successfully Written And The Private Secret Key Is "+a);
            b=key.pubkey(PrimeNo, PrimitiveRoot, DecoderKeyPath);
            System.out.println();
            System.out.println("Public key Containing File Of Decoder Is Successfully Written And The Private Secret Key Is "+b);
            System.out.println();
        }
    
    /**
     * In this function, the required pixels are selected from the chosen cover
     * image, and the encrypted message is embedded to the LSB of each RGB values
     * of the image pixels. The stego image thus created will be stored in a 
     * suitable directory for user access.
     * 
     * @param Message The Message to be embedded 
     * @param ImagePath The path of the image to which the message is to be embedded
     * @param OutputImagePath The path to which the embedded image is to be saved (in png format)
     * @throws IOException
     */
    public void Embed(String Message, String ImagePath, String OutputImagePath) throws IOException
        {
            GetSetPixels getsetpixels = new GetSetPixels();
            getsetpixels.Embed(Message, ImagePath, OutputImagePath, Prime, Decoder);
        }

    /**
     * In this function, the stego image is chosen, and the required pixels are
     * selected for extracting the encrypted message out of the LSB bits of each
     * RGB values of the image pixels. 
     * 
     * @param EmbeddedImagePath The path to the Embedded Image
     * @return The Secret Message
     * @throws IOException
     */
    public String Extract(String EmbeddedImagePath) throws IOException
        {  
        TakeBackPixels takebackpixels = new TakeBackPixels();
        String Message = takebackpixels.Extract(EmbeddedImagePath, Prime, Encoder); 
        return Message;
        }
}

