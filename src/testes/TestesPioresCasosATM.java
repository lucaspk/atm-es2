package testes;

import org.junit.*;

import api.ATMExec;
import api.AbstractOperation;
import api.AbstractOperation.Deposit;

public class TestesPioresCasosATM {
	
	private ATMExec atm, atm2;
	private Deposit operation;
	
	@Before
	public void setUp(){
		atm = new ATMExec();
		atm2 = new ATMExec();
		atm.turnOn();
		atm.insertBills("2");
		
	}
	
	@Test
	public void testCaso01(){
		Assert.assertEquals("Unable to read card", atm.insertCard(0));
	}
	
	@Test
	public void testCaso02(){
		Assert.assertEquals("Card accepted", atm.insertCard(1));
	}
	
	@Test
	public void testCaso03(){
		Assert.assertEquals("Card accepted", atm.insertCard(2));
	}
	
	@Test
	public void testCaso04(){
		Assert.assertEquals("Card accepted", atm.insertCard(4));
	}
	
	@Test
	public void testCaso05(){
		Assert.assertEquals("Card accepted", atm.insertCard(5));
	}
	
	@Test
	public void testCaso06(){
		Assert.assertEquals("Unable to read card", atm.insertCard(6));
	}	
}
