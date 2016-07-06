package FilesOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class FileHash {
    
    public static String calcSHA1(File file) throws FileNotFoundException,IOException, NoSuchAlgorithmException
    {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        try (InputStream input = new FileInputStream(file)) {//The File is read
            
            byte[] buffer = new byte[1024*1024];//1MB
            int len = input.read(buffer);//fetch the content of the file (/fetch)
            while (len != -1)
            {
                sha1.update(buffer, 0, len);
                len = input.read(buffer);
            }
            return new HexBinaryAdapter().marshal(sha1.digest());
        }
    }
//    public static void main(String args[]) throws IOException, NoSuchAlgorithmException
//    {
//        String ff;
//        ff=calcSHA1(new File("C:\\Users\\Kowalski\\Test\\Btree2.ppt.004"));
//        System.out.println(ff);
//    }
//    
}
