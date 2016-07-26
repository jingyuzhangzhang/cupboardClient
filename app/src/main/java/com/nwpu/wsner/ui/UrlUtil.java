package com.nwpu.wsner.ui;

/**
 * URL static constants Util Class used for Http Request
 */
public class UrlUtil {

    private static final String PRODUCT_ADD_URL = "http://localhost:8088/SmartCabinet/product/productScan?";

    /**
    添加物品 * http://ip:port/SmartCabinet/product?barcode=xx&productionDate=xx&productionValid=xxx&username=xxx
     */
    public static String addProdcutUrl(String barcode,String productionDate,String username ){
        return PRODUCT_ADD_URL+"barcode="+barcode+"productionDate="+productionDate+"username="+username;
    }
}
