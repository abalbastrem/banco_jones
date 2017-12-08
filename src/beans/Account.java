package beans;

public class Account {

	private String iban;
	private double saldo;
	private String cliente;


	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double d) {
		this.saldo = d;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Account [iban=" + iban + ", saldo=" + saldo + ", cliente=" + cliente + "]";
	}
	
	// Check account has enough moneys
	public boolean isThereEnoughMoney(double d) throws Exception {
		if ( this.saldo < d ) {
			throw new Exception("ERROR: La cuenta de origen no tiene suficientes fondos");
		} else {
			return true;
		}
		
	}


}