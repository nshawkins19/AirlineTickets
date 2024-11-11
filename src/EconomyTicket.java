public class EconomyTicket extends Ticket {
    public EconomyTicket(String customerName, String startingCity, String destinationCity,
                         String flightNumber, String departureDate, String departureTime) {
        super(customerName, startingCity, destinationCity, flightNumber, departureDate, departureTime);
        this.price = 300.0;
    }

    @Override
    public String reserveTicket() {
        return "Economy Ticket reserved for " + customerName;
    }
}