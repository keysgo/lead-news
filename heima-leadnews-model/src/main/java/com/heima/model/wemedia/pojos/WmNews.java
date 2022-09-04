package com.heima.model.wemedia.pojos;

import java.math.BigInteger;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 自媒体图文内容信息表(WmNews)表实体类
 *
 * @author makejava
 * @since 2022-09-04 14:19:35
 */
@SuppressWarnings("serial")
@Data
@TableName("wm_news")
public class WmNews extends Model<WmNews> {

    /**
     * 主键
     */

    @TableId("id")
    private Integer id;

    /**
     * 自媒体用户ID
     */

    @TableField("user_id")
    private Integer userId;
    /**
     * 标题
     */

    @TableField("title")
    private String title;
    /**
     * 图文内容
     */

    @TableField("content")
    private String content;
    /**
     * 文章布局
     * 0 无图文章
     * 1 单图文章
     * 3 多图文章
     */

    @TableField("type")
    private Integer type;
    /**
     * 图文频道ID
     */

    @TableField("channel_id")
    private Integer channelId;

    @TableField("labels")
    private String labels;
    /**
     * 创建时间
     */

    @TableField("created_time")
    private Date createdTime;
    /**
     * 提交时间
     */

    @TableField("submited_time")
    private Date submitedTime;
    /**
     * 当前状态
     * 0 草稿
     * 1 提交（待审核）
     * 2 审核失败
     * 3 人工审核
     * 4 人工审核通过
     * 8 审核通过（待发布）
     * 9 已发布
     */

    @TableField("status")
    private Integer status;
    /**
     * 定时发布时间，不定时则为空
     */

    @TableField("publish_time")
    private Date publishTime;
    /**
     * 拒绝理由
     */

    @TableField("reason")
    private String reason;
    /**
     * 发布库文章ID
     */

    @TableField("article_id")
    private BigInteger articleId;

    /**
     *图片用逗号分隔
    */
    @TableField("images")
    private String images;

    @TableField("enable")
    private Integer enable;

    //状态枚举类
    /**
     * 当前状态
     * 0 草稿
     * 1 提交（待审核）
     * 2 审核失败
     * 3 人工审核
     * 4 人工审核通过
     * 8 审核通过（待发布）
     * 9 已发布
     */
    @Alias("WmNewsStatus")
    public enum Status{
        NORMAL((short)0),SUBMIT((short)1),FAIL((short)2),ADMIN_AUTH((short)3),ADMIN_SUCCESS((short)4),SUCCESS((short)8),PUBLISHED((short)9);
        short code;
        Status(short code){
            this.code = code;
        }
        public short getCode(){
            return this.code;
        }
    }
}

