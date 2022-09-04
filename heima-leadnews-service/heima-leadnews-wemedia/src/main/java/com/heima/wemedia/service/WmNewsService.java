package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;

/**
 * 自媒体图文内容信息表(WmNews)表服务接口
 *
 * @author makejava
 * @since 2022-09-04 15:22:27
 */
public interface WmNewsService extends IService<WmNews> {

    /***
     * 查询所有
     * @param dto
     * @return
     */
    ResponseResult findAll(WmNewsPageReqDto dto);
}

