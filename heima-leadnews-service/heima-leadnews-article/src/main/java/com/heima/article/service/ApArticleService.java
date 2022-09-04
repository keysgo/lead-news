package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;

public interface ApArticleService extends IService<ApArticle> {
    /***
     * type 为1加载更多   2加载最新
     * @param dto
     * @param type
     * @return
     */
    ResponseResult loadApArticleList(ArticleHomeDto dto,Short type);
}
