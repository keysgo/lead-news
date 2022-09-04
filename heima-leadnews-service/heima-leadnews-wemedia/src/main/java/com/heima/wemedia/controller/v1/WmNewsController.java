package com.heima.wemedia.controller.v1;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 自媒体图文内容信息表(WmNews)表控制层
 *
 * @author keysto
 * @since 2022-09-04 15:22:24
 */
@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private WmNewsService wmNewsService;

    /**
     * 分页查询所有数据
     *
     * @param dto   WmNews对象dto
     * @return 所有数据
     */
    @PostMapping("/list")
    public ResponseResult findAll(WmNewsPageReqDto dto) {
        return wmNewsService.findAll(dto);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.wmNewsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param wmNews 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody WmNews wmNews) {
        return success(this.wmNewsService.save(wmNews));
    }

    /**
     * 修改数据
     *
     * @param wmNews 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody WmNews wmNews) {
        return success(this.wmNewsService.updateById(wmNews));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.wmNewsService.removeByIds(idList));
    }
}

