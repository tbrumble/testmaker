package Generator;

public class GenerateResult {
    private boolean result;
    private long time;

    @Override
    public String toString() {
        return String.format("result = %s, spent time = %s", result, time);
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
