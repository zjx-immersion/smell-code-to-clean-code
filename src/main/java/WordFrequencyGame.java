import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String play(String inputStr) {

        String[] wordStrList = inputStr.split("\\s+");

        List<Word> wordList = buildWordList(wordStrList);

        wordList = caculateWordsCount(wordList);

        sortWords(wordList);

        String wordsReport = formatWordsOutput(wordList).toString();

        return wordsReport;

    }

    private void sortWords(List<Word> wordList) {
        wordList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
    }

    private StringJoiner formatWordsOutput(List<Word> wordList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Word w : wordList) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner;
    }

    private List<Word> caculateWordsCount(List<Word> wordList) {

        return wordList.stream()
                .collect(Collectors.groupingBy(Word::getValue))
                .entrySet().stream()
                .map(entry -> new Word(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private List<Word> buildWordList(String[] wordStrList) {
        List<Word> wordList = new ArrayList<>();
        for (String s : wordStrList) {
            Word word = new Word(s, 1);
            wordList.add(word);
        }
        return wordList;
    }

}
