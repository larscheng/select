package com.slxy.www.model.po;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengql123
 * @since 2018-03-29
 */
@TableName("select_score_per")
public class SelectScorePer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 成绩模块
     */
    @TableField("score_name")
    private String scoreName;
    /**
     * 成绩所占百分比
     */
    @TableField("score_per")
    private Integer scorePer;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @TableField("gmt_modify")
    private Date gmtModify;


    public Integer getId() {
        return id;
    }

    public SelectScorePer setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getScoreName() {
        return scoreName;
    }

    public SelectScorePer setScoreName(String scoreName) {
        this.scoreName = scoreName;
        return this;
    }

    public Integer getScorePer() {
        return scorePer;
    }

    public SelectScorePer setScorePer(Integer scorePer) {
        this.scorePer = scorePer;
        return this;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public SelectScorePer setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public SelectScorePer setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
        return this;
    }

    @Override
    public String toString() {
        return "SelectScorePer{" +
        "id=" + id +
        ", scoreName=" + scoreName +
        ", scorePer=" + scorePer +
        ", gmtCreate=" + gmtCreate +
        ", gmtModify=" + gmtModify +
        "}";
    }
}
