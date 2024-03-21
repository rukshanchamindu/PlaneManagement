import java.util.Scanner;

public class PlaneManagement {
    private static int[][] SeatsArray = new int[4][]; // creating array for store seat status
    private static Ticket[][] Sold_Tickets = new Ticket[4][14]; // creating array for store ticket object
    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application\n");
        gen_seat_list();
        show_menu();
    }

    private static void gen_seat_list(){ //changing seat array colum length as need

        SeatsArray[0] = new int[14];
        SeatsArray[1] = new int[12];
        SeatsArray[2] = new int[12];
        SeatsArray[3] = new int[14];

    }

    private static void show_menu(){ //menu for get options
        System.out.print("""
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
                Please select an option:\s""");

        int choice = get_choice();

        switch(choice){
            case 0:
                System.exit(0);
                break;
            case 1:
                reserve_seat(true);
                break;
            case 2:
                reserve_seat(false);
                break;
            case 3:
                first_available_seat();
                break;
            case 4:
                show_seating_plan();
                break;
            case 5:
                print_tickets_info();
                break;
            case 6:
                search_ticket();
                break;
            default:
                System.out.print("Invalid Option ! , Please select an option: ");
                show_menu();
        }
    }

    private static int get_choice(){  //get input for selection and make sure selected option is not in
        int choice = 0;
        Scanner input = new Scanner(System.in);
        try {
            choice = input.nextInt();
            if(choice < 0 || choice > 6){
                System.out.print("Invalid Option ! , Please select an option: ");
                return get_choice();
            }
        }
        catch(Exception e) {
            System.out.print("Invalid Input ! , Please select an option: ");
            return get_choice();
        }

        return choice;
    }

    private static void reserve_seat(boolean purchase) { //getting input for cancel or buy seat and calling buy or sell method after that
        Scanner input = new Scanner(System.in);
        String row;
        int row_number = 0;
        int seat_number = 0;
        try {
            System.out.print("Enter Row Letter (A,B,C,D): ");
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
                    System.out.print("Invalid Row Letter ! ");
                    reserve_seat(purchase);
                    break;
                
            }
        } catch (Exception e) {
            System.out.print("Invalid Row Letter ! ");
            reserve_seat(purchase);
        }
        if (purchase) {
            buy_seat(row_number, seat_number - 1);
        }else{
            cancel_seat(row_number, seat_number - 1);
        }
    }

    private static int get_seat_number(int max_seat) { //getting correct seat number according to row
        Scanner input = new Scanner(System.in);
        int seat_number;
        try {
            System.out.print("Enter Seat Number (0 - "+ max_seat +"): ");
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

    private static String get_input(String TextToPrint) { //show arg text and get string input
        Scanner input = new Scanner(System.in);
        String text;
        try {
            System.out.print(TextToPrint+" : ");
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

    private static double get_seat_price(int seat) { //return seat price
        if (seat <= 5){
            return 200;
        } else if (seat <= 9) {
            return 150;
        } else if (seat <= 15) {
            return 180;
        } else{
            System.out.println("Invalid Seat Number ("+ seat+") ! ");
            return 0;
        }
    }

    private static void buy_seat(int row, int seatNumber){ //get user info and store them for feature use
        if (SeatsArray[row][seatNumber] == 0){
            String name = get_input("Enter Your First Name");
            String surname = get_input("Enter Your Last Name");
            String email = get_input("Enter Your Email");
            Person person = new Person(name, surname, email);
            Ticket ticket = new Ticket(seat_number_to_letter(row), (seatNumber+1), get_seat_price(seatNumber+1), person);
            SeatsArray[row][seatNumber] = 1;
            Sold_Tickets[row][seatNumber] = ticket;
            ticket.save();

            System.out.println("\nTicket Information:");
            System.out.println("Seat: " + ticket.getRow() + ticket.getSeat());
            System.out.println("Price: £" + ticket.getPrice());
            System.out.println("Person Information:");
            System.out.println("First Name: " + person.getName());
            System.out.println("Last name: " + person.getSurname());
            System.out.println("Email: " + person.getEmail());

            System.out.print("\nSeat " + (seat_number_to_letter(row)) +" "+ (seatNumber + 1) + " has been reserved successfully.\n");
            press_enter_to_continue();
        } else{
            System.out.print("\nSeat " + (seat_number_to_letter(row)) +" "+ (seatNumber + 1) + " is already reserved by someone else.\n");
            press_enter_to_continue();
        }
    }

    private static void cancel_seat(int row, int seatNumber){ //clear stored data
        if (SeatsArray[row][seatNumber] == 1){
            SeatsArray[row][seatNumber] = 0;
            Sold_Tickets[row][seatNumber].delete();
            Sold_Tickets[row][seatNumber] = null;
            System.out.print("\nSeat " + (seat_number_to_letter(row)) +" "+ (seatNumber + 1) + " has been canceled successfully.\n");
            press_enter_to_continue();
        } else{
            System.out.print("\nSeat " + (seat_number_to_letter(row)) +" "+ (seatNumber + 1) + " is not reserved yet.\n");
            press_enter_to_continue();
        }
    }

    public static String seat_number_to_letter(int seat) {//convert row index to letter
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

    private static void first_available_seat(){//return 1st available for buy
        for (int row = 0; row < SeatsArray.length; row++) {
            int seatsInRow = SeatsArray[row].length;
            for (int seatNumber = 0; seatNumber < seatsInRow; seatNumber++) {
                if (SeatsArray[row][seatNumber] == 0) {
                    System.out.println("\nFirst Available Seat " + (seat_number_to_letter(row)) + "-" + (seatNumber + 1) + " \n");
                    press_enter_to_continue();
                    return;
                }
            }
        }
    }
    private static void show_seating_plan(){//create ui for showcase sets
        System.out.print("\n");
        for (int row = 0; row < SeatsArray.length; row++) {
            int seatsInRow = SeatsArray[row].length;
            for (int seatNumber = 0; seatNumber < seatsInRow; seatNumber++) {
                if (SeatsArray[row][seatNumber] == 0){
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        press_enter_to_continue();
    }

    public static void print_tickets_info() {//print all purchased tickets and print total
        double total = 0;
        for (int row = 0; row < SeatsArray.length; row++) {
            int seatsInRow = SeatsArray[row].length;
            for (int seatNumber = 0; seatNumber < seatsInRow; seatNumber++) {
                if (SeatsArray[row][seatNumber] == 1) {
                    double ticket_price = Sold_Tickets[row][seatNumber].getPrice();
                    total += ticket_price;
                    System.out.println(seat_number_to_letter(row) + (seatNumber+1) + " = £ " + ticket_price);
                }
            }
        }
        if (total == 0){
            System.out.print("There is no ticket reserved yet.");
        } else {
            System.out.println("Total Sales: £" + total);
        }
        press_enter_to_continue();
    }

    private static void search_ticket() { //print user selected seat info if  it is reserved
        Scanner input = new Scanner(System.in);
        String row;
        int row_number = 0;
        int seat_number = 0;
        try {
            System.out.print("Enter Row Letter (A,B,C,D): ");
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
        press_enter_to_continue();
    }

    public static void press_enter_to_continue() { //get input before showing main menu
        Scanner input = new Scanner(System.in);
        System.out.print("\nPress Enter To Continue ! ");
        input.nextLine();
        System.out.println();
        show_menu();
    }
}

