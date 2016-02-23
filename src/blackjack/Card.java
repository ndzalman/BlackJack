/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import blackjack.Utils.RANK;
import blackjack.Utils.SUIT;

/**
 *
 * @author IBM
 */
public class Card {
    
    private SUIT suit;
    private RANK rank;
    private boolean hidden; //?
    
    public Card(SUIT suit, RANK rank) {
	this.suit = suit;
	this.rank = rank;
	this.hidden = true;
   }
    
    public SUIT getSuit()
    {
        return suit;
    }
    
    public RANK getRank()
    {
        return rank;
    }
    
    public void reveal()
    {
         hidden = false;
    }
    
    public boolean isHidden()
    {
        return hidden;
    }
    
    public static Card createCard(int i, int j)
    {
        SUIT suit = SUIT.values()[i];
        RANK rank = RANK.values()[j];
        return new Card(suit,rank);
    }
    
    public int getValue()
    {
        int value = 0;
        
        if (hidden) return value; // don't count hidden cards
        
        switch(rank)
        {
            case ACE:  value = 11;
            break;
            case KING: value = 10;
            break;
            case QUEEN: value = 10;
            break;
            case JACK: value = 10;
            break;
            case TEN: value = 10;
            break;
            case NINE: value = 9;
            break;
            case EIGHT: value = 8;
            break;
            case SEVEN: value = 7;
            break;
            case SIX: value = 6;
            break;
            case FIVE: value = 5;
            break;
            case FOUR: value = 4;
            break;
            case THREE: value = 3;
            break;
            case TWO: value = 2;
            break;
            
            default: System.out.println("Invalid card!");
            break;
        }
        return value;
        }

    @Override
    public String toString() {
        if (!hidden)
        return suit.toString() + " " + rank.toString();
        else
            return "Hidden";
    }
    
    
    
    
    }


