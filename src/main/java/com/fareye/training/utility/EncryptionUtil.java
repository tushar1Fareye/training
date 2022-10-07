package com.fareye.training.utility;

import org.apache.commons.codec.binary.Base64;

public class EncryptionUtil {

    static Base64 base64 = new Base64();

    public static String encodeString(String rawString) {
        return new String(base64.encode(rawString.getBytes()));
    }

    public static String decodeString(String encodedString) {
        return new String(base64.decode(encodedString.getBytes()));
    }


}
