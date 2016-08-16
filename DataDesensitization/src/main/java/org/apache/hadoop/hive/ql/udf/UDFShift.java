package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * UDFShift.
 */
@Description(name = "shift",
        value = "_FUNC_(data,direction,digit,mode) - shift 'data' \n"
                + "direction - 0 for right, 1 for left\n"
                + "digit - number of digits for shifting"
                + "mode - 0: fill 0 for left shift and unsigned shift for right shift;"
                + "1: fill 1 for left shift and signed shift for right shift",
        extended = "Example:\n")
public class UDFShift extends UDF {
  private int evaluate(int data, int direction, int digit, int mode) {
    int result = data;
    // right shift
    if(direction == 0) {
      switch (mode) {
        // unsigned right shift
        case 0: result = data >>> digit; break;
        // signed right shift
        case 1: result = data >> digit; break;
        default:
      }
    }
    // left shift
    else {
      switch (mode) {
        // fill 0
        case 0: result = data << digit; break;
        // fill 1
        case 1: result = (data << digit) + ((1 << digit) - 1); break;
        default:
      }
    }
    return result;
  }

  private long evaluate(long data, int direction, int digit, int mode) {
    long result = data;
    // right shift
    if(direction == 0) {
      switch (mode) {
        // unsigned right shift
        case 0: result = data >>> digit; break;
        // signed right shift
        case 1: result = data >> digit; break;
        default:
      }
    }
    // left shift
    else {
      switch (mode) {
        // fill 0
        case 0: result = data << digit; break;
        // fill 1
        case 1: result = data << digit + 1 << digit - 1; break;
        default:
      }
    }
    return result;
  }

  /**
   * Integer version
   */
  public IntWritable evaluate(IntWritable data, IntWritable direction, IntWritable digit, IntWritable mode) {
    if(data == null || direction == null || digit == null || mode == null) {
      return null;
    }
    int dataVal = data.get();
    int directionVal = direction.get();
    int digitVal = digit.get();
    int modeVal = mode.get();
    if(directionVal != 0 && directionVal != 1 || modeVal != 0 && modeVal != 1) {
      return null;
    }
    IntWritable result = new IntWritable();
    result.set(evaluate(dataVal,directionVal,digitVal,modeVal));
    return result;
  }

  /**
   * Long version
   */
  public LongWritable evaluate(LongWritable data, IntWritable direction, IntWritable digit, IntWritable mode) {
    if(data == null || direction == null || digit == null || mode == null) {
      return null;
    }
    long dataVal = data.get();
    int directionVal = direction.get();
    int digitVal = digit.get();
    int modeVal = mode.get();
    if(directionVal != 0 && directionVal != 1 || modeVal != 0 && modeVal != 1) {
      return null;
    }
    LongWritable result = new LongWritable();
    result.set(evaluate(dataVal,directionVal,digitVal,modeVal));
    return result;
  }

  /**
   * Short version
   */
  public ShortWritable evaluate(ShortWritable data, IntWritable direction, IntWritable digit, IntWritable mode) {
    if(data == null || direction == null || digit == null || mode == null) {
      return null;
    }
    int dataVal = data.get();
    int directionVal = direction.get();
    int digitVal = digit.get();
    int modeVal = mode.get();
    if(directionVal != 0 && directionVal != 1 || modeVal != 0 && modeVal != 1) {
      return null;
    }
    ShortWritable result = new ShortWritable();
    // get 4 digits from LSB (for short type has 2^4 digits)
    digitVal = digitVal & (1 << 4 - 1);
    result.set((short)evaluate(dataVal,directionVal,digitVal,modeVal));
    return result;
  }

  /**
   * Byte version
   */
  public ByteWritable evaluate(ByteWritable data, IntWritable direction, IntWritable digit, IntWritable mode) {
    if(data == null || direction == null || digit == null || mode == null) {
      return null;
    }
    int dataVal = data.get();
    int directionVal = direction.get();
    int digitVal = digit.get();
    int modeVal = mode.get();
    if(directionVal != 0 && directionVal != 1 || modeVal != 0 && modeVal != 1) {
      return null;
    }
    ByteWritable result = new ByteWritable();
    // get 3 digits from LSB (for byte type has 2^3 digits)
    digitVal = digitVal & (1 << 3 - 1);
    result.set((byte)evaluate(dataVal,directionVal,digitVal,modeVal));
    return result;
  }
}
