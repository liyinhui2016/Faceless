package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFHiding.
 */
@Description(name = "hiding",
        value = "_FUNC_(data,value) - replace data with value\n",
        extended = "Example:\n")
public class UDFHiding extends UDF{
  /**
   * Byte version
   */
  public ByteWritable evaluate(ByteWritable data, ByteWritable value) {
    if(value == null) {
      return null;
    }
    ByteWritable result = new ByteWritable(value.get());
    return result;
  }

  /**
   * Double version
   */
  public DoubleWritable evaluate(DoubleWritable data, DoubleWritable value) {
    if(value == null) {
      return null;
    }
    DoubleWritable result = new DoubleWritable(value.get());
    return result;
  }

  /**
   * Float version
   */
  public FloatWritable evaluate(FloatWritable data, FloatWritable value) {
    if(value == null) {
      return null;
    }
    FloatWritable result = new FloatWritable(value.get());
    return result;
  }

  /**
   * Int version
   */
  public IntWritable evaluate(IntWritable data, IntWritable value) {
    if(value == null) {
      return null;
    }
    IntWritable result = new IntWritable(value.get());
    return result;
  }

  /**
   * Long version
   */
  public LongWritable evaluate(LongWritable data, LongWritable value) {
    if(value == null) {
      return null;
    }
    LongWritable result = new LongWritable(value.get());
    return result;
  }

  /**
   * Short version
   */
  public ShortWritable evaluate(ShortWritable data, ShortWritable value) {
    if(value == null) {
      return null;
    }
    ShortWritable result = new ShortWritable(value.get());
    return result;
  }

  /**
   * Text version
   */
  public Text evaluate(Text data, Text value) {
    if(value == null) {
      return null;
    }
    Text result = new Text();
    return result;
  }
}
