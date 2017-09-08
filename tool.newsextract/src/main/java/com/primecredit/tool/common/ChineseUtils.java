package com.primecredit.tool.common;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.util.HashSet;
import java.util.Set;

public class ChineseUtils {

	public static String removeNonChinese(String input) {
		if(input == null) {
			input = "";
		}
		
		StringBuilder sbResult = new StringBuilder();

		@SuppressWarnings("serial")
		Set<UnicodeBlock> chineseUnicodeBlocks = new HashSet<UnicodeBlock>() {
			{
				add(UnicodeBlock.CJK_COMPATIBILITY);
				add(UnicodeBlock.CJK_COMPATIBILITY_FORMS);
				add(UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS);
				add(UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT);
				add(UnicodeBlock.CJK_RADICALS_SUPPLEMENT);
				add(UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
				add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
				add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
				add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B);
				add(UnicodeBlock.KANGXI_RADICALS);
				add(UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS);

			}
		};

		for (char c : input.toCharArray()) {
			if (chineseUnicodeBlocks.contains(UnicodeBlock.of(c))) {
				sbResult.append(c);
			}
		}

		return sbResult.toString();
	}
	
	public static String convertToUrlBig5(String utf8){
		byte[] big5Str = new byte[0];
		
		try {
			big5Str = utf8.getBytes("big5");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		for(byte b : big5Str){		
			sb.append("%");
			sb.append(String.format("%02X", b));
		}
		
		return sb.toString();
	}
}
