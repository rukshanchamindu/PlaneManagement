public class Ticket {
    private String row;
    private int seat;
    private Double price;
    private Person person;
    // Constructor
    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getters and Setters
    public String  getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // Method to print ticket information
    public void printTicketInfo() {
        System.out.println("Ticket Information:");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
        System.out.println("Person Information:");
        person.printPersonInfo();
    }


    // Example usage
    public static void main(String[] args) {
        Person person = new Person("Ruki", "White", "ruki.white@example.com");
        Ticket ticket = new Ticket("A", 12, 50.0, person);
        ticket.printTicketInfo();
    }
}
