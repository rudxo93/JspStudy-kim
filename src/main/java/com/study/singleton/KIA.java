package com.study.singleton;

public class KIA {

	private int serialNum = 20210001;
	private static KIA instance = new KIA();
	
	private KIA() {  //생성이 외부에서 되지 않게끔 public 가 아니라 private
		
	}
	
	public static KIA getInstance() {
		if(instance == null) {
			instance = new KIA();
		}
		return instance;
	}
	
	public Car createCar(String model) {
		return new Car(serialNum++, model, getClass().getSimpleName());
	}
	
	
	
}
