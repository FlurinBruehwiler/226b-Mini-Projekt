public class LearnMultipleChoice extends LearnBasis{

    Input input;

    public LearnMultipleChoice(Lernset lernset, Input input){
        super(lernset);
        this.input = input;
    }

    /**
     * zeigt multiple Choice Aufgabe an und checkt ob richtig oder falsch.
     * @param card
     * @return ob Antwort richtig oder falsch
     */
    public boolean multipleChoice(Card card){
        System.out.println("----------------------------");
        System.out.println("------Multiple Choice-------");
        System.out.println("----------------------------\n");

        System.out.println("Begriff: " + card.begriff);
        System.out.println("Mögliche Definitionen: ");
        int correctCard = displayCards(card);

        System.out.print("Wähle die richtige Definition aus: ");

        int choice = input.getValidIntegerInput(4);
        return choice == correctCard;
    }
}
