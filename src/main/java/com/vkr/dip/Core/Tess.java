package com.vkr.dip.Core;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tess
{
    public static String run(String filename)
    {
        File scan = new File(filename);

        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("./tessdata");
            tesseract.setLanguage("rus");

            return tesseract.doOCR(scan);
        }
        catch (TesseractException e)
        {
            e.printStackTrace();

            return "Default OCR can't recognize this! Trying Deep OCR...";
        }
    }

    public static String deeper(String filename, String pattern) throws IOException
    {
        String[] ps = pattern.split(",");

        Integer x = Integer.parseInt(ps[0]);
        Integer y = Integer.parseInt(ps[1]);
        Integer xd = Integer.parseInt(ps[2]);
        Integer yd = Integer.parseInt(ps[3]);

        File scan = new File(filename);
        BufferedImage img = ImageIO.read(scan);

        Tesseract tesseract1 = new Tesseract();
        try {
            tesseract1.setDatapath("./tessdata");
            tesseract1.setLanguage("rus");

            return tesseract1.doOCR(img.getSubimage(x, y, xd, yd));
        }
        catch (TesseractException e)
        {
            e.printStackTrace();

            return "Deep OCR can't recognize!";
        }
    }
}

