package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * UDFAge.
 */
@Description(name = "age",
        value = "_FUNC_(age,mode,unit) - returns the generalization value for age\n"
                + "mode - generalization method: 0 for floor, 1 for ceil\n"
                + "unit - generalization unit: must be a positive integer",
        extended = "Example:\n")
public class UDFAge extends UDF{
  private double evaluate(double age, int mode, int unit) {
    double result = 0;
    if (mode == 0) {
      result = Math.floor(age/unit)*unit;
    }
    else {
      result = Math.ceil(age/unit)*unit;
    }
    return result;
  }

  /**
   * Integer version
   */
  public IntWritable evaluate(IntWritable age, IntWritable mode, IntWritable unit) {
    if (age == null || mode == null || unit == null) {
      return null;
    }
    double ageVal = age.get();
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
    double resultDouble = evaluate(ageVal,modeVal,unitVal);
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
   * Byte version
   */
  public ByteWritable evaluate(ByteWritable age, IntWritable mode, IntWritable unit) {
    if (age == null || mode == null || unit == null) {
      return null;
    }
    double ageVal = age.get();
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
    double resultDouble = evaluate(ageVal,modeVal,unitVal);
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
  public LongWritable evaluate(LongWritable age, IntWritable mode, IntWritable unit) {
    if (age == null || mode == null || unit == null) {
      return null;
    }
    double ageVal = age.get();
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
    double resultDouble = evaluate(ageVal,modeVal,unitVal);
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
  public ShortWritable evaluate(ShortWritable age, IntWritable mode, IntWritable unit) {
    if (age == null || mode == null || unit == null) {
      return null;
    }
    double ageVal = age.get();
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
    double resultDouble = evaluate(ageVal,modeVal,unitVal);
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
