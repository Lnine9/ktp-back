package com.ktp.main.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HomeworkDto {

    private String id;

    private String taskId;

    private String userId;

    private Integer score;

    private String filePath;

    private String fileName;

}
