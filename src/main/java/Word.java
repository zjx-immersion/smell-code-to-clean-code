/**
 * Created by jxzhong on 2018/5/22.
 */
public class Word {
    private String value;
    private int count;

    public Word(String word, int count) {
        this.value = word;
        this.count = count;
    }

    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.count;
    }
}
