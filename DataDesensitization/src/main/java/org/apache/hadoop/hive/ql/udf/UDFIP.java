package org.apache.hadoop.hive.ql.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFIP.
 *
 */
@Description(name = "ip",
             value = "_FUNC_(ip, mask) - returns the masked value of ip\n"
                + "ip - original ip like x.x.x.x\n"
                + "mask - flags to imply hiding or displaying: 1 masked, 0 unmasked",
             extended = "Example:\n")
public class UDFIP extends UDF {
  private String evaluate(String ip, int mask) {
    String sMask = Integer.toBinaryString(mask);
    char [] cMask = sMask.toCharArray();
    String [] subs = ip.split("\\.");

    if (subs.length < cMask.length) return ip;
    int distance = subs.length - cMask.length;

    for (int i = 0; i < cMask.length; i++) {
      if (cMask[i] == '1') {
        subs[distance + i] = "0";
      }
    }

    return StringUtils.join(subs, ".");
  }

  public Text evaluate(Text ip, IntWritable mask) {
    if (ip == null || mask == null) return null;
    int mode = mask.get();
    String str_ip = ip.toString();
    Text result = new Text();
    result.set(evaluate(str_ip, mode));
    return result;
  }
}
