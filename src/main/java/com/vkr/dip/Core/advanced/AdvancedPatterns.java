package com.vkr.dip.Core.advanced;

import java.util.HashMap;

public class AdvancedPatterns
{
    public static HashMap<String, String> advPat = new HashMap();

    public static void patInit()
    {
        advPat.put("ТОВАРНО-ТРАНСПОРТНАЯ НАКЛАДНАЯ","450,60,90,35");
    }

    public static String getAdvPat(String key)
    {
        return advPat.get(key);
        //advPat.e
    }


}
