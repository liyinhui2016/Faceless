package org.apache.hadoop.hive.ql.udf;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Random;
import javax.crypto.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testUDFAscii() {

    }

    public void testUDFGeneralization() {
       /* UDFGeneralization test = new UDFGeneralization();

        //data = integer
        IntWritable val1 = new IntWritable();
        // mode=floor, data=integer
        int[] data1={1,45,0,10,21,29,-31,-30,-29,-5,-1,23,-23,435};
        int[] unit1={10,10,10,10,10,10,10,10,10,10,10,8,6,20};
        int mode1 = 0;
        int[] result1={0,40,0,10,20,20,-40,-30,-30,-10,-10,16,-24,420}; //expected result
        for (int i = 0; i < data1.length; i++) {
            IntWritable data = new IntWritable(data1[i]);
            IntWritable unit = new IntWritable(unit1[i]);
            IntWritable mode = new IntWritable(mode1);
            val1 = test.evaluate(data,mode,unit);
            if (val1.get() != result1[i]) {
                System.out.printf("data=%d,mode=0,unit=%d,expect result=%d,result=%d\n",data1[i],unit1[i],result1[i],val1.get());
            }
        }

        // mode=ceil, data=integer
        int[] data2={1,45,0,10,21,29,-31,-30,-29,-5,-1,23,-23,435};
        int[] unit2={10,10,10,10,10,10,10,10,10,10,10,8,6,20};
        int mode2 = 1;
        int[] result2={10,50,0,10,30,30,-30,-30,-20,0,0,24,-18,440}; //expected result
        for (int i = 0; i < data2.length; i++) {
            IntWritable data = new IntWritable(data2[i]);
            IntWritable unit = new IntWritable(unit2[i]);
            IntWritable mode = new IntWritable(mode2);
            val1 = test.evaluate(data,mode,unit);
            if (val1.get() != result2[i]) {
                System.out.printf("data=%d,mode=1,unit=%d,expect result=%d,result=%d\n",data2[i],unit2[i],result2[i],val1.get());
            }
        }

        //data = double
        DoubleWritable val2 = new DoubleWritable();
        // mode=floor, data=double
        double[] data3={0.1,0,-0.1,9.9,10,10.01,-10.3,-9.98,-10,-0.002,23.5,200.45,32.4,-0.3,-34.5};
        int[] unit3={10,10,10,10,10,10,10,10,10,10,6,20,1,1,1};
        double[] result3={0,0,-10,0,10,10,-20,-10,-10,-10,18,200,32,-1,-35};
        for (int i = 0; i < data3.length; i++) {
            DoubleWritable data = new DoubleWritable(data3[i]);
            IntWritable unit = new IntWritable(unit3[i]);
            IntWritable mode = new IntWritable(mode1);
            val2 = test.evaluate(data,mode,unit);
            if (val2.get() != result3[i]) {
                System.out.printf("data=%f,mode=0,unit=%d,expect result=%f,result=%f\n",data3[i],unit3[i],result3[i],val2.get());
            }
        }

        // mode=ceil, data=double
        double[] data4={0.1,0,-0.1,9.9,10,10.01,-10.3,-9.98,-10,-0.002,23.5,200.45,32.4,-0.3,-34.5};
        int[] unit4={10,10,10,10,10,10,10,10,10,10,6,20,1,1,1};
        double[] result4={10,0,0,10,10,20,-10,0,-10,0,24,220,33,0,-34};
        for (int i = 0; i < data4.length; i++) {
            DoubleWritable data = new DoubleWritable(data4[i]);
            IntWritable unit = new IntWritable(unit4[i]);
            IntWritable mode = new IntWritable(mode2);
            val2 = test.evaluate(data,mode,unit);
            if (val2.get() != result4[i]) {
                System.out.printf("data=%f,mode=1,unit=%d,expect result=%f,result=%f\n",data4[i],unit4[i],result4[i],val2.get());
            }
        }*/

        // incorrect mode or unit
    }

    public void testUDFTruncation() {
        UDFTruncation test = new UDFTruncation();
        Text str = new Text("1234567890");
        Text result = new Text();
        IntWritable length = new IntWritable(4);
        IntWritable mode = new IntWritable(0);
        result = test.evaluate(str,length,mode);
        mode.set(1);
        result = test.evaluate(str,length,mode);
    }

    public void testUDFMask() {
        Text data = new Text("hello world");
        UDFMask test = new UDFMask();
        IntWritable start = new IntWritable(-3);
        IntWritable end = new IntWritable(0);
        Text tag = new Text("*");
        Text result = test.evaluate(data,start,end,tag);
        result.set("hello");
    }

    public void testUDFPhone() {
        UDFPhone phone = new UDFPhone();
        Text text = new Text("021-123456");

        IntWritable mask = new IntWritable(1);
        Text mText = phone.evaluate(text, mask);
        if (false == mText.equals(new Text("021"))) {
          System.out.printf("testUDFPhone() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText.toString(), mask.get());
        }

        IntWritable mask2 = new IntWritable(2);
        Text mText2 = phone.evaluate(text, mask2);
        if (false == mText2.equals(new Text("123456"))) {
          System.out.printf("testUDFPhone() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText2.toString(), mask2.get());
        }
    }

    public void testUDFMoblie() {
        UDFMobile mobile = new UDFMobile();
        Text text = new Text("13834566543");

        IntWritable mask = new IntWritable(2);
        Text mText = mobile.evaluate(text, mask);
        if (false == mText.equals(new Text("138****6543"))) {
          System.out.printf("testUDFMobile() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText.toString(), mask.get());
        }

        IntWritable mask2 = new IntWritable(4);
        Text mText2 = mobile.evaluate(text, mask2);
        if (false == mText2.equals(new Text("***34566543"))) {
          System.out.printf("testUDFMobile() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText2.toString(), mask2.get());
        }

        IntWritable mask3 = new IntWritable(5);
        Text mText3 = mobile.evaluate(text, mask3);
        if (false == mText3.equals(new Text("***3456****"))) {
          System.out.printf("testUDFMobile() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText3.toString(), mask3.get());
        }
    }

    public void testUDFEmail() {
        UDFEmail email = new UDFEmail();
        Text text = new Text("test@163.com");

        IntWritable mask = new IntWritable(1);
        Text mText = email.evaluate(text, mask);
        if (false == mText.equals(new Text("test"))) {
          System.out.printf("testUDFEmail() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText.toString(), mask.get());
        }

        IntWritable mask2 = new IntWritable(2);
        Text mText2 = email.evaluate(text, mask2);
        if (false == mText2.equals(new Text("163.com"))) {
          System.out.printf("testUDFEmail() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText2.toString(), mask2.get());
        }
    }

    public void testUDFIP() {
        UDFIP ip = new UDFIP();
        Text text = new Text("255.255.106.10");

        IntWritable mask = new IntWritable(9);
        Text mText = ip.evaluate(text, mask);
        if (false == mText.equals(new Text("0.255.106.0"))) {
          System.out.printf("testUDFIP() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText.toString(), mask.get());
        }

        IntWritable mask2 = new IntWritable(15);
        Text mText2 = ip.evaluate(text, mask2);
        if (false == mText2.equals(new Text("0.0.0.0"))) {
          System.out.printf("testUDFIP() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText2.toString(), mask2.get());
        }
    }

    public void testUDFTimestamp() {
        UDFTimestamp ts = new UDFTimestamp();
        Timestamp time = Timestamp.valueOf("2016-07-20 21:42:00");
        TimestampWritable text = new TimestampWritable(time);

        IntWritable mask = new IntWritable(7);
        TimestampWritable mText = ts.evaluate(text, mask);
        if (false ==
            mText.equals(new TimestampWritable(Timestamp.valueOf("2016-07-20 00:00:00")))) {
          System.out.printf("testUDFTimestamp() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText.toString(), mask.get());
        }

        IntWritable mask2 = new IntWritable(15);
        TimestampWritable mText2 = ts.evaluate(text, mask2);
        if (false ==
            mText2.equals(new TimestampWritable(Timestamp.valueOf("2016-07-01 00:00:00")))) {
          System.out.printf("testUDFTimestamp() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText2.toString(), mask2.get());
        }

        IntWritable mask3 = new IntWritable(56);
        TimestampWritable mText3 = ts.evaluate(text, mask3);
        if (false ==
            mText3.equals(new TimestampWritable(Timestamp.valueOf("1960-01-01 21:42:00")))) {
          System.out.printf("testUDFTimestamp() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText3.toString(), mask3.get());
        }

        IntWritable mask4 = new IntWritable(63);
        TimestampWritable mText4 = ts.evaluate(text, mask4);
        if (false ==
            mText4.equals(new TimestampWritable(Timestamp.valueOf("1960-01-01 00:00:00")))) {
          System.out.printf("testUDFTimestamp() has failed! text=%s, masked_text=%s, mask=%d\n",
              text.toString(), mText4.toString(), mask4.get());
        }
    }

    public void testUDFHash() {
        UDFHash hash = new UDFHash();
        Random rand = new Random();
        byte[] str = new byte[100];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 100; i++) {
            rand.nextBytes(str);
            for (byte b : str) sb.append(b);
            Text test = new Text(sb.toString());
            sb.delete(0, sb.length() - 1);
            //System.out.println(hash.evaluate(test));
            hash.evaluate(test);
        }
    }

    public void testUDFUIdentifier() {
        System.out.println("----------- UDFUIdentifier Test ------------");
        UDFUIdentifier uid = new UDFUIdentifier();
        Text text = new Text("rdfuhwqehfihasdihgiauhs");
        Text text2 = new Text("sdfuhwqehfihasdihgiauhs");
        Text result = uid.evaluate(text, 0);

        Text result2 = uid.evaluate(text);
        Text result3 = uid.evaluate(text2);
        Text result4 = uid.evaluate(text, "MD5");

        HashMap<String, Text> items = new HashMap<String, Text>();
        Random rand = new Random();
        byte[] str = new byte[100];
        int item_len = 100000;
        StringBuilder sb = new StringBuilder();
        LocalTime st, et;
        System.out.println("----------- test one ---------------");
        st = LocalTime.now();
        for (int i = 0; i < item_len; i++) {
            //Text test = new Text("rdfuhwqehfihasdihgiauhs" + i);
            rand.nextBytes(str);
            for (byte b : str) sb.append(b);
            Text test = new Text(sb.toString());
            sb.delete(0, sb.length() - 1);
            items.put(uid.evaluate(test).toString(), test);
        }
        et = LocalTime.now();
        System.out.println("item_len = " + item_len + ", map_size = " + items.size());
        System.out.println("[total time]: " + (et.toSecondOfDay() - st.toSecondOfDay()) + "s");

        System.out.println("----------- test two ---------------");
        items.clear();
        st = LocalTime.now();
        for (int i = 0; i < item_len; i++) {
            // Text test = new Text("rdfuhwqehfihasdihgiauhs" + i);
            rand.nextBytes(str);
            for (byte b : str) sb.append(b);
            Text test = new Text(sb.toString());
            sb.delete(0, sb.length() - 1);
            items.put(uid.evaluate(test, 0).toString(), test);
        }
        et = LocalTime.now();
        System.out.println("item_len = " + item_len + ", map_size = " + items.size());
        System.out.println("[total time]: " + (et.toSecondOfDay() - st.toSecondOfDay()) + "s");

        System.out.println("----------- test three ---------------");
        items.clear();
        st = LocalTime.now();
        for (int i = 0; i < item_len; i++) {
            // Text test = new Text("rdfuhwqehfihasdihgiauhs" + i);
            rand.nextBytes(str);
            for (byte b : str) sb.append(b);
            Text test = new Text(sb.toString());
            sb.delete(0, sb.length() - 1);
            items.put(uid.evaluate(test, "MD5").toString(), test);
        }
        et = LocalTime.now();
        System.out.println("item_len = " + item_len + ", map_size = " + items.size());
        System.out.println("[total time]: " + (et.toSecondOfDay() - st.toSecondOfDay()) + "s");
    }

    public void testUDFCipher() {
        System.out.println("----------- UDFCipher Test ------------");
        UDFCipher cipher = new UDFCipher();
        Text text = new Text("This is a test. If you can see the message after decrypted, then it's OK!");
        System.out.println("Before encrypt : " + text);
        Text result = cipher.evaluate(text, Cipher.ENCRYPT_MODE);
        //System.out.println("After encrypted : " + result);

        //Text deresult = new Text(cipher.decryptAES(cipher.encryptAES(text.getBytes())));
        Text deresult = cipher.evaluate(result, Cipher.DECRYPT_MODE);
        //System.out.println("After decrypted : " + deresult);
    }
}
