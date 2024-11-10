public class BusinessTicket extends Ticket {
    private String seatPreference; // Window or Aisle
    private String snack; // Cookies or Peanuts

    public BusinessTicket(String customerName, String startingCity, String destinationCity, String flightNumber, String departureDate, String departureTime) {
        super(customerName, startingCity, destinationCity, flightNumber, departureDate, departureTime);
        this.price = 600.0; // Business class costs more
    }

    // Overloading reserveTicket (Static Polymorphism)
    public String reserveTicket(String seatPreference, String snack) {
        this.seatPreference = seatPreference;
        this.snack = snack;
        return "Business Ticket reserved for " + customerName + " with " + seatPreference + " seat and " + snack;
    }

    // Overriding reserveTicket (Dynamic Polymorphism)
    @Override
    public String reserveTicket() {
        return "Business Ticket reserved for " + customerName;
    }


}