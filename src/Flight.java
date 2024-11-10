public class Flight {
    private String flightNumber;
    private String startingCity;
    private String destinationCity;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int seatsSold;

    public Flight(String flightNumber, String startingCity, String destinationCity, String departureTime, String arrivalTime, int totalSeats) {
        this.flightNumber = flightNumber;
        this.startingCity = startingCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.seatsSold = 0;
    }

    public boolean hasAvailableSeats() {
        return seatsSold < totalSeats;
    }

    public void reserveSeat() {
        if (hasAvailableSeats()) {
            seatsSold++;
        } else {
            throw new RuntimeException("All seats are sold out");
        }
    }
}