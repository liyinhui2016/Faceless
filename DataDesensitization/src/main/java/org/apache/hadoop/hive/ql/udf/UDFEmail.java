package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFEmail.
 *
 */
@Description(name = "email",
             value = "_FUNC_(email, mask) - returns the masked value of email\n"
                + "email - original email string combined with an account name and an email domain like xx@xx\n"
                + "mask - hide the user name or the email domain: 1 masked, 0 unmasked",
             extended = "Example:\n")
public class UDFEmail extends UDF {
  private String evaluate(String email, int mask) {
    String result = "";
    switch(mask) {
      case 0:
        result = email;
        break;
      case 1:
        result = email.substring(0, email.indexOf('@'));
        break;
      case 2:
        result = email.substring(email.indexOf('@') + 1, email.length());
        break;
      case 3:
        break;
    }
    return result;
  }

  /* mask XXX@YYY:
   * 0-(00)2  XXX@YYY
   * 1-(01)2  XXX
   * 2-(10)2  YYY
   * 3-(11)2  
   */
  public Text evaluate(Text email, IntWritable mask) {
    if (email == null || mask == null) return null;
    int mode = mask.get();
    String str_email = email.toString();
    Text result = new Text();
    result.set(evaluate(str_email, mode));
    return result;
  }
}
