package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFPhone.
 *
 */
@Description(name = "phone",
             value = "_FUNC_(phone, mask) - returns the masked value of phone\n"
                + "phone - original phone number combined with a district number and a phone number like 001-66666666\n"
                + "mask - hide the district number or the phone number: 1 masked, 0 unmasked",
             extended = "Example:\n")
public class UDFPhone extends UDF {
  private String evaluate(String phone, int mask) {
    String result = "";
    switch(mask) {
      case 0:
        result = phone;
        break;
      case 1:
        result = phone.substring(0, phone.indexOf('-'));
        break;
      case 2:
        result = phone.substring(phone.indexOf('-') + 1, phone.length());
        break;
      case 3:
        break;
    }
    return result;
  }

  /* mask XXX-YYY:
   * 0-(00)2  XXX-YYY
   * 1-(01)2  XXX
   * 2-(10)2  YYY
   * 3-(11)2  
   */
  public Text evaluate(Text phone, IntWritable mask) {
    if (phone == null || mask == null) return null;
    int mode = mask.get();
    String str_phone = phone.toString();
    Text result = new Text();
    result.set(evaluate(str_phone, mode));
    return result;
  }
}
