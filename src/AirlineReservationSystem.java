import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AirlineReservationSystem extends JFrame implements ActionListener {
    // GUI Components
    private JTextField customerNameField;
    private JComboBox<String> startingCityBox, destinationCityBox, flightNumberBox, classBox, seatPreferenceBox, snackBox;
    private JComboBox<String> monthBox, dayBox, yearBox, departureTimeBox; // Separate boxes for Month, Day, and Year
    private JButton reserveButton;
    private JLabel statusLabel, seatsRemainingLabel;

    // Flights
    private Flight[] greensboroToNewarkFlights;  // Flights from GSO to EWR
    private Flight[] newarkToGreensboroFlights;  // Flights from EWR to GSO

    //Seat counter
    private int reservationCount = 0;

    public AirlineReservationSystem() {


        // Initialize flights with constructor  , end int is number of seats
        initializeFlights();


        // Set up GUI
        setTitle("Greensboro Airlines Reservation System");
        setSize(600, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setBounds(20, 20, 120, 25);
        add(customerNameLabel);
        customerNameField = new JTextField();
        customerNameField.setBounds(150, 20, 200, 25);
        add(customerNameField);

        JLabel startingCityLabel = new JLabel("Starting City:");
        startingCityLabel.setBounds(20, 60, 120, 25);
        add(startingCityLabel);
        startingCityBox = new JComboBox<>(new String[]{"Greensboro", "Newark"});
        startingCityBox.setBounds(150, 60, 200, 25);
        add(startingCityBox);

        JLabel destinationCityLabel = new JLabel("Destination:");
        destinationCityLabel.setBounds(20, 100, 120, 25);
        add(destinationCityLabel);
        destinationCityBox = new JComboBox<>(new String[]{"Greensboro", "Newark"});
        destinationCityBox.setBounds(150, 100, 200, 25);
        add(destinationCityBox);

        JLabel flightNumberLabel = new JLabel("Flight Number:");
        flightNumberLabel.setBounds(20, 140, 120, 25);
        add(flightNumberLabel);
        flightNumberBox = new JComboBox<>(new String[]{"GOS 1", "GOS 2"});
        flightNumberBox.setBounds(150, 140, 200, 25);
        add(flightNumberBox);

        // Separate dropdowns for Month, Day, and Year
        JLabel departureDateLabel = new JLabel("Departure Date:");
        departureDateLabel.setBounds(20, 180, 120, 25);
        add(departureDateLabel);

        // Month dropdown (01 to 12)
        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setBounds(20, 210, 50, 25);
        add(monthLabel);
        monthBox = new JComboBox<>(generateMonths());
        monthBox.setBounds(90, 210, 70, 25);  // Adjusted position
        add(monthBox);

        // Day dropdown (01 to 31)
        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setBounds(160, 210, 40, 25);
        add(dayLabel);
        dayBox = new JComboBox<>(generateDays());
        dayBox.setBounds(200, 210,70, 25);  // Adjusted position
        add(dayBox);

        // Year dropdown (2024 to 2026)
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(270, 210, 40, 25);
        add(yearLabel);
        yearBox = new JComboBox<>(generateYears());
        yearBox.setBounds(310, 210, 100, 25);  // Adjusted position
        add(yearBox);

        // Dropdown for Times
        JLabel departureTimeLabel = new JLabel("Departure Time:");
        departureTimeLabel.setBounds(20, 250, 120, 25);
        add(departureTimeLabel);
        departureTimeBox = new JComboBox<>(new String[]{
                "5:00 AM", "7:00 AM", "9:00 AM", "4:00 PM", "5:30 PM", "7:00 PM"
        });
        departureTimeBox.setBounds(150, 250, 200, 25);
        add(departureTimeBox);

        JLabel classLabel = new JLabel("Class:");
        classLabel.setBounds(20, 290, 120, 25);
        add(classLabel);
        classBox = new JComboBox<>(new String[]{"Economy", "Business"});
        classBox.setBounds(150, 290, 200, 25);
        classBox.addActionListener(this);
        add(classBox);

        JLabel seatPreferenceLabel = new JLabel("Seat Preference:");
        seatPreferenceLabel.setBounds(20, 320, 120, 25);
        add(seatPreferenceLabel);
        seatPreferenceBox = new JComboBox<>(new String[]{"Window", "Aisle"});
        seatPreferenceBox.setBounds(150, 320, 200, 25);
        add(seatPreferenceBox);

        JLabel snackLabel = new JLabel("Snack:");
        snackLabel.setBounds(20, 350, 120, 25);
        add(snackLabel);
        snackBox = new JComboBox<>(new String[]{"Cookies", "Peanuts"});
        snackBox.setBounds(150, 350, 200, 25);
        add(snackBox);

        seatsRemainingLabel = new JLabel("Seats Available: " + getCurrentFlight().getTotalAvailableSeats());
        seatsRemainingLabel.setBounds(20, 500, 300, 25);
        add(seatsRemainingLabel);

        reserveButton = new JButton("Reserve Ticket");
        reserveButton.setBounds(20, 380, 330, 25);
        reserveButton.addActionListener(this);
        add(reserveButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(20, 410, 350, 100);
        add(statusLabel);

        // Set visibility for seat preference and snack options based on class selection
        seatPreferenceBox.setVisible(false);
        snackBox.setVisible(false);

        // Update seat availability when user changes flight, starting city, or destination city
        startingCityBox.addActionListener(this);
        destinationCityBox.addActionListener(this);
        flightNumberBox.addActionListener(this);

        setVisible(true);
    }

    private void initializeFlights() {
        // Initialize arrays for both directions
        greensboroToNewarkFlights = new Flight[6];
        newarkToGreensboroFlights = new Flight[6];

        // Create flights from Greensboro to Newark
        greensboroToNewarkFlights[0] = new Flight("GSO-EWR-500", "Greensboro", "Newark",
                "5:00 AM", "7:00 AM", 2, 1);
        greensboroToNewarkFlights[1] = new Flight("GSO-EWR-700", "Greensboro", "Newark",
                "7:00 AM", "9:00 AM", 2, 1);
        greensboroToNewarkFlights[2] = new Flight("GSO-EWR-900", "Greensboro", "Newark",
                "9:00 AM", "11:00 AM", 2, 1);
        greensboroToNewarkFlights[3] = new Flight("GSO-EWR-1600", "Greensboro", "Newark",
                "4:00 PM", "6:00 PM", 2, 1);
        greensboroToNewarkFlights[4] = new Flight("GSO-EWR-1730", "Greensboro", "Newark",
                "5:30 PM", "7:30 PM", 2, 1);
        greensboroToNewarkFlights[5] = new Flight("GSO-EWR-1900", "Greensboro", "Newark",
                "7:00 PM", "9:00 PM", 2, 1);

        // Create flights from Newark to Greensboro
        newarkToGreensboroFlights[0] = new Flight("EWR-GSO-500", "Newark", "Greensboro",
                "5:00 AM", "7:00 AM", 2, 1);
        newarkToGreensboroFlights[1] = new Flight("EWR-GSO-700", "Newark", "Greensboro",
                "7:00 AM", "9:00 AM", 2, 1);
        newarkToGreensboroFlights[2] = new Flight("EWR-GSO-900", "Newark", "Greensboro",
                "9:00 AM", "11:00 AM", 2, 1);
        newarkToGreensboroFlights[3] = new Flight("EWR-GSO-1600", "Newark", "Greensboro",
                "4:00 PM", "6:00 PM", 2, 1);
        newarkToGreensboroFlights[4] = new Flight("EWR-GSO-1730", "Newark", "Greensboro",
                "5:30 PM", "7:30 PM", 2, 1);
        newarkToGreensboroFlights[5] = new Flight("EWR-GSO-1900", "Newark", "Greensboro",
                "7:00 PM", "9:00 PM", 2, 1);
    }

    // Modified method to get the current flight based on selected cities and time
    private Flight getCurrentFlight() {
        String startCity = (String) startingCityBox.getSelectedItem();
        String destCity = (String) destinationCityBox.getSelectedItem();
        String departureTime = (String) departureTimeBox.getSelectedItem();

        Flight[] flights = startCity.equals("Greensboro") ? greensboroToNewarkFlights : newarkToGreensboroFlights;

        for (Flight flight : flights) {
            if (flight.getDepartureTime().equals(departureTime)) {
                return flight;
            }
        }
        return null;
    }

    // Modified findAlternativeFlight method to check all available times
    private String[] findAlternativeFlights(String startCity, String destCity, String ticketClass) {
        Flight[] flights = startCity.equals("Greensboro") ? greensboroToNewarkFlights : newarkToGreensboroFlights;
        List<String> alternatives = new ArrayList<>();

        for (Flight flight : flights) {
            if (ticketClass.equals("Economy") && flight.getAvailableEconomySeats() > 0) {
                alternatives.add(flight.getDepartureTime());
            } else if (ticketClass.equals("Business") && flight.getAvailableBusinessSeats() > 0) {
                alternatives.add(flight.getDepartureTime());
            }
        }

        return alternatives.isEmpty() ? null : alternatives.toArray(new String[0]);
    }

    // Method to generate months (01 to 12)
    private String[] generateMonths() {
        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = String.format("%02d", i + 1);
        }
        return months;
    }

    // Method to generate days (01 to 31)
    private String[] generateDays() {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        return days;
    }

    // Method to generate years (2024 to 2026)
    private String[] generateYears() {
        return new String[]{"2024", "2025", "2026"};
    }

    private void updateSeatsDisplay(Flight flight) {
        if (flight != null) {
            String selectedClass = (String) classBox.getSelectedItem();
            if (selectedClass.equals("Business")) {
                seatsRemainingLabel.setText("Business Class Seats Available: " + flight.getAvailableBusinessSeats()
                        + " (Economy: " + flight.getAvailableEconomySeats() + ")");
            } else {
                seatsRemainingLabel.setText("Economy Class Seats Available: " + flight.getAvailableEconomySeats()
                        + " (Business: " + flight.getAvailableBusinessSeats() + ")");
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == classBox) {
            String selectedClass = (String) classBox.getSelectedItem();
            boolean isBusiness = selectedClass.equals("Business");
            seatPreferenceBox.setVisible(isBusiness);
            snackBox.setVisible(isBusiness);

            // Update seat display when class changes
            String selectedFlight = (String) flightNumberBox.getSelectedItem();
            Flight currentFlight = selectedFlight.equals("GOS 1") ? getCurrentFlight() : getCurrentFlight();
            updateSeatsDisplay(currentFlight);

        } else if (e.getSource() == reserveButton) {
            handleReservation();
        } else if (e.getSource() instanceof JComboBox<?>) {
            // Only handle route changes for relevant dropdowns
            if (e.getSource() == startingCityBox || e.getSource() == destinationCityBox || e.getSource() == flightNumberBox) {
                String selectedFlight = (String) flightNumberBox.getSelectedItem();
                Flight currentFlight = selectedFlight.equals("GOS 1") ? getCurrentFlight() : getCurrentFlight();
                String startCity = (String) startingCityBox.getSelectedItem();
                String destCity = (String) destinationCityBox.getSelectedItem();

                if (isValidRoute(selectedFlight, startCity, destCity)) {
                    statusLabel.setText(""); // Clear any previous error messages
                    updateSeatsDisplay(currentFlight);
                } else {
                    statusLabel.setText("<html>Invalid route. Available routes:<br>GOS 1: Greensboro → Newark<br>GOS 2: Newark → Greensboro</html>");
                }
            }
        }
    }

    private boolean isValidRoute(String flightNumber, String startCity, String destCity) {
        if (flightNumber.equals("GOS 1")) {
            return startCity.equals("Greensboro") && destCity.equals("Newark");
        } else if (flightNumber.equals("GOS 2")) {
            return startCity.equals("Newark") && destCity.equals("Greensboro");
        }
        return false;
    }

    private void handleReservation() {
        String customerName = customerNameField.getText();
        String startingCity = (String) startingCityBox.getSelectedItem();
        String destinationCity = (String) destinationCityBox.getSelectedItem();
        String month = (String) monthBox.getSelectedItem();
        String day = (String) dayBox.getSelectedItem();
        String year = (String) yearBox.getSelectedItem();
        String departureTime = (String) departureTimeBox.getSelectedItem();
        String ticketClass = (String) classBox.getSelectedItem();

        if (customerName.isEmpty()) {
            statusLabel.setText("Please fill in all required fields.");
            return;
        }

        Flight flight = getCurrentFlight();
        if (flight == null) {
            statusLabel.setText("Invalid flight selection.");
            return;
        }

        String departureDate = month + "/" + day + "/" + year;

        try {
            Ticket ticket;
            boolean seatReserved = false;

            if (ticketClass.equals("Economy")) {
                if (flight.getAvailableEconomySeats() > 0) {
                    flight.reserveEconomySeat();
                    ticket = new EconomyTicket(customerName, startingCity, destinationCity,
                            flight.getFlightNumber(), departureDate, departureTime);
                    seatReserved = true;
                } else {
                    String[] alternativeTimes = findAlternativeFlights(startingCity, destinationCity, ticketClass);
                    if (alternativeTimes != null && alternativeTimes.length > 0) {
                        StringBuilder message = new StringBuilder("<html>No economy seats available on this flight.<br>");
                        message.append("Alternative flights available at:<br>");
                        for (String time : alternativeTimes) {
                            message.append("- ").append(time).append("<br>");
                        }
                        message.append("Please select a different flight time to book.</html>");
                        throw new RuntimeException(message.toString());
                    } else {
                        throw new RuntimeException("No economy seats available on any flight today. Try business class!");
                    }
                }
            } else {
                // Similar logic for business class...
                if (flight.getAvailableBusinessSeats() > 0) {
                    flight.reserveBusinessSeat();
                    BusinessTicket bTicket = new BusinessTicket(customerName, startingCity, destinationCity,
                            flight.getFlightNumber(), departureDate, departureTime);
                    String seatPref = (String) seatPreferenceBox.getSelectedItem();
                    String snack = (String) snackBox.getSelectedItem();
                    bTicket.setSeatPreference(seatPref);
                    bTicket.setSnack(snack);
                    ticket = bTicket;
                    seatReserved = true;
                } else {
                    String[] alternativeTimes = findAlternativeFlights(startingCity, destinationCity, ticketClass);
                    if (alternativeTimes != null && alternativeTimes.length > 0) {
                        StringBuilder message = new StringBuilder("<html>No business seats available on this flight.<br>");
                        message.append("Alternative flights available at:<br>");
                        for (String time : alternativeTimes) {
                            message.append("- ").append(time).append("<br>");
                        }
                        message.append("Please select a different flight time to book.</html>");
                        throw new RuntimeException(message.toString());
                    } else {
                        throw new RuntimeException("No business seats available on any flight today. Try economy class!");
                    }
                }
            }

            if (seatReserved) {
                String info = ticket.reserveTicket() + "\n"
                        + "Departure Date: " + departureDate + "\n"
                        + "Flight Number: " + flight.getFlightNumber() + "\n"
                        + "Departure Time: " + departureTime + "\n"
                        + "Total Charge: $" + ticket.getPrice();
                statusLabel.setText("<html>" + info.replaceAll("\n", "<br>") + "</html>");
                updateSeatsDisplay(flight);
            }

        } catch (RuntimeException ex) {
            statusLabel.setText(ex.getMessage());
        }
    }
    /**
     * Finds an alternative flight on the same route with available seats
     * @param startCity Starting city
     * @param destCity Destination city
     * @param ticketClass Desired ticket class (Economy or Business)
     * @return Alternative flight time if available, null if no alternatives found
     */
    private String findAlternativeFlight(String startCity, String destCity, String ticketClass) {
        // Check which direction we're going
        boolean isGreensboroToNewark = startCity.equals("Greensboro") && destCity.equals("Newark");
        Flight currentFlight = isGreensboroToNewark ? getCurrentFlight() : getCurrentFlight();

        // If current flight is GOS 1 (7:00 AM), suggest GOS 2 (5:30 PM) and vice versa
        if (isGreensboroToNewark) {
            if (getCurrentFlight().getStartCity().equals(startCity) &&
                    getCurrentFlight().getEndCity().equals(destCity)) {
                if (ticketClass.equals("Economy") && getCurrentFlight().getAvailableEconomySeats() > 0) {
                    return getCurrentFlight().getDepartureTime();
                } else if (ticketClass.equals("Business") && getCurrentFlight().getAvailableBusinessSeats() > 0) {
                    return getCurrentFlight().getDepartureTime();
                }
            }
        } else {
            if (getCurrentFlight().getStartCity().equals(startCity) &&
                    getCurrentFlight().getEndCity().equals(destCity)) {
                if (ticketClass.equals("Economy") && getCurrentFlight().getAvailableEconomySeats() > 0) {
                    return getCurrentFlight().getDepartureTime();
                } else if (ticketClass.equals("Business") && getCurrentFlight().getAvailableBusinessSeats() > 0) {
                    return getCurrentFlight().getDepartureTime();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        AirlineReservationSystem app = new AirlineReservationSystem();
        app.setVisible(true);
    }
}