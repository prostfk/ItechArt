package by.prostrmk.model.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

    public static String hash(String password){
        return DigestUtils.md5Hex(password);
    }

}
