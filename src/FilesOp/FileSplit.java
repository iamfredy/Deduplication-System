package FilesOp;


import UI.FindHash;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileSplit
{    
    
    public  static void splitFile(File originalFile) throws IOException, FileNotFoundException, NoSuchAlgorithmException, ClassNotFoundException, SQLException, Exception//changed  to static
    {
        int partCounter = 1;//I like to name parts from 001, 002, 003, ...  //you can change it to 0 if you want 000, 001, ...
        int sizeOfFiles = 1024*1024;//*100=1KB,*1024= 1MB
        byte[] buffer = new byte[sizeOfFiles];//tempoarary container to store chunk
        File encFile=new File(Variables.Enctemppath+originalFile.getName());
        AESCrypter.encrypt(originalFile,encFile);
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(encFile))) 
        {//try-with-resources to ensure closing stream
            String name = originalFile.getName();
            int tmp = 0;
            while ((tmp = bis.read(buffer)) > 0){//write each chunk of data into separate file with different number in name
                File newFile = new File(Variables.temppath, name + "."+ String.format("%03d", partCounter));//local temp folder
//                File newFile = new File("C:\\Users\\Kowalski\\Test\\Chunks\\", name + "."+ String.format("%03d", partCounter));
                String chunkhash;
                try (FileOutputStream out = new FileOutputStream(newFile))
                {
                    out.write(buffer, 0, tmp);//tmp is no of bytes read from the file in a single fetch
                    chunkhash=FilesOp.FileHash.calcSHA1(newFile);//Calculate hash Of Each Chunk
                    System.out.println("ChunkHash : "+chunkhash+" PC: "+partCounter);
                }
                File chunk=new File(Variables.serverpath+chunkhash);//the path of server storage
                PreparedStatement st=dbConnection.DBcon.getCon().prepareStatement("select count(chunkhash) from tblchunks where chunkhash=?");
                st.setString(1,chunkhash);
                ResultSet r=st.executeQuery();
                if(r.next()&&r.getInt(1)>0)//if chunk  already exists
                {  
                    //make entry in tblchunks
                    boolean del=newFile.delete();
                    System.out.println("Chunks Exists for File: "+FindHash.fid+" You Are :"+Variables.user+" Delete ?:"+del);
                    
                }
                else//chunk is new, ready to make entry and upload
                {
                    Files.move(newFile.toPath(),chunk.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);//saving (UPLOADING)
                    
                }
                        PreparedStatement st4=dbConnection.DBcon.getCon().prepareStatement("insert into tblchunks values(null,?,?,?) ");//making entry in chunk table
                        st4.setString(1,chunkhash);//chunk hash
                        st4.setInt(2,FindHash.fid);//file id
                        st4.setInt(3,partCounter);//sequence number
                        st4.executeUpdate();
                        partCounter++;
            }
        }
        finally
        {
            encFile.delete();
            System.out.println("Success");
        }
    }
}