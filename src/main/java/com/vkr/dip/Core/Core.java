package com.vkr.dip.Core;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.vkr.dip.Core.advanced.AdvancedReco;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Core
{
    private static final String TTN1 = "ТОВАРНО-ТРАНСПОРТНАЯ НАКЛАДНАЯ";
    public static final String patterntest = "450,60,90,35";

    private static String fullpath, response, pattern;
    private static String type, id, num, count;

    public static String[] keyTypes = {"TTN","SPRAVKA","DOGOVOR"};

    public static String main(String path) throws WriterException, IOException, NotFoundException
    {
        fullpath = path;

        if(tryZebra())
        {
            response = type + "," + id + "," + num + "," + count;
        }
        else
            if(!tryZebra() && tryTess())
            {
                Patterns patterns = new Patterns();

                response = type;
                id = doDeepTess(fullpath, patterntest);
                String[] ids = id.split(" ");

                response += ":" + ids[0];
            }
            else response = "Service can not recognize this scan. Sorry."; //response false

        System.out.println(response);
        return response;
    }

    private static Boolean tryZebra() throws WriterException, IOException, NotFoundException
    {
        Zebra zebra = new Zebra();
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        String zeb = zebra.run(fullpath);
        System.out.println(zeb);

        if (zebra.run(fullpath).equalsIgnoreCase("false")) return false;
        else {
            String[] params = zebra.run(fullpath).split("-");

            type = params[0];
            id = params[1];
            num = params[2];
            count = params[3];

            if (Arrays.asList(keyTypes).contains(type)) return true;
            else return false;
        }
    }

    private static Boolean tryTess()
    {
        Tess tess = new Tess();
        Patterns patterns = new Patterns();

        String[] words = tess.run(fullpath).split("\n");
        Boolean isTrue = false;

        for (int i = 0; i < keyTypes.length; i++)
        {
            if(tess.run(fullpath).contains(TTN1))
            {
                isTrue = true;
                type = TTN1;
                break;
            } else
                {
                    type = "unknown";

                    isTrue = false;
                }
        }

        return isTrue;
    }

    private static String doDeepTess(String path, String pattern) throws IOException
    {
        Tess tess1 = new Tess();

        return tess1.deeper(path, patterntest);
    }

    public static String altMain(String path) throws IOException
    {
        AdvancedReco advancedReco = new AdvancedReco();

        type = advancedReco.typeReco(path);

        if (!type.equalsIgnoreCase(">>1") && !type.equalsIgnoreCase(">>?"))
        {
            String info = advancedReco.infoReco(path, type);
            if (!info.equalsIgnoreCase(">>2")) return type + ":" + info.replaceAll("\n","");
            else return type + ":unknown";
        }
        else return "unknown:unknown";
    }
}
