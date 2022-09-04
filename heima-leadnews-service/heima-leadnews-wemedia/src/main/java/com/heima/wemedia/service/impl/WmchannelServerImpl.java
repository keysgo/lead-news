package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.wemedia.mapper.WmchannelMapper;
import com.heima.wemedia.service.WmchannelServer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WmchannelServerImpl extends ServiceImpl<WmchannelMapper, WmChannel> implements WmchannelServer {
    @Override
    public ResponseResult findAll() {
        List<WmChannel> list = list();
        return ResponseResult.okResult(list);
    }
}
