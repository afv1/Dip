package com.vkr.dip.Core;

import java.util.HashMap;

public class Patterns
{
    public static HashMap<String, String> pats = new HashMap();

    public static void getPattern(String type)
    {
        pats.put("ТОВАРНО-ТРАНСПОРТНАЯ НАКЛАДНАЯ","0,0,300,100;450,60,90,35");
        pats.put("СПРАВКА","0,0,300,100;450,60,90,35");
        pats.put("ДОГОВОР","0,0,300,100;450,60,90,35");

    }
}
