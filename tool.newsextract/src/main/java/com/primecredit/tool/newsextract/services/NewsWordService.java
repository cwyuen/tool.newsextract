package com.primecredit.tool.newsextract.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.primecredit.tool.common.ChineseUtils;


@Service
public class NewsWordService {
	public List<String> extractNewsWord(String lineStr) {

		List<String> results = new ArrayList<>();

		String chineseLine = ChineseUtils.removeNonChinese(lineStr);

		String[] list = chineseLine.split("");

		int maxLength = list.length;

		for (int i = 0; i < list.length; i++) {
			String start = list[i];
			if (!start.equals("")) {

				// 3 Word Verb
				if (i + 2 < maxLength) {
					String word1 = list[i];
					String word2 = list[i + 1];
					String word3 = list[i + 2];
					if (!word1.equals("") || !word2.equals("") || !word3.equals("")) {
						StringBuilder sb = new StringBuilder();
						sb.append(word1);
						sb.append(word2);
						sb.append(word3);
						results.add(sb.toString());
					}
				}

				// 4 Word Verb
				if (i + 3 < maxLength) {
					String word1 = list[i];
					String word2 = list[i + 1];
					String word3 = list[i + 2];
					String word4 = list[i + 3];
					if (!word1.equals("") || !word2.equals("") || !word3.equals("") || !word4.equals("")) {
						StringBuilder sb = new StringBuilder();
						sb.append(word1);
						sb.append(word2);
						sb.append(word3);
						sb.append(word4);
						results.add(sb.toString());
					}
				}

				// 5 Word Verb
				if (i + 4 < maxLength) {
					String word1 = list[i];
					String word2 = list[i + 1];
					String word3 = list[i + 2];
					String word4 = list[i + 3];
					String word5 = list[i + 4];
					if (!word1.equals("") || !word2.equals("") || !word3.equals("") || !word4.equals("")
							|| !word5.equals("")) {
						StringBuilder sb = new StringBuilder();
						sb.append(word1);
						sb.append(word2);
						sb.append(word3);
						sb.append(word4);
						sb.append(word5);
						results.add(sb.toString());
					}
				}

				// 6 Word Verb
				if (i + 5 < maxLength) {
					String word1 = list[i];
					String word2 = list[i + 1];
					String word3 = list[i + 2];
					String word4 = list[i + 3];
					String word5 = list[i + 4];
					String word6 = list[i + 5];
					if (!word1.equals("") || !word2.equals("") || !word3.equals("") || !word4.equals("")
							|| !word5.equals("") || !word6.equals("")) {
						StringBuilder sb = new StringBuilder();
						sb.append(word1);
						sb.append(word2);
						sb.append(word3);
						sb.append(word4);
						sb.append(word5);
						sb.append(word6);
						results.add(sb.toString());
					}
				}

			}

		}
		return results;
	}
}
