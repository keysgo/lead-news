package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.file.service.impl.MinIOFileStorageService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.utils.thread.WmThreadLocalUtils;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.service.WmMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**+
 *
 */

@Service
@Slf4j
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {

    @Autowired
    private MinIOFileStorageService minIOFileStorageService;

    /***
     * 上传文件到minio
     * @param multipartFile
     * @return
     */
    @Override
    public ResponseResult uploadPicTure(MultipartFile multipartFile) {

        if(multipartFile==null||multipartFile.getSize()<0){
            ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"参数错误");
        }

        String filename = multipartFile.getOriginalFilename();
        //后缀名,此处需要验证文件后缀
        String postfix = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-","");
        String url=null;
        //上传文件
        try {
           url = minIOFileStorageService.uploadImgFile("", uuid + postfix, multipartFile.getInputStream());
           log.info("上传文件成功:"+url);
        } catch (IOException e) {
            log.error("上传文件失败");
            e.printStackTrace();
        }
        log.debug(WmThreadLocalUtils.getUser().getId()+"");
        //写入结果到数据库
        WmMaterial wmMaterial=new WmMaterial();
        wmMaterial.setUserId(WmThreadLocalUtils.getUser().getId());
        wmMaterial.setUrl(url);
        wmMaterial.setIsCollection((short)0);
        wmMaterial.setCreatedTime(new Date());
        wmMaterial.setType((short)0);
        save(wmMaterial);
        return ResponseResult.okResult(wmMaterial);
    }

    /***
     * 分页显示图片列表
     * @param wmMaterialDto
     * @return
     */
    @Override
    public ResponseResult list(WmMaterialDto wmMaterialDto) {
        //设置默认分页参数
        wmMaterialDto.checkParam();
        //查询条件
        LambdaQueryWrapper<WmMaterial> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //判断参数
        if(wmMaterialDto.getIsCollection()!=null){
            //判断是否收藏
            lambdaQueryWrapper.eq(WmMaterial::getIsCollection,wmMaterialDto.getIsCollection());
        }
        //判断用户
        lambdaQueryWrapper.eq(WmMaterial::getUserId,WmThreadLocalUtils.getUser().getId());
        //构建分页查询条件
        IPage page=new Page(wmMaterialDto.getPage(),wmMaterialDto.getSize());
        //分页查询
        IPage pageInfo = page(page, lambdaQueryWrapper);
        //设置返回参数中分页参数
        ResponseResult responseResult=new PageResponseResult(wmMaterialDto.getPage(),wmMaterialDto.getSize(), (int) pageInfo.getTotal());
        //设置返回数据
        responseResult.setData(pageInfo.getRecords());
        return responseResult;
    }
}
