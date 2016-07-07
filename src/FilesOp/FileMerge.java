package FilesOp;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileMerge {

    public static void mergeChunks(int fid,String fname) throws IOException, ClassNotFoundException, SQLException, Exception
    {
        File out=new File(Variables.dectemppath+fname);//output local storage path
        PreparedStatement st=dbConnection.DBcon.getCon().prepareStatement("SELECT chunkhash,seqno FROM tblchunks WHERE  fileid=? ORDER BY seqno");
        st.setInt(1,fid);
        ResultSet r=st.executeQuery();
        try (BufferedOutputStream mergingFile = new BufferedOutputStream(new FileOutputStream(out)))
        {
            while(r.next())
            {
                String hash=r.getString("chunkhash");
                File temp=new File(Variables.serverpath+hash);// read each chunks 
                Files.copy(temp.toPath(), mergingFile);
                
            }            
        }
        File original=new File(Variables.downloadpath+fname);
        AESCrypter.decrypt(out,original);
        out.delete();
    }
}
