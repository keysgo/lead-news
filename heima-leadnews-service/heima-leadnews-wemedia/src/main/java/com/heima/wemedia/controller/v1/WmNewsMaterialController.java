package com.heima.wemedia.controller.v1;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heima.model.wemedia.pojos.WmNewsMaterial;
import com.heima.wemedia.service.WmNewsMaterialService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 自媒体图文引用素材信息表(WmNewsMaterial)表控制层
 *
 * @author makejava
 * @since 2022-09-04 17:18:43
 */
@RestController
@RequestMapping("wmNewsMaterial")
public class WmNewsMaterialController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private WmNewsMaterialService wmNewsMaterialService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param wmNewsMaterial 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<WmNewsMaterial> page, WmNewsMaterial wmNewsMaterial) {
        return success(this.wmNewsMaterialService.page(page, new QueryWrapper<>(wmNewsMaterial)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.wmNewsMaterialService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param wmNewsMaterial 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody WmNewsMaterial wmNewsMaterial) {
        return success(this.wmNewsMaterialService.save(wmNewsMaterial));
    }

    /**
     * 修改数据
     *
     * @param wmNewsMaterial 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody WmNewsMaterial wmNewsMaterial) {
        return success(this.wmNewsMaterialService.updateById(wmNewsMaterial));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.wmNewsMaterialService.removeByIds(idList));
    }
}

