package com.fatesg.utils;

import java.util.HashMap;

public class DescontoUtil {
    public static HashMap<String, Double> obterDescontosPadrao() {
        HashMap<String, Double> descontos = new HashMap<>();
        descontos.put("INSS", 11.0);// 11%
        descontos.put("IRRF", 0.75);// 0,75%
        descontos.put("Vale Transporte", 0.5);// 0,5%
        return descontos;
    }
}
