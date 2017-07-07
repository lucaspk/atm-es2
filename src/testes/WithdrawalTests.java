package testes;

import org.junit.*;

import api.ATMExec;
import api.AbstractOperation;
import api.AbstractOperation.Withdrawal;

public class WithdrawalTests {

	private ATMExec atm;
	private Withdrawal operation;
	
	private final String SUCCESS = "Would you like to do another transaction?";
	
	@Before
	public void setUp() {
		atm = new ATMExec();
		operation = withdrawalPattern();
	}
	
	private Withdrawal withdrawalPattern() {
		atm.defineOperation("withdrawal");
		Withdrawal operation = (Withdrawal)atm.getOperation();
		return operation;
	}

	@Test
	public void amountGreaterThanAvalilableCash() {
		atm.insertBills("5");
		Assert.assertEquals("Insufficient cash available", operation.operateInCheckings(1, 42, 200));
	}
	
	@Test
	public void invalidCardTest() {
		atm.insertBills("50");
		Assert.assertEquals("Invalid card", operation.operateInCheckings(123, 123, 200));
	}
	
	@Test
	public void amountGreaterThanBalance() {
		atm.insertBills("50");
		Assert.assertEquals("Insufficient available balance", operation.operateInCheckings(1, 42, 200));
	}
	
	@Test
	public void happyPath() {
		atm.insertBills("5");
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInCheckings(1, 42, 20));
		Assert.assertEquals(80, AbstractOperation.getAmountOfCashFromAccount("checking"), 1e-3);
	}
}
