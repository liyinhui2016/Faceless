package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.apache.commons.lang.StringUtils;

/**
 * UDFAge.
 *
 */
@Description(name = "age",
             value = "_FUNC_(x) - returns the masked age",
             extended = "Example:\n")
public class UDFAge extends UDF
{
    public Text evaluate(Integer age) {
        if (age == null) {
            return null;
        }
        String RetString;
        if (age >= 60) {
            RetString = ">=" + 60;
        }
        else if (age < 20) {
            RetString = "<" + 20;
        }
        else {
            RetString = age/10 + "*";
        }
        Text RetText = new Text();
        RetText.set(RetString);
        return RetText;
    }
}
