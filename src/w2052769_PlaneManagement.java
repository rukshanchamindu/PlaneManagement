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
        System.out.println("""
                **********************************************
                *                MENU OPTIONS                *
                **********************************************
                1) Buy a seat
                2) Cancel a seat
                3) Find first available seat
                4) Show seating plan
                5) Print tickets information and total sales
                6) Search ticket
                0) Quit
                **********************************************
                Please select an option:""");

        int choice = get_choice();

        switch(choice){
            case 0:
                System.exit(0);
                break;
            case 1:
                System.out.println(choice);
                reserve_seat(true);
                break;
            case 2:
                System.out.println(choice);
                reserve_seat(false);
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
                print_tickets_info();
                break;
            case 6:
                System.out.println(choice);
                search_ticket();
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
            if(choice < 0 || choice > 6){
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

    private static void reserve_seat(boolean purchase) {

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
                    reserve_seat(purchase);
                    break;
                
            }
        } catch (Exception e) {
            System.out.println("Invalid Row Letter ! ");
            reserve_seat(purchase);
        }
        if (purchase) {
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
            if (text.isEmpty()) {
                System.out.println("Invalid Input !");
                return  get_input(TextToPrint);
            }
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
            return 180;
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
            Ticket ticket = new Ticket(seat_number_to_letter(row), (seatNumber+1), get_seat_price(seatNumber), person);
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
            Sold_Tickets[row][seatNumber] = null;
            System.out.println("\nSeat " + (seat_number_to_letter(row)) +" "+ (seatNumber + 1) + " has been canceled successfully.\n");
            show_menu();
        } else{
            System.out.println("\nSeat " + (seat_number_to_letter(row)) +" "+ (seatNumber + 1) + " is not canceled.\n");
            show_menu();
        }
    }

    public static String seat_number_to_letter(int seat) {
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
        for (int row = 0; row < SeatsArray.length; row++) {
            int seatsInRow = SeatsArray[row].length;
            for (int seatNumber = 0; seatNumber < seatsInRow; seatNumber++) {
                if (SeatsArray[row][seatNumber] == 0) {
                    System.out.println("\nFirst Available Seat " + (seat_number_to_letter(row)) + "-" + (seatNumber + 1) + " \n");
                    show_menu();
                    return;
                }
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

    public static void print_tickets_info() {
        double total = 0;
        String total_text = "";
        for (int row = 0; row < SeatsArray.length; row++) {
            int seatsInRow = SeatsArray[row].length;
            for (int seatNumber = 0; seatNumber < seatsInRow; seatNumber++) {
                if (SeatsArray[row][seatNumber] == 1) {
                    if (!total_text.isEmpty()){
                        total_text = total_text + " + ";
                    }
                    double ticket_price = Sold_Tickets[row][seatNumber].getPrice();
                    total += ticket_price;
                    total_text = total_text + seat_number_to_letter(row) + (seatNumber+1) + " = £" + ticket_price;
                }
            }
        }
        if (total_text.isEmpty()){
            System.out.println("There is no ticket reserved yet.");
        } else {
            System.out.println("£" + total + " ("+ total_text + ")");
        }
        show_menu();
    }

    private static void search_ticket() {
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
                    search_ticket();
                    break;

            }
        } catch (Exception e) {
            System.out.println("Invalid Row Letter ! ");
            search_ticket();
        }
        if (Sold_Tickets[row_number][seat_number-1] != null){
            Sold_Tickets[row_number][seat_number-1].printTicketInfo();

        } else {
            System.out.println("Seat " + (seat_number_to_letter(row_number)) +" "+ (seat_number) + " is not reserved yet.\n");
        }
        show_menu();
    }
}

