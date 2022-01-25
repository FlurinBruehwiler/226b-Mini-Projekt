public class MultipleChoice extends Pool{
    @Override
    public Pool NextPool(boolean result) {
        return result ? new Schriftlich() : new MultipleChoice();
    }
}
