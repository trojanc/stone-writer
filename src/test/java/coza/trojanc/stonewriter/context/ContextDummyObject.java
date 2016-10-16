package coza.trojanc.stonewriter.context;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class ContextDummyObject {

	public static final String NAME = "Tautua";
	public static final int AGE = 21;
	public static final String AGE_STRING = AGE + "";

	public String getName(){
		return NAME;
	}

	public int getAge(){
		return AGE;
	}

	public Date getBirth(){
		// 2016-10-01
		Calendar c = Calendar.getInstance();
		c.set(2016,9,01);
		return c.getTime();
	}
}
