package challenge3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {

  public static void main(String[] args) {
    Pattern pattern = Pattern.compile("[^\\w]");
    Map<String, Long> wordCount = new BufferedReader(new InputStreamReader(System.in)).
        lines().
        flatMap(line -> pattern.splitAsStream(line)).
        filter(string -> !string.isEmpty()).
        collect(groupingBy(String::toLowerCase, counting()));
    System.out.println("\nWord counts:");
    for (Map.Entry<String, Long> entry: wordCount.entrySet()) {
      System.out.println(format("%5d %s", entry.getValue(), entry.getKey()));
    }
  }

}
