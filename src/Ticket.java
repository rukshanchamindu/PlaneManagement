import java.io.File;
import java.io.FileWriter;

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

    public void save() {
        String text = "Ticket Information: "+
                "\nRow: " + this.row+
                "\nSeat: " + this.seat+
                "\nPrice: £" + this.price+
                "\n\nPerson Information: "+
                "\nFirst Name: " + this.person.getName()+
                "\nLast name: " + this.person.getSurname()+
                "\nEmail: " + this.person.getEmail();
        String filename = this.row+this.seat+".txt";
        File file = new File(filename);
        if (file.exists()){
//            System.out.println("File already exists.");
            write_ticket_file(text, filename);
        }else{
            try {
                boolean file_created = file.createNewFile();
                if (file_created){
//                    System.out.println("File created: " + file.getName());
                    write_ticket_file(text, filename);
                }
                else{
                    System.out.println("Error while creating file: " + file.getName());
                }
            } catch (Exception e) {
                System.out.println("Failed to create a file "+file.getName()+" :"+e);
            }
        }
    }
    private static void write_ticket_file(String text, String filename) {
        try {
            FileWriter Writer = new FileWriter(filename);
            Writer.write(text);
            Writer.close();
//            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("Failed To write a text file "+filename+" "+e);
        }
    }
    public void delete() {
        String filename = this.row+this.seat+".txt";
        File file = new File(filename);
        if (file.exists()){
            file.delete();
//            System.out.println("File deleted.");
        }
        else{
            System.out.println("File does not exist.");
        }
    }
}
