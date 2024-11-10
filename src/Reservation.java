public class Reservation {
    private String customerName;
    private String departureDate;
    private String flightNumber;
    private double totalCharge;

    // Constructor to create a Reservation object
    public Reservation(String customerName, String departureDate, String flightNumber, double totalCharge) {
        this.customerName = customerName;
        this.departureDate = departureDate;
        this.flightNumber = flightNumber;
        this.totalCharge = totalCharge;
    }

    // Method to display reservation details
    public String getReservationDetails() {
        return "Reservation Details:\n" +
                "Customer Name: " + customerName + "\n" +
                "Departure Date: " + departureDate + "\n" +
                "Flight Number: " + flightNumber + "\n" +
                "Total Charge: $" + totalCharge;
    }
}
