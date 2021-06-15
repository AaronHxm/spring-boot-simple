package com.simple.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName BaseEntity.java
 * @Description TODO
 * @createTime 2021年05月31日 16:48:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity<T extends Model<?>> extends Model<T> {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private String remarks;

//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private String updateName;
//    @TableField(fill = FieldFill.INSERT)
//    private String createName;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}