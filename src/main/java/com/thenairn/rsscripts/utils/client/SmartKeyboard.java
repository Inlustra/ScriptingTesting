package com.thenairn.rsscripts.utils.client;

import java.util.ArrayList;
import java.util.Random;

import org.osbot.rs07.api.Keyboard;
import org.osbot.rs07.script.Script;

public class SmartKeyboard {

    public int wordsPM, charsPM, charsPS, msPC, minMS, maxMS, typoRate = 0;
    public Random rand;
    public Script baseScript;
    public Keyboard kb;

    public int typoConstant = 100000; //You can change this if needed

    //This is a huge array of all potential typos for every alphabet character
    public String[][] typos = new String[][] { { "q", "s", "z" }, //a
            { "v", "g", "n" }, { "x", "d", "v" }, { "s", "e", "f", "c" }, //bcd
            { "w", "d", "r" }, { "d", "r", "g", "v" }, { "f", "t", "h", "b" }, //efg
            { "g", "y", "j", "n" }, { "u", "k", "o" }, { "h", "m", "k", "u" }, //hij
            { "j", ",", "l", "i" }, { "k", ".", ";", "o" }, { "n", "j", "," }, //klm
            { "b", "h", "m" }, { "i", "l", "p" }, { "o", ";", "[" }, //nop
            { "w", "a", "s" }, { "e", "f", "t", "4" }, { "w", "a", "d", "x" },//qrs
            { "r", "g", "y", "5" }, { "y", "j", "i", "8" }, { "c", "f", "b" },//tuv
            { "q", "s", "e" }, { "z", "s", "c", "d" }, { "t", "h", "u", "j" }, //wxy
            { "a", "s", "x", "\\" }//z
    };

    public String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public SmartKeyboard(Script base, int wpm) {
        baseScript = base;
        kb = base.getKeyboard();
        wordsPM = wpm;
        charsPM = (wpm * 6);
        charsPS = charsPM / 60;
        msPC = 1000 / charsPS;

        rand = new Random();
        minMS = msPC - (wordsPM / 2) + rand.nextInt(15); //Make this a little bit more random to simulate real key presses
        maxMS = msPC + (wordsPM / 2) + rand.nextInt(15);
    }

    public boolean typeString(String toType, boolean enter, boolean typos) throws InterruptedException {
        int x = calculateTypo();
        ArrayList<TypoChar> indexes = new ArrayList<TypoChar>();
        int index = 0;
        for (char ch : toType.toCharArray()) {

            //Here we are going to erase any typos
            if (typos && rand.nextInt(2) == 1) {
                for (TypoChar tc : indexes) {
                    if (tc.index == (index - 1)) { //We're going to erase this fucker
                        kb.typeKey((char)8); //8 is the backspace character (or 127 which is delete, not sure here)
                        sleep(rand(minMS, maxMS, msPC));
                        kb.typeKey(tc.original);
                        sleep(rand(minMS, maxMS, msPC));
                    }
                }
            }

            char old = ch;
            if (typos && (rand.nextInt(x) == (x / 2))) {
                ch = toTypo(String.valueOf(ch)).toCharArray()[0];
                indexes.add(new TypoChar(index, old)); //We're going to see
            }
            kb.typeKey(ch);
            sleep(rand(minMS, maxMS, msPC));
            index++;
        }

        if (enter) {
            pressEnter();
        }

        return true;
    }

    public boolean typeCharacter(String character, boolean enter) throws InterruptedException {
        kb.typeKey(character.toCharArray()[0]);
        sleep(rand(minMS, maxMS, msPC));

        if (enter) {
            pressEnter();
        }

        return true;
    }

    public boolean typeInteger(int integer, boolean enter) throws InterruptedException {
        kb.typeKey(Character.forDigit(integer, 10));
        sleep(rand(minMS, maxMS, msPC));

        if (enter) {
            pressEnter();
        }

        return true;
    }

    public boolean pressEnter() {
        kb.typeKey((char)13);
        kb.typeKey((char)10);
        return true;
    }

    public int calculateTypo() {
        int temp = typoConstant / (wordsPM * 10);
        temp += rand.nextInt(6);
        return temp;
    }

    public String toTypo(String toTypo) { //TODO: preserve uppercase (if wanted)
        toTypo = toTypo.toLowerCase();
        int index = alphabet.indexOf(toTypo.charAt(0)); //Get the index to get typos for

        String[] charTypos = typos[index]; //We have our typos stored in the order abcdef...z
        int len = charTypos.length;
        toTypo = charTypos[getRandom(0, len - 1)]; //We do len - 1 as Java uses zero-based arrays.

        return toTypo;
    }

    public int rand(int min, int max, int mean) { //Edited slightly; in a normal distribution, mean = mode = median = midpoint of graph
        int n;
        //int mean = (min + max) / 2;
        int std = (max - mean) / 3;
        Random r = new Random();

        do {
            double val = r.nextGaussian() * std + mean;
            n = (int) Math.round(val);
        } while (n < min || n > max);

        return n;
    }

    public int getRandom(int min, int max) {
        int range = (max - min) + 1;
        return rand.nextInt(range) + min;
    }

    public void sleep(int t) throws InterruptedException {
        Script.sleep(t);
    }

}

class TypoChar { //We'll make an arraylist of these bad boys
    public int index;
    public char original;

    public TypoChar(int i, char o) {
        original = o;
        index = i;
    }
}