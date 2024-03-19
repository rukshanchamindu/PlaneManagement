import java.util.Scanner;

public class w2052769_PlaneManagement {
    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application\n");
        gen_seat_list();
        show_menu();
    }
    private static int[][] SeatsArray = new int[4][14]; // Static field (acts like a global array)
    private static Ticket[][] Sold_Tickets = new Ticket[4][14]; // Static field (acts like a global array)
    private static void gen_seat_list(){

        for (int col = 0; col < 14; col++) {
            SeatsArray[0][col] = 0;
        }
        for (int col = 0; col < 12; col++) {
            SeatsArray[1][col] = 0;
        }
        for (int col = 0; col < 12; col++) {
            SeatsArray[2][col] = 0;
        }
        for (int col = 0; col < 14; col++) {
            SeatsArray[3][col] = 0;
        }
    }

    private static void show_menu(){
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

        int choice = get_choice();

        switch(choice){
            case 0:
                System.exit(0);
                break;
            case 1:
                System.out.println(choice);
                get_row(true);
                break;
            case 2:
                System.out.println(choice);
                get_row(false);
                break;
            case 3:
                System.out.println(choice);
                first_available_seat();
                break;
            case 4:
                System.out.println(choice);
                show_seating_plan();
                break;
            case 5:
                System.out.println(choice);
                break;
            case 6:
                System.out.println(choice);
                break;
            default:
                System.out.println("Invalid Option ! , Please select an option:");
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

    private static void get_row(boolean ispurchase) {

        Scanner input = new Scanner(System.in);
        String row;
        int row_number = 0;
        int seat_number = 0;
        try {
            System.out.println("Enter Row Letter (A,B,C,D): ");
            row = input.nextLine();
            switch (row.toUpperCase()) {
                case "A":
                    seat_number = get_seat_number(14);
                    row_number = 0;
                    break;
                case "B":
                    seat_number = get_seat_number(12);
                    row_number = 1;
                    break;
                case "C":
                    seat_number = get_seat_number(12);
                    row_number = 2;
                    break;
                case "D":
                    seat_number = get_seat_number(14);
                    row_number = 3;
                    break;
                default:
                    System.out.println("Invalid Row Letter ! ");
                    get_row(ispurchase);
                    break;
                
            }
        } catch (Exception e) {
            System.out.println("Invalid Row Letter ! ");
            get_row(ispurchase);
        }
        if (ispurchase) {
            buy_seat(row_number, seat_number - 1);
        }else{
            cancel_seat(row_number, seat_number - 1);
        }
    }

    private static int get_seat_number(int max_seat) {
        Scanner input = new Scanner(System.in);
        int seat_number;
        try {
            System.out.println("Enter Seat Number (0 - "+ max_seat +"): ");
            seat_number = input.nextInt();
            if (seat_number < 0 || seat_number > max_seat){
                System.out.println("Invalid Seat Number ! ");
               return  get_seat_number(max_seat);
            }
        } catch (Exception e) {
            System.out.println("Invalid Seat Number ! ");
            return  get_seat_number(max_seat);
        }
        return seat_number;
    }

    private static String get_input(String TextToPrint) {
        Scanner input = new Scanner(System.in);
        String text;
        try {
            System.out.println(TextToPrint+" : ");
            text = input.nextLine();
            return text;
        } catch (Exception e) {
            System.out.println("Invalid Input !");
            return  get_input(TextToPrint);
        }
    }

    private static double get_seat_price(int seat) {
        if (seat <= 5){
            return 200;
        } else if (seat <= 9) {
            return 150;
        } else if (seat <= 15) {
            return 100;
        } else{
            System.out.println("Invalid Seat Number ! "+ seat);
            return 0;
        }
    }

    private static void buy_seat(int row, int seatNumber){
        if (SeatsArray[row][seatNumber] == 0){
            String name = get_input("Enter Your First Name");
            String surname = get_input("Enter Your Last Name");
            String email = get_input("Enter Your Email");
            Person person = new Person(name, surname, email);
            Ticket ticket = new Ticket(row, seatNumber, get_seat_price(seatNumber), person);
            SeatsArray[row][seatNumber] = 1;
            Sold_Tickets[row][seatNumber] = ticket;
            System.out.println("\nSeat " + (seat_number_to_letter(row)) +" "+ (seatNumber + 1) + " has been purchased successfully.\n");
            show_menu();
        } else{
            System.out.println("\nSeat " + (seat_number_to_letter(row)) +" "+ (seatNumber + 1) + " is already purchased.\n");
            show_menu();
        }
    }


    private static void cancel_seat(int row, int seatNumber){
        if (SeatsArray[row][seatNumber] == 1){
            SeatsArray[row][seatNumber] = 0;
            System.out.println("\nSeat " + (seat_number_to_letter(row)) +" "+ (seatNumber + 1) + " has been canceled successfully.\n");
            show_menu();
        } else{
            System.out.println("\nSeat " + (seat_number_to_letter(row)) +" "+ (seatNumber + 1) + " is not canceled.\n");
            show_menu();
        }
    }

    private static String seat_number_to_letter(int seat) {
        switch (seat) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            default:
                return "Invalid Row Letter !";
        }
    }

    private static void first_available_seat(){
        for (int seatNumber = 0; seatNumber < 14; seatNumber++) {
            if (SeatsArray[0][seatNumber] == 0){
                System.out.println("\nFirst Available Seat " + (seat_number_to_letter(0)) +"-"+ (seatNumber + 1) + " \n");
                show_menu();
                return;
            }
        }
        for (int seatNumber = 0; seatNumber < 12; seatNumber++) {
            if (SeatsArray[1][seatNumber] == 0){
                System.out.println("\nFirst Available Seat " + (seat_number_to_letter(1)) +"-"+ (seatNumber + 1) + " \n");
                show_menu();
                return;
            }
        }
        for (int seatNumber = 0; seatNumber < 12; seatNumber++) {
            if (SeatsArray[2][seatNumber] == 0){
                System.out.println("\nFirst Available Seat " + (seat_number_to_letter(2)) +"-"+ (seatNumber + 1) + " \n");
                show_menu();
                return;
            }
        }
        for (int seatNumber = 0; seatNumber < 14; seatNumber++) {
            if (SeatsArray[3][seatNumber] == 0){
                System.out.println("\nFirst Available Seat " + (seat_number_to_letter(3)) +"-"+ (seatNumber + 1) + " \n");
                show_menu();
                return;
            }
        }
    }
    private static void show_seating_plan(){
        System.out.print("\n");
        for (int seatNumber = 0; seatNumber < 14; seatNumber++) {
            if (SeatsArray[0][seatNumber] == 0){
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.print("\n");
        for (int seatNumber = 0; seatNumber < 12; seatNumber++) {
            if (SeatsArray[1][seatNumber] == 0){
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.print("\n");
        for (int seatNumber = 0; seatNumber < 12; seatNumber++) {
            if (SeatsArray[2][seatNumber] == 0){
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.print("\n");
        for (int seatNumber = 0; seatNumber < 14; seatNumber++) {
            if (SeatsArray[3][seatNumber] == 0){
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.print("\n");
        show_menu();
    }


}

