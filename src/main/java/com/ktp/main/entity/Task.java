package com.ktp.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Task implements Serializable {

    @TableId(value = "id", type = IdType.INPUT)
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
