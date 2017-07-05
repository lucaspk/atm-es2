package api;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOperation {
	private double savings = 100;
	private double checking = 100;
	private double moneyMarket = 100;
	
	private Map<String, Double> accounts = new HashMap<String, Double>();
	
	public AbstractOperation(){
		accounts.put("savings", savings);
		accounts.put("checking", checking);
		accounts.put("moneyMarket", moneyMarket);
	}
	
	abstract String operateInSavings(double value);
	abstract String operateInCheckings(double value, String accountType);
	abstract String operateInMoneyMarket(double value, String accountType);
	
	public class Withdrawal extends AbstractOperation{

		@Override
		String operateInSavings(double value) {
			String answer = "Insufficient cash avaliable";
			double currentSavings = accounts.get("savings");
			if (currentSavings > value && 
					currentSavings - value >= 0 && 
					ATMExec.getAmountOfCashInDispenser() % value == 0.0) {
				currentSavings -= value;
				accounts.put("savings", currentSavings);
				answer = "Total avaliable $" + currentSavings;
			}
			
			return answer;
		}

		@Override
		String operateInCheckings(double value, String accountType) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		String operateInMoneyMarket(double value, String accountType) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class Deposit extends AbstractOperation{

		@Override
		String operateInSavings(double value) {
			String answer = "";
			double currentSavings = accounts.get("savings");
			if (value >= 0.0 && value <= 9999999.99) {
				answer = "TOTAL BALANCE $" + (currentSavings + value);
				accounts.put("savings", currentSavings + value);
			}
			return null;
		}

		@Override
		String operateInCheckings(double value, String accountType) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		String operateInMoneyMarket(double value, String accountType) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class Transfer extends AbstractOperation{
		
		@Override
		String operateInSavings(double value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		String operateInCheckings(double value, String accountType) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		String operateInMoneyMarket(double value, String accountType) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class BalanceInquiry extends AbstractOperation{

		@Override
		String operateInSavings(double value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		String operateInCheckings(double value, String accountType) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		String operateInMoneyMarket(double value, String accountType) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}

