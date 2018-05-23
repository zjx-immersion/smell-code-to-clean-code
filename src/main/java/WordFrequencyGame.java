import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String play(String inputStr) {

        //split the input string with 1 to n pieces of spaces
        String[] wordStrList = inputStr.split("\\s+");

        List<Input> wordList = buildWordList(wordStrList);
        wordList = caculateWordsCount(wordList);


        wordList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

        StringJoiner joiner = formatWordsOutput(wordList);
        return joiner.toString();

    }

    private StringJoiner formatWordsOutput(List<Input> wordList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input w : wordList) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner;
    }

    private List<Input> caculateWordsCount(List<Input> wordList) {
        //get the map for the next step of sizing the same word
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input1 : wordList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input1.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input1);
                map.put(input1.getValue(), arr);
            } else {
                map.get(input1.getValue()).add(input1);
            }
        }
        Map<String, List<Input>> wordMap = map;

        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : wordMap.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        wordList = list;
        return wordList;
    }

    private List<Input> buildWordList(String[] wordStrList) {
        List<Input> wordList = new ArrayList<>();
        for (String s : wordStrList) {
            Input input = new Input(s, 1);
            wordList.add(input);
        }
        return wordList;
    }

}
