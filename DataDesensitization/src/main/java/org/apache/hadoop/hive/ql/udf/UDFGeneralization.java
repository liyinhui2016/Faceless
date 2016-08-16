package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;


/**
 * UDFGeneralization
 */
@Description(name = "generalization",
        value = "_FUNC_(data,mode,unit) - returns the generalization value for data\n"
                + "mode - generalization method: 0 for floor, 1 for ceil\n"
                + "unit - generalization unit: must be a positive integer",
        extended = "Example:\n")
public class UDFGeneralization extends UDF {
  private double evaluate(double data, int mode, int unit) {
    double result = 0;
    if (mode == 0) {
      result = Math.floor(data/unit)*unit;
    }
    else {
      result = Math.ceil(data/unit)*unit;
    }
    return result;
  }

  /**
   * Integer version
   */
  public IntWritable evaluate(IntWritable data, IntWritable mode, IntWritable unit) {
    if (data == null || mode == null || unit == null) {
      return null;
    }
    double dataVal = data.get();
    int modeVal = mode.get();
    int unitVal = unit.get();
    IntWritable result = new IntWritable();
    if (modeVal != 0 && modeVal != 1) {
      return null;
    }
    if (unitVal <= 0) {
      return null;
    }
	// calculate the result in double type
	double resultDouble = evaluate(dataVal,modeVal,unitVal);
	// type transform with boundary detection
	if(resultDouble > (double)Integer.MAX_VALUE) {
	  result.set(Integer.MAX_VALUE);
	}
	else if(resultDouble < (double)Integer.MIN_VALUE) {
	  result.set(Integer.MIN_VALUE);
	}
	else {
	  result.set((int)resultDouble);
	}
    return result;
  }

  /**
   * Double version
   */
  public DoubleWritable evaluate(DoubleWritable data, IntWritable mode, IntWritable unit) {
    if (data == null || mode == null || unit == null) {
      return null;
    }
    double dataVal = data.get();
    int modeVal = mode.get();
    int unitVal = unit.get();
    DoubleWritable result = new DoubleWritable();
    if (modeVal != 0 && modeVal != 1) {
      return null;
    }
    if (unitVal <= 0) {
      return null;
    }
    result.set(evaluate(dataVal,modeVal,unitVal));
    return result;
  }

  /**
   * Float version
   */
  public FloatWritable evaluate(FloatWritable data, IntWritable mode, IntWritable unit) {
    if (data == null || mode == null || unit == null) {
      return null;
    }
    double dataVal = data.get();
    int modeVal = mode.get();
    int unitVal = unit.get();
    FloatWritable result = new FloatWritable();
    if (modeVal != 0 && modeVal != 1) {
      return null;
    }
    if (unitVal <= 0) {
      return null;
    }
    result.set((float)evaluate(dataVal,modeVal,unitVal));
    return result;
  }

  /**
   * Byte version
   */
  public ByteWritable evaluate(ByteWritable data, IntWritable mode, IntWritable unit) {
    if (data == null || mode == null || unit == null) {
      return null;
    }
    double dataVal = data.get();
    int modeVal = mode.get();
    int unitVal = unit.get();
    ByteWritable result = new ByteWritable();
    if (modeVal != 0 && modeVal != 1) {
      return null;
    }
    if (unitVal <= 0) {
      return null;
    }
    // calculate the result in double type
	double resultDouble = evaluate(dataVal,modeVal,unitVal);
	// type transform with boundary detection
	if(resultDouble > (double)Byte.MAX_VALUE) {
	  result.set(Byte.MAX_VALUE);
	}
	else if(resultDouble < (double)Byte.MIN_VALUE) {
	  result.set(Byte.MIN_VALUE);
	}
	else {
	  result.set((byte)resultDouble);
	}
    return result;
  }

  /**
   * Long version
   */
  public LongWritable evaluate(LongWritable data, IntWritable mode, IntWritable unit) {
    if (data == null || mode == null || unit == null) {
      return null;
    }
    double dataVal = data.get();
    int modeVal = mode.get();
    int unitVal = unit.get();
    LongWritable result = new LongWritable();
    if (modeVal != 0 && modeVal != 1) {
      return null;
    }
    if (unitVal <= 0) {
      return null;
    }
    // calculate the result in double type
	double resultDouble = evaluate(dataVal,modeVal,unitVal);
	// type transform with boundary detection
	if(resultDouble > (double)Long.MAX_VALUE) {
	  result.set(Long.MAX_VALUE);
	}
	else if(resultDouble < (double)Long.MIN_VALUE) {
	  result.set(Long.MIN_VALUE);
	}
	else {
	  result.set((long)resultDouble);
	}
    return result;
  }

  /**
   * Short version
   */
  public ShortWritable evaluate(ShortWritable data, IntWritable mode, IntWritable unit) {
    if (data == null || mode == null || unit == null) {
      return null;
    }
    double dataVal = data.get();
    int modeVal = mode.get();
    int unitVal = unit.get();
    ShortWritable result = new ShortWritable();
    if (modeVal != 0 && modeVal != 1) {
      return null;
    }
    if (unitVal <= 0) {
      return null;
    }
    // calculate the result in double type
	double resultDouble = evaluate(dataVal,modeVal,unitVal);
	// type transform with boundary detection
	if(resultDouble > (double)Short.MAX_VALUE) {
	  result.set(Short.MAX_VALUE);
	}
	else if(resultDouble < (double)Short.MIN_VALUE) {
	  result.set(Short.MIN_VALUE);
	}
	else {
	  result.set((short)resultDouble);
	}
    return result;
  }

}

