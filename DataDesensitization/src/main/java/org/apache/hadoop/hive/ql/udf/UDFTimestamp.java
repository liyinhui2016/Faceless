package org.apache.hadoop.hive.ql.udf;

import java.sql.Timestamp;
import java.lang.StringBuilder;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.io.IntWritable;

/**
 * UDFTimestamp.
 *
 */
@Description(name = "timestamp",
             value = "_FUNC_(timestamp, mask) - returns the masked value of timestamp\n"
                + "timestamp - original timestamp type with date string and time string\n"
                + "mask - hide the date components or the time components, 1 masked, 0 unmasked",
             extended = "Example:\n")
public class UDFTimestamp extends UDF {

  // Timestamp type limits month in 00~11
  public static final String[] DATE = {"1960","01","01"};

  private String evaluate(String timestamp, int mask) {
    String sMask = Integer.toBinaryString(mask);
    char [] cMask = sMask.toCharArray();
    String [] date_time = timestamp.split("\\x20|-|:");

    if (date_time.length < cMask.length) return timestamp;
    int distance = date_time.length - cMask.length;

    String[] date_subs = new String[3];
    String[] time_subs = new String[3];
    for (int i = 0; i < cMask.length; i++) {
      if (cMask[i] == '1') {
        if (distance + i < 3) {
          date_time[distance + i] = DATE[distance + i];
        } else if (cMask[i] == '1') {
          date_time[distance + i] = date_time[distance + i].replaceAll("\\d", "0");
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < date_time.length; i++) {
      if (i < 2) sb.append(date_time[i] + "-");
      if (i == 2) sb.append(date_time[i] + " ");
      if (i > 2 && i < date_time.length - 1) sb.append(date_time[i] + ":");
      if (i == date_time.length - 1) sb.append(date_time[i]);
    }

    return sb.toString();
  }

  public TimestampWritable evaluate(TimestampWritable timestamp, IntWritable mask) {
    if (timestamp == null || mask == null) return null;
    int mode = mask.get();
    Timestamp ts = timestamp.getTimestamp();
    TimestampWritable result = new TimestampWritable();

    result.set(Timestamp.valueOf(evaluate(ts.toString(), mode)));
    return result;
  }
}
