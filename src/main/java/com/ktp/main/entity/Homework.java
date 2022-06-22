package com.ktp.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Homework implements Serializable {

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    private String taskId;

    private String userId;

    private Integer score;

    private String filePath;

    private String fileName;


}
