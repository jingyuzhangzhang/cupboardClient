package com.nwpu.wsner.constants;

/**
 * Created by zjy on 16/7/18.
 */
public class Url {
    private static final String IP_PORT="http://10.13.33.91:8088/";
    private static final String BASE_URL=IP_PORT+"SmartCabinet/";


    public static final String LOGIN_URL=BASE_URL+"user/userLogin?";
    public static final String SIGIN_URL=BASE_URL+"";
    public static final String WETHER_URL="http://php.weather.sina.com.cn/xml.php?city=%CE%F7%B0%B2&password=DJOYnieT8234jlsK&day=0";

    private static final String PRODUCT_ADD_URL = IP_PORT+"SmartCabinet/product/productScan?";

    /**
     添加物品 * http://ip:port/SmartCabinet/product?barcode=xx&productionDate=xx&productionValid=xxx&username=xxx
     */
    public static String addProdcutUrl(String barcode,String productionDate,String username ) {
        return PRODUCT_ADD_URL + "barcode=" + barcode + "&productionDate=" + productionDate + "&username=" + username;
    }

}
