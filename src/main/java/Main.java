import com.sun.deploy.config.Config;
import nenga.Address;
import nenga.NengaRenderer;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import javax.sound.midi.Soundbank;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void printNenga(Address from, Address to, PrintService printer,boolean isTest){
        System.out.println("Happy New Year 2021");
        BufferedImage image = new BufferedImage(1000,1480,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.BLACK);

        graphics.setColor(new Color(255,255,255));
        graphics.fillRect(0,0,1000,1480);

        NengaRenderer.render(image,from,to,"HiraMinProN-W3",1 / 0.93);

        try {
            if(isTest){
                ImageIO.write(image,"png",new File("./outputs/" + to.getFullname() + ".png"));
            }
            else{
                PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
                attributeSet.add(MediaSizeName.JAPANESE_POSTCARD);
                attributeSet.add(OrientationRequested.PORTRAIT);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image,"png",baos);

                Doc surface = new SimpleDoc(baos.toByteArray(),DocFlavor.BYTE_ARRAY.PNG,null);
                DocPrintJob job = printer.createPrintJob();

                System.out.println("Printing...");
                job.print(surface,attributeSet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PrintException e) {
            e.printStackTrace();
        }
    }

    public static void renderTest(Address from, Address to){
        try {
            BufferedImage image = ImageIO.read(new File(nenga.Config.INSTANCE.background));
            NengaRenderer.render(image,from,to,"HiraMinProN-W3",1);
            ImageIO.write(image,"png",new File("./outputs/" + to.getFullname() + "_rendertest.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        Address from = nenga.Config.INSTANCE.from;

        PrintService printer = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println("Printer:" + printer.getName());

        for (Address to:nenga.Config.INSTANCE.to){
            printNenga(from,to,printer,false);
            renderTest(from,to);
            //break;
        }

    }
}
