package testes;

import org.junit.*;

import api.ATMExec;
import api.AbstractOperation;
import api.AbstractOperation.Deposit;

public class TestesPEForteRobusta {
	
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
		Assert.assertEquals("Unable to read card", atm.insertCard(000000000000));
	}
	
	@Test
	public void testCaso02(){
		Assert.assertEquals("Unable to read card", atm.insertCard(00000000000));
	}
	
	@Test
	public void testCaso03(){
		Assert.assertEquals("Unable to read card", atm.insertCard(100000000000L));
	}
		
}
