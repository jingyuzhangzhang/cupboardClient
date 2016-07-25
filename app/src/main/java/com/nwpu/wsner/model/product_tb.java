package com.nwpu.wsner.model;

import android.provider.ContactsContract;

import org.litepal.crud.DataSupport;

/**
 * Created by lmy on 16/7/22.
 */
public class product_tb extends DataSupport{
    private long id;
    private String product_barcode;
    private String product_name;
    private String product_date;
    private String product_valid;
    private String product_function;
    private String product_type;



    public product_tb( String s, String s1, String s2, String s3, String s4, String s5) {

        this.product_barcode=s;
        this.product_name=s1;
        this.product_date=s2;
        this.product_valid=s3;
        this.product_function=s4;
        this.product_type=s5;
    }



    public String getProduct_barcode() {
        return product_barcode;
    }

    public void setProduct_barcode(String product_barcode) {
        this.product_barcode = product_barcode;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_date() {
        return product_date;
    }

    public void setProduct_date(String product_date) {
        this.product_date = product_date;
    }

    public String getProduct_valid() {
        return product_valid;
    }

    public void setProduct_valid(String product_valid) {
        this.product_valid = product_valid;
    }

    public String getProduct_function() {
        return product_function;
    }

    public void setProduct_function(String product_function) {
        this.product_function = product_function;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }
}
