import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String play(String inputStr) {

        String[] wordStrList = inputStr.split("\\s+");

        List<Word> wordList = buildWordList(wordStrList);
        wordList = caculateWordsCount(wordList);

        sortWords(wordList);

        StringJoiner joiner = formatWordsOutput(wordList);
        return joiner.toString();

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
        Map<String, List<Word>> map = new HashMap<>();
        for (Word word : wordList) {
            map.computeIfAbsent(word.getValue(), k -> new ArrayList<>()).add(word);
        }
        Map<String, List<Word>> wordMap = map;

        List<Word> list = new ArrayList<>();
        for (Map.Entry<String, List<Word>> entry : wordMap.entrySet()) {
            Word word = new Word(entry.getKey(), entry.getValue().size());
            list.add(word);
        }
        wordList = list;
        return wordList;
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
