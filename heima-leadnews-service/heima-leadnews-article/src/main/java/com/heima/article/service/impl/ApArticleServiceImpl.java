package com.heima.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle>  implements ApArticleService {
    //每页最大条数
    private final static short MAX_PAGE_SIZE=50;

    @Autowired
    ApArticleMapper apArticleMapper;
    /***
     * type 为1加载更多   2加载最新
     * @param dto
     * @param type
     * @return
     */
    @Override
    public ResponseResult loadApArticleList(ArticleHomeDto dto, Short type) {
        //校验参数
        Integer size = dto.getSize();
        if(size==null|| size==0){
            size=10;
        }
        size=Math.min(size,MAX_PAGE_SIZE);
        dto.setSize(size);
        //校验加载类别 为1加载更多   2加载最新
        if(!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE)&&!type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)){
            type=ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        //校验频道
        if(dto.getTag()==null){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        //校验时间
        if(dto.getMaxBehotTime()==null){
            dto.setMaxBehotTime(new Date());
        }
        //校验时间
        if(dto.getMinBehotTime()==null){
            dto.setMinBehotTime(new Date());
        }
        //查询
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, type);
        return ResponseResult.okResult(apArticles);
    }
}
