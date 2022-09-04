package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmChannel;

public interface WmchannelServer extends IService<WmChannel> {
    /***
     * 查找所有分类
     * @return
     */
    ResponseResult findAll();

}
