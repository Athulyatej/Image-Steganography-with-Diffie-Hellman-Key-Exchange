/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steganography;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This module is used to do all the manipulations for image steganography and
 * it finally comes out with the stego image containing the message to be 
 * carried to the other side.
 * 
 * @author Athulyatej, Poornima, Deepika, Priya, Athira
 */
public class GetSetPixels {

    /**
     * In this function, the required pixels are selected from the chosen cover
     * image, and the encrypted message is embedded to the LSB of each RGB values
     * of the image pixels. The stego image thus created will be stored in a 
     * suitable directory for user access.
     * 
     * @param Message The Message to be embedded
     * @param ImagePath Path of the image in which the message is to be embedded
     * @param OutputImagePath Path to where the embedded image is to be saved
     * @param PrimeNo Prime Number
     * @param EncoderKeyPath The Path where the Public Key of Encoder is saved
     * @throws IOException
     */
    public void Embed(String Message, String ImagePath, String OutputImagePath, int PrimeNo, String EncoderKeyPath) throws IOException {
    
    BufferedImage img = null;
    File f = null;
    
    
    Conversion Conv = new Conversion();
    Encryption encrypt = new Encryption();
    
    
    int p,a = 0,b = 0,g = 0,r = 0,z=0,y=0,i,j,pp=0;
    int[] rbits = new int[8]; 
    int[] gbits = new int[8];
    int[] bbits = new int[8];
    
    
    String message = Message;
    char[] msg = message.toCharArray();
    
    int length = message.length();

    String bin = Conv.Decto12Binary(length);
    
    char[] msgg = new char[message.length()];

    
    msgg = encrypt.Encrypt(message, PrimeNo, EncoderKeyPath);
    String msg1 = new String(msgg);
  
    StringBuilder binary = Conv.StringtoBinary(msg1);
 

    //read image
    try{
      f = new File(ImagePath);
      img = ImageIO.read(f);
    }catch(IOException e){
      System.out.println(e);
    }

    //get image width and height
    int width = img.getWidth();
    int height = img.getHeight();


    if((length*8)>(width*height))
        System.out.println("The message is too long!!..");
    
    
    for(i=0;i<1;i++)
   {
       for(j=0;j<4;j++)
       { 
            if(j>0)
            {
                 //set the pixel value
                   p = (a<<24) | (r<<16) | (g<<8) | b;
                   img.setRGB(i, j-1, p);
            }
           
            //get pixel value
            p = img.getRGB(i,j);

            //get alpha
            a = (p>>24) & 0xff;

            //get red
            r = (p>>16) & 0xff;

            //get green
            g = (p>>8) & 0xff;

            //get blue
            b = p & 0xff;
            
            rbits = Conv.Pixelto8bitArray(r);
            rbits[7]=bin.charAt(pp++)-48;
            r = Conv.BintoDec(rbits);
            
            gbits = Conv.Pixelto8bitArray(g);
            gbits[7]=bin.charAt(pp++)-48;
            g = Conv.BintoDec(gbits);
            
            bbits = Conv.Pixelto8bitArray(b);
            bbits[7]=bin.charAt(pp++)-48;
            b = Conv.BintoDec(bbits);  
       }
       
        //set the pixel value
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(i, j, p);
       
   }
    
   for(i=0;i<height;i++)
   {
       for(j=4;j<width;j++)
       {
            
            if(j>0)
            {
                 //set the pixel value
                   p = (a<<24) | (r<<16) | (g<<8) | b;
                   img.setRGB(i, j-1, p);
            }
           
            //get pixel value
            p = img.getRGB(i,j);

            //get alpha
            a = (p>>24) & 0xff;

            //get red
            r = (p>>16) & 0xff;

            //get green
            g = (p>>8) & 0xff;

            //get blue
            b = p & 0xff;
              
            if(z==binary.length())
                break;
            
            rbits = Conv.Pixelto8bitArray(r);
            rbits[7]=binary.charAt(z)-48;
            r = Conv.BintoDec(rbits);

            z++;
            if(z==binary.length())
                break;
            
            
            gbits = Conv.Pixelto8bitArray(g);
            gbits[7]=binary.charAt(z)-48;            
            g = Conv.BintoDec(gbits);
            
            z++;
            if(z==binary.length())
                break;
            
            
            bbits = Conv.Pixelto8bitArray(b);            
            bbits[7]=binary.charAt(z)-48;           
            b = Conv.BintoDec(bbits);
            
            z++;
            if(z==binary.length())
                break;
       }
       
        //set the pixel value
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(i, j, p);
       
   }
    
    
    //write image
    try{
      f = new File(OutputImagePath);
      ImageIO.write(img, "png", f);
    }catch(IOException e){
      System.out.println(e);
    } 
    System.out.println();
    System.out.println("Message Embedded!.");
    System.out.println();
    }
    
}