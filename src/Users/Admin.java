/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import blackjack.DB;
import blackjack.Utils;
import blackjack.Utils.ACCOUNTTYPE;

/**
 *
 * @author IBM
 */
public class Admin extends User{
    
    DB db = DB.getInstance();
    
    
    public Admin() {}

    public Admin(int id,String firstName, String lastName, String gender, String userName, String password) {
        super(id, firstName, lastName, gender, userName, password, 1); // 1 is admin
    }
    
    public Admin(String firstName, String lastName, String gender,
            String userName, String password, int wins, int balance) {
    super(firstName, lastName, gender, userName, password, 1, wins, balance); // 1 is admin
    }

    @Override
    public void signUp(String firstName, String lastName, String gender,
            String userName, String password) {
        Admin admin = new Admin(firstName, lastName,
                gender, userName, password,0,100000);
        db.addUser(admin);
    }
    
        @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.getPassword().equals(oldPassword))
        {
            this.setPassword(newPassword);
            return true;
        }
        else
            return false;
    }
    @Override
    public void removeAccount(User user) {
        db.removeAccount(user);
    }
    
    public void changePermission(User user)
    {
        db.changePermission(user.getId());
    }
    
}
