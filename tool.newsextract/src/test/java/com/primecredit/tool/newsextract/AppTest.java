package com.primecredit.tool.newsextract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.primecredit.tool.newsextract.services.OnccNewsExtractService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest{

	private static Logger logger = LoggerFactory.getLogger(AppTest.class);
	
	@Autowired
	private OnccNewsExtractService OnccNewsExtractService;

	@Test
	public void testOnccNewsExtractService() {
		
		logger.info("OnccNewsExtractService.extract: Start");
		OnccNewsExtractService.extract();
	}
}
