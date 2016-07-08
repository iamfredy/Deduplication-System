package FilesOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypter {
    
  public static void encrypt(File fin,File fout)  throws Exception{
    InputStream in=new FileInputStream(fin);
    OutputStream out=new FileOutputStream(fout);
    
    byte[] buf = new byte[1024];
    Cipher ecipher;
    String keyString="TheDarkKnightRises";
    SecretKey key=new SecretKeySpec(keyString.getBytes(),0,16,"AES");
    byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A,(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
    AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
    ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

    ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
    out = new CipherOutputStream(out, ecipher);
    int numRead = 0;
    while ((numRead = in.read(buf)) >= 0) {
      out.write(buf, 0, numRead);
    }
    out.close();
    in.close();
    Files.move(fout.toPath(),fin.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);

  }

  public static void decrypt(File fin,File fout)  throws Exception{
    InputStream in=new FileInputStream(fin);
    OutputStream out=new FileOutputStream(fout);
    Cipher dcipher;
    byte[] buf = new byte[1024];
    String keyString="TheDarkKnightRises";
    SecretKey key=new SecretKeySpec(keyString.getBytes(),0,16,"AES");
    byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A,(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
    AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
    dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);    
    in = new CipherInputStream(in, dcipher);
    int numRead = 0;
    while ((numRead = in.read(buf)) >= 0) {
      out.write(buf, 0, numRead);
    }
    out.close();
    in.close();
  }
  
  
}

/*public class Cryptor{
  public static void main(String[] argv) throws Exception  {
    String keyString="TheDarkKnightRises";
    SecretKey key2=new SecretKeySpec(keyString.getBytes(),0,16,"AES");
    System.out.println("Key: "+key2);
    //AESEncrypter encrypter = new AESEncrypter(key2);
   AESEncrypter.encrypt(new File("C:\\Users\\Kowalski\\Desktop\\Downloads\\a.txt"), new File("C:\\Users\\Kowalski\\Desktop\\Downloads\\out.txt"));
  //AESEncrypter.decrypt(new File("C:\\Users\\Kowalski\\Desktop\\Downloads\\out.txt"), new File("C:\\Users\\Kowalski\\Desktop\\Downloads\\aaa.txt"));
// System.out.println("A.mp3: "+FilesOp.FileHash.calcSHA1(new File("C:\\Users\\Kowalski\\Desktop\\Downloads\\a.mkv")));
    //System.out.println("b.mp3: "+FilesOp.FileHash.calcSHA1(new File("C:\\Users\\Kowalski\\Desktop\\Downloads\\b.mkv")));
    //System.out.println("c.mp3: "+FilesOp.FileHash.calcSHA1(new File("C:\\Users\\Kowalski\\Desktop\\Downloads\\c.mkv")));
    //System.out.println("d.mp3: "+FilesOp.FileHash.calcSHA1(new File("C:\\Users\\Kowalski\\Desktop\\Downloads\\d.mkv")));
    
  }
}
*/
   
    
    
    
  