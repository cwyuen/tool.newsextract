package com.primecredit.tool.newsextract.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.primecredit.tool.common.ChineseUtils;

@Service
public class CommonNewsExtractService {

	private static final Logger logger = LoggerFactory.getLogger(CommonNewsExtractService.class);

	public List<String> extractChineseFromHtml(String htmlSource) {
		
		logger.info("CommonNewsExtractService.extractChineseFromHtml: Start");
		logger.info("HTML Source = {}", htmlSource);
		
		List<String> results = new ArrayList<>();
		
		String[] htmls = htmlSource.split("\\s*(=>|,|\\s|。|「|」|《|》|、)\\s*");

		for (String html : htmls) {

			String chinese = ChineseUtils.removeNonChinese(html).trim();

			if (!chinese.equals("")) {

				results.add(chinese);
			}
		}
		
		return results;
	}
	
}
