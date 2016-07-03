
import FilesOp.AESCrypter;
import java.io.File;
public class Shamir
{
    public static void main(String args[]) throws Exception
    {
        //AESCrypter.encrypt(new File("C:\\Users\\Kowalski\\Desktop\\Inputs\\in.mp3"),new File("C:\\Users\\Kowalski\\Desktop\\Inputs\\out"));
        AESCrypter.decrypt(new File("C:\\Users\\Kowalski\\Desktop\\Inputs\\in.mp3"),new File("C:\\Users\\Kowalski\\Desktop\\Inputs\\out.mp3"));

    }
}