package UI;

import FilesOp.Variables;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindHash extends javax.swing.JFrame {

    public FindHash() {
        initComponents();
    }
    public static String filehash;
    public static int fid;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fchHash = new javax.swing.JFileChooser();
        lblHash = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));

        fchHash.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        fchHash.setForeground(new java.awt.Color(0, 102, 102));
        fchHash.setPreferredSize(new java.awt.Dimension(600, 500));
        fchHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fchHashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(fchHash, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblHash, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(fchHash, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHash, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fchHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fchHashActionPerformed
     if("ApproveSelection".equals(evt.getActionCommand()))//when a file is selected
     {       
         try {
             //lblHash.setText("File Hash : "+Files.FileHash.calcSHA1(fchHash.getSelectedFile()));
             filehash=FilesOp.FileHash.calcSHA1(fchHash.getSelectedFile());
                //System.out.println("Hash "+filehash);           //calculate hash of the selected file
             try{                 
                 PreparedStatement st1=dbConnection.DBcon.getCon().prepareStatement("select count(*) from tblfiles where filehash=?");//checking file aready exists?
                 st1.setString(1,filehash);
                 ResultSet r=st1.executeQuery();  
                    if(r.next())
                    {
                    if(r.getInt(1)>0)//if yes,file already exists  in the server
                    {
                        System.out.println("File Exists"+r.getInt(1)); 
                        PreparedStatement st2=dbConnection.DBcon.getCon().prepareStatement("select fileid from tblfiles where filehash=?");//get file id
                         st2.setString(1,filehash);
                         ResultSet r2=st2.executeQuery();
                         if(r2.next())//get the file id
                         {
                            PreparedStatement st3=dbConnection.DBcon.getCon().prepareStatement("insert into tbluser_access values(?,?,?)");
                                    //making entry into users files 
                            st3.setString(1,Variables.user);//username
                            st3.setInt(2,r2.getInt(1));//fileid
                            st3.setString(3, fchHash.getName(fchHash.getSelectedFile()));//filename
                            st3.executeUpdate();
                           // System.out.println("Successfully Uploaded ID:"+r2.getInt(1));
                         }
                    }
                    else//file is new
                    {   //make entry for the new file in file table
                        //TO DO
                        PreparedStatement st=dbConnection.DBcon.getCon().prepareStatement("insert into tblfiles values(null,?,?)");
                        //st.setString(1,fchHash.getName(fchHash.getSelectedFile()));
                        
                        st.setString(1, filehash);//filehash
                        st.setLong(2, (fchHash.getSelectedFile().length()/1024));//filesize
                        st.executeUpdate(); 
                        //get the current fileid
                        PreparedStatement stt=dbConnection.DBcon.getCon().prepareStatement("select fileid from tblfiles where filehash=?");//get file id
                         stt.setString(1,filehash);                         
                         ResultSet r3=stt.executeQuery();
                         if(r3.next())
                         {
                            fid=r3.getInt(1);
                            PreparedStatement st3=dbConnection.DBcon.getCon().prepareStatement("insert into tbluser_access values(?,?,?)");//making entry into users access files 
                            st3.setString(1,Variables.user);//username
                            st3.setInt(2,fid);//fileid
                            st3.setString(3,fchHash.getName(fchHash.getSelectedFile()));//filename
                            st3.executeUpdate();
                            FilesOp.FileSplit.splitFile(fchHash.getSelectedFile());//split the selected file to be uploaded   
                            System.out.println("2");
                         }

                    }
                }
                        System.out.println("Sucessfully Uploaded file: "+fchHash.getName(fchHash.getSelectedFile()));
                        this.setVisible(false);
                        Variables.status="Successfully Uploaded The File";
                        new Home().setVisible(true);                        
                 
                }
             catch(ClassNotFoundException c){}
             catch(MySQLIntegrityConstraintViolationException m)
             {
                 System.out.println("You already have uploaded the same file");
                 Variables.status="You Already have Uploded This File";
                 this.setVisible(false);
                 new Home().setVisible(true); 
             }
             catch(SQLException sq){
                 System.out.println("Expception :"+sq.getStackTrace().toString());
             } catch (Exception ex) {
                 Logger.getLogger(FindHash.class.getName()).log(Level.SEVERE, null, ex);
             }

         } catch (IOException | NoSuchAlgorithmException ex) {
             Logger.getLogger(FindHash.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     else if(evt.getActionCommand().equals("CancelSelection"))
     {
        System.out.println("Bye");
        this.setVisible(false);
        new Upload().setVisible(true);         
     }
    }//GEN-LAST:event_fchHashActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FindHash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FindHash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FindHash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FindHash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FindHash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fchHash;
    private javax.swing.JLabel lblHash;
    // End of variables declaration//GEN-END:variables
}
