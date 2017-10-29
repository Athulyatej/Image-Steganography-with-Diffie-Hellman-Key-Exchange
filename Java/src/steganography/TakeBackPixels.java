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
 * It is in this module that we extract the information carrying pixels from the
 * stego image. The extracted information will be in an encrypted form so that 
 * only the intended user who knows the secret key common between encoder and 
 * decoder can retrieve the exact message being sent by the encoder.
 * 
 * @author Athulyatej, Poornima, Deepika, Priya, Athira
 */
public class TakeBackPixels {

    /**
     * In this function, the stego image is chosen, and the required pixels are
     * selected for extracting the encrypted message out of the LSB bits of each
     * RGB values of the image pixels. 
     * 
     * @param EmbeddedImagePath The path of the Embedded Image
     * @param PrimeNo Prime Number
     * @param EncoderKeyPath The path to where the Public Key of the Encoder is saved
     * @return The Secret Message
     * @throws IOException
     */
    @SuppressWarnings("null")

    
        public String Extract(String EmbeddedImagePath, int PrimeNo, String EncoderKeyPath) throws IOException{
            
        int p,a,b,g,r,i,j,k=0,decimal,pp=0,y;
        
        int[] rbits = new int[8]; 
        int[] gbits = new int[8];
        int[] bbits = new int[8];
        int[] ssize = new int[12];
       
        StringBuilder sb = new StringBuilder();
        
        
        Conversion Conv = new Conversion();
        Decryption decrypt = new Decryption();
       
        
         BufferedImage img = null;
         File f;
         
         
         //read image
        try{
          f = new File(EmbeddedImagePath);
          img = ImageIO.read(f);
        }catch(IOException e){
          System.out.println(e);
        }

        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();
        
        
        for(i=0;i<1;i++)
        {
             for(j=0;j<4;j++)
             {
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
            ssize[pp++] = rbits[7];
            
            gbits = Conv.Pixelto8bitArray(g);
            ssize[pp++] = gbits[7];
                       
            bbits = Conv.Pixelto8bitArray(b);
            ssize[pp++] = bbits[7];
             }
        }
        

        decimal = Conv.BintoDec(ssize);
        
        
        for(i=0;i<height;i++)
        {
             for(j=4;j<width;j++)
             {
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
                
                if(k==decimal*8)
                break;

            rbits = Conv.Pixelto8bitArray(r);
            sb.append(rbits[7]);
            
            k++;
            if(k==decimal*8)
                break;
            
            gbits = Conv.Pixelto8bitArray(g);
            sb.append(gbits[7]);
            
            k++;
            if(k==decimal*8)
                break;
            
            bbits = Conv.Pixelto8bitArray(b);
            sb.append(bbits[7]);
            
            k++;
            if(k==decimal*8)
                break;
             }
        }
        
       
    String str = "";

    for (i = 0; i < sb.length()/8; i++) {

        int ap = Integer.parseInt(sb.substring(8*i,(i+1)*8),2);
        str += (char)(ap);
    }

    
    String msg1 = decrypt.Decrypt(str, PrimeNo, EncoderKeyPath);
    
    System.out.println();
    
    return msg1;

        }
}