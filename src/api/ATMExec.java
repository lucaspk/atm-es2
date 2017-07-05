package api;

import simulation.*;

public final class ATMExec {
	
	private boolean isOn;
	private static int amountOfCashInDispenser;
	private int cardNumber;
	private int[] PIN = {0, 42, 123, 1234};
	private int userPIN;
	
	public void turnOn(){
		isOn = true;
		System.out.println("The ATM Machine is on");
	}
	
	public void turnOff(){
		isOn = false;
		System.out.println("The ATM Machine is off");
	}
	
	public boolean isOn(){
		return isOn;
	}
	
	public String insertBills(int billsNumber){
		String answer = "Must be a valid integer >= 0";
		if (billsNumber >= 0 && String.valueOf(billsNumber).length() < 10) {
			amountOfCashInDispenser = 20*billsNumber;
			answer = "The avaliable dollar value in the cash dispenser is " + amountOfCashInDispenser; 
		}
		return answer;
	}
	
	public String insertCard(int cardNumber){
		String answer = "Unable to read card";
		if (String.valueOf(cardNumber).length() < 10){
			this.cardNumber = cardNumber;
			answer = "Card accepted";
		}
		return answer;
	}
	
	public String insertPIN(int PIN_number){
		String answer = "PIN incorret";
		if (isValidPIN(PIN_number)){
			this.userPIN = PIN_number;
			answer = "PIN correct";
		}
		return answer;
	}
	
	private boolean isValidPIN(int PIN_number){
		return  PIN_number == PIN[cardNumber];
	}
	
	public static int getAmountOfCashInDispenser() {
		return amountOfCashInDispenser;
	}
	
	
}
