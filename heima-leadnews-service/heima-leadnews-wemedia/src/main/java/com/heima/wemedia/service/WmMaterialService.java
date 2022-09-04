package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

public interface WmMaterialService extends IService<WmMaterial> {

    ResponseResult uploadPicTure(MultipartFile multipartFile);

    /***
     * 分页显示图片列表
     * @param wmMaterialDto
     * @return
     */
    ResponseResult list(WmMaterialDto wmMaterialDto);
}

