import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {

    private final String wordStrSplitRegex = "\\s+";
    private final String formatJoinSymbol = "\n";

    public String play(String inputStr) {

        List<String> wordStrList = formatTheInputs(inputStr);

        List<Word> wordList = caculateWordsCount(wordStrList);

        wordList = sortWords(wordList);

        String wordsReport = formatWordReport(wordList);

        return wordsReport;

    }

    private List<String> formatTheInputs(String inputStr) {
        return Arrays.stream(inputStr.split(wordStrSplitRegex)).collect(Collectors.toList());
    }

    private String formatWordReport(List<Word> wordList) {

        return wordList.stream()
                .map(word -> String.format("%s %d", word.getValue(), word.getWordCount()))
                .collect(joining(formatJoinSymbol));
    }

    private List<Word> sortWords(List<Word> wordList) {
        return wordList.stream().sorted(comparing(Word::getWordCount).reversed())
                .collect(Collectors.toList());
    }

    private List<Word> caculateWordsCount(List<String> wordStrList) {

        return wordStrList.stream()
                .collect(Collectors.groupingBy(wordStr -> wordStr))
                .entrySet().stream()
                .map(entry -> new Word(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

}
