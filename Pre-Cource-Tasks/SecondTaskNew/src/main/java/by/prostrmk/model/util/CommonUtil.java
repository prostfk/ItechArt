package by.prostrmk.model.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.util.List;

public class CommonUtil {

    public static String hash(String password){
        return DigestUtils.md5Hex(password);
    }


    public static void saveFile(){



    }

}
