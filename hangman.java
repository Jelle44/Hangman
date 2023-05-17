//This is a programme for playing hangman.

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class hangman{
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String selectedWord = pickRandomWord();
        
        char[] lettersOfWord = convertToCharacters(selectedWord);        
        char[] underscores = convertToUnderscores(lettersOfWord);
        
        String guess;
        int guessRemain = 10;
        char input;
        ArrayList<Character> listOfGuesses = new ArrayList<Character>();
        boolean winner = false;
        
        while (guessRemain != 0 && !winner){

            printWithSpaces(underscores);
            System.out.println("");
            System.out.println("You have " + guessRemain + " guesses left.");
            System.out.println("Past guesses: " + listOfGuesses);
            System.out.print("What is your guess? ");
            guess = scanner.nextLine().trim().toLowerCase();
        
            if (guess.equals(selectedWord)){
                System.out.println("Congratulations, you won!");
                guessRemain = 0;
            } else {
                input = guess.charAt(0);
                underscores = checkGuesses(lettersOfWord, underscores, input);
                
                if (listOfGuesses.contains(input)) {
                    guessRemain++;
                }
                
                listOfGuesses = rememberGuesses(listOfGuesses, input, guessRemain);
                
                winner = checkForWin(underscores, winner);
                
                guessRemain--;
                System.out.println("");
            }        
        }
        if(winner){
            System.out.println("Congratulations! You won! The word was \"" + selectedWord + "\".");
            guessRemain = 0;
        }
        
        if(guessRemain == 0 && !winner){
            System.out.println("Game over!");
        }
    }
    
    public static String pickRandomWord (){
        Random random = new Random();
        String[] listRandomWords = {"voodoo", "jazz", "awkward", "beekeeper", "zombie", "penguin", "hangman", "duck", "abyss", "buffoon","kangaroo"};
        int randomNum = random.nextInt(listRandomWords.length);
        String selectedWord = (listRandomWords[randomNum]);
        return selectedWord;
    }
    
    public static char[] convertToCharacters(String word){
        char [] lettersOfWord = word.toCharArray();

        return lettersOfWord;
    }
    
    public static char[] convertToUnderscores(char[] inputList){
        char[] result = new char[inputList.length];
        for(int i = 0; i < inputList.length; i++){
            result[i] = '_';
        }
        
        return result;
    }
    
    public static void printWithSpaces(char[] characters) {
        for(int i = 0; i < characters.length; i++){
            System.out.print(characters[i] + " ");
        }
    }
    
    public static ArrayList<Character> rememberGuesses (ArrayList<Character> pastGuesses, char input, int guessRemain){
        
        if(pastGuesses.contains(input)){
            System.out.println("You have already guessed \"" + input + "\". Please try again.");
        } else {
            pastGuesses.add(input);
        }
        return pastGuesses;
    }
    
    public static char[] checkGuesses (char[] lettersOfWord, char[] underscores, char input){
        for (int i = 0; i < lettersOfWord.length; i++) {
            if (lettersOfWord[i] == input && underscores[i] != input) {
                underscores[i] = input;
            }
        }
        return underscores;
    }
    
    public static boolean checkForWin (char[] underscores, boolean contains){
        String word = new String(underscores);
        if (!word.contains("_")){
            System.out.println("Hier");
            contains = true;
        }
        return contains;
    }
}