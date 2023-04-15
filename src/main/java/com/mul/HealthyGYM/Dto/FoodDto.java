package com.mul.HealthyGYM.Dto;

public class FoodDto {
	 	private String DESC_KOR;
	    private String SERVING_WT;
	    private String NUTR_CONT1;
	    private String NUTR_CONT2;
	    private String NUTR_CONT3;
	    private String NUTR_CONT4;
	    private String NUTR_CONT5;
	    private String NUTR_CONT6;
	    private String NUTR_CONT7;
	    private String NUTR_CONT8;
	    private String NUTR_CONT9;
	    private String BGN_YEAR;
	    private String ANIMAL_PLANT;
	    
		public FoodDto() {
			super();
		}

		public FoodDto(String dESC_KOR, String sERVING_WT, String nUTR_CONT1, String nUTR_CONT2, String nUTR_CONT3,
				String nUTR_CONT4, String nUTR_CONT5, String nUTR_CONT6, String nUTR_CONT7, String nUTR_CONT8,
				String nUTR_CONT9, String bGN_YEAR, String aNIMAL_PLANT) {
			super();
			DESC_KOR = dESC_KOR;
			SERVING_WT = sERVING_WT;
			NUTR_CONT1 = nUTR_CONT1;
			NUTR_CONT2 = nUTR_CONT2;
			NUTR_CONT3 = nUTR_CONT3;
			NUTR_CONT4 = nUTR_CONT4;
			NUTR_CONT5 = nUTR_CONT5;
			NUTR_CONT6 = nUTR_CONT6;
			NUTR_CONT7 = nUTR_CONT7;
			NUTR_CONT8 = nUTR_CONT8;
			NUTR_CONT9 = nUTR_CONT9;
			BGN_YEAR = bGN_YEAR;
			ANIMAL_PLANT = aNIMAL_PLANT;
		}

		public String getDESC_KOR() {
			return DESC_KOR;
		}

		public void setDESC_KOR(String dESC_KOR) {
			DESC_KOR = dESC_KOR;
		}

		public String getSERVING_WT() {
			return SERVING_WT;
		}

		public void setSERVING_WT(String sERVING_WT) {
			SERVING_WT = sERVING_WT;
		}

		public String getNUTR_CONT1() {
			return NUTR_CONT1;
		}

		public void setNUTR_CONT1(String nUTR_CONT1) {
			NUTR_CONT1 = nUTR_CONT1;
		}

		public String getNUTR_CONT2() {
			return NUTR_CONT2;
		}

		public void setNUTR_CONT2(String nUTR_CONT2) {
			NUTR_CONT2 = nUTR_CONT2;
		}

		public String getNUTR_CONT3() {
			return NUTR_CONT3;
		}

		public void setNUTR_CONT3(String nUTR_CONT3) {
			NUTR_CONT3 = nUTR_CONT3;
		}

		public String getNUTR_CONT4() {
			return NUTR_CONT4;
		}

		public void setNUTR_CONT4(String nUTR_CONT4) {
			NUTR_CONT4 = nUTR_CONT4;
		}

		public String getNUTR_CONT5() {
			return NUTR_CONT5;
		}

		public void setNUTR_CONT5(String nUTR_CONT5) {
			NUTR_CONT5 = nUTR_CONT5;
		}

		public String getNUTR_CONT6() {
			return NUTR_CONT6;
		}

		public void setNUTR_CONT6(String nUTR_CONT6) {
			NUTR_CONT6 = nUTR_CONT6;
		}

		public String getNUTR_CONT7() {
			return NUTR_CONT7;
		}

		public void setNUTR_CONT7(String nUTR_CONT7) {
			NUTR_CONT7 = nUTR_CONT7;
		}

		public String getNUTR_CONT8() {
			return NUTR_CONT8;
		}

		public void setNUTR_CONT8(String nUTR_CONT8) {
			NUTR_CONT8 = nUTR_CONT8;
		}

		public String getNUTR_CONT9() {
			return NUTR_CONT9;
		}

		public void setNUTR_CONT9(String nUTR_CONT9) {
			NUTR_CONT9 = nUTR_CONT9;
		}

		public String getBGN_YEAR() {
			return BGN_YEAR;
		}

		public void setBGN_YEAR(String bGN_YEAR) {
			BGN_YEAR = bGN_YEAR;
		}

		public String getANIMAL_PLANT() {
			return ANIMAL_PLANT;
		}

		public void setANIMAL_PLANT(String aNIMAL_PLANT) {
			ANIMAL_PLANT = aNIMAL_PLANT;
		}

		@Override
		public String toString() {
			return "FoodDto [DESC_KOR=" + DESC_KOR + ", SERVING_WT=" + SERVING_WT + ", NUTR_CONT1=" + NUTR_CONT1
					+ ", NUTR_CONT2=" + NUTR_CONT2 + ", NUTR_CONT3=" + NUTR_CONT3 + ", NUTR_CONT4=" + NUTR_CONT4
					+ ", NUTR_CONT5=" + NUTR_CONT5 + ", NUTR_CONT6=" + NUTR_CONT6 + ", NUTR_CONT7=" + NUTR_CONT7
					+ ", NUTR_CONT8=" + NUTR_CONT8 + ", NUTR_CONT9=" + NUTR_CONT9 + ", BGN_YEAR=" + BGN_YEAR
					+ ", ANIMAL_PLANT=" + ANIMAL_PLANT + "]";
		}
	    
	    
}
