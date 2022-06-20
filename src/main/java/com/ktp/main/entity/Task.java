package com.ktp.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String title;

    private String courseId;

    private String description;

    private Float fullScore;

    private LocalDateTime deadline;

    private String type;

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
