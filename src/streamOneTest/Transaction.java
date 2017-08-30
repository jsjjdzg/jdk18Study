package streamOneTest;

public class Transaction {

	private final Trader trader;
	private final int year;
	private final int value;
	private final boolean isOk;

	public Transaction(Trader trader, int year, int value, boolean isOk) {
		super();
		this.trader = trader;
		this.year = year;
		this.value = value;
		this.isOk = isOk;
	}

	public Trader getTrader() {
		return trader;
	}

	public int getYear() {
		return year;
	}

	public int getValue() {
		return value;
	}

	public boolean getIsOk() {
		return isOk;
	}

	@Override
	public String toString() {
		return "Transaction [trader=" + trader + ", year=" + year + ", value=" + value + ", isOk=" + isOk + "]";
	}

}
