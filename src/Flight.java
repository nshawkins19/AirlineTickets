public class Flight {
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;

    public int getbSeat() {
        return bSeat;
    }

    public void setbSeat(int bSeat) {
        this.bSeat = bSeat;
    }

    public int geteSeat() {
        return eSeat;
    }

    public void seteSeat(int eSeat) {
        this.eSeat = eSeat;
    }

    private int bSeat; //business class seats
    private int eSeat; //economy class seats
    private int seatsAvailable;

    public Flight(String flightNumber, String departureCity, String arrivalCity, String departureTime, String arrivalTime, int bSeat, int eSeat) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.bSeat = bSeat;
        this.eSeat = eSeat;
        totalSeats = bSeat +eSeat;
        this.seatsAvailable = totalSeats;
        //totalSeats = 1; test code
    }


    // Reserve a seat
    public void reserveSeat() throws RuntimeException {
        if (seatsAvailable <= 0) {
            throw new RuntimeException("Sorry, no seats available on this flight.");
        }
        seatsAvailable--;
    }

    // Get the number of seats left
    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void resetSeats() {
        // Reset to original capacity
        this.seatsAvailable = this.totalSeats;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getTotalSeats() {
        return bSeat + eSeat;
    }
}