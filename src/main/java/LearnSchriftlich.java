import java.util.Scanner;

public class LearnSchriftlich extends LearnBasis{
    public LearnSchriftlich(Lernset lernset){
        super(lernset);
    }

    public boolean schriftlich(Card card){
        System.out.println("----------------------------");
        System.out.println("--------Schriftlich---------");
        System.out.println("----------------------------\n");

        System.out.println("Begriff: " + card.begriff);
        System.out.print("Geben sie die Definition ein: ");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input == card.definition;
    }
}
