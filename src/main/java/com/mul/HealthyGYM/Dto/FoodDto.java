package com.mul.HealthyGYM.Dto;

import java.math.BigInteger;

public class FoodDto {
		private int bbsseq;
		private int msgseq;
		private int whenmeal;

		private String desckor;
	    private String servingwt;
	    private String nutrcont1;
	    private String nutrcont2;
	    private String nutrcont3;
	    private String nutrcont4;
	    private String nutrcont5;
	    private String nutrcont6;
	    private String nutrcont7;
	    private String nutrcont8;
	    private String nutrcont9;
	    private String bgnyear;
	    private String animalplant;
	    
		public FoodDto() {
			super();
		}

		public FoodDto(String desckor, String servingwt, String nutrcont1, String nutrcont2, String nutrcont3,
				String nutrcont4, String nutrcont5, String nutrcont6, String nutrcont7, String nutrcont8,
				String nutrcont9, String bgnyear, String animalplant) {
			super();
			this.desckor = desckor;
			this.servingwt = servingwt;
			this.nutrcont1 = nutrcont1;
			this.nutrcont2 = nutrcont2;
			this.nutrcont3 = nutrcont3;
			this.nutrcont4 = nutrcont4;
			this.nutrcont5 = nutrcont5;
			this.nutrcont6 = nutrcont6;
			this.nutrcont7 = nutrcont7;
			this.nutrcont8 = nutrcont8;
			this.nutrcont9 = nutrcont9;
			this.bgnyear = bgnyear;
			this.animalplant = animalplant;
		}

		
		public int getWhenmeal() {
			return whenmeal;
		}

		public void setWhenmeal(int whenmeal) {
			this.whenmeal = whenmeal;
		}

		public int getBbsseq() {
			return bbsseq;
		}

		public void setBbsseq(int bbsseq) {
			this.bbsseq = bbsseq;
		}

		public String getDesckor() {
			return desckor;
		}

		public void setDesckor(String desckor) {
			this.desckor = desckor;
		}

		public String getServingwt() {
			return servingwt;
		}

		public void setServingwt(String servingwt) {
			this.servingwt = servingwt;
		}

		public String getNutrcont1() {
			return nutrcont1;
		}

		public void setNutrcont1(String nutrcont1) {
			this.nutrcont1 = nutrcont1;
		}

		public String getNutrcont2() {
			return nutrcont2;
		}

		public void setNutrcont2(String nutrcont2) {
			this.nutrcont2 = nutrcont2;
		}

		public String getNutrcont3() {
			return nutrcont3;
		}

		public void setNutrcont3(String nutrcont3) {
			this.nutrcont3 = nutrcont3;
		}

		public String getNutrcont4() {
			return nutrcont4;
		}

		public void setNutrcont4(String nutrcont4) {
			this.nutrcont4 = nutrcont4;
		}

		public String getNutrcont5() {
			return nutrcont5;
		}

		public void setNutrcont5(String nutrcont5) {
			this.nutrcont5 = nutrcont5;
		}

		public String getNutrcont6() {
			return nutrcont6;
		}

		public void setNutrcont6(String nutrcont6) {
			this.nutrcont6 = nutrcont6;
		}

		public String getNutrcont7() {
			return nutrcont7;
		}

		public void setNutrcont7(String nutrcont7) {
			this.nutrcont7 = nutrcont7;
		}

		public String getNutrcont8() {
			return nutrcont8;
		}

		public void setNutrcont8(String nutrcont8) {
			this.nutrcont8 = nutrcont8;
		}

		public String getNutrcont9() {
			return nutrcont9;
		}

		public void setNutrcont9(String nutrcont9) {
			this.nutrcont9 = nutrcont9;
		}

		public String getBgnyear() {
			return bgnyear;
		}

		public void setBgnyear(String bgnyear) {
			this.bgnyear = bgnyear;
		}

		public String getAnimalplant() {
			return animalplant;
		}

		public void setAnimalplant(String animalplant) {
			this.animalplant = animalplant;
		}
		
	 	public int getMsgseq() {
			return msgseq;
		}

		public void setMsgseq(int msgseq2) {
			this.msgseq = msgseq2;
		}

		@Override
		public String toString() {
			return "FoodDto [bbsseq=" + bbsseq + ", msgseq=" + msgseq + ", whenmeal=" + whenmeal + ", desckor="
					+ desckor + ", servingwt=" + servingwt + ", nutrcont1=" + nutrcont1 + ", nutrcont2=" + nutrcont2
					+ ", nutrcont3=" + nutrcont3 + ", nutrcont4=" + nutrcont4 + ", nutrcont5=" + nutrcont5
					+ ", nutrcont6=" + nutrcont6 + ", nutrcont7=" + nutrcont7 + ", nutrcont8=" + nutrcont8
					+ ", nutrcont9=" + nutrcont9 + ", bgnyear=" + bgnyear + ", animalplant=" + animalplant + "]";
		}

		

		



		
		
		
	    
		
	    
	    
}
