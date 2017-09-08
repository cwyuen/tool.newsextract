package com.primecredit.tool.newsextract.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnccNewsExtractService {

	private static final Logger logger = LoggerFactory.getLogger(OnccNewsExtractService.class);

	@Autowired
	private CommonNewsExtractService commonNewsExtractService;
	
	private static final String ROOT_URL = "http://m.on.cc/nc/hknews/";
	private static final DateFormat PATH_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	private static final DateFormat PATH_TIME_FORMAT = new SimpleDateFormat("HHmmss");
	private static final String KEY_NEWS_DATE_COUNT = "NEWS_DATE_COUNT";
	private static final String KEY_NEWS_TIME_COUNT = "NEWS_TIME_COUNT";
	private static final String CONFIG_FILE_PATH = "/Users/maxwellyuen/Documents/samples/config.properties";

	private String newsDateStr = "20170101";
	private String newsTimeStr = "000000";

	//@Scheduled(fixedRate = 6000)
	public void extract() {
		init();

		String lastDayStr = generateLastDayString();

		boolean success = false;
		while (!success) {

			String newsCountStr = generateNewsTimeStr(newsTimeStr);
			newsTimeStr = newsCountStr;
			
			String urlStr = ROOT_URL + lastDayStr + "/" + lastDayStr + newsCountStr + "lc.html";

			success = true;

			URL url = null;
			try {
				url = new URL(urlStr);
			} catch (MalformedURLException e) {
				logger.error("MalformedURLException - OnccNewsExtractService.extract: {}" + e.getMessage());
				success = false;
			}

			List<String> htmlSources = new ArrayList<String>();

			try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
				String htmlLine;
				while ((htmlLine = br.readLine()) != null) {
					htmlSources.add(htmlLine);
				}
			} catch (IOException e) {
				logger.error("IOException - OnccNewsExtractService.extract: {}" + e.getMessage());
				success = false;
			}

			if (success) {
				for(String htmlSource : htmlSources) {
					List<String> htmls = commonNewsExtractService.extractChineseFromHtml(htmlSource);
					for(String html : htmls) {
						System.out.println(html);
					}
				}
				
				saveConfig(newsDateStr, newsTimeStr);
			}
		}

	}

	private String generateLastDayString() {
		// Get Last Day news
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		Date lastDay = cal.getTime();
		String lastDayStr = PATH_DATE_FORMAT.format(lastDay);

		return lastDayStr;
	}

	private void init() {
		loadConfig();

		String lastDayStr = this.generateLastDayString();
		// init value
		if (!newsDateStr.equalsIgnoreCase(lastDayStr)) {
			newsDateStr = lastDayStr;
			newsTimeStr = "000000";
		}
	}

	private void loadConfig() {

		Path path = Paths.get(CONFIG_FILE_PATH);
		File file = path.toFile();
		Properties prop = new Properties();

		try (InputStream stream = new FileInputStream(file)) {
			// load a properties file
			prop.load(stream);

			this.newsDateStr = prop.getProperty(KEY_NEWS_DATE_COUNT);
			this.newsTimeStr = prop.getProperty(KEY_NEWS_TIME_COUNT);

		} catch (IOException ex) {
			logger.error("IOException - OnccNewsExtractService.loadConfig: {}", ex.getMessage());
		}
	}

	private void saveConfig(String newsDateStr, String newTimeStr) {

		Path path = Paths.get(CONFIG_FILE_PATH);
		File file = path.toFile();
		Properties prop = new Properties();

		try (OutputStream out = new FileOutputStream(file);) {

			// set the properties value
			prop.setProperty(KEY_NEWS_DATE_COUNT, newsDateStr);
			prop.setProperty(KEY_NEWS_TIME_COUNT, newTimeStr);

			// save properties to project root folder

			prop.store(out, null);

		} catch (IOException ex) {
			logger.error("IOException - OnccNewsExtractService.loadConfig: {}", ex.getMessage());
		}

	}

	private String generateNewsTimeStr(String currentTimeStr) {
		Date sourceDate;
		try {
			sourceDate = PATH_TIME_FORMAT.parse(currentTimeStr);
		} catch (ParseException e) {
			return "000000";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(sourceDate);
		cal.add(Calendar.SECOND, 1);
		return PATH_TIME_FORMAT.format(cal.getTime());
	}
	
	
}
