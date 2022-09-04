package com.heima.model.wemedia.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 自媒体图文引用素材信息表(WmNewsMaterial)表实体类
 *
 * @author makejava
 * @since 2022-09-04 17:18:46
 */
@SuppressWarnings("serial")
@Data
@TableName("wm_news_material")
public class WmNewsMaterial extends Model<WmNewsMaterial> {

    /**
     * 主键
     */

    @TableId("id")
    private Integer id;

    /**
     * 素材ID
     */

    @TableField("material_id")
    private Integer materialId;
    /**
     * 图文ID
     */

    @TableField("news_id")
    private Integer newsId;
    /**
     * 引用类型
     * 0 内容引用
     * 1 主图引用
     */

    @TableField("type")
    private Integer type;
    /**
     * 引用排序
     */

    @TableField("ord")
    private Integer ord;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

