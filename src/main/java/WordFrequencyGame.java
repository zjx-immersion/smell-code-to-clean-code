import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String play(String inputStr) {

        //split the input string with 1 to n pieces of spaces
        String[] wordStrList = inputStr.split("\\s+");

        List<Input> wordList = buildWordList(wordStrList);

        //get the map for the next step of sizing the same word
        Map<String, List<Input>> wordMap = getListMap(wordList);

        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : wordMap.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        wordList = list;

        wordList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

        StringJoiner joiner = new StringJoiner("\n");
        for (Input w : wordList) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();

    }

    private List<Input> buildWordList(String[] wordStrList) {
        List<Input> wordList = new ArrayList<>();
        for (String s : wordStrList) {
            Input input = new Input(s, 1);
            wordList.add(input);
        }
        return wordList;
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
