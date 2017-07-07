package testes;

import org.junit.*;

import api.ATMExec;
import api.AbstractOperation;
import api.AbstractOperation.Deposit;

public class TestesPEFracaRobusta {
	
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
		Assert.assertEquals("PIN correct. Please choose transiction type", atm.insertPIN(1));
	}
	
	@Test
	public void testCaso02(){
		Assert.assertEquals("PIN correct. Please choose transiction type", atm.insertPIN(0000000000000000000000));
	}
		
}
