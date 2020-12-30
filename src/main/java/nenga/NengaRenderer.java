package nenga;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class NengaRenderer {

    /**
     * Happy new year!
     */
    public static void render(BufferedImage image,Address from,Address to,String font,double scale){
        System.out.println("Render nenga from:" + from.getFullname() + ",to:" + to.getFullname());

        int width = image.getWidth();
        int height = image.getHeight();
        Graphics2D graphics = image.createGraphics();
        graphics.translate(-image.getWidth() * (scale - 1) / 2,-image.getHeight() * (scale - 1) / 2);
        graphics.scale(scale,scale);

        graphics.setColor(Color.BLACK);

        //render postal code
        graphics.setFont(new Font(font,Font.PLAIN,(int)(width * 0.05)));
        for (int i = 0;i < 3;++i){
            String s = String.valueOf(to.getPostalCode().charAt(i));
            graphics.drawString(s,(int)(width * (0.462 + i * 0.071)),(int)(height * 0.12));
        }

        for (int i = 0;i < 4;++i){
            String s = String.valueOf(to.getPostalCode().charAt(4 + i));
            graphics.drawString(s,(int)(width * (0.68 + i * 0.069)),(int)(height * 0.12));
        }

        //render postal code
        graphics.setFont(new Font(font,Font.PLAIN,(int)(width * 0.035)));
        for (int i = 0;i < 3;++i){
            String s = String.valueOf(from.getPostalCode().charAt(i));
            graphics.drawString(s,(int)(width * (0.064 + i * 0.0411)),(int)(height * 0.865));
        }

        for (int i = 0;i < 4;++i){
            String s = String.valueOf(from.getPostalCode().charAt(4 + i));
            graphics.drawString(s,(int)(width * (0.195 + i * 0.0411)),(int)(height * 0.865));
        }

        //render address(to)
        graphics.setFont(new Font(font,Font.PLAIN,(int)(width * 0.085)));
        for (int i = 0;i <= to.getFullname().length();++i){
            if(i == to.getFullname().length()){
                graphics.drawString("様",(int)(width * 0.48),(int)(height * 0.3 + (width * 0.11) * (i + 1)));
            }
            else{
                String s = String.valueOf(to.getFullname().charAt(i));
                graphics.drawString(s,(int)(width * 0.48),(int)(height * 0.3 + (width * 0.11) * i));
            }
        }

        graphics.setFont(new Font(font,Font.PLAIN,(int)(width * 0.05)));
        int linecount = 0;
        int charcount = 0;
        for (int i = 0;i < to.getAddress().length();++i){
            String s = String.valueOf(to.getAddress().charAt(i));
            graphics.drawString(s,(int)(width * (0.85 - linecount * 0.065)),(int)(height * 0.25 + (width * 0.055) * charcount));
            if(to.getAddress().charAt(i) == '\n'){
                ++linecount;
                charcount = linecount * 3;
            }
            else ++charcount;
        }

        //render address(from)
        graphics.setFont(new Font(font,Font.PLAIN,(int)(width * 0.035)));
        linecount = 0;
        charcount = 0;
        for (int i = 0;i < from.getAddress().length();++i){
            String s = String.valueOf(from.getAddress().charAt(i));
            graphics.drawString(s,(int)(width * (0.25 - linecount * 0.05)),(int)(height * 0.44 + (width * 0.04) * charcount));
            if(from.getAddress().charAt(i) == '\n'){
                ++linecount;
                charcount = linecount * 2;
            }
            else ++charcount;
        }

        graphics.setFont(new Font(font,Font.PLAIN,(int)(width * 0.065)));
        for (int i = 0;i < from.getFullname().length();++i){
            String s = String.valueOf(from.getFullname().charAt(i));
            graphics.drawString(s,(int)(width * 0.1),(int)(height * 0.62 + (width * 0.07) * i));
        }

    }
}
