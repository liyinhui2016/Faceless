package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BinaryComparable;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFAge.
 *
 */
@Description(name = "hash",
             value = "_FUNC_(x) - returns a hash value instead of x",
             extended = "Example:\n")
public class UDFHash extends UDF {
    public IntWritable evaluate(Object data) {
        if (data == null) return null;
        IntWritable hash = new IntWritable(data.hashCode());
        return hash;
    }

}
