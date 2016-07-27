package com.nwpu.wsner.constants;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zjy on 16/7/26.
 */
public class GattInfo {
    public static final UUID CC_SERVICE_UUID = UUID.fromString("0000ccc0-0000-1000-8000-00805f9b34fb");
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static final UUID OAD_SERVICE_UUID = UUID.fromString("f000ffc0-0451-4000-b000-000000000000");
    private static Map<String, String> mDescrMap = new HashMap();
    private static Map<String, String> mNameMap = new HashMap();
    private static final String uuidBtSigBase = "0000****-0000-1000-8000-00805f9b34fb";
    private static final String uuidTiBase = "f000****-0451-4000-b000-000000000000";

    public GattInfo(XmlResourceParser var1) {
        try {
            this.readUuidData(var1);
        } catch (XmlPullParserException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }

    public static String getDescription(UUID var0) {
        String var1 = toShortUuidStr(var0);
        return (String)mDescrMap.get(var1.toUpperCase());
    }

    public static boolean isBtSigUuid(UUID var0) {
        return var0.toString().replace(toShortUuidStr(var0), "****").equals("0000****-0000-1000-8000-00805f9b34fb");
    }

    public static boolean isTiUuid(UUID var0) {
        return var0.toString().replace(toShortUuidStr(var0), "****").equals("f000****-0451-4000-b000-000000000000");
    }

    private void readUuidData(XmlResourceParser var1) throws XmlPullParserException, IOException {
        var1.next();
        String var3 = null;
        String var4 = null;
        String var5 = null;

        for(int var6 = var1.getEventType(); var6 != 1; var6 = var1.next()) {
            if(var6 != 0) {
                if(var6 == 2) {
                    var3 = var1.getName();
                    var4 = var1.getAttributeValue((String)null, "uuid");
                    var5 = var1.getAttributeValue((String)null, "descr");
                } else if(var6 != 3 && var6 == 4 && var3.equalsIgnoreCase("item") && !var4.isEmpty()) {
                    var4 = var4.replace("0x", "");
                    mNameMap.put(var4, var1.getText());
                    mDescrMap.put(var4, var5);
                }
            }
        }

    }

    private static String toShortUuidStr(UUID var0) {
        return var0.toString().substring(4, 8);
    }

    private static String uuidToName(String var0) {
        return (String)mNameMap.get(var0);
    }

    public static String uuidToName(UUID var0) {
        return uuidToName(toShortUuidStr(var0).toUpperCase());
    }

    public static String uuidToString(UUID var0) {
        String var1;
        if(isBtSigUuid(var0)) {
            var1 = toShortUuidStr(var0);
        } else {
            var1 = var0.toString();
        }

        return var1.toUpperCase();
    }
}
