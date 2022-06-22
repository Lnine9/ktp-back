package com.ktp.main.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileUtil {

    public static final String DIR = "D:\\ktpFile";

    public static String saveFile(MultipartFile file) throws IOException {
        String uuid = StringUtil.getUUID();
        String filename = file.getOriginalFilename();

        String newname = uuid + filename;
        //保存文件对象 加上uuid是为了防止文件重名
        File serverFile = new File(DIR+"\\"+newname);

        file.transferTo(serverFile);

        return newname;
    }

}
