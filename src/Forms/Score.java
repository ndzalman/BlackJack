/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

/**
 *
 * @author Anael
 */
public class Score {
    
    private String userName;
    private int wins;
    private int balance;

    @Override
    public String toString() {
        return userName + " " + wins + " " + balance;
    }
    
    public Score(String userName,int wins,int balance)
    {
        this.userName = userName;
        this.wins = wins;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    
    
}
