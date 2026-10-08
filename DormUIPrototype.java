package dorm.system.ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dorm.system.model.*;
import dorm.system.backend.DormDAO;

 class DormUIPrototype {

	public static void main(String[] args) {
	    java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
	}
}


class Theme {

    static final Color DARK_PINK = new Color(198, 76, 125);
    static final Color PINK = new Color(218, 92, 142);
    static final Color LIGHT_PINK = new Color(250, 232, 240);
    static final Color SOFT_PINK = new Color(255, 247, 250);

    static final Color DARK_GRAY = new Color(55, 55, 65);
    static final Color GRAY = new Color(105, 105, 115);
    static final Color LIGHT_GRAY = new Color(242, 243, 246);
    static final Color BORDER_GRAY = new Color(210, 212, 218);
    static final Color WHITE = Color.WHITE;

    static final Font TITLE = new Font("Segoe UI", Font.BOLD, 22);
    static final Font SUBTITLE = new Font("Segoe UI", Font.PLAIN, 13);
    static final Font LABEL = new Font("Segoe UI", Font.BOLD, 13);
    static final Font NORMAL = new Font("Segoe UI", Font.PLAIN, 13);
    static final Font BUTTON = new Font("Segoe UI", Font.BOLD, 13);

    static void prepareFrame(JFrame frame, String title, int width, int height) {
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setMinimumSize(new Dimension(Math.min(width, 700), Math.min(height, 450)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(LIGHT_GRAY);
    }
    static JPanel createWelcomePanel(String name, String description) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(WHITE);

        JLabel welcome = new JLabel("Welcome, " + name + "!");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcome.setForeground(DARK_GRAY);

        JLabel desc = new JLabel(description);
        desc.setFont(NORMAL);
        desc.setForeground(GRAY);

        panel.add(welcome);
        panel.add(Box.createVerticalStrut(4));
        panel.add(desc);

        return panel;
    }


    static JPanel createHeader(String title, String subtitle) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_PINK);
        header.setBorder(BorderFactory.createEmptyBorder(16, 25, 16, 25));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(WHITE);
        titleLabel.setFont(TITLE);

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setForeground(new Color(255, 225, 236));
        subtitleLabel.setFont(SUBTITLE);

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.add(titleLabel);
        text.add(Box.createVerticalStrut(3));
        text.add(subtitleLabel);

        header.add(text, BorderLayout.WEST);

        JLabel system = new JLabel("DORM PORTAL");
        system.setForeground(new Color(255, 225, 236));
        system.setFont(new Font("Segoe UI", Font.BOLD, 10));
        header.add(system, BorderLayout.EAST);

        return header;
    }

    static JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL);
        label.setForeground(DARK_GRAY);
        return label;
    }

    static void styleTextField(JTextField field) {
        field.setFont(NORMAL);
        field.setForeground(DARK_GRAY);
        field.setBackground(WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_GRAY),
                BorderFactory.createEmptyBorder(8, 11, 8, 11)
        ));
        field.setCaretColor(DARK_PINK);
    }

    static void styleComboBox(JComboBox<String> box) {
        box.setFont(NORMAL);
        box.setForeground(DARK_GRAY);
        box.setBackground(WHITE);
        box.setBorder(BorderFactory.createLineBorder(BORDER_GRAY));
        box.setFocusable(false);
    }

    static void styleTextArea(JTextArea area) {
        area.setFont(NORMAL);
        area.setForeground(DARK_GRAY);
        area.setBackground(WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
    }

    static JButton button(String text, boolean primary) {
        JButton button = new JButton(text);
        button.setFont(BUTTON);
        button.setForeground(primary ? WHITE : DARK_GRAY);
        button.setBackground(primary ? PINK : WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(primary ? PINK : BORDER_GRAY),
                BorderFactory.createEmptyBorder(9, 16, 9, 16)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(primary ? DARK_PINK : LIGHT_PINK);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(primary ? PINK : WHITE);
            }
        });

        return button;
    }

    static JPanel cardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_GRAY),
                BorderFactory.createEmptyBorder(20, 22, 20, 22)
        ));
        return panel;
    }

    static void styleTable(JTable table) {
        table.setFont(NORMAL);
        table.setForeground(DARK_GRAY);
        table.setBackground(WHITE);
        table.setGridColor(new Color(230, 231, 235));
        table.setRowHeight(32);
        table.setSelectionBackground(LIGHT_PINK);
        table.setSelectionForeground(DARK_GRAY);
        table.setFillsViewportHeight(true);

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setForeground(WHITE);
        table.getTableHeader().setBackground(DARK_PINK);
        table.getTableHeader().setPreferredSize(new Dimension(0, 36));
    }

    static JScrollPane scrollPane(Component component) {
        JScrollPane scroll = new JScrollPane(component);
        scroll.setBorder(BorderFactory.createLineBorder(BORDER_GRAY));
        scroll.getViewport().setBackground(WHITE);
        return scroll;
    }

    static void styleOptionPane() {
        UIManager.put("OptionPane.background", WHITE);
        UIManager.put("Panel.background", WHITE);
        UIManager.put("OptionPane.messageFont", NORMAL);
        UIManager.put("OptionPane.buttonFont", BUTTON);
    }
    
}

class DormLogo extends JPanel {

    private static final long serialVersionUID = 1L;

    private final int logoSize;

    DormLogo(int logoSize) {
        this.logoSize = logoSize;
        setOpaque(false);
        setPreferredSize(new Dimension(logoSize + 20, logoSize + 20));
        setMinimumSize(new Dimension(logoSize + 20, logoSize + 20));
        setMaximumSize(new Dimension(logoSize + 20, logoSize + 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int centerX = getWidth() / 2;
        int top = 12;
        int houseWidth = logoSize - 22;
        int houseHeight = logoSize - 20;
        int left = centerX - houseWidth / 2;
        int right = centerX + houseWidth / 2;
        int roofTop = top;
        int roofBottom = top + houseWidth / 2;
        int bottom = top + houseHeight;

        g2.setColor(Theme.DARK_PINK);
        g2.setStroke(new BasicStroke(
                8f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));

        Polygon roof = new Polygon();
        roof.addPoint(centerX, roofTop);
        roof.addPoint(right, roofBottom);
        roof.addPoint(right, bottom);
        roof.addPoint(left, bottom);
        roof.addPoint(left, roofBottom);

        g2.drawLine(centerX, roofTop, right, roofBottom);
        g2.drawLine(right, roofBottom, right, bottom);
        g2.drawLine(right, bottom, left, bottom);
        g2.drawLine(left, bottom, left, roofBottom);
        g2.drawLine(left, roofBottom, centerX, roofTop);

        int doorWidth = houseWidth / 4;
        int doorLeft = centerX - doorWidth / 2;
        int doorTop = bottom - houseHeight / 2;

        g2.drawLine(doorLeft, bottom, doorLeft, doorTop);
        g2.drawLine(doorLeft, doorTop, centerX + doorWidth / 2, doorTop);
        g2.drawLine(centerX + doorWidth / 2, doorTop,
                centerX + doorWidth / 2, bottom);

        g2.setStroke(new BasicStroke(5f));
        g2.fillOval(centerX + 6, doorTop + 22, 6, 6);

        g2.dispose();
    }
}

class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JLabel subtitleLabel;

    public LoginFrame() {

        Theme.styleOptionPane();

        Theme.prepareFrame(
                this,
                "Dormitory Management System - Login",
                900,
                580
        );

        setLayout(new BorderLayout());

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Theme.DARK_PINK);
        topBar.setPreferredSize(new Dimension(900, 58));
        topBar.setBorder(
                BorderFactory.createEmptyBorder(0, 28, 0, 28)
        );

        JLabel appTitle =
                new JLabel("Dormitory Management System");

        appTitle.setForeground(Color.WHITE);
        appTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        JPanel titleLeft =
                new JPanel(new FlowLayout(
                        FlowLayout.LEFT,
                        0,
                        16
                ));

        titleLeft.setOpaque(false);
        titleLeft.add(appTitle);

        topBar.add(titleLeft, BorderLayout.WEST);

        JLabel secure =
                new JLabel("SECURE PORTAL");

        secure.setForeground(
                new Color(255, 225, 236)
        );

        secure.setFont(
                new Font("Segoe UI", Font.BOLD, 10)
        );

        topBar.add(secure, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        JPanel main =
                new JPanel(new GridLayout(1, 2));

        main.setBackground(Theme.LIGHT_GRAY);

        JPanel brand = new JPanel();

        brand.setBackground(Theme.SOFT_PINK);

        brand.setLayout(
                new BoxLayout(
                        brand,
                        BoxLayout.Y_AXIS
                )
        );

        brand.setBorder(
                BorderFactory.createEmptyBorder(
                        35,
                        35,
                        30,
                        35
                )
        );

        DormLogo logo = new DormLogo(150);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        brand.add(logo);
        brand.add(Box.createVerticalStrut(12));

        JLabel brandName =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                        + "<span style='color:#C64C7D;'>"
                        + "DORMITORY"
                        + "</span><br>"
                        + "<span style='color:#373741;'>"
                        + "MANAGEMENT SYSTEM"
                        + "</span>"
                        + "</div></html>"
                );

        brandName.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        brandName.setFont(
                new Font("Segoe UI", Font.BOLD, 25)
        );

        brand.add(brandName);
        brand.add(Box.createVerticalStrut(12));

        JPanel accentLine =
                new JPanel();

        accentLine.setBackground(Theme.PINK);
        accentLine.setMaximumSize(
                new Dimension(110, 3)
        );

        accentLine.setPreferredSize(
                new Dimension(110, 3)
        );

        accentLine.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        brand.add(accentLine);
        brand.add(Box.createVerticalStrut(12));

        JLabel tagline =
                new JLabel(
                        "Safe Stay  •  Better Tomorrow"
                );

        tagline.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        tagline.setForeground(Theme.GRAY);
        tagline.setFont(
                new Font(
                        "Segoe UI",
                        Font.ITALIC,
                        14
                )
        );

        brand.add(tagline);

        brand.add(Box.createVerticalGlue());

        JLabel info =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                        + "Manage your dormitory access<br>"
                        + "quickly and securely."
                        + "</div></html>"
                );

        info.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        info.setForeground(Theme.GRAY);
        info.setFont(Theme.NORMAL);

        brand.add(info);

        main.add(brand);

        JPanel right =
                new JPanel(new GridBagLayout());

        right.setBackground(
                Theme.LIGHT_GRAY
        );

        right.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        35,
                        25,
                        35
                )
        );

        JPanel card = Theme.cardPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setPreferredSize(
                new Dimension(400, 410)
        );

        JLabel loginTitle =
                new JLabel("Login");

        loginTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        loginTitle.setForeground(
                Theme.DARK_GRAY
        );

        loginTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(loginTitle);

        card.add(
                Box.createVerticalStrut(4)
        );

        subtitleLabel =
                new JLabel(
                        "Login as Dorm Resident"
                );

        subtitleLabel.setFont(
                Theme.SUBTITLE
        );

        subtitleLabel.setForeground(
                Theme.GRAY
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(subtitleLabel);

        card.add(
                Box.createVerticalStrut(25)
        );

        JLabel roleLabel =
                Theme.label("Select Role");

        roleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(roleLabel);

        card.add(
                Box.createVerticalStrut(6)
        );

        roleComboBox =
                new JComboBox<>(
                        new String[]{
                                "Dorm Resident",
                                "Hall Manager",
                                "Maintenance Staff"
                        }
                );

        Theme.styleComboBox(
                roleComboBox
        );

        roleComboBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        roleComboBox.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(roleComboBox);

        card.add(
                Box.createVerticalStrut(16)
        );

        JLabel usernameLabel =
                Theme.label("Username");

        usernameLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(usernameLabel);

        card.add(
                Box.createVerticalStrut(6)
        );

        usernameField =
                new JTextField();

        Theme.styleTextField(
                usernameField
        );

        usernameField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        usernameField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(usernameField);

        card.add(
                Box.createVerticalStrut(16)
        );

        JLabel passwordLabel =
                Theme.label("Password");

        passwordLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(passwordLabel);

        card.add(
                Box.createVerticalStrut(6)
        );

        passwordField =
                new JPasswordField();

        Theme.styleTextField(
                passwordField
        );

        passwordField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        passwordField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(passwordField);

        card.add(
                Box.createVerticalStrut(25)
        );

        JButton loginButton =
                Theme.button(
                        "LOGIN",
                        true
                );

        loginButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        loginButton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        44
                )
        );

        card.add(loginButton);

        right.add(card);

        main.add(right);

        add(
                main,
                BorderLayout.CENTER
        );

        roleComboBox.addActionListener(e -> {

            String selectedRole =
                    (String)
                    roleComboBox.getSelectedItem();

            subtitleLabel.setText(
                    "Login as " + selectedRole
            );
        });

        loginButton.addActionListener(e -> {

            String role =
                    (String)
                    roleComboBox.getSelectedItem();

            String username =
                    usernameField
                            .getText()
                            .trim();

            if (username.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter your username.",
                        "Login Required",
                        JOptionPane.WARNING_MESSAGE
                );

                usernameField.requestFocus();
                return;
            }

            if (passwordField
                    .getPassword()
                    .length == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter your password.",
                        "Login Required",
                        JOptionPane.WARNING_MESSAGE
                );

                passwordField.requestFocus();
                return;
            }

            String password =
                    new String(
                            passwordField.getPassword()
                    );
            boolean validLogin = false;

            String sql;

            if ("Dorm Resident".equals(role)) {

                sql = "SELECT residentID FROM Resident "
                        + "WHERE residentID = ? AND password = ?";

            } else if ("Hall Manager".equals(role)) {

                sql = "SELECT managerID FROM Hall_Manager "
                        + "WHERE managerID = ? AND password = ?";

            } else {

                sql = "SELECT staffID FROM Maintenance_Staff "
                        + "WHERE staffID = ? AND password = ?";
            }

            try (
                    Connection connection =
                            DatabaseConnection.getConnection();

                    PreparedStatement statement =
                            connection.prepareStatement(sql)
            ) {

                statement.setInt(
                        1,
                        Integer.parseInt(username)
                );

                statement.setString(
                        2,
                        password
                );

                try (ResultSet resultSet =
                        statement.executeQuery()) {

                    validLogin = resultSet.next();
                }

            } catch (SQLException | NumberFormatException ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to connect to the database.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (!validLogin) {

                JOptionPane.showMessageDialog(
                        this,
                        "Incorrect username or password for "
                        + role + ".",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                passwordField.setText("");
                passwordField.requestFocus();
                return;
            }

            dispose();

            if ("Dorm Resident".equals(role)) {

                new ResidentDashboard()
                        .setVisible(true);

            } else if ("Hall Manager".equals(role)) {

                new HallManagerDashboard()
                        .setVisible(true);

            } else {

                new MaintenanceDashboard(
                        username
                ).setVisible(true);
            }
        });
    }
}

class ResidentDashboard extends JFrame {

    private static final long serialVersionUID = 1L;

    public static Resident resident =
            new Resident(
                    123,
                    "Resident User",
                    "resident123@dorm.local",
                    "123",
                    "309",
                    "N/A"
            );

    public static List<RepairRequest> repairRequests =
            new ArrayList<>();
    
    private void loadRepairRequestsFromDatabase() {

        DormDAO dao = new DormDAO();

        try {

            repairRequests.clear();

            repairRequests.addAll(
                    dao.getRequestsForResident(
                            resident.getResidentID()
                    )
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load your repair requests from the database.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public ResidentDashboard() {

        loadRepairRequestsFromDatabase();

        Theme.styleOptionPane();

        Theme.prepareFrame(
                this,
                "Resident Dashboard - File & Track Repair Requests",
                720,
                560
        );

        setLayout(new BorderLayout());

        add(
                Theme.createHeader(
                        "Resident Dashboard",
                        "File a complaint and track your repair requests"
                ),
                BorderLayout.NORTH
        );

        JPanel content =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        content.setBackground(
                Theme.LIGHT_GRAY
        );

        content.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        JPanel formCard =
                Theme.cardPanel();

        formCard.setLayout(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(7, 7, 7, 7);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        JTextField roomField =
                new JTextField();

        Theme.styleTextField(
                roomField
        );

        roomField.setText(resident.getRoomNumber());

        JComboBox<String> categoryBox =
                new JComboBox<>(
                        new String[]{
                                "Noise Complaint",
                                "Bed Issue",
                                "Roommate Concern",
                                "Plumbing",
                                "Electrical",
                                "Furniture",
                                "Internet",
                                "Room Issue",
                                "Other"
                        }
                );

        Theme.styleComboBox(
                categoryBox
        );

        JTextArea descArea =
                new JTextArea(4, 20);

        Theme.styleTextArea(
                descArea
        );

        JScrollPane descScroll =
                Theme.scrollPane(
                        descArea
                );

        descScroll.setPreferredSize(
                new Dimension(300, 100)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.25;

        formCard.add(
                Theme.label("Room Number"),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.75;

        formCard.add(
                roomField,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formCard.add(
                Theme.label("Complaint Category"),
                gbc
        );

        gbc.gridx = 1;

        formCard.add(
                categoryBox,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor =
                GridBagConstraints.NORTHWEST;

        formCard.add(
                Theme.label("Description"),
                gbc
        );

        gbc.gridx = 1;

        formCard.add(
                descScroll,
                gbc
        );

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        buttons.setOpaque(false);

        JButton submitBtn =
                Theme.button(
                        "Submit Complaint",
                        true
                );

        JButton trackBtn =
                Theme.button(
                        "Track Ticket Status",
                        false
                );

        JButton followUpBtn =
                Theme.button(
                        "Follow Up on Ticket",
                        false
                );

        JButton logoutBtn =
                Theme.button(
                        "Logout",
                        false
                );

        buttons.add(submitBtn);
        buttons.add(trackBtn);
        buttons.add(followUpBtn);
        buttons.add(logoutBtn);

        content.add(
                formCard,
                BorderLayout.CENTER
        );

        content.add(
                buttons,
                BorderLayout.SOUTH
        );

        add(
                content,
                BorderLayout.CENTER
        );

        submitBtn.addActionListener(e -> {

            String room =
                    roomField.getText().trim();

            String category =
                    (String)
                    categoryBox.getSelectedItem();

            String description =
                    descArea.getText().trim();

            if (room.isEmpty() ||
                    description.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            resident.setRoomNumber(room);

            int newRequestId =
                    100 + repairRequests.size() + 1;

            String newIdLabel =
                    "#" + newRequestId;

            String dateSubmitted =
                    LocalDate.now().toString();

            RepairRequest newRequest =
                    new RepairRequest(
                            newRequestId,
                            resident.getResidentID(),
                            "Pending",
                            category,
                            "Medium",
                            description,
                            dateSubmitted
                    );

            DormDAO dao = new DormDAO();

            try {

                dao.saveRepairRequest(newRequest);

                repairRequests.add(newRequest);

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to save the repair request to the database.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            HallManagerDashboard
                    .sharedTableModel
                    .addRow(
                            new Object[]{
                                    newIdLabel,
                                    room,
                                    category,
                                    description,
                                    "Pending",
                                    "Unassigned"
                            }
                    );

            JOptionPane.showMessageDialog(
                    this,
                    "Repair Request successfully submitted!\n"
                    + "Ticket ID: " + newIdLabel
                    + "\nStatus: Pending.",
                    "Submission Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            descArea.setText("");
        });

        trackBtn.addActionListener(e -> {

            StringBuilder trackingInfo =
                    new StringBuilder(
                            "YOUR SUBMITTED TICKETS\n\n"
                    );

            for (
                    RepairRequest t :
                    repairRequests) {

            	String assignedStaff = "Unassigned";

            	DormDAO dao = new DormDAO();

            	try {

            	    MaintenanceTask task =
            	            dao.getTaskForRequest(
            	                    t.getRequestID()
            	            );

            	    if (task != null) {

            	        assignedStaff =
            	                String.valueOf(task.getStaffID());
            	    }

            	} catch (Exception ex) {

            	    ex.printStackTrace();
            	}

                trackingInfo
                        .append("Ticket ID: #")
                        .append(t.getRequestID())
                        .append("\nRoom: ")
                        .append(resident.getRoomNumber())
                        .append("\nCategory: ")
                        .append(t.getIssueType())
                        .append("\nPriority: ")
                        .append(t.getPriority())
                        .append("\nDescription: ")
                        .append(t.getDescription())
                        .append("\nStatus: ")
                        .append(t.getStatus())
                        .append("\nDate Submitted: ")
                        .append(t.getDateSubmitted())
                        .append("\nAssigned Staff: ")
                        .append(assignedStaff)
                        .append("\n")
                        .append("--------------------------------")
                        .append("\n\n");
            }

            JTextArea textArea =
                    new JTextArea(
                            trackingInfo.toString(),
                            12,
                            35
                    );

            Theme.styleTextArea(
                    textArea
            );

            textArea.setEditable(false);

            JOptionPane.showMessageDialog(
                    this,
                    Theme.scrollPane(textArea),
                    "Track Ticket Status",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

         followUpBtn.addActionListener(e -> {

            if (repairRequests.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "You have no submitted tickets to follow up on yet.",
                        "No Tickets",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String[] ticketIds =
                    new String[repairRequests.size()];

            for (int i = 0; i < repairRequests.size(); i++) {
                ticketIds[i] = "#" + repairRequests.get(i).getRequestID();
            }

            String selectedTicketId =
                    (String) JOptionPane.showInputDialog(
                            this,
                            "Select the ticket you'd like to follow up on:",
                            "Follow Up on Ticket",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            ticketIds,
                            ticketIds[0]
                    );

            if (selectedTicketId == null) {
                return;
            }

            String followUpMessage =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter your follow-up message for "
                            + selectedTicketId + ":"
                    );

            if (followUpMessage == null
                    || followUpMessage.trim().isEmpty()) {
                return;
            }

            followUpMessage = followUpMessage.trim();

            int selectedRequestId =
                    Integer.parseInt(
                            selectedTicketId.replace("#", "")
                    );

            for (RepairRequest t : repairRequests) {

                if (t.getRequestID() == selectedRequestId) {

                    t.setDescription(
                            t.getDescription()
                            + "\n[Follow-up] " + followUpMessage
                    );

                    t.setStatus("Follow-Up Requested");

                    DormDAO dao = new DormDAO();

                    try {

                        dao.updateRepairRequestStatus(
                                selectedRequestId,
                                "Follow-Up Requested"
                        );

                    } catch (Exception ex) {

                        ex.printStackTrace();

                        JOptionPane.showMessageDialog(
                                this,
                                "Unable to save the follow-up status.",
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                        return;
                    }

                    HallManagerDashboard.applyFollowUp(
                            selectedTicketId,
                            followUpMessage
                    );
                    break;
                }
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Your follow-up was sent for ticket "
                    + selectedTicketId + ".",
                    "Follow-Up Sent",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        logoutBtn.addActionListener(e -> {

            dispose();

            new LoginFrame()
                    .setVisible(true);
        });
    }
}

class HallManagerDashboard extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable ticketTable;

    public static DefaultTableModel sharedTableModel =
            new DefaultTableModel(
                    new Object[][]{
                            {
                                    "#101",
                                    "309",
                                    "Plumbing",
                                    "Leaking pipe under sink",
                                    "In-Progress",
                                    "789"
                            },
                            {
                                    "#102",
                                    "309",
                                    "Bed Issue",
                                    "Bed frame needs repair",
                                    "Pending",
                                    "Unassigned"
                            }
                    },
                    new String[]{
                            "Ticket ID",
                            "Room",
                            "Category",
                            "Description",
                            "Status",
                            "Assigned Staff"
                    }
            );

    public static HallManager manager =
            new HallManager(
                    456,
                    "Hall Manager",
                    "manager456@dorm.local",
                    "456",
                    "N/A"
            );

    public static List<FacultyReport> facultyReports =
            new ArrayList<>();

    public static List<MaintenanceTask> maintenanceTasks =
            new ArrayList<>(Arrays.asList(
                    new MaintenanceTask(
                            501,
                            101,
                            789,
                            "2026-09-01",
                            "In-Progress",
                            null
                    ),
                    new MaintenanceTask(
                            502,
                            102,
                            789,
                            "2026-09-05",
                            "Assigned",
                            null
                    )
            ));

    static void updateStatusByTicketId(String ticketID, String status) {

        for (int row = 0; row < sharedTableModel.getRowCount(); row++) {

            String rowTicketId =
                    (String) sharedTableModel.getValueAt(row, 0);

            if (rowTicketId.equals(ticketID)) {

                sharedTableModel.setValueAt(status, row, 4);
                break;
            }
        }
    }

    static void applyFollowUp(String ticketID, String followUpMessage) {

        for (int row = 0; row < sharedTableModel.getRowCount(); row++) {

            String rowTicketId =
                    (String) sharedTableModel.getValueAt(row, 0);

            if (rowTicketId.equals(ticketID)) {

                String currentDescription =
                        (String) sharedTableModel.getValueAt(row, 3);

                sharedTableModel.setValueAt(
                        currentDescription
                        + "\n[Follow-up] " + followUpMessage,
                        row,
                        3
                );

                sharedTableModel.setValueAt(
                        "Follow-Up Requested",
                        row,
                        4
                );

                break;
            }
        }
    }

    private static JPanel createLegendItem(Color swatchColor, String text) {

        JPanel item =
                new JPanel(
                        new FlowLayout(FlowLayout.LEFT, 6, 0)
                );

        item.setOpaque(false);

        JPanel swatch = new JPanel();
        swatch.setPreferredSize(new Dimension(14, 14));
        swatch.setBackground(swatchColor);
        swatch.setBorder(
                BorderFactory.createLineBorder(Theme.BORDER_GRAY)
        );

        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        label.setForeground(Theme.GRAY);

        item.add(swatch);
        item.add(label);

        return item;
    }
    
    private void loadRepairRequestsFromDatabase() {

        DormDAO dao = new DormDAO();

        try {

            List<RepairRequest> requests =
                    dao.getAllRepairRequests();

            sharedTableModel.setRowCount(0);

            for (RepairRequest request : requests) {

            	String assignedStaff = "Unassigned";

            	MaintenanceTask task =
            	        dao.getTaskForRequest(
            	                request.getRequestID()
            	        );

            	if (task != null) {

            	    assignedStaff =
            	            String.valueOf(task.getStaffID());
            	}

                sharedTableModel.addRow(
                        new Object[]{
                                "#" + request.getRequestID(),
                                "309",
                                request.getIssueType(),
                                request.getDescription(),
                                request.getStatus(),
                                assignedStaff
                        }
                );
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load repair requests from the database.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public HallManagerDashboard() {

        loadRepairRequestsFromDatabase();

        Theme.styleOptionPane();

        Theme.prepareFrame(
                this,
                "Hall Manager Dashboard - Review & Assign Complaints",
                950,
                560
        );

        setLayout(new BorderLayout());

        add(
                Theme.createHeader(
                        "Hall Manager Dashboard",
                        "Review resident complaints and assign maintenance staff"
                ),
                BorderLayout.NORTH
        );

        JPanel content =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        content.setBackground(
                Theme.LIGHT_GRAY
        );

        content.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        JPanel tableCard =
                Theme.cardPanel();

        tableCard.setLayout(
                new BorderLayout()
        );

        JLabel sectionTitle =
                Theme.label(
                        "Submitted Resident Complaints"
                );

        sectionTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        12,
                        0
                )
        );

        JPanel legendPanel =
                new JPanel(
                        new FlowLayout(FlowLayout.RIGHT, 14, 0)
                );

        legendPanel.setOpaque(false);

        legendPanel.add(
                createLegendItem(
                        new Color(255, 218, 218),
                        "Needs Assignment"
                )
        );

        legendPanel.add(
                createLegendItem(
                        new Color(255, 227, 201),
                        "Follow-Up Requested"
                )
        );

        legendPanel.add(
                createLegendItem(
                        new Color(255, 249, 205),
                        "In-Progress"
                )
        );

        legendPanel.add(
                createLegendItem(
                        new Color(222, 245, 224),
                        "Completed"
                )
        );

        JPanel tableHeaderRow =
                new JPanel(new BorderLayout());

        tableHeaderRow.setOpaque(false);

        tableHeaderRow.add(
                sectionTitle,
                BorderLayout.WEST
        );

        tableHeaderRow.add(
                legendPanel,
                BorderLayout.EAST
        );

        ticketTable =
                new JTable(
                        sharedTableModel
                );

        ticketTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_LAST_COLUMN
        );

        Theme.styleTable(
                ticketTable
        );

        ticketTable.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        Component cell =
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );

                        if (isSelected) {
                            cell.setBackground(Theme.LIGHT_PINK);
                            cell.setForeground(Theme.DARK_GRAY);
                            return cell;
                        }

                        String status =
                                String.valueOf(
                                        table.getValueAt(row, 4)
                                );

                        String assignedStaff =
                                String.valueOf(
                                        table.getValueAt(row, 5)
                                );

                        Color rowColor;

                        if ("Completed".equalsIgnoreCase(status)) {

                            rowColor = new Color(222, 245, 224);

                        } else if ("Follow-Up Requested"
                                .equalsIgnoreCase(status)) {

                            rowColor = new Color(255, 227, 201);

                        } else if ("In-Progress"
                                .equalsIgnoreCase(status)
                                || !"Unassigned"
                                        .equalsIgnoreCase(assignedStaff)) {

                            rowColor = new Color(255, 249, 205);

                        } else {

                            rowColor = new Color(255, 218, 218);
                        }

                        cell.setBackground(rowColor);
                        cell.setForeground(Theme.DARK_GRAY);

                        return cell;
                    }
                }
        );

        JScrollPane scrollPane =
                Theme.scrollPane(
                        ticketTable
                );

        tableCard.add(
                tableHeaderRow,
                BorderLayout.NORTH
        );

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        bottomPanel.setOpaque(false);

        JButton assignBtn =
                Theme.button(
                        "Assign Selected Ticket",
                        true
                );

        JButton reportBtn =
                Theme.button(
                        "Generate Facility Report",
                        false
                );

        JButton logoutBtn =
                Theme.button(
                        "Logout",
                        false
                );

        bottomPanel.add(assignBtn);
        bottomPanel.add(reportBtn);
        bottomPanel.add(logoutBtn);

        content.add(
                tableCard,
                BorderLayout.CENTER
        );

        content.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(
                content,
                BorderLayout.CENTER
        );

        assignBtn.addActionListener(e -> {

            int selectedRow =
                    ticketTable.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a ticket from the table first.",
                        "Selection Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String ticketID =
                    (String)
                    sharedTableModel.getValueAt(
                            selectedRow,
                            0
                    );

            int requestId =
                    Integer.parseInt(ticketID.replace("#", ""));

            String staffInput =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Maintenance Staff ID to assign for "
                            + ticketID + ":",
                            "789"
                    );

            if (staffInput != null &&
                    !staffInput.trim().isEmpty()) {

                int staffId;

                try {
                    staffId = Integer.parseInt(staffInput.trim());
                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Staff ID must be a number.",
                            "Invalid Staff ID",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                String today = LocalDate.now().toString();

                DormDAO dao = new DormDAO();

                try {

                    int newTaskId = dao.getNextTaskId();

                    dao.assignTask(
                            newTaskId,
                            requestId,
                            staffId,
                            today,
                            "Assigned"
                    );

                    // Update the table after the database save succeeds
                    sharedTableModel.setValueAt(
                            "In-Progress",
                            selectedRow,
                            4
                    );

                    sharedTableModel.setValueAt(
                            String.valueOf(staffId),
                            selectedRow,
                            5
                    );

                    ticketTable.repaint();
                    ticketTable.clearSelection();

                    // Keep the current program data synchronized
                    for (
                            RepairRequest r :
                            ResidentDashboard.repairRequests) {

                        if (r.getRequestID() == requestId) {

                            r.setStatus("In-Progress");
                        }
                    }

                    maintenanceTasks.add(
                            new MaintenanceTask(
                                    newTaskId,
                                    requestId,
                                    staffId,
                                    today,
                                    "Assigned",
                                    null
                            )
                    );

                } catch (Exception ex) {

                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to assign the ticket in the database.",
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Ticket " + ticketID
                        + " successfully assigned to Staff #"
                        + staffId + "!",
                        "Assignment Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        reportBtn.addActionListener(e -> {

            int pending = 0;
            int inProgress = 0;
            int completed = 0;
            int followUp = 0;

            for (int row = 0; row < sharedTableModel.getRowCount(); row++) {

                String status =
                        String.valueOf(sharedTableModel.getValueAt(row, 4));

                if (status.startsWith("Pending")) {
                    pending++;
                } else if ("In-Progress".equalsIgnoreCase(status)) {
                    inProgress++;
                } else if ("Completed".equalsIgnoreCase(status)) {
                    completed++;
                } else if ("Follow-Up Requested".equalsIgnoreCase(status)) {
                    followUp++;
                }
            }

            int newReportId = facultyReports.size() + 1;
            String today = LocalDate.now().toString();

            String details =
                    "Total tickets: " + sharedTableModel.getRowCount()
                    + " | Pending: " + pending
                    + " | In-Progress: " + inProgress
                    + " | Follow-Up Requested: " + followUp
                    + " | Completed: " + completed;

            facultyReports.add(
                    new FacultyReport(
                            newReportId,
                            manager.getManagerID(),
                            today,
                            details
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Facility Report #" + newReportId + " Generated\n"
                    + "Date: " + today + "\n\n"
                    + details,
                    "Facility Report",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        logoutBtn.addActionListener(e -> {

            dispose();

            new LoginFrame()
                    .setVisible(true);
        });
    }
}

class MaintenanceDashboard extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable taskTable;
    private DefaultTableModel taskTableModel;
    private final int staffID;

    public MaintenanceDashboard(
            String staffName) {

        this.staffID = Integer.parseInt(staffName.trim());

        Theme.styleOptionPane();

        Theme.prepareFrame(
                this,
                "Maintenance Staff Portal - Welcome, "
                        + staffName,
                900,
                520
        );

        setLayout(new BorderLayout());

        add(
                Theme.createHeader(
                        "Maintenance Staff Portal",
                        "Assigned tasks for: " + staffName
                ),
                BorderLayout.NORTH
        );

        JPanel content =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        content.setBackground(
                Theme.LIGHT_GRAY
        );

        content.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        JPanel tableCard =
                Theme.cardPanel();

        tableCard.setLayout(
                new BorderLayout()
        );

        JLabel sectionTitle =
                Theme.label(
                        "My Assigned Tasks"
                );

        sectionTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        12,
                        0
                )
        );

        taskTableModel =
                new DefaultTableModel(
                        new String[]{
                                "Task ID",
                                "Ticket ID",
                                "Category",
                                "Description",
                                "Progress Status"
                        },
                        0
                );

        taskTable =
                new JTable(
                        taskTableModel
                );

        Theme.styleTable(
                taskTable
        );

        tableCard.add(
                sectionTitle,
                BorderLayout.NORTH
        );

        tableCard.add(
                Theme.scrollPane(
                        taskTable
                ),
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        bottomPanel.setOpaque(false);

        JButton refreshBtn =
                Theme.button(
                        "Refresh Tasks",
                        false
                );

        JButton updateProgressBtn =
                Theme.button(
                        "Update to In-Progress",
                        true
                );

        JButton completeBtn =
                Theme.button(
                        "Mark as Completed",
                        false
                );

        JButton logoutBtn =
                Theme.button(
                        "Logout",
                        false
                );

        bottomPanel.add(refreshBtn);
        bottomPanel.add(
                updateProgressBtn
        );

        bottomPanel.add(
                completeBtn
        );

        bottomPanel.add(
                logoutBtn
        );

        content.add(
                tableCard,
                BorderLayout.CENTER
        );

        content.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(
                content,
                BorderLayout.CENTER
        );

        loadTasks();

        refreshBtn.addActionListener(e -> loadTasks());

        updateProgressBtn.addActionListener(e -> {

            int selectedRow =
                    taskTable.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a task from the table first.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String ticketID =
                    (String)
                    taskTableModel.getValueAt(
                            selectedRow,
                            1
                    );

            int requestId =
                    Integer.parseInt(ticketID.replace("#", ""));

            taskTableModel.setValueAt(
                    "In-Progress",
                    selectedRow,
                    4
            );

            for (
                    MaintenanceTask tr :
                    HallManagerDashboard.maintenanceTasks) {

                if (tr.getRequestID() == requestId) {
                    tr.setProgress("In-Progress");
                }
            }

            for (
                    RepairRequest rr :
                    ResidentDashboard.repairRequests) {

                if (rr.getRequestID() == requestId) {
                    rr.setStatus("In-Progress");
                }
            }

            DormDAO dao = new DormDAO();

            try {

                List<MaintenanceTask> tasks =
                        dao.getTasksForStaff(staffID);

                MaintenanceTask selectedTask = null;

                for (MaintenanceTask task : tasks) {

                    if (task.getRequestID() == requestId) {

                        selectedTask = task;
                        break;
                    }
                }

                if (selectedTask == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "The selected task could not be found in the database.",
                            "Task Not Found",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                dao.updateTaskStatus(
                        selectedTask.getTaskID(),
                        requestId,
                        "In-Progress"
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to update the task in the database.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Task progress updated to In-Progress.",
                    "Progress Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        completeBtn.addActionListener(e -> {

            int selectedRow =
                    taskTable.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a task from the table first.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String ticketID =
                    (String)
                    taskTableModel.getValueAt(
                            selectedRow,
                            1
                    );

            int requestId =
                    Integer.parseInt(ticketID.replace("#", ""));


            DormDAO dao = new DormDAO();

            try {

                List<MaintenanceTask> tasks =
                        dao.getTasksForStaff(staffID);

                MaintenanceTask selectedTask = null;

                for (MaintenanceTask task : tasks) {

                    if (task.getRequestID() == requestId) {

                        selectedTask = task;
                        break;
                    }
                }

                if (selectedTask == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "The selected task could not be found in the database.",
                            "Task Not Found",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                dao.updateTaskStatus(
                        selectedTask.getTaskID(),
                        requestId,
                        "Completed"
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to complete the task in the database.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            taskTableModel.removeRow(selectedRow);

            JOptionPane.showMessageDialog(
                    this,
                    "Task successfully marked as Completed and removed "
                    + "from your active task list.",
                    "Task Completed",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        logoutBtn.addActionListener(e -> {

            dispose();

            new LoginFrame()
                    .setVisible(true);
        });
    }

    private void loadTasks() {

        taskTableModel.setRowCount(0);

        DormDAO dao = new DormDAO();

        try {

            List<MaintenanceTask> tasks =
                    dao.getTasksForStaff(staffID);

            List<RepairRequest> requests =
                    dao.getRequestsForResident(
                            ResidentDashboard.resident.getResidentID()
                    );

            for (MaintenanceTask t : tasks) {

                if ("Completed".equalsIgnoreCase(
                        t.getProgress())) {

                    continue;
                }

                String category = "";
                String description = "";

                for (RepairRequest req : requests) {

                    if (req.getRequestID() ==
                            t.getRequestID()) {

                        category =
                                req.getIssueType();

                        description =
                                req.getDescription();

                        break;
                    }
                }

                taskTableModel.addRow(
                        new Object[]{
                                "#T-" + t.getTaskID(),
                                "#" + t.getRequestID(),
                                category,
                                description,
                                t.getProgress()
                        }
                );
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load maintenance tasks from the database.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}