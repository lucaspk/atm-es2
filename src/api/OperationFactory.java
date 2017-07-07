package api;

public class OperationFactory {

	public static AbstractOperation defineOperation(String operationName){
		AbstractOperation op = new AbstractOperation() {
		};
		if (operationName == "deposit"){
			return op.new Deposit();
		}
		else if (operationName == "withdrawal"){
			return op.new Withdrawal();
		}
		else if (operationName == "balance"){
			return op.new BalanceInquiry();
		}
		else{
			return op.new Transfer();
		}
		
	}
}
