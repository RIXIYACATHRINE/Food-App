package com.foodApp.model;

public class Restaurant {
	private int restaurantId;
	private String name;
	private String address;
	private String phoneNumber;
	private String cusineType;
	private String deliveryTime;
	private int admineUserId;
	private String rating;
	private String isActive;
	private String imagePath;
	
	public Restaurant() {
		
	}
	
	public Restaurant(String name, String address, String phoneNumber, String cusineType, String deliveryTime,
			int admineUserId, String rating, String isActive, String imagePath) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.cusineType = cusineType;
		this.deliveryTime = deliveryTime;
		this.admineUserId = admineUserId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
	}

	public Restaurant(int restaurantId, String name, String address, String phoneNumber, String cusineType,
			String deliveryTime, int admineUserId, String rating, String isActive, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.cusineType = cusineType;
		this.deliveryTime = deliveryTime;
		this.admineUserId = admineUserId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCusineType() {
		return cusineType;
	}

	public void setCusineType(String cusineType) {
		this.cusineType = cusineType;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getAdmineUserId() {
		return admineUserId;
	}

	public void setAdmineUserId(int admineUserId) {
		this.admineUserId = admineUserId;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", address=" + address + ", cusineType="
				+ cusineType + ", rating=" + rating + "]";
	}
}