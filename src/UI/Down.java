package UI;

import FilesOp.FileMerge;
import FilesOp.Variables;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Down extends javax.swing.JFrame {

    public Down() throws ClassNotFoundException, SQLException {
        initComponents();
        //User.user="fredy799@gmail.com";//TO TEST
        lblHello.setText("Hello "+Variables.user);
        try
        {
        DefaultTableModel m=(DefaultTableModel) tblFilesList.getModel();
        PreparedStatement st=dbConnection.DBcon.getCon().prepareStatement("SELECT * FROM tbluser_access WHERE userid=?");
        st.setString(1,Variables.user);///userid
        ResultSet r=st.executeQuery();
        PreparedStatement st2;
        ResultSet r2;
        while(r.next())
        {
            long fid=r.getLong("fileid");
            long size=000;
            String fname=r.getString("filename");
            st2=dbConnection.DBcon.getCon().prepareStatement("SELECT filesize FROM tblfiles where fileid=?");
            //System.out.println("id "+fid);
            st2.setLong(1,fid);///userid
            r2=st2.executeQuery();
            if(r2.next())
                size=r2.getLong("filesize");
            m.addRow(new Object[]{fid,fname,size});
            ///System.out.println("id "+fid);
        }
        }
        catch(ClassNotFoundException | SQLException c){System.out.println("Err :"+c);}
        //System.out.println("Here");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDownload = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFilesList = new javax.swing.JTable();
        btnDownload = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        lblHello = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(600, 500));

        pnlDownload.setBackground(new java.awt.Color(0, 102, 102));
        pnlDownload.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Download Files", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 255, 255))); // NOI18N

        tblFilesList.setBackground(new java.awt.Color(153, 255, 255));
        tblFilesList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FileID", "File Name", "Size(KB)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblFilesList);

        btnDownload.setBackground(new java.awt.Color(255, 255, 255));
        btnDownload.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDownload.setForeground(new java.awt.Color(0, 102, 102));
        btnDownload.setText("Download");
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadActionPerformed(evt);
            }
        });

        lblMsg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(51, 255, 255));

        javax.swing.GroupLayout pnlDownloadLayout = new javax.swing.GroupLayout(pnlDownload);
        pnlDownload.setLayout(pnlDownloadLayout);
        pnlDownloadLayout.setHorizontalGroup(
            pnlDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDownloadLayout.createSequentialGroup()
                .addGroup(pnlDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                    .addGroup(pnlDownloadLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(pnlDownloadLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDownloadLayout.setVerticalGroup(
            pnlDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDownloadLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDownload)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        btnHome.setBackground(new java.awt.Color(255, 255, 255));
        btnHome.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHome.setForeground(new java.awt.Color(0, 204, 153));
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblHello, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(258, 258, 258)
                        .addComponent(btnHome)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHello, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
    
        //int rowindex=tblFilesList.getSelectedRow();
        //int colindex=tblFilesList.getSelectedColumn();
        int[] rows=tblFilesList.getSelectedRows();
        int rowcount=tblFilesList.getSelectedRowCount();
        //int[] cols=tblFilesList.getSelectedColumns();
        boolean flag=true;
        if(rowcount>0)
        {
            lblMsg.setText("");
            try
            {
                    for(int i : rows)//i contains rowindex of each selected item
                    {
                        try {
                            //System.out.println("ID "+tblFilesList.getValueAt(i,0)+" FileName "+tblFilesList.getValueAt(i, 1));
                            FileMerge.mergeChunks(Integer.parseInt(tblFilesList.getValueAt(i, 0).toString()),tblFilesList.getValueAt(i, 1).toString() );
                                                                    //file id                                 //file name    
                        }
                        catch (IOException | ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(Down.class.getName()).log(Level.SEVERE, null, ex);
                            flag=false;
                        }
                    }
            }
            finally
            {
                if(flag==true)
                {
                    System.out.println("Successfully Downloaded All Files");
                    this.setVisible(false);
                    Variables.status="Successfully Downloaded  all The Selected Files";
                    //new DdsLogin().setVisible(true);
                    new Home().setVisible(true);
                }
            }
        }
        else
        {
            lblMsg.setText("Please Select Atleast One File");
        }
    }//GEN-LAST:event_btnDownloadActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
      this.setVisible(false);
      Variables.status="";
      new Home().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed


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
            java.util.logging.Logger.getLogger(Down.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Down.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Down.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Down.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Down().setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Down.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnHome;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHello;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JPanel pnlDownload;
    private javax.swing.JTable tblFilesList;
    // End of variables declaration//GEN-END:variables
}
