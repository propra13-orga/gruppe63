package gruppe63_dungeon_crawler;

import java.net.*;
import java.io.*;
 
public class protocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
 
    private static final int NUMJOKES = 5;
 
    private int state = WAITING;
    private int currentJoke = 0;
 
    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
                                 "I didn't know you could yodel!",
                                 "Bless you!",
                                 "Is there an owl in here?",
                                 "Is there an echo in here?" };
 
    public int processInput(int theInput) {
        int theOutput=0;
 
        if (state == WAITING) {
            theOutput = 0;
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput==1) {
                theOutput = 1;
                state = SENTCLUE;
            } else {
                theOutput = 1;
            }
        } else if (state == SENTCLUE) {
            if (theInput==2) {
                theOutput = 2;
                state = ANOTHER;
            } else {
                theOutput = 5;
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
            if (theInput==3) {
                theOutput = 3;
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTKNOCKKNOCK;
            } else {
                theOutput = 4;
                state = WAITING;
            }
        }
        return theOutput;
    }
}