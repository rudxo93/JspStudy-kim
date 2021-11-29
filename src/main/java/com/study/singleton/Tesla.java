package com.study.singleton;

public class Tesla {

	private int serialNum = 20210001;
	private static Tesla instance = new Tesla();
	
	private Tesla() {  //생성이 외부에서 되지 않게끔 public 가 아니라 private
		
	}
	
	public static Tesla getInstance() {
		if(instance == null) {
			instance = new Tesla();
		}
		return instance;
	}
	
	public Car createCar(String model) {
		return new Car(serialNum++, model, getClass().getSimpleName());
	}
	
	
	
}
