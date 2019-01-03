package com.five.year.fiveyearblog;

import com.five.year.fiveyearblog.entity.BlogArticle;
import com.five.year.fiveyearblog.mapper.BlogArticleMapper;
import com.five.year.fiveyearblog.service.BlogArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FiveYearBlogApplicationTests {

	@Autowired
	private BlogArticleMapper mapper;

	@Test
	public void contextLoads() {
		for (int i = 0; i < 50; i++) {
//            BlogArticle blogArticle = new BlogArticle();
//            blogArticle.setContent("段落示意：蚂蚁金服设计平台 ant.design，用最小的工作量，无缝接入蚂蚁金服生态，提供跨越设计与开发的体验解决方案。蚂蚁金服设计平台 ant.design，用最小的工作量，无缝接入蚂蚁金服生态，提供跨越设计与开发的体验解决方案。");
//            blogArticle.setId(UUID.randomUUID().toString());
//            blogArticle.setTitle("哈哈哈哈哈哈哈哈");
//            blogArticle.setPointNum(new BigDecimal(12312312));
//            blogArticle.setReadNum(new BigDecimal(12312312));
//            mapper.insert(blogArticle);
        }
	}




}

