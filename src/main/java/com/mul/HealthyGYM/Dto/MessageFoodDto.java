package com.mul.HealthyGYM.Dto;

import java.util.List;

public class MessageFoodDto {
	List<MessageDto> message;
	List<FoodDto> fooddto;
	
	public MessageFoodDto() {
		super();
	}

	public MessageFoodDto(List<MessageDto> message, List<FoodDto> fooddto) {
		super();
		this.message = message;
		this.fooddto = fooddto;
	}

	public List<MessageDto> getMessage() {
		return message;
	}

	public void setMessage(List<MessageDto> message) {
		this.message = message;
	}

	public List<FoodDto> getFooddto() {
		return fooddto;
	}

	public void setFooddto(List<FoodDto> fooddto) {
		this.fooddto = fooddto;
	}

	@Override
	public String toString() {
		return "MessageFoodDto [message=" + message + ", fooddto=" + fooddto + "]";
	}
	
	
	
	
	
	
	



	
	
	


	

	

}
