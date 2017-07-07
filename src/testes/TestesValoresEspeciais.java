package testes;

import org.junit.*;

import api.ATMExec;
import api.AbstractOperation;
import api.AbstractOperation.Deposit;

public class TestesValoresEspeciais {
	
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
		Assert.assertEquals("Unable to read card", atm.insertCard("AAA"));
	}
	
	@Test
	public void testCaso02(){
		Assert.assertEquals("Unable to read card", atm.insertCard("?%$#"));
	}
	
	@Test
	public void testCaso03(){
		Assert.assertEquals("Unable to read card", atm.insertCard("2.22"));
	}
	
	
}
