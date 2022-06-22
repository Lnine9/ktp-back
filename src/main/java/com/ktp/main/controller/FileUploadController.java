package com.ktp.main.controller;

import com.ktp.main.util.FileUtil;
import com.ktp.main.util.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@Slf4j
@RequestMapping("/api/file")
public class FileUploadController {

    @PostMapping
    public ResResult<String> upload(@RequestBody MultipartFile file){
        String filename;
        try {
            filename = FileUtil.saveFile(file);
            return ResResult.ok(filename);
        } catch (IOException e){
            log.info(Arrays.toString(e.getStackTrace()));
            return ResResult.fail("上传失败,检查文件");
        }
    }

}
