import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {

    private final String wordStrSplitRegex = "\\s+";
    private String formatJoinSymbol;

    public String play(String inputStr) {

        List<String> wordStrList = Arrays.stream(inputStr.split(wordStrSplitRegex)).collect(Collectors.toList());

        List<Word> wordList = caculateWordsCount(wordStrList);

        sortWords(wordList);

        String wordsReport = formatWordReport(wordList);

        return wordsReport;

    }

    private String formatWordReport(List<Word> wordList) {

        formatJoinSymbol = "\n";
        return wordList.stream()
                .map(word -> String.format("%s %d", word.getValue(), word.getWordCount()))
                .collect(joining(formatJoinSymbol));
    }

    private void sortWords(List<Word> wordList) {
        wordList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
    }

    private List<Word> caculateWordsCount(List<String> wordStrList) {

        return wordStrList.stream()
                .collect(Collectors.groupingBy(wordStr -> wordStr))
                .entrySet().stream()
                .map(entry -> new Word(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private List<Word> buildWordList(String[] wordStrList) {
        return Arrays.stream(wordStrList).map(s -> new Word(s, 1)).collect(Collectors.toList());
    }

}
