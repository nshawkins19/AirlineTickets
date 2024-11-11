import javax.swing.*;
import java.awt.event.*;

public class AirlineReservationSystem extends JFrame implements ActionListener {
    // GUI Components
    private JTextField customerNameField;
    private JComboBox<String> startingCityBox, destinationCityBox, flightNumberBox, classBox, seatPreferenceBox, snackBox;
    private JComboBox<String> monthBox, dayBox, yearBox, departureTimeBox; // Separate boxes for Month, Day, and Year
    private JButton reserveButton;
    private JLabel statusLabel, seatsRemainingLabel;

    // Flights
    private Flight gos1, gos2;
    //Seat counter
    private int reservationCount = 0;

    public AirlineReservationSystem() {
        // Initialize flights with constructor  , end int is number of seats
        gos1 = new Flight("GOS 1", "Greensboro", "Newark", "7:00 AM", "9:00 AM", 2, 1); // 2 economy, 1 business
        gos2 = new Flight("GOS 2", "Newark", "Greensboro", "5:30 PM", "7:00 PM", 2, 1); // 2 economy, 1 business
        // Set up GUI
        setTitle("Greensboro Airlines Reservation System");
        setSize(600, 600);
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
        departureTimeBox = new JComboBox<>(new String[]{"5:00 AM", "9:00 AM", "4:00 PM", "7:00 PM"});
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

        seatsRemainingLabel = new JLabel("Seats Available: " + gos1.getTotalAvailableSeats());
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
        String selectedClass = (String) classBox.getSelectedItem();
        if (selectedClass.equals("Business")) {
            seatsRemainingLabel.setText("Business Class Seats Available: " + flight.getAvailableBusinessSeats()
                    + " (Economy: " + flight.getAvailableEconomySeats() + ")");
        } else {
            seatsRemainingLabel.setText("Economy Class Seats Available: " + flight.getAvailableEconomySeats()
                    + " (Business: " + flight.getAvailableBusinessSeats() + ")");
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
            Flight currentFlight = selectedFlight.equals("GOS 1") ? gos1 : gos2;
            updateSeatsDisplay(currentFlight);

        } else if (e.getSource() == reserveButton) {
            handleReservation();
        } else if (e.getSource() instanceof JComboBox<?>) {
            // Only handle route changes for relevant dropdowns
            if (e.getSource() == startingCityBox || e.getSource() == destinationCityBox || e.getSource() == flightNumberBox) {
                String selectedFlight = (String) flightNumberBox.getSelectedItem();
                Flight currentFlight = selectedFlight.equals("GOS 1") ? gos1 : gos2;
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
        String flightNumber = (String) flightNumberBox.getSelectedItem();
        String month = (String) monthBox.getSelectedItem();
        String day = (String) dayBox.getSelectedItem();
        String year = (String) yearBox.getSelectedItem();
        String departureTime = (String) departureTimeBox.getSelectedItem();
        String ticketClass = (String) classBox.getSelectedItem();

        if (customerName.isEmpty()) {
            statusLabel.setText("Please fill in all required fields.");
            return;
        }

        if (!isValidRoute(flightNumber, startingCity, destinationCity)) {
            statusLabel.setText("<html>Invalid route selected. Please check flight routes.</html>");
            return;
        }

        Flight flight = flightNumber.equals("GOS 1") ? gos1 : gos2;
        String departureDate = month + "/" + day + "/" + year;

        try {
            Ticket ticket;
            boolean seatReserved = false;

            if (ticketClass.equals("Economy")) {
                if (flight.getAvailableEconomySeats() > 0) {
                    flight.reserveEconomySeat();
                    ticket = new EconomyTicket(customerName, startingCity, destinationCity,
                            flightNumber, departureDate, departureTime);
                    seatReserved = true;
                } else {
                    throw new RuntimeException("No economy seats available. Try business class!");
                }
            } else {
                if (flight.getAvailableBusinessSeats() > 0) {
                    flight.reserveBusinessSeat();
                    BusinessTicket bTicket = new BusinessTicket(customerName, startingCity, destinationCity,
                            flightNumber, departureDate, departureTime);

                    // Set business class specific options
                    String seatPref = (String) seatPreferenceBox.getSelectedItem();
                    String snack = (String) snackBox.getSelectedItem();
                    bTicket.setSeatPreference(seatPref);
                    bTicket.setSnack(snack);
                    ticket = bTicket;
                    seatReserved = true;
                } else {
                    throw new RuntimeException("No business seats available. Try economy class!");
                }
            }

            if (seatReserved) {
                String info = ticket.reserveTicket() + "\n"
                        + "Departure Date: " + departureDate + "\n"
                        + "Flight Number: " + flightNumber + "\n"
                        + "Total Charge: $" + ticket.getPrice();
                statusLabel.setText("<html>" + info.replaceAll("\n", "<br>") + "</html>");
                updateSeatsDisplay(flight);
            }

        } catch (RuntimeException ex) {
            statusLabel.setText(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        AirlineReservationSystem app = new AirlineReservationSystem();
        app.setVisible(true);
    }
}