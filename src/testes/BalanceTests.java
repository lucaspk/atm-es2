package testes;

import org.junit.*;

import api.ATMExec;
import api.AbstractOperation;
import api.AbstractOperation.BalanceInquiry;

public class BalanceTests {

	private ATMExec atm;
	private BalanceInquiry operation;
	
	@Before
	public void setUp() {
		atm = new ATMExec();
		operation = balancePattern();
	}
	
	private BalanceInquiry balancePattern() {
		atm.defineOperation("balance");
		BalanceInquiry operation = (BalanceInquiry)atm.getOperation();
		return operation;
	}
	
	@Test
	public void invalidCardTest() {
		Assert.assertEquals("Invalid card", operation.operateInCheckings(123, 123)); 
	}
	
	@Test
	public void validCardTest() {
		double cash = AbstractOperation.getAmountOfCashFromAccount("checking");
		Assert.assertEquals("TOTAL BAL[checking]: $" + cash, operation.operateInCheckings(1, 42));
	}

}