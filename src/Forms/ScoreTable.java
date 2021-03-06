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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import org.apache.derby.impl.sql.execute.AggregatorInfoList;

/**
 *
 * @author IBM
 */
public class ScoreTable extends javax.swing.JFrame {
    UserHome previous = null;
    User player = null;
    /**
     * Creates new form ScoreTable
     */
    public ScoreTable() {
        initComponents();
        initTable();
    }
    
        public ScoreTable(UserHome previous,User player) {
            this();
            this.previous = previous;
            this.player = player;
    }
    
    private void initTable()
    {
        DB db = DB.getInstance();
        String sql = "SELECT * FROM APP.SCORE";
       try{
        Class.forName(DBUtils.DbDriverClass);
       Connection connection = DriverManager.getConnection(
                 DBUtils.DbUrl, DBUtils.DbUser, DBUtils.DbPassword );
       
       Statement stat = connection.createStatement();
       ResultSet resultSet = stat.executeQuery(sql);
       ResultSetMetaData rsmd = resultSet.getMetaData();

       DefaultTableModel dtm = new DefaultTableModel();
       Vector columnsName = new Vector();

        columnsName.addElement("User Name");
        columnsName.addElement("Wins");
        columnsName.addElement("Balance");
       dtm.setColumnIdentifiers(columnsName);
       
       while (resultSet.next())
       {
           Vector dataRows = new Vector();
           
           int userId = resultSet.getInt("USERID");
            int wins = resultSet.getInt("WINS");
            int balance = resultSet.getInt("BALANCE");
            
            String userName = "";
            for (User u : db.getUsers())
            {
                if (u.getId() == userId)
                {
                    userName = u.getUserName();
                    break;
                }
           }
//            if (userId == player.getId())
//            {
//                scoreTable.setSelectionBackground(Color.black);
//            }
            
          dataRows.addElement(userName);
          dataRows.addElement(wins);
          dataRows.addElement(balance);
          
          dtm.addRow(dataRows);
       }
       
       scoreTable.setModel(dtm);
       }catch(Exception e)
       {
           System.out.println(e.getCause());
       }
        
       scoreTable.setEnabled(false);
       scoreTable.setBackground(new java.awt.Color(204, 204, 255));
       scoreTable.getTableHeader().setReorderingAllowed(false);
       
        
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
        scoreTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        scoreTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scoreTable.setSelectionForeground(new java.awt.Color(240, 240, 240));
        jScrollPane1.setViewportView(scoreTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(461, 434));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
                int confirmed = JOptionPane.showConfirmDialog(null, 
        "Are you sure you want to exit the program?", "Exit Program",
        JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

    if (confirmed == JOptionPane.YES_OPTION) {
      this.dispose();
        previous.setVisible(true);
    }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ScoreTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScoreTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScoreTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScoreTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScoreTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable scoreTable;
    // End of variables declaration//GEN-END:variables
}
