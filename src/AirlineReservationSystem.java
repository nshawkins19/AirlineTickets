import javax.swing.*;
import java.awt.event.*;

public class AirlineReservationSystem extends JFrame implements ActionListener {
    // GUI Components
    private JTextField customerNameField, departureDateField, departureTimeField;
    private JComboBox<String> startingCityBox, destinationCityBox, flightNumberBox, classBox, seatPreferenceBox, snackBox;
    private JButton reserveButton;
    private JLabel statusLabel;

    // Flights
    private Flight gos1, gos2;

    public AirlineReservationSystem() {
        // Initialize flights with constructor setting number of seats (Requirement 2)
        gos1 = new Flight("GOS 1", "Greensboro", "Newark", "7:00 AM", "9:00 AM", 100);
        gos2 = new Flight("GOS 2", "Newark", "Greensboro", "5:30 PM", "7:00 PM", 100);

        // Set up GUI (Requirement 1)
        setTitle("Greensboro Airlines Reservation System");
        setSize(400, 400);
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

        JLabel departureDateLabel = new JLabel("Departure Date:");
        departureDateLabel.setBounds(20, 180, 120, 25);
        add(departureDateLabel);
        departureDateField = new JTextField();
        departureDateField.setBounds(150, 180, 200, 25);
        add(departureDateField);

        JLabel departureTimeLabel = new JLabel("Departure Time:");
        departureDateLabel.setBounds(20, 180, 120, 25);
        add(departureTimeLabel);
        departureTimeField = new JTextField();
        departureTimeField.setBounds(150, 160, 200, 25);
        add(departureTimeField);

        JLabel classLabel = new JLabel("Class:");
        classLabel.setBounds(20, 220, 120, 25);
        add(classLabel);
        classBox = new JComboBox<>(new String[]{"Economy", "Business"});
        classBox.setBounds(150, 220, 200, 25);
        classBox.addActionListener(this);
        add(classBox);

        JLabel seatPreferenceLabel = new JLabel("Seat Preference:");
        seatPreferenceLabel.setBounds(20, 260, 120, 25);
        add(seatPreferenceLabel);
        seatPreferenceBox = new JComboBox<>(new String[]{"Window", "Aisle"});
        seatPreferenceBox.setBounds(150, 260, 200, 25);
        add(seatPreferenceBox);

        JLabel snackLabel = new JLabel("Snack:");
        snackLabel.setBounds(20, 300, 120, 25);
        add(snackLabel);
        snackBox = new JComboBox<>(new String[]{"Cookies", "Peanuts"});
        snackBox.setBounds(150, 300, 200, 25);
        add(snackBox);

        reserveButton = new JButton("Reserve Ticket");
        reserveButton.setBounds(20, 340, 330, 25);
        reserveButton.addActionListener(this);
        add(reserveButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(20, 370, 350, 25);
        add(statusLabel);

        // Initially hide seat preference and snack options for Economy class
        seatPreferenceLabel.setVisible(false);
        seatPreferenceBox.setVisible(false);
        snackLabel.setVisible(false);
        snackBox.setVisible(false);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == classBox) {
            String selectedClass = (String) classBox.getSelectedItem();
            boolean isBusiness = selectedClass.equals("Business");
            // Show or hide seat preference and snack options based on class
            seatPreferenceBox.setVisible(isBusiness);
            snackBox.setVisible(isBusiness);
        } else if (e.getSource() == reserveButton) {
            // Gather information
            String customerName = customerNameField.getText();
            String startingCity = (String) startingCityBox.getSelectedItem();
            String destinationCity = (String) destinationCityBox.getSelectedItem();
            String flightNumber = (String) flightNumberBox.getSelectedItem();
            String departureDate = departureDateField.getText();
            String departureTime = departureTimeField.getText();
            String ticketClass = (String) classBox.getSelectedItem();

            // Input validation
            if (customerName.isEmpty() || departureDate.isEmpty() || departureTime.isEmpty()) {
                statusLabel.setText("Please fill in all required fields.");
                return;
            }

            // Select flight
            Flight flight = flightNumber.equals("GOS 1") ? gos1 : gos2;

            try {
                flight.reserveSeat(); // Check seat availability and reserve (Requirement 3)
                Ticket ticket;
                if (ticketClass.equals("Economy")) {
                    ticket = new EconomyTicket(customerName, startingCity, destinationCity, flightNumber, departureDate, departureTime);
                    String info = ticket.reserveTicket();
                }
            } finally {
                
            } 
        }
    }

    public static void main(String[] args){
    AirlineReservationSystem app = new AirlineReservationSystem();
    app.setVisible(true);
    }
}