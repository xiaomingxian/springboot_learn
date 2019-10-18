package com.xxm.mbp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Insert;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoming.xian
 * @since 2019-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Person extends Model<Person> {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String name;
    /**
     * private * LocalDateTime  createTime;
     * 低版本默认是java.util.Date
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 高版本用法
     * private  * LocalDateTime  updateTime;
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(select = false)
    private int deleted;

    @Version
    private int version;



    @TableField(value = "tenant_id")
    private int zuhu;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
