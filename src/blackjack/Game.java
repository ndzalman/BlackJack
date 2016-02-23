/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import Users.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author IBM
 */
public class Game {
    private enum GameState{ 
        BET //player place a bet
        ,DEAL //new cards are dealt
        ,HIT //palyer needs to hit, stay, double down, split
        ,DEALER //flip hidden card and draw until total value 
        ,RESOLVE
    }
    
    private GameState state = GameState.BET;
    private GameState next;
    private Hand dealerHand = new Hand();
    private Hand currentHand;
    private int currentHandIndex = 0;
    private List<Hand> playerHands = new ArrayList<Hand>();
    private Deck deck;
    private int playerCash = 10000;
    private static int MIN_BET = 5;
    private static int MAX_BET = 500;
    private int playerWins = 0;
    private int dealerWins = 0;
    Scanner input = new Scanner(System.in);
    private int playerId;
    DB db = DB.getInstance();

    
    public Game()
    {
        deck = new Deck();
        playerHands.add(new Hand());
    }
    
    public Game(User u)
    {
        this.playerId = u.getId();
        deck = new Deck();
        playerHands.add(new Hand());
        playerWins = u.getWins();
        playerCash = u.getBalance();
    }
    
    private void update()
    {
        state = next;
        currentHand = playerHands.get(currentHandIndex);
        
        switch (state)
        {
            case BET:
                break;
            case DEALER:
                dealerHand.getCard(1).reveal();
                while (dealerHand.getValue() < 17)
                {
                    dealCard(dealerHand);
                }
                updateState(GameState.RESOLVE);
                //
                update();
                //
                break;
            case DEAL:
                dealCard(currentHand);
                dealCard(dealerHand);
                dealCard(currentHand);
                dealCard(dealerHand,false);
                
                if (currentHand.isBlackjack() == true)
                    updateState(GameState.RESOLVE);
                else
                   updateState(GameState.HIT);
                break;
            case HIT:
                break;
            case RESOLVE:
                if (dealerHand.getCard(1).isHidden())
                    dealerHand.getCard(1).reveal();
                
                dispenseWinnings();
                updateState(GameState.BET);
                break;
                
        } 
        
    }
    
      private void updateState(GameState nextState)
        {
            this.next = nextState;
        }
      
      private void dispenseWinnings()
      {
          for (Hand c : playerHands)
          {
              Hand hand = c;
              switch(hand.getWinner(dealerHand))
              {
                  case 0: //dealer wins  
                     dealerWins++;
                     break;
                  case 1: // player win
                      playerCash +=hand.getBet() * 2;
                      
                      if (currentHand.isBlackjack())
                      {
                          playerCash +=hand.getBet() * 0.5;
                      }
                      playerWins++;
                      break;
                  case -1: //TIE
                      playerCash+=hand.getBet();
                      break;
                          
              }
          }
      }
      
      private void dealCard(Hand hand, boolean faceUp)
      {
          Card newCard = deck.drawCard(faceUp);
          if (newCard == null) //out of cards, reshuffle
          {
              deck.reshuffle();
              dealCard(hand,faceUp);
          }
          else
              hand.AddCard(newCard);
      }
      
      private void dealCard(Hand hand)
      {
          dealCard(hand,true);
      }
      
      private void betSate()
      {
        int bet = 0;
        boolean flag = false;
        while (!flag)
        {
        try{
            System.out.println("Please place your bet, MIN: " + MIN_BET + ", MAX: " + MAX_BET);
            
            bet = Integer.parseInt(input.nextLine());
            input.nextLine();
            flag= true;
            
            if (bet > playerCash)
            {
                System.out.println("You dont have enough money");
                flag = false;
            }
            else if (bet < MIN_BET || bet > MAX_BET)
            {
                System.out.println("Bet must be between " + MIN_BET + " And " + MAX_BET);
                flag = false;
            }
            else
            {
                playerHands.clear();
                playerHands.add(new Hand());
                currentHandIndex = 0;
                currentHand = playerHands.get(currentHandIndex);
                dealerHand.Clear();
                
                playerCash -=bet;
                currentHand.setBet(bet);
                updateState(GameState.DEAL);
            }
        }catch (NumberFormatException e)
        {
            System.out.println("Only numbers!");
        }
        }
      }
      
     private void hit()
     {
         dealCard(currentHand);
         if (!currentHand.isBust() && !currentHand.isBlackjack())
             updateState(GameState.HIT);
         else{
             if (currentHandIndex == (playerHands.size() -1))
                 updateState(GameState.DEALER);
             else
                 currentHandIndex++;
         }
     }
     
     private void stay()
     {
        // go to dealer or next hand
         if (currentHandIndex == (playerHands.size()) -1)
             updateState(GameState.DEALER);
         else
             currentHandIndex++;
     }
     
     private void doubleDown()
     {
         playerCash -= currentHand.getBet();
         currentHand.setBet((currentHand.getBet() *2));
         dealCard(currentHand);
         if (currentHandIndex == (playerHands.size()) -1)
             updateState(GameState.DEALER);
         else
             currentHandIndex++;
     }
     
     private void split()
     {
         Hand newHand = currentHand.split();
         playerCash -=currentHand.getBet();
         playerHands.add(newHand);
     }
     
     private void show()
     {
        System.out.println("Your cards: ");
        for (int i=0; i< currentHand.getSize(); i++)
        {
            System.out.println(currentHand.getCard(i));
        }
         System.out.println("Dealer");
         for (int i =0 ; i< dealerHand.getSize();i++)
         {
             System.out.println(dealerHand.getCard(i));
         }
         System.out.println("");
         System.out.println("You: " + playerWins);
         System.out.println("");
         System.out.println("Dealer :" + dealerWins);
     }
    
    public void initGame()
    {
       int choice = 1;
       while (choice!= 0)
       {
      System.out.println("Welcome to blackjack1");
      System.out.println("Play - 1");
      System.out.println("EXIT - 0");
      choice = input.nextInt();
      input.nextLine();
      if (choice == 0)
          break;
      betSate();
      update();
      show();
        System.out.println("Choose action");
        System.out.println("HIT/STAY/SPLIT/Double");
        String ans = input.nextLine();
        if (ans.equalsIgnoreCase("hit") )
        {
            hit();
            update();
            show();
        }
        else if (ans.equalsIgnoreCase("stay") )
        {
            stay();
            update();
            show();
        }
        else if (ans.equalsIgnoreCase("double") )
        {
            split();
            update();
        }
       }
        db.updateUser(playerId,playerWins,playerCash);
        
        }
        
        
        
    }
