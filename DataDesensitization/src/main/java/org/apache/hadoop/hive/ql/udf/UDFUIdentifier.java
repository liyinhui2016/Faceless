package org.apache.hadoop.hive.ql.udf;

import java.lang.StringBuilder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * UDFUIdentifier.
 *
 */
@Description(name = "unique indentifier",
             value = "_FUNC_(x) - returns a unique identifier of x",
             extended = "Example:\n")
public class UDFUIdentifier extends UDF {
  private static SecureRandom rand_ = new SecureRandom();
  private static String encryptor_ = "MD5";

  public Text evaluate(Integer age) {
    return null;
  }

  /* Method-1: use UUID, the best efficiency*/
  public Text evaluate(Text content, int a) {
    if (content == null) return null;
    String str = content.toString();
    byte[] bytes = str.getBytes();
    UUID uuid = UUID.nameUUIDFromBytes(bytes);

    return new Text(uuid.toString().replaceAll("\\-", ""));
  }

  /*Method-2: use random time and hashCode, the worst efficiency*/
  public Text evaluate(Text content) {
    if (content == null) return null;
    int hash = content.hashCode();
    long time = System.currentTimeMillis();

    /* refer to the algorithm of SHA-1 to shuffle the time*/
    time = (rand_.nextLong() & time) ^ (~time & rand_.nextLong());

    String sid = Integer.toHexString((int)time);
    return new Text(sid + hash);
  }

  /* Method-3: use MD5/SHA/..*/
  public Text evaluate(Text content, String encryptor) {
    if (content == null) return null;
    if (encryptor != null) encryptor_ = encryptor;
    try {
      MessageDigest md = MessageDigest.getInstance(encryptor_);
      md.update(content.getBytes());
      byte[] eb = md.digest();
      md.reset();

      StringBuilder sb = new StringBuilder();
      for (byte b : eb) sb.append(Integer.toHexString(b&0xff));

      return new Text(sb.toString());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }
}
