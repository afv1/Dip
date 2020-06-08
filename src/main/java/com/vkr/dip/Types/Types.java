package com.vkr.dip.Types;

import java.util.ArrayList;
import java.util.List;

public class Types
{
    public static final String TTN = "ТОВАРНО-ТРАНСПОРТНАЯ НАКЛАДНАЯ";
    public static final String SPR = "СПРАВКА";
    public static final String DOC = "ДОГОВОР";

    List types = new ArrayList();

    public static String ttn = "450,60,90,35";
    public static String spr = "";
    public static String doc = "";

    public void Types()
    {
        types.add(TTN);
        types.add(SPR);
        types.add(DOC);
    }
}
