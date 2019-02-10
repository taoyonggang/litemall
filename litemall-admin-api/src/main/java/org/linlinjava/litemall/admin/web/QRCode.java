package org.linlinjava.litemall.admin.web;

import org.linlinjava.litemall.admin.util.QRCodeUtil;

public class QRCode {
    public static void main(String[] args) throws Exception {
        String text = "http://www.1897.com/";

        //QRCodeUtil.encode(text,"", "D:/barcode",true);
        QRCodeUtil.encode(text, "D:/barcode/milk.jpg", "D:/barcode", true);


    }
}
