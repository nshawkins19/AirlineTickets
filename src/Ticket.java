public abstract class Ticket {
    protected String customerName;
    protected String startingCity;
    protected String destinationCity;
    protected String flightNumber;
    protected String departureDate;
    protected String departureTime;
    protected double price;

    public Ticket(String customerName, String startingCity, String destinationCity,
                  String flightNumber, String departureDate, String departureTime) {
        this.customerName = customerName;
        this.startingCity = startingCity;
        this.destinationCity = destinationCity;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    // Abstract method to be implemented by subclasses
    public abstract String reserveTicket();

    // Getters
    public String getCustomerName() { return customerName; }
    public String getStartingCity() { return startingCity; }
    public String getDestinationCity() { return destinationCity; }
    public String getFlightNumber() { return flightNumber; }
    public String getDepartureDate() { return departureDate; }
    public String getDepartureTime() { return departureTime; }
    public double getPrice() { return price; }




}

