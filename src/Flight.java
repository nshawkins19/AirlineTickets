public class Flight {
    private String flightNumber;
    private String startCity;
    private String endCity;
    private String departureTime;
    private String arrivalTime;
    private int economySeats;
    private int businessSeats;
    private int availableEconomySeats;
    private int availableBusinessSeats;

    public Flight(String flightNumber, String startCity, String endCity,
                  String departureTime, String arrivalTime, int economySeats, int businessSeats) {
        this.flightNumber = flightNumber;
        this.startCity = startCity;
        this.endCity = endCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.economySeats = economySeats;
        this.businessSeats = businessSeats;
        this.availableEconomySeats = economySeats;
        this.availableBusinessSeats = businessSeats;
    }

    public boolean reserveEconomySeat() throws RuntimeException {
        if (availableEconomySeats > 0) {
            availableEconomySeats--;
            return true;
        }
        throw new RuntimeException("No economy seats available");
    }

    public boolean reserveBusinessSeat() throws RuntimeException {
        if (availableBusinessSeats > 0) {
            availableBusinessSeats--;
            return true;
        }
        throw new RuntimeException("No business seats available");
    }

    public void resetSeats() {
        this.availableEconomySeats = this.economySeats;
        this.availableBusinessSeats = this.businessSeats;
    }

    // Getters for available seats
    public int getAvailableEconomySeats() { return availableEconomySeats; }
    public int getAvailableBusinessSeats() { return availableBusinessSeats; }
    public int getTotalAvailableSeats() { return availableEconomySeats + availableBusinessSeats; }

    // Other getters
    public String getFlightNumber() { return flightNumber; }
    public String getStartCity() { return startCity; }
    public String getEndCity() { return endCity; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
}