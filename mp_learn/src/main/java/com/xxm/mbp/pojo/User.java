package com.xxm.mbp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoming.xian
 * @since 2019-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)//忽略equals和HashCode方法重写的警告
@Accessors(chain = true)
public class User extends Model<User> {//AR模式

    private static final long serialVersionUID=1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;


    //此处的condition是自定义
    @TableField(value = "username",condition = SqlCondition.LIKE)
    private String name;

    private String password;


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
