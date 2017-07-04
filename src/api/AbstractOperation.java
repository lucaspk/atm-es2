package api;

public abstract class AbstractOperation {
	public double savings;
	public double checking;
	public double moneyMarket;
	
	abstract String depositInSavings(double value);
	abstract String depositInCheckings(double value);
	abstract String depositInMoneyMarket(double value);
}
