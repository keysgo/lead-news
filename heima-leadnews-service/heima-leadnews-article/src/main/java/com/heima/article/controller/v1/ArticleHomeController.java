package com.heima.article.controller.v1;

import com.heima.article.service.ApArticleService;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController {

    @Autowired
    ApArticleService apArticleService;

    /***
     * 加载首页数据
     * @param articleHomeDto
     * @return
     */
    @PostMapping()
    public ResponseResult load(@RequestBody ArticleHomeDto articleHomeDto){
        ResponseResult responseResult = apArticleService.loadApArticleList(articleHomeDto, (short) 1);
        return responseResult;
    }

    /***
     * 上拉加载更多
     * @param articleHomeDto
     * @return
     */
    @PostMapping("/more")
    public ResponseResult loadMore(@RequestBody ArticleHomeDto articleHomeDto){
        return null;
    }

    /***
     * 下拉加载最新
     * @param articleHomeDto
     * @return
     */
    @PostMapping("/new")
    public ResponseResult loadNew(@RequestBody ArticleHomeDto articleHomeDto){
        return null;
    }

}
