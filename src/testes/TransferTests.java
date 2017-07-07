package testes;

import org.junit.*;

import api.ATMExec;
import api.AbstractOperation;
import api.AbstractOperation.Transfer;

public class TransferTests {

	private ATMExec atm;
	private Transfer operation;
	
	private final String SUCCESS = "Would you like to do another transaction?";
	
	@Before
	public void setUp() {
		atm = new ATMExec();
		operation = transferPattern();
	}
	
	private Transfer transferPattern() {
		atm.defineOperation("transfer");
		Transfer operation = (Transfer)atm.getOperation();
		return operation;
	}

	@Test
	public void avlTest1() {
		Assert.assertNull(operation.transferMoney("checking", "savings", 0.00));
	}
	
	@Test
	public void avlTest2() {
		Assert.assertEquals(SUCCESS, operation.transferMoney("checking", "savings", 0.01));
	}
	
	@Test
	public void avlTest3() {
		Assert.assertEquals(SUCCESS, operation.transferMoney("checking", "savings", 0.02));
	}
	
	@Test
	public void avlTest4() {
		Assert.assertEquals(SUCCESS, operation.transferMoney("checking", "savings", 100.00));
	}
	
	@Test
	public void avlTest5() {
		Assert.assertEquals(SUCCESS, operation.transferMoney("checking", "savings", (1e19) - 0.02));
	}
	
	@Test
	public void avlTest6() {
		Assert.assertEquals(SUCCESS, operation.transferMoney("checking", "savings", (1e19) - 0.01));
	}
	
	@Test
	public void avlTest7() {
		Assert.assertEquals("Invalid amount", operation.transferMoney("checking", "savings", 1e19));
	}
	
	@Test
	public void invalidCardTest() {
		Assert.assertEquals("Invalid card", operation.transferMoney(123, 123, "checking", "savings", 12.34));
	}
	
	@Test
	public void equalAccountsTest() {
		Assert.assertEquals("Can't transfer money from one account to itself", operation.transferMoney(1, 42, "checking", "checking", 12.34));
	}
	
	@Test
	public void amountGreaterThanBalanceTest() {
		Assert.assertEquals("Insufficient avaliable balance", operation.transferMoney(1, 42, "checking", "savings", 123.45));
	}
	
	@Test
	public void happyPathTest() {
		Assert.assertEquals(SUCCESS, operation.transferMoney(1, 42, "checking", "savings", 12.34));
		Assert.assertEquals(100 - 12.34, AbstractOperation.getAmountOfCashFromAccount("checking"), 1e-3);
		Assert.assertEquals(100 + 12.34, AbstractOperation.getAmountOfCashFromAccount("savings"), 1e-3);
	}

}
