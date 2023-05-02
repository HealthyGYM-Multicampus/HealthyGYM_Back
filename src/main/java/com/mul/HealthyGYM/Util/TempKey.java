package com.mul.HealthyGYM.Util;

import java.util.Random;

public class TempKey {
	private boolean onlyNumber;		// 대문자를 소문자로 바꿀 것인지 여부 설정	true == 소문자, false == 그대로반환
    private int size;

    public String getKey(int size, boolean onlyNumber) {
        this.size = size;
        this.onlyNumber = onlyNumber;
        
        if(onlyNumber) {
        	Random ran = new Random();
        	String key = "";
        	for (int i = 0; i < size; i++) {
				key += Integer.toString(ran.nextInt(10)); 
			}
        	return key;
        }
        return init();
    }

    private String init() {
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        int num  = 0;
        do {
            num = ran.nextInt(75) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
                sb.append((char) num);
            } else {
                continue;
            }
        } while (sb.length() < size);

        return sb.toString().toLowerCase();
    }
}
