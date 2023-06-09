package util;

import java.util.Base64;

public class Decryption {
    public static String decodeCredentials(String encodedString){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByte = decoder.decode(encodedString);
        return new String(decodedByte);
    }
}
