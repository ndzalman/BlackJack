/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Users.User;
import blackjack.DB;

/**
 *
 * @author Anael
 */
public class InputValidation {
    
    public static String checkFirstName(String firstName)
    {
        if (firstName.isEmpty())
            return "First Name can't be empty";
        else if (firstName.matches(".*\\d+.*"))
            return "First Name Can't contain numbers!";
        else 
           return "";
    }
    
        public static String checkLastName(String lastName)
    {
        if (lastName.isEmpty())
            return "Last Name can't be empty";
        else if (lastName.matches(".*\\d+.*"))
            return "Last Name Can't contain numbers!";
        else 
           return "";
    }
        
    public static String checkUserName(String userName)
    {
        // think about it
       DB db = DB.getInstance();
       for (User u : db.getUsers())
       {
           if (u.getUserName().equals(userName))
               return "User Name is taken, Please Choose another one";
           
       }
       
       if (userName.length() > 20 || userName.length() < 4)
           return "Invalid user name (up to 20 letters, minimum 4";
       else if (userName.isEmpty())
           return "User name can't be empty!";
       
       return "";
    }
    
    
    public static String checkPassword(String password)
    {
       if (password.length() < 6 || password.length() > 20) 
           return "Invalid paswword (up to 20 letters, minimum 6";
       else if (password.isEmpty())
           return "Password can't be empty!";
       
       return "";

    }
}
