class Word implements Comparable<Word>{
    private String text;
    private int count;
    public Word(String text) {
        this.text = text;
        this.count = 0;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment()
    {
        this.count++;
    }

    @Override
    public int compareTo(Word o) {
        return Integer.compare(o.getCount(), this.count);
    }
}