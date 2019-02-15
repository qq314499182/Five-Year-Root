package com.five.year.fiveyearblog;

import com.five.year.fiveyearblog.cache.BlogUserCache;
import com.five.year.fiveyearblog.entity.BlogArticle;
import com.five.year.fiveyearblog.entity.BlogUser;
import com.five.year.fiveyearblog.mapper.BlogArticleMapper;
import com.five.year.fiveyearblog.service.BlogArticleService;
import org.apache.ibatis.cache.decorators.BlockingCache;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

	@Autowired
	private BlogUserCache blogUserCache;

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

	@Test
	public void contentListLoads(){
		BlogArticle blogArticle = mapper.selectByPrimaryKey("ac5158f3-5b5c-48c5-bace-ed0c1be9e5e7");
		Document document = Jsoup.parseBodyFragment(blogArticle.getContent());
		Element p = document.selectFirst("p");
		String text = p.text();
		System.out.println(text);
	}

	@Test
	public void testCache(){
		BlogUser blogUser = new BlogUser();
		blogUser.setUserName("哈克叫啥空间很大");
		blogUser.setUserPassword("asdkjhaskdjhaksjd");
		blogUserCache.put("456",blogUser);
	}

}

