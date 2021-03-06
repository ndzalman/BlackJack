/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Users.User;
import blackjack.DB;
import blackjack.DBUtils;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IBM
 */
public class ListOfUsers extends javax.swing.JFrame {
    
    private static int id;
    private static String userName = null;
    AdminHome previous = null;
    /**
     * Creates new form ListOfUsers
     */
    public ListOfUsers(AdminHome previous) {
        initComponents();
        this.previous = previous;
        initTable();
    }

    private void initTable()
    {
        DB db = DB.getInstance();
        String sql = "SELECT ID,FIRSTNAME,LASTNAME,GENDER,USERNAME FROM APP.USERS";
       try{
        Class.forName(DBUtils.DbDriverClass);
       Connection connection = DriverManager.getConnection(
                 DBUtils.DbUrl, DBUtils.DbUser, DBUtils.DbPassword );
       
       Statement stat = connection.createStatement();
       ResultSet resultSet = stat.executeQuery(sql);
       ResultSetMetaData rsmd = resultSet.getMetaData();

       DefaultTableModel dtm = new DefaultTableModel() {
               @Override
        public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
        }
       };
       Vector columnsName = new Vector();

        columnsName.addElement("ID");
        columnsName.addElement("First Name");
        columnsName.addElement("Last Name");
        columnsName.addElement("Gender");
        columnsName.addElement("User Name");
       dtm.setColumnIdentifiers(columnsName);
       
       while (resultSet.next())
       {
           Vector dataRows = new Vector();
           
           int id = resultSet.getInt("ID");
            String firstName = resultSet.getString("FIRSTNAME");
            String lastName = resultSet.getString("LASTNAME");
            String gender = resultSet.getString("GENDER");
            String usertName = resultSet.getString("USERNAME");

          dataRows.addElement(id);
          dataRows.addElement(firstName);
          dataRows.addElement(lastName);
          dataRows.addElement(gender);
          dataRows.addElement(usertName);
          
          dtm.addRow(dataRows);
       }
       
       usersTable.setModel(dtm);
       }catch(Exception e)
       {
           System.out.println(e.getCause());
       }
       usersTable.setBackground(new java.awt.Color(204, 204, 255));
       usersTable.getTableHeader().setReorderingAllowed(false);
       
       
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        btnChangePermission = new javax.swing.JButton();
        btnRemoveUser = new javax.swing.JButton();
        labBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usersTable);

        btnChangePermission.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChangePermission.setText("Change Permission");
        btnChangePermission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePermissionActionPerformed(evt);
            }
        });

        btnRemoveUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRemoveUser.setText("Remove User");
        btnRemoveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveUserActionPerformed(evt);
            }
        });

        labBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/specialBack.png"))); // NOI18N
        labBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labBackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btnRemoveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnChangePermission)
                .addGap(46, 46, 46)
                .addComponent(labBack))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRemoveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnChangePermission, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labBack))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(543, 549));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked
        int selectedRow = usersTable.getSelectedRow();
        id = 0;
        if (selectedRow != -1)
        {
            usersTable.setSelectionBackground(Color.WHITE);
            id = (int) usersTable.getValueAt(selectedRow, 0);
            userName = (String) usersTable.getValueAt(selectedRow, 4);
        }
    }//GEN-LAST:event_usersTableMouseClicked

    
    private void btnChangePermissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePermissionActionPerformed
        if (usersTable.getSelectedRow() != -1)
        {
        DB db = DB.getInstance();
        User u = db.findUserById(id);
            if (u != null) {
                    if (u.getPermission() == 1) {
                        JOptionPane.showMessageDialog(null, "You cant change an admin!", "Invalid Option",
                                JOptionPane.ERROR_MESSAGE);
                    }
            else{
        
            int option = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to make " + userName + " admin?","Change Permission", 
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
                        db.changePermission(id);
                        initTable();
                    }
                    }   
        }
        }
        else
        {
            JOptionPane.showMessageDialog(null,  "Please select user !", "Change Permission",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnChangePermissionActionPerformed

    private void btnRemoveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveUserActionPerformed
        if (usersTable.getSelectedRow() != -1) {
                DB db = DB.getInstance();
                User u = db.findUserById(id);
                if (u != null) {
                    if (u.getPermission() == 1) {
                        JOptionPane.showMessageDialog(null, "You cant delete an admin!", "Invalid Option",
                                JOptionPane.ERROR_MESSAGE);
                    } 
                    else{
            
            int option = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete " + userName + " ?", "Delete User",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                        db.removeAccount(u);
                        initTable();
                    }
                    else {
                    System.out.println("can't find user");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select user !", "Delete User",
                    JOptionPane.INFORMATION_MESSAGE);
        }
     }
    }//GEN-LAST:event_btnRemoveUserActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int confirmed = JOptionPane.showConfirmDialog(null, 
        "Are you sure you want to exit the program?", "Exit Program",
        JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

    if (confirmed == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
    }//GEN-LAST:event_formWindowClosing

    private void labBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labBackMouseClicked
        this.dispose();
        previous.setVisible(true);
    }//GEN-LAST:event_labBackMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePermission;
    private javax.swing.JButton btnRemoveUser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labBack;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
