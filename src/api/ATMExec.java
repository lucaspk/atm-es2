package api;

import simulation.*;

public final class ATMExec {
	private AbstractOperation operation;
	private boolean isOn;
	private static long amountOfCashInDispenser;
	private static long cardNumber;
	private static int[] PIN = {0, 42, 1234, 66, 2145};
	private long userPIN;
	
	protected static boolean isValidCard(int cardNumber) {
		return cardNumber > 0 && cardNumber < PIN.length;
	}
	
	protected static boolean isValidPIN(long PIN_number) {
		return  PIN_number == PIN[(int) cardNumber];
	}
	
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
	
	public String insertBills(String billsNumber){
		String answer = "Must be a valid integer >= 0";
		
		if (!billsNumber.contains(".") && billsNumber.length() < 10) {
			Long billsNum = Long.parseLong(billsNumber);
			amountOfCashInDispenser = 20*billsNum;
			answer = "The avaliable dollar value in the cash dispenser is $" + amountOfCashInDispenser; 
		}
		return answer;
	}
	
	public String insertCard(String cardNumber){
		String answer = "Unable to read card";
		if (cardNumber.matches("[0-9]") && cardNumber.length() < 10){
			this.cardNumber = Integer.parseInt(cardNumber);
			answer = "Card accepted";
		}
		return answer;
	}
	
	public String insertCard(long cardNumber){
		String answer = "Unable to read card";
		if (cardNumber > 0 && String.valueOf(cardNumber).length() < 10){
			this.cardNumber = cardNumber;
			answer = "Card accepted";
		}
		return answer;
	}
		
	public String insertPIN(long PIN_number){
		String answer = "PIN incorret";
		if (PIN_number == 0) {
			answer = "Waiting for PIN";
		}
		else if (isValidPIN(PIN_number)){
			this.userPIN = PIN_number;
			answer = "PIN correct. Please choose transiction type";
		}
		return answer;
	}
		
	public static long getAmountOfCashInDispenser() {
		return amountOfCashInDispenser;
	}
	
	public void defineOperation(String operationName){
		operation = OperationFactory.defineOperation(operationName);
	}
	
	public AbstractOperation getOperation() {
		return operation;
	}
}
