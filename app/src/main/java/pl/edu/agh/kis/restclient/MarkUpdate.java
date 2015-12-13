package pl.edu.agh.kis.restclient;

/**
 * Created by wukat on 13.12.15.
 */
public class MarkUpdate {

    private float mark;

    public MarkUpdate(float mark) {
        this.mark = mark;
    }

    public float getMark() {

        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
}
