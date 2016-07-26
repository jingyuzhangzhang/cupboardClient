package com.nwpu.wsner.constants;

/**
 * URL static constants Util Class used for Http Request
 */
public class UrlUtil {

    private static final String BASE_URL = "http://ip:port/SmartCabinet/";
    /**
    添加物品 * http://ip:port/SmartCabinet/product?barcode=xx&productionDate=xx&productionValid=xxx&username=xxx
     */
    public static String addProdcutUrl(String barcode,String productionDate,String productionValid,String username ){
        return BASE_URL+"product?"+"barcode="+barcode+"productionDate="+productionDate+"productionValid="+productionValid+"username+"+username;
    }

}
