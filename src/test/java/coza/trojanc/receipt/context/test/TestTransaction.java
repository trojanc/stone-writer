package coza.trojanc.receipt.context.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Charl Thiem
 */
public class TestTransaction {

	public static final String VALUE_TRADERNAME = "Joe's Fisheries";
	public static final double VALUE_TRANSACTION_TOTAL = 200.95;
	public static final int VALUE_NUM_ITEMS = 21;
	public static final String VALUE_NUM_ITEMS_STRING = Integer.toString(VALUE_NUM_ITEMS);
	public static final Date VALUE_TRANSACTION_DATE;
	public static final LocalDateTime VALUE_SYSTEM_DATE_TIME;
	static{
		Calendar c = Calendar.getInstance();
		c.set(2016, 02, 15);
		VALUE_TRANSACTION_DATE = c.getTime();

		VALUE_SYSTEM_DATE_TIME = LocalDateTime.of(2016, 02, 15, 0, 0, 0);
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

	public LocalDateTime getSystemDatetime(){
		return VALUE_SYSTEM_DATE_TIME;
	}

	public List<SoldItem> getSoldItems(){
		return new ArrayList<SoldItem>(){{
			add(new SoldItem(SoldItem.SOLD_ITEM1_NAME, SoldItem.SOLD_ITEM1_VALUE));
			add(new SoldItem(SoldItem.SOLD_ITEM2_NAME, SoldItem.SOLD_ITEM2_VALUE));
		}};
	}

}
