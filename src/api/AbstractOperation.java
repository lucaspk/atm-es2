package api;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOperation {
	private double savings = 100;
	private double checking = 100;
	private double moneyMarket = 100;
	
	private static Map<String, Double> accounts = new HashMap<String, Double>();
	
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
	
	public class Withdrawal extends AbstractOperation{

		private String auxOperate(double value, String accountType){
			String answer = "Insufficient cash avaliable";
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
		
		
		String operateInSavings(double value) {
			return auxOperate(value, "savings");
		}
		
		String operateInCheckings(double value) {
			return auxOperate(value, "checking");
		}

		String operateInMoneyMarket(double value) {
			return auxOperate(value, "moneyMarket");
		}
		
	}
	
	public class Deposit extends AbstractOperation{

		private String auxOperate(double value, String accountType){
			String answer = "Exception in thread Thread-2 for " + String.valueOf(value);
			double currentSavings = AbstractOperation.getAmountOfCashFromAccount(accountType);
			if (value >= 0.0 && value <= 9999999.99) {
				answer = "Would you like to do another transaction?";
				accounts.put(accountType, currentSavings + value);
			}
			return answer;
		}
		
		String operateInSavings(double value) {
			return auxOperate(value, "savings");
		}

		String operateInCheckings(double value) {
			return auxOperate(value, "checking");
		}

		String operateInMoneyMarket(double value) {
			return "Invalid account type";
		}
		
	}
	
	public class Transfer extends AbstractOperation{
		
		String transferMoney(String sourceAccount, String destinyAccount, double amount){
			String msg = "Insufficient avaliable balance";
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
		
	}
	
	public class BalanceInquiry extends AbstractOperation{
		
		
		String operateInSavings() {
			return "TOTAL BAL[savings]: $" + savings;
		}

		String operateInCheckings() {
			return "TOTAL BAL[checking]: $" + checking;
		}

		String operateInMoneyMarket() {
			return "Invalid account type";
		}
		
	}
}

