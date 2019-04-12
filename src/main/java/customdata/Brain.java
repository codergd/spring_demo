package customdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Brain {

    private int wave;

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    @Override
    public String toString(){
        return "["+
                "wave: "+ wave +
                "]";
    }
}
