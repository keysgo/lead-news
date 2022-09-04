package com.heima.article.test;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.ArticleApplication;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.service.ApArticleService;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest(classes = ArticleApplication.class)
@RunWith(SpringRunner.class)
public class ArticleFreemarkerTest {
    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private Configuration configuration;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ApArticleService apArticleService;
@Test
    public void test01() throws IOException, TemplateException {
        //获取文章内容
        ApArticleContent apArticleContent = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId, 1302862387124125698L));
        //1.生成页面
        //新建字符流
        StringWriter out=new StringWriter();
        //创建Freemarker模板对象
        Template template = configuration.getTemplate("article.ftl");
        //创建一个map对象，用于存储文章内容数据
        Map<String,Object> map=new HashMap<>();
        //JSON.parseArray(apArticleContent.getContent())把文章内容转换成对象
        map.put("content", JSON.parseArray(apArticleContent.getContent()));
        //把map对象放入out字符流中
        template.process(map,out);
        //把ou字符流放入InputStream输出流中
        InputStream byteArrayInputStream = new ByteArrayInputStream(out.toString().getBytes());
        //上传文件到minio服务器
        String s = fileStorageService.uploadHtmlFile("", apArticleContent.getArticleId()+".html", byteArrayInputStream);
        //把上传完url写入数据库ap_article中static_url
        ApArticle article=new ApArticle();
        article.setStaticUrl(s);
        article.setId(apArticleContent.getArticleId());
        apArticleService.updateById(article);
        System.out.println(s);
    }
}
