package com.mul.HealthyGYM.Util;

import java.util.Date;

public class FileUtility {
	
	public static String getNewFileName(String filename) {
		String newfilename = "";
		String fpost = "";
		
		if (filename.indexOf(".") >= 0) { // 확장자명 있음
			fpost = filename.substring(filename.indexOf('.')); // -> .jpg
			newfilename = new Date().getTime() + fpost; // -> 23645753 + .jpg -> 숫자 + 문자이기에 Date()가 문자열로 바뀜
		}
		else { // 확장자명 없음
			newfilename = new Date().getTime() + ".back"; // -> 26346467 + .back
		}
		
		return newfilename;
	}
}
