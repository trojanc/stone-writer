package coza.trojanc.stonewriter.context.test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class TestTransaction {

	public static final String VALUE_TRADERNAME = "Joe's Fisheries";
	public static final double VALUE_TRANSACTION_TOTAL = 200.95;
	public static final int VALUE_NUM_ITEMS = 1;
	public static final String VALUE_NUM_ITEMS_STRING = Integer.toString(VALUE_NUM_ITEMS);
	public static final Date VALUE_TRANSACTION_DATE;
	static{
		Calendar c = Calendar.getInstance();
		c.set(2016, 02, 15);
		VALUE_TRANSACTION_DATE = c.getTime();
	}


	public String getTraderName(){
		return VALUE_TRADERNAME;
	}

	public double getTransactionTotal(){
		return VALUE_TRANSACTION_TOTAL;
	}

	public Date getTransactionDate(){
		return VALUE_TRANSACTION_DATE;
	}

	public int getNumItems(){
		return VALUE_NUM_ITEMS;
	}

	public SoldItem getSoldItem(){
		return new SoldItem();
	}

}
