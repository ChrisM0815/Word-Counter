/*This is a simple program that counts the words in the titles of all Files
* in a given directory
*/

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class Main {

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Please add the Path as argument!");
            System.exit(0);
        }
        System.out.println("Choosen Path: "+args[0]);

        File directory = new File(args[0]);
        ArrayList<String> wordList = getWordList(directory);
        ArrayList<Word> countList = countWords(wordList);
        printCountList(countList);
    }

    private static ArrayList<String> getWordList(File directory){
        ArrayList<String> wordlist = new ArrayList<>();
        String[] fileList = directory.list();

        for (String fileName:fileList) {
            String[] words = fileName.split("[^\\w']+|[_]+|'(?!\\w)|(?<!\\w)'");
            for (String word:words) {
                if(!word.matches(".*\\d.*") && !word.isBlank() && !word.matches("[-]*"))//Filters words containing numbers and empty words
                    wordlist.add(word.toLowerCase(Locale.ROOT));
            }
        }
        wordlist.removeAll(Arrays.asList("", null));//Parameter: all Strings to be filtered out of the File
        wordlist.sort(String::compareToIgnoreCase);
        return wordlist;
    }

    private static ArrayList<Word> countWords(ArrayList<String> wordlist)//Counts how often every word the Textfile contains is used in this textfile
    {
        ArrayList<Word> countList = new ArrayList<>();
        Word current = new Word(wordlist.get(0));
        for (String word:wordlist) {
            if(!word.equals(current.getText())) {
                countList.add(current);
                current = new Word(word);
            }
            current.increment();
        }
        countList.sort(Word::compareTo);
        return countList;
    }

    private static void printCountList( ArrayList<Word> wordlist){
        for (Word word:wordlist) {
            System.out.printf("\nWord: %20s ",word.getText());
            System.out.printf("Count: %3d ",word.getCount());
        }
    }

    private static void printWordList( ArrayList<String> wordlist){
        for (String word:wordlist) {
            System.out.println(word);
        }
    }

}
