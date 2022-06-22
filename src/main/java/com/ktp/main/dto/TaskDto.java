package com.ktp.main.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TaskDto {

    private String id;

    private String title;

    private String courseId;

    private String description;

    private Integer fullScore;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadline;

    /**
     * 已批改数量
     */
    private Integer correctNum;

    /**
     * 未批改数量
     */
    private Integer uncorrectNum;

    /**
     * 已提交数量
     */
    private Integer submitNum;

    /**
     * 未提交数量
     */
    private Integer unSubmitNum;

}
