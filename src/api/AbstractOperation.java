package api;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOperation {
	private double savings = 100;
	private double checking = 100;
	private double moneyMarket = 100;
	
	private static Map<String, Double> accounts = new HashMap<String, Double>();
	private static final int VALID_ACCOUNT = 1, VALID_PIN = 42;
	
	public AbstractOperation(){
		accounts.put("savings", savings);
		accounts.put("checking", checking);
		accounts.put("moneyMarket", moneyMarket);
	}
			
	public static Map<String, Double> getAccounts() {
		return accounts;
	}
	
	public void updateAccount(String account, double newValue){
		accounts.put(account, newValue);
	}
	
	public static double getAmountOfCashFromAccount(String account){
		return accounts.get(account);
	}
	
	private String detectInvalid(int cardNumber, int userPIN) {
		if (!ATMExec.isValidCard(cardNumber)) return "Invalid card"; 
		if (!ATMExec.isValidPIN(cardNumber, userPIN)) return "PIN was incorrect";
		return null;
	}
	
public class Withdrawal extends AbstractOperation{
		
		private String auxOperate(int cardNumber, int userPIN, double value, String accountType){
			String answer = detectInvalid(cardNumber, userPIN);
			if (answer != null) return answer;
			
			answer = "Insufficient cash avaliable";
			double currentSavings = accounts.get(accountType);
			if (currentSavings > value && 
					currentSavings - value >= 0 && 
					ATMExec.getAmountOfCashInDispenser() % value == 0.0) {
				currentSavings -= value;
				accounts.put(accountType, currentSavings);
				answer = "Would you like to do another transaction?";
			}
			
			return answer;
		}		
		
		public String operateInSavings(int cardNumber, int userPIN, double value) {
			return auxOperate(cardNumber, userPIN, value, "savings");
		}
		
		public String operateInSavings(double value) {
			return operateInSavings(VALID_ACCOUNT, VALID_PIN, value);
		}
		
		public String operateInCheckings(int cardNumber, int userPIN, double value) {
			return auxOperate(cardNumber, userPIN, value, "checking");
		}
		
		public String operateInCheckings(double value) {
			return operateInCheckings(VALID_ACCOUNT, VALID_PIN, value);
		}

		public String operateInMoneyMarket(int cardNumber, int userPIN, double value) {
			return auxOperate(cardNumber, userPIN, value, "moneyMarket");
		}
		
		public String operateInMoneyMarket(double value) {
			return operateInMoneyMarket(value);
		}
		
	}
	
	public class Deposit extends AbstractOperation{

		private String auxOperate(int cardNumber, int userPIN, double value, String accountType){
			String answer = detectInvalid(cardNumber, userPIN);
			if (answer != null) return answer;
			
			answer = "Exception in thread Thread-2 for " + String.valueOf(value);
			double currentSavings = AbstractOperation.getAmountOfCashFromAccount(accountType);
			double totalSavings = currentSavings + value;
			if (value >= 0.0 && totalSavings <= Long.MAX_VALUE / 1e2) {
				answer = "Would you like to do another transaction?";
				accounts.put(accountType, totalSavings);
			}
			return answer;
		}
		
		public String operateInSavings(int cardNumber, int userPIN, double value) {
			return auxOperate(cardNumber, userPIN, value, "savings");
		}
		
		public String operateInSavings(double value) {
			return operateInSavings(VALID_ACCOUNT, VALID_PIN, value);
		}

		public String operateInCheckings(int cardNumber, int userPIN, double value) {
			return auxOperate(cardNumber, userPIN, value, "checking");
		}
		
		public String operateInCheckings(double value) {
			return operateInCheckings(VALID_ACCOUNT, VALID_PIN, value);
		}

		public String operateInMoneyMarket(int cardNumber, int userPIN, double value) {
			String answer = detectInvalid(cardNumber, userPIN);
			if (answer != null) return answer;
			
			return "Invalid account type";
		}
		
		public String operateInMoneyMarket(double value) {
			return operateInMoneyMarket(VALID_ACCOUNT, VALID_PIN, value);
		}
	}
	
	public class Transfer extends AbstractOperation{
		
		public String transferMoney(int cardNumber, int userPIN, String sourceAccount, String destinyAccount, double amount){
			String msg = detectInvalid(cardNumber, userPIN);
			if (msg != null) return msg;
			
			msg = "Insufficient avaliable balance";
			double currentSourceCash = AbstractOperation.getAmountOfCashFromAccount(sourceAccount);
			double currentDestinyCash = AbstractOperation.getAmountOfCashFromAccount(destinyAccount);
			if (sourceAccount.equals(destinyAccount)){
				msg = "Can't transfer money from one account to itself";
			}
			if (amount < currentSourceCash && currentSourceCash - amount >= 0.0){
				this.updateAccount(sourceAccount, currentSourceCash - amount);
				this.updateAccount(destinyAccount, currentDestinyCash + amount);
				msg = "Would you like to do another transaction?";
			}					
			return msg;
		}
		
		public String transferMoney(String sourceAccount, String destinyAccount, double amount) {
			return transferMoney(VALID_ACCOUNT, VALID_PIN, sourceAccount, destinyAccount, amount);
		}
		
	}
	
	public class BalanceInquiry extends AbstractOperation{
		
		
		public String operateInSavings(int cardNumber, int userPIN) {
			String answer = detectInvalid(cardNumber, userPIN);
			if (answer != null) return answer;
			
			return "TOTAL BAL[savings]: $" + savings;
		}
		
		public String operateSavings() {
			return operateInSavings(VALID_ACCOUNT, VALID_PIN);
		}

		public String operateInCheckings(int cardNumber, int userPIN) {
			String answer = detectInvalid(cardNumber, userPIN);
			if (answer != null) return answer;
			
			return "TOTAL BAL[checking]: $" + checking;
		}
		
		public String operateInCheckings() {
			return operateInCheckings(VALID_ACCOUNT, VALID_PIN);
		}

		public String operateInMoneyMarket(int cardNumber, int userPIN) {
			String answer = detectInvalid(cardNumber, userPIN);
			if (answer != null) return answer;
			
			return "Invalid account type";
		}
		
		public String operateInMoneyMarket() {
			return operateInMoneyMarket(VALID_ACCOUNT, VALID_PIN);
		}
		
	}
}

