public class BusinessTicket extends Ticket {
    private String seatPreference; // Window or Aisle
    private String snack; // Cookies or Peanuts

    public BusinessTicket(String customerName, String startingCity, String destinationCity,
                          String flightNumber, String departureDate, String departureTime) {
        super(customerName, startingCity, destinationCity, flightNumber, departureDate, departureTime);
        this.price = 600.0;
    }

    public void setSeatPreference(String seatPreference) {
        this.seatPreference = seatPreference;
    }

    public void setSnack(String snack) {
        this.snack = snack;
    }

    public String reserveTicket(String seatPreference, String snack) {
        this.seatPreference = seatPreference;
        this.snack = snack;
        return "Business Ticket reserved for " + customerName + " with " + seatPreference + " seat and " + snack;
    }

    @Override
    public String reserveTicket() {
        String details = "Business Ticket reserved for " + customerName;
        if (seatPreference != null) {
            details += " with " + seatPreference + " seat";
        }
        if (snack != null) {
            details += " and " + snack;
        }
        return details;
    }
}