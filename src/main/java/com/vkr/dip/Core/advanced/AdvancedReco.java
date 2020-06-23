package com.vkr.dip.Core.advanced;

import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdvancedReco
{
    private static String response, type, info, adPatt;
    private static HashMap<String, String> pat = new HashMap();

    public AdvancedReco()
    {
        Map<String, String> patterns = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Patterns.txt"),"Patterns")))
        {
            String line;
            String[] pp;

            while ((line = br.readLine()) != null)
            {
                pp = br.readLine().split(":");
                patterns.put(pp[0], pp[1]);
            }
            br.close();
        } catch (Exception e)
        {
            System.out.println(e);
        }

        //pat.put("ТОВАРНО-ТРАНСПОРТНАЯ НАКЛАДНАЯ","210,75,440,95;490,70,530,90");
        pat.put("ТОВАРНО-ТРАНСПОРТНАЯ НАКЛАДНАЯ","210,75,230,20;490,70,40,20");
    }

    public static String typeReco(String path) throws IOException
    {
        File scan = new File(path);
        BufferedImage img = ImageIO.read(scan);

        Tesseract advTesseract = new Tesseract();

        try
        {
            advTesseract.setDatapath("./tessdata");
            advTesseract.setLanguage("rus");

            for (Map.Entry<String, String> entry : pat.entrySet())
            {
                String[] pl = entry.getValue().split(";");
                String[] tpl = pl[0].split(",");
                int X = Integer.parseInt(tpl[0]);
                int Y = Integer.parseInt(tpl[1]);
                int Xd = Integer.parseInt(tpl[2]);
                int Yd = Integer.parseInt(tpl[3]);

                if (advTesseract.doOCR(img.getSubimage(X, Y, Xd, Yd)).contains(entry.getKey()))
                {
                    response = entry.getKey();
                    adPatt = pl[1];
                    break;

                }
                else response = "unknown";
            }
        }
        catch (Exception exception)
        {
            //exception.printStackTrace();

            return ">>1";
        }

       return response;
    }

    public static String infoReco(String path, String type) throws IOException
    {
        File scan = new File(path);
        BufferedImage img = ImageIO.read(scan);

        Tesseract advTesseract = new Tesseract();

        try
        {
            advTesseract.setDatapath("./tessdata");
            advTesseract.setLanguage("rus");

            String[] ptl = pat.get(type).split(";");
            String[] ptt = ptl[1].split(",");
            int X = Integer.parseInt(ptt[0]);
            int Y = Integer.parseInt(ptt[1]);
            int Xd = Integer.parseInt(ptt[2]);
            int Yd = Integer.parseInt(ptt[3]);

            response = advTesseract.doOCR(img.getSubimage(X, Y, Xd, Yd));;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();

            return ">>2";
        }

        return  response;
    }
}
