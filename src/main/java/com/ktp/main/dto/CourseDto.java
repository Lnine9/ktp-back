package com.ktp.main.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CourseDto {
    private String id;

    private String courseName;

    private String year;

    private String term;

    private String background;

    private String teacherName;

    private Integer taskCount;
}
