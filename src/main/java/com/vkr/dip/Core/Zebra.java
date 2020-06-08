package com.vkr.dip.Core;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Zebra
{
    public static String run(String filePath) throws WriterException, IOException, NotFoundException
    {
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        return readBarCode(filePath, charset, hintMap);
    }

    public static String readBarCode(String filePath, String charset, Map hintMap) throws FileNotFoundException, IOException, NotFoundException
    {
        try
        {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
            Result ZebraResult = new MultiFormatReader().decode(binaryBitmap, hintMap);

            return ZebraResult.getText();
        }
        catch (Exception e)
        {
            return "false";
        }
    }
}
