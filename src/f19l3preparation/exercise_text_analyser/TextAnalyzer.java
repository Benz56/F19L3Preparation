package f19l3preparation.exercise_text_analyser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TextAnalyzer {

    private final File file;

    public TextAnalyzer(String fileName) {
        file = new File(fileName);
    }

    // Opgave 2A     
    // Parameteren sorted afgør om der skal benyttes et sorteret Set
    public Set<String> findUniqueWords(boolean sorted) {
        Set<String> set = sorted ? new TreeSet<>() : new HashSet<>();

        try (Scanner scanner = new Scanner(file)) {
            scanner.forEachRemaining(word -> set.add(clean(word)));
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong when reading the file!");
        }

        return set;
    }

    // Opgave 2B:   Nearly as Listing 21.9 from Liang
    public Map<String, Integer> countWords(boolean sorted) {
        Map<String, Integer> map = sorted ? new TreeMap<>() : new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            scanner.forEachRemaining(word -> map.merge(clean(word), 1, Integer::sum));
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong when reading the file!");
        }

        return map;
    }

    // Opgave 2C:     Udvidelse af P15.1
    public Map<Integer, Set<String>> lengtOfWords(boolean sorted) {
        Map<Integer, Set<String>> mapOfSets = sorted ? new TreeMap<>() : new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            scanner.forEachRemaining(word -> mapOfSets.computeIfAbsent(clean(word).length(), key -> sorted ? new TreeSet<>() : new HashSet<>()).add(clean(word)));
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong when reading the file!");
        }

        return mapOfSets;

    }

    // Denne metode forsøger at fjerne alt 'snavs' fra en String,
    // så kun bogstaver bevares og store gøres til små
    // One-lined by Benz56 :P
    private String clean(String s) {
        return s.chars().flatMap(c -> Character.isLetter((char) c) ? IntStream.of(c) : IntStream.empty()).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString().toLowerCase();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        TextAnalyzer ta = new TextAnalyzer("src/alice30.txt");

        // Opgave 2A. Find alle unikke ord i filen
        Set<String> set = ta.findUniqueWords(true);
        System.out.println(set);
        System.out.println("Number of unique words: " + set.size());
        System.out.println("\n------------------------------------------------------------------\n");

        // Opgave 2B. Tæl forekomster af ord
        Map<String, Integer> map = ta.countWords(true);
        System.out.println(map);

        System.out.println("\n------------------------------------------------------------------\n");

        // Opgave 2C. Benyt en mappe til at gruppere ord efter længde
        Map<Integer, Set<String>> map2 = ta.lengtOfWords(true);
        System.out.println(map2);
    }
}
