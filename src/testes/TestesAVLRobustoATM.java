package testes;

import org.junit.*;

import api.ATMExec;
import api.AbstractOperation;
import api.AbstractOperation.Deposit;

public class TestesAVLRobustoATM {
	
	private ATMExec atm, atm2;
	private Deposit operation;
	
	@Before
	public void setUp(){
		atm = new ATMExec();
		atm2 = new ATMExec();
		atm.turnOn();
		atm.insertCard(1);
		atm.insertPIN(42);
		operation = depositPattern();
	}
	
	@Test
	public void testaLigarATM(){
		Assert.assertEquals(true, atm.isOn());
	}
	
	@Test
	public void testaInsertBillsQtdDecimalCaso1(){
		Assert.assertEquals("Must be a valid integer >= 0", atm2.insertBills("0.0"));
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(0));
	}	
	
	@Test
	public void testaInsertBillsQtdDecimalCaso2(){
		Assert.assertEquals("Must be a valid integer >= 0", atm2.insertBills("0.0"));
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(1));
	}	
	
	@Test
	public void testaInsertBillsQtdDecimalCaso3(){
		Assert.assertEquals("Must be a valid integer >= 0", atm2.insertBills("0.0"));
		Assert.assertEquals("Exception in thread Thread-2 for 1.0E19", operation.operateInSavings(9999999999999999998.99));
	}
	
	@Test
	public void testaInsertBillsQtdDecimalCaso4(){
		Assert.assertEquals("Must be a valid integer >= 0", atm2.insertBills("0.0"));
		Assert.assertEquals("Exception in thread Thread-2 for 1.0E19", operation.operateInSavings(9999999999999999999.99));
	}
	
	@Test
	public void testaDepositoPioresCasos1(){
		Assert.assertEquals("The avaliable dollar value in the cash dispenser is $20", atm.insertBills("1"));
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(0));
	}
	
	@Test
	public void testaDepositoPioresCasos2(){
		Assert.assertEquals("The avaliable dollar value in the cash dispenser is $20", atm.insertBills("1"));
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(1));
	}
	@Test
	public void testaDepositoPioresCasos3(){
		Assert.assertEquals("The avaliable dollar value in the cash dispenser is $20", atm.insertBills("1"));
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(9999999999999999998.99));
	}
	@Test
	public void testaDepositoPioresCasos4(){
		Assert.assertEquals("The avaliable dollar value in the cash dispenser is $20", atm.insertBills("1"));
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(9999999999999999999.99));
	}
	
	private void piorCasoBills() {
		Assert.assertEquals("The avaliable dollar value in the cash dispenser is $19999999980", atm.insertBills("999999999"));
	}
	
	private Deposit depositPattern() {
		atm.defineOperation("deposit");
		Deposit operation = (Deposit) atm.getOperation();
		return operation;
	}
	
	@Test
	public void testaBillsNumberPioresCasos1(){
		piorCasoBills();
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(0));
	}
	
	@Test
	public void testaBillsNumberPioresCasos2(){
		piorCasoBills();
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(1));
	}
	
	@Test
	public void testaBillsNumberPioresCasos3(){
		piorCasoBills();
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(9999999999999999998.99));
	}
	
	@Test
	public void testaBillsNumberPioresCasos4(){
		piorCasoBills();
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(9999999999999999999.99));
	}
	
	@Test
	public void testaBillsNumberPioresCasosMenosUmCaso4(){
		Assert.assertEquals("The avaliable dollar value in the cash dispenser is $19999999960", atm.insertBills("999999998"));
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(0));
	}
	@Test
	public void testaBillsNumberPioresCasosMenosUmCaso1(){
		Assert.assertEquals("The avaliable dollar value in the cash dispenser is $19999999960", atm.insertBills("999999998"));
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(1));
	}
	@Test
	public void testaBillsNumberPioresCasosMenosUmCaso2(){
		Assert.assertEquals("The avaliable dollar value in the cash dispenser is $19999999960", atm.insertBills("999999998"));
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(9999999999999999998.99));
	}
	@Test
	public void testaBillsNumberPioresCasosMenosUmCaso3(){
		Assert.assertEquals("The avaliable dollar value in the cash dispenser is $19999999960", atm.insertBills("999999998"));
		Assert.assertEquals("Would you like to do another transaction?", operation.operateInSavings(9999999999999999999.99));
	}
	
	
}
