package beans;

public class Account {

	private String iban;
	private long saldo;
	private String cliente;


	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public long getSaldo() {
		return saldo;
	}
	public void setSaldo(long saldo) {
		this.saldo = saldo;
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


}