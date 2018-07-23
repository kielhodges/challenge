package challenge3;

import graphs.GNode;
import challenge2.PathFinder;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class PatternLearningTest {

  private static final GNode[] NO_CHILDREN = {};

  private PathFinder pathFinder = new PathFinder();

  @Test
  public void splitProducesEmptyStringWhenThereAreConsecutiveDelimiters() {
    Pattern pattern = Pattern.compile("[^\\w]");

    String[] words = pattern.split("This is a test. This is only a test; so just chill!");

    assertEquals(asList("This", "is", "a", "test", "", "This", "is", "only", "a", "test", "", "so", "just", "chill"),
        asList(words));
  }

  @Test
  public void splitAsStreamReturnsAConvenientStream() {
    Pattern pattern = Pattern.compile("[^\\w]");

    Stream<String> words = pattern.splitAsStream("This is a test. This is only a test; so just chill!");

    assertEquals(asList("This", "is", "a", "test", "", "This", "is", "only", "a", "test", "", "so", "just", "chill"),
        words.collect(toList()));
  }

  @Test
  public void splitAsStreamUsedWithAReader() {
    Pattern pattern = Pattern.compile("[^\\w]");
    String input = "This is a test. This is only a test; so just chill!\nThis completes this test.";
    BufferedReader reader = new BufferedReader(new StringReader(input));
    Stream<String> lines = reader.lines();

    Stream<String> words = lines.flatMap(line -> pattern.splitAsStream(line));

    assertEquals(asList("This", "is", "a", "test", "", "This", "is", "only", "a", "test", "", "so", "just", "chill",
        "This", "completes", "this", "test"),
        words.collect(toList()));
  }

  @Test
  public void splitAsStreamCanBeUsedToDropEmptyStringWhenThereAreConsecutiveDelimiters() {
    Pattern pattern = Pattern.compile("[^\\w]");

    Stream<String> words = pattern.splitAsStream("This is a test. This is only a test; so just chill!").
        filter(string -> !string.isEmpty());

    assertEquals(asList("This", "is", "a", "test", "This", "is", "only", "a", "test", "so", "just", "chill"),
        words.collect(toList()));
  }

  @Test
  public void groupingByCollectorCanBeUsedToCreateAMapOfWordsToCounts() {
    Pattern pattern = Pattern.compile("[^\\w]");

    Map<String, Long> wordCount = pattern.
        splitAsStream("This is a test. This is only a test; so just chill! This completes this test.").
        filter(string -> !string.isEmpty()).
        collect(groupingBy(String::toLowerCase, counting()));

    Map<String, Long> expectedWordCount = new HashMap<>();
    expectedWordCount.put("this",      4L);
    expectedWordCount.put("is",        2L);
    expectedWordCount.put("a",         2L);
    expectedWordCount.put("test",      3L);
    expectedWordCount.put("only",      1L);
    expectedWordCount.put("so",        1L);
    expectedWordCount.put("just",      1L);
    expectedWordCount.put("chill",     1L);
    expectedWordCount.put("completes", 1L);
    assertEquals(expectedWordCount, wordCount);
  }

}
