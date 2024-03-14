import java.util.Scanner;

public class w2052769_PlaneManagement {
    public static void main(String[] args) {

        System.out.println("**********************************************\n" +
                "*" +
                "                MENU OPTIONS                " +
                "*\n" +
                "**********************************************\n" +
                "1) Buy a seat\n" +
                "2) Cancel a seat\n" +
                "3) Find first available seat\n" +
                "4) Show seating plan\n" +
                "5) Print tickets information and total sales\n" +
                "6) Search ticket\n" +
                "0) Quit\n" +
                "**********************************************\n" +
                "Please select an option:");
        Scanner input = new Scanner(System.in);
//        int choice = input.nextInt();
        int choice = get_choice();

        switch(choice){
            case 0:
                System.exit(0);
                break;
            case 1:
                System.out.println(choice);
                break;
            case 2:
                System.out.println(choice);
                break;
            case 3:
                System.out.println(choice);
                break;
            case 4:
                System.out.println(choice);
                break;
            case 5:
                System.out.println(choice);
                break;
            case 6:
                System.out.println(choice);
                break;
            default:
                System.out.println("Invalid Option1 ! , Please select an option:");
        }
    }

    private static int get_choice(){
        int choice = 0;
        Scanner input = new Scanner(System.in);
        try {
            choice = input.nextInt();
            if(choice < 0 || choice > 5){
                System.out.println("Invalid Option ! , Please select an option:");
                return get_choice();
            }
        }
        catch(Exception e) {
            System.out.println("Invalid Input ! , Please select an option:");
            return get_choice();
        }

        return choice;
    }

}