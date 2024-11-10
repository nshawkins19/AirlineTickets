public class Ticket {
    protected String customerName;
    protected String startingCity;
    protected String destinationCity;
    protected String flightNumber;
    protected String departureDate;
    protected String departureTime;
    protected double price;

    // Constructor
    public Ticket(String customerName, String startingCity, String destinationCity, String flightNumber, String departureDate, String departureTime) {
        this.customerName = customerName;
        this.startingCity = startingCity;
        this.destinationCity = destinationCity;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.price = 400.0; // Default price for Economy
    }

    // ReserveTicket method
    public String reserveTicket() {
        return "Ticket reserved for " + customerName;
    }
}