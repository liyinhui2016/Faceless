package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFTruncation
 */
@Description(name = "truncation",
        value = "_FUNC_(data,length,mode) - returns a sub-Text with 'length' digits\n"
                + "mode - truncation method (0 for reserving the first half, 1 for reserving the second half)\n",
        extended = "Example:\n")
public class UDFTruncation extends UDF{
  private final Text result = new Text();

  public Text evaluate(Text data, IntWritable length, IntWritable mode) {
    // returns null when input is null
    if(data == null || length == null || mode == null) {
      return null;
    }
    String dataStr = data.toString();
    int lengthVal = length.get();
    int modeVal = mode.get();
    // returns null when the value of 'mode' or 'length' is invalid
    if(modeVal != 0 && modeVal != 1 || lengthVal < 0) {
      return null;
    }
    // returns entire data when 'length' exceeds the length of data
    if(lengthVal >= dataStr.length()) {
      result.set(data);
      return result;
    }
    // returns the expected result
    if(modeVal == 0) {
      result.set(dataStr.substring(0,lengthVal));
    }
    else {
      result.set(dataStr.substring(dataStr.length()-lengthVal,dataStr.length()));
    }
    return result;
  }
}
