import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import javax.swing.border.LineBorder; // Import for borders

public class app extends JFrame {
    // Lecture Tab Fields
    private JTextField lectureEssayField, lecturePvmField, lectureJavaBasicsField, lectureJsIntroField;
    private JTextField lectureAttendanceResultField;
    private JTextField lecturePrelimQuizzesField, lectureAttendanceField, lectureClassStandingResultField;
    private JTextField lectureExamField, lecturePrelimClassStandingField, quizzesResultField;

    // Laboratory Tab Fields
    private JTextField labMp1Field, labMp2Field, labMp3Field, labMp3DocField, labWorkResultField;
    private JTextField labWorkField, labAttendanceField, labClassStandingResultField;
    private JTextField labJava1Field, labJava2Field, labJs1Field, labJs2Field, labPrelimExamResultField;
    private JTextField labExamField, labPrelimClassStandingField;

    // Shared Fields
    private JTextArea resultArea, labResultArea;
    private JButton calculateButton, calculateQuizzesButton, calculateClassStandingButton, calculateAttendanceButton;
    private JButton calculateLabWorkButton, calculateLabClassStandingButton, calculatePrelimExamButton;
    private JTabbedPane tabbedPane;

    public app() {
        setTitle("Prelim Grade Calculator");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set dark mode colors
        Color darkBackground = new Color(30, 30, 30);
        Color darkForeground = new Color(255, 255, 255);
        Color darkBorder = new Color(255, 255, 255);

        // Apply dark mode to the frame
        getContentPane().setBackground(darkBackground);

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(darkBackground);
        tabbedPane.setForeground(darkForeground);

        // Lecture Tab
        JPanel lecturePanel = createPanel(darkBackground, darkForeground, darkBorder);
        lecturePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Prelim Quizzes Calculator Section
        addSection(lecturePanel, "--- Prelim Quizzes Calculator ---");
        // Add Prelim Quizzes Formula
        lecturePanel.add(createLabel("Formula:", darkForeground));
        lecturePanel.add(createLabel("(Essay + (PVM / 60 * 100) + (Java Basics / 40 * 100) + (JS Intro / 40 * 100)) / 4", darkForeground));

        lecturePanel.add(createLabel("Essay Grade (0-100):", darkForeground));
        lectureEssayField = createTextField(darkBackground, darkForeground, darkBorder);
        lecturePanel.add(lectureEssayField);

        lecturePanel.add(createLabel("PVM Grade (0-60):", darkForeground));
        lecturePvmField = createTextField(darkBackground, darkForeground, darkBorder);
        lecturePanel.add(lecturePvmField);

        lecturePanel.add(createLabel("Javascript Basics Grade (0-40):", darkForeground));
        lectureJavaBasicsField = createTextField(darkBackground, darkForeground, darkBorder);
        lecturePanel.add(lectureJavaBasicsField);

        lecturePanel.add(createLabel("Intro to JAVA Grade (0-40):", darkForeground));
        lectureJsIntroField = createTextField(darkBackground, darkForeground, darkBorder);
        lecturePanel.add(lectureJsIntroField);

        calculateQuizzesButton = createButton("Calculate Prelim Quizzes", darkBackground, darkForeground, darkBorder);
        lecturePanel.add(calculateQuizzesButton);

        quizzesResultField = createTextField(darkBackground, darkForeground, darkBorder);
        quizzesResultField.setEditable(false);
        lecturePanel.add(quizzesResultField);

       // Lec Attendance Calculator Section
        addSection(lecturePanel, "--- Lec Attendance Calculator ---");
        lecturePanel.add(createLabel("Formula:", darkForeground));
        lecturePanel.add(createLabel("100 - (Absences * 10)", darkForeground));
        lecturePanel.add(createLabel("Select Attended Dates:", darkForeground));

        // Create checkboxes for each Monday from January 20, 2025
        String[] dates = {"Jan 20, 2025", "Jan 27, 2025", "Feb 3, 2025", "Feb 10, 2025", "Feb 17, 2025"};
        JCheckBox[] attendanceCheckBoxes = new JCheckBox[5];

        for (int i = 0; i < 5; i++) {
            attendanceCheckBoxes[i] = new JCheckBox(dates[i]);
        
            // Apply dark theme
            attendanceCheckBoxes[i].setBackground(darkBackground); // Dark background
            attendanceCheckBoxes[i].setForeground(darkForeground); // White text
            attendanceCheckBoxes[i].setFocusPainted(false); // Removes focus highlight
            attendanceCheckBoxes[i].setBorder(new LineBorder(darkBorder, 1)); // Fix: Create a LineBorder
        
            lecturePanel.add(attendanceCheckBoxes[i]);
        }

        calculateAttendanceButton = createButton("Calculate Attendance", darkBackground, darkForeground, darkBorder);
        lecturePanel.add(calculateAttendanceButton);

        lectureAttendanceResultField = createTextField(darkBackground, darkForeground, darkBorder);
        lectureAttendanceResultField.setEditable(false);
        lecturePanel.add(lectureAttendanceResultField);

        // Prelim Class Standing Calculator Section
        addSection(lecturePanel, "--- Prelim Class Standing Calculator ---");
        lecturePanel.add(createLabel("Formula:", darkForeground));
        lecturePanel.add(createLabel("(0.6 * Prelim Quizzes Average) + (0.4 * Attendance)", darkForeground));
        lecturePanel.add(createLabel("Prelim Quizzes Grade (0-100):", darkForeground));
        lecturePrelimQuizzesField = createTextField(darkBackground, darkForeground, darkBorder);
        lecturePanel.add(lecturePrelimQuizzesField);

        lecturePanel.add(createLabel("Attendance (0-100):", darkForeground));
        lectureAttendanceField = createTextField(darkBackground, darkForeground, darkBorder);
        lecturePanel.add(lectureAttendanceField);

        calculateClassStandingButton = createButton("Calculate Prelim Class Standing", darkBackground, darkForeground, darkBorder);
        lecturePanel.add(calculateClassStandingButton);

        lectureClassStandingResultField = createTextField(darkBackground, darkForeground, darkBorder);
        lectureClassStandingResultField.setEditable(false);
        lecturePanel.add(lectureClassStandingResultField);

        // Lecture Grade Calculator Section
        addSection(lecturePanel, "--- Lecture Prelim Grade Calculator ---");
        lecturePanel.add(createLabel("Formula:", darkForeground));
        lecturePanel.add(createLabel("(0.6 * Prelim Exam Grade) + (0.4 * Class Standing)", darkForeground));
        lecturePanel.add(createLabel("Prelim Exam Grade (0-100):", darkForeground));
        lectureExamField = createTextField(darkBackground, darkForeground, darkBorder);
        lecturePanel.add(lectureExamField);

        lecturePanel.add(createLabel("Prelim Class Standing (0-100):", darkForeground));
        lecturePrelimClassStandingField = createTextField(darkBackground, darkForeground, darkBorder);
        lecturePanel.add(lecturePrelimClassStandingField);

        calculateButton = createButton("Calculate Final Prelim Grade", darkBackground, darkForeground, darkBorder);
        lecturePanel.add(calculateButton);

        resultArea = createTextArea(darkBackground, darkForeground, darkBorder);
        lecturePanel.add(new JScrollPane(resultArea));

        tabbedPane.addTab("Lecture", lecturePanel);

        // Laboratory Tab
        JPanel labPanel = createPanel(darkBackground, darkForeground, darkBorder);
        labPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Lab Work Grade Calculator Section
        addSection(labPanel, "--- Lab Work Grade Calculator ---");
        labPanel.add(createLabel("Formula:", darkForeground));
        labPanel.add(createLabel("(MP1 + MP2 + MP3 + MP3 Documentation) / 4", darkForeground));
        labPanel.add(createLabel("MP1 Grade (0-100):", darkForeground));
        labMp1Field = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labMp1Field);

        labPanel.add(createLabel("MP2 Grade (0-100):", darkForeground));
        labMp2Field = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labMp2Field);

        labPanel.add(createLabel("MP3 Grade (0-100):", darkForeground));
        labMp3Field = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labMp3Field);

        labPanel.add(createLabel("MP3 Documentation Grade (0-100):", darkForeground));
        labMp3DocField = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labMp3DocField);

        calculateLabWorkButton = createButton("Calculate Lab Work Grade", darkBackground, darkForeground, darkBorder);
        labPanel.add(calculateLabWorkButton);

        labWorkResultField = createTextField(darkBackground, darkForeground, darkBorder);
        labWorkResultField.setEditable(false);
        labPanel.add(labWorkResultField);

        // Lab Attendance Calculator Section
        addSection(labPanel, "--- Lab Attendance Calculator ---");
        labPanel.add(createLabel("Formula:", darkForeground));
        labPanel.add(createLabel("100 - (Absences * 10)", darkForeground));
        labPanel.add(createLabel("Select Attended Dates:", darkForeground));

        // Create checkboxes for each Monday from January 20, 2025
        String[] labDates = {"Jan 20, 2025", "Jan 27, 2025", "Feb 3, 2025", "Feb 10, 2025", "Feb 17, 2025"};
        JCheckBox[] labAttendanceCheckBoxes = new JCheckBox[5];

        for (int i = 0; i < 5; i++) {
            labAttendanceCheckBoxes[i] = new JCheckBox(labDates[i]);

            // Apply dark theme styles
            labAttendanceCheckBoxes[i].setBackground(darkBackground);
            labAttendanceCheckBoxes[i].setForeground(darkForeground);
            labAttendanceCheckBoxes[i].setFocusPainted(false);
            labAttendanceCheckBoxes[i].setBorder(new LineBorder(darkBorder, 1));

            labPanel.add(labAttendanceCheckBoxes[i]);
        }

        JButton labCalculateAttendanceButton = createButton("Calculate Attendance", darkBackground, darkForeground, darkBorder);
        labPanel.add(labCalculateAttendanceButton);

        JTextField labAttendanceResultField = createTextField(darkBackground, darkForeground, darkBorder);
        labAttendanceResultField.setEditable(false);
        labPanel.add(labAttendanceResultField);


        // Lab Class Standing Calculator Section
        addSection(labPanel, "--- Prelim Lab Class Standing Calculator ---");
        labPanel.add(createLabel("Formula:", darkForeground));
        labPanel.add(createLabel("(0.6 * Lab Work Average) + (0.4 * Attendance)", darkForeground));
        labPanel.add(createLabel("Lab Work (0-100):", darkForeground));
        labWorkField = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labWorkField);

        labPanel.add(createLabel("Attendance (0-100):", darkForeground));
        labAttendanceField = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labAttendanceField);

        calculateLabClassStandingButton = createButton("Calculate Prelim Class Standing", darkBackground, darkForeground, darkBorder);
        labPanel.add(calculateLabClassStandingButton);

        labClassStandingResultField = createTextField(darkBackground, darkForeground, darkBorder);
        labClassStandingResultField.setEditable(false);
        labPanel.add(labClassStandingResultField);

        // Lab Prelim Exam Calculator Section
        addSection(labPanel, "--- Prelim Exam Calculator ---");
        labPanel.add(createLabel("Formula:", darkForeground));
        labPanel.add(createLabel("(0.2 * Java 1) + (0.3 * Java 2) + (0.2 * JS 1) + (0.3 * JS 2)", darkForeground));
        labPanel.add(createLabel("Java 1 Grade (0-100):", darkForeground));
        labJava1Field = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labJava1Field);

        labPanel.add(createLabel("Java 2 Grade (0-100):", darkForeground));
        labJava2Field = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labJava2Field);

        labPanel.add(createLabel("JS 1 Grade (0-100):", darkForeground));
        labJs1Field = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labJs1Field);

        labPanel.add(createLabel("JS 2 Grade (0-100):", darkForeground));
        labJs2Field = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labJs2Field);

        calculatePrelimExamButton = createButton("Calculate Prelim Exam", darkBackground, darkForeground, darkBorder);
        labPanel.add(calculatePrelimExamButton);

        labPrelimExamResultField = createTextField(darkBackground, darkForeground, darkBorder);
        labPrelimExamResultField.setEditable(false);
        labPanel.add(labPrelimExamResultField);

        // Lab Grade Calculator Section
        addSection(labPanel, "--- Laboratory Prelim Grade Calculator ---");
        labPanel.add(createLabel("Formula:", darkForeground));
        labPanel.add(createLabel("(0.6 * Prelim Exam Grade) + (0.4 * Class Standing)", darkForeground));
        labPanel.add(createLabel("Prelim Exam Grade (0-100):", darkForeground));
        labExamField = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labExamField);

        labPanel.add(createLabel("Prelim Class Standing (0-100):", darkForeground));
        labPrelimClassStandingField = createTextField(darkBackground, darkForeground, darkBorder);
        labPanel.add(labPrelimClassStandingField);

        JButton labCalculateButton = createButton("Calculate Final Prelim Grade", darkBackground, darkForeground, darkBorder);
        labPanel.add(labCalculateButton);

        labResultArea = createTextArea(darkBackground, darkForeground, darkBorder);
        labPanel.add(new JScrollPane(labResultArea));

        tabbedPane.addTab("Laboratory", labPanel);

        add(tabbedPane, BorderLayout.CENTER);

        // Button actions
        calculateQuizzesButton.addActionListener(e -> calculatePrelimQuizzes());
        calculateAttendanceButton.addActionListener(e -> calculateLectureAttendance(attendanceCheckBoxes, lectureAttendanceResultField));
        labCalculateAttendanceButton.addActionListener(e -> calculateLabAttendance(labAttendanceCheckBoxes, labAttendanceResultField));
        calculateClassStandingButton.addActionListener(e -> calculatePrelimClassStanding());
        calculateButton.addActionListener(e -> calculateLectureGrade());
        labCalculateButton.addActionListener(e -> calculateLabGrade());
        calculateLabWorkButton.addActionListener(e -> calculateLabWork());
        calculateLabClassStandingButton.addActionListener(e -> calculatePrelimLabClassStanding());
        calculatePrelimExamButton.addActionListener(e -> calculatePrelimExam());
    }

    private JPanel createPanel(Color background, Color foreground, Color border) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBackground(background);
        panel.setForeground(foreground);
        panel.setBorder(BorderFactory.createLineBorder(border));
        return panel;
    }

    private JLabel createLabel(String text, Color foreground) {
        JLabel label = new JLabel(text);
        label.setForeground(foreground);
        return label;
    }

    private JTextField createTextField(Color background, Color foreground, Color border) {
        JTextField textField = new JTextField();
        textField.setBackground(background);
        textField.setForeground(foreground);
        textField.setBorder(BorderFactory.createLineBorder(border));
        textField.setCaret(new DefaultCaret() {
            {
                setBlinkRate(500); // Set blink rate (in milliseconds)
            }

            @Override
            protected synchronized void damage(Rectangle r) {
                if (r == null) return;
                x = r.x;
                y = r.y;
                width = 2; // Width of the caret
                height = r.height;
                repaint();
            }

            @Override
            public void paint(Graphics g) {
                if (isVisible()) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(Color.WHITE); // Set cursor color to white
                    g2d.fillRect(x, y, width, height); // Draw the caret
                }
            }
        });
        return textField;
    }

    private JButton createButton(String text, Color background, Color foreground, Color border) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setBorder(BorderFactory.createLineBorder(border));
        return button;
    }

    private JTextArea createTextArea(Color background, Color foreground, Color border) {
        JTextArea textArea = new JTextArea();
        textArea.setBackground(background);
        textArea.setForeground(foreground);
        textArea.setBorder(BorderFactory.createLineBorder(border));
        textArea.setCaret(new DefaultCaret() {
            {
                setBlinkRate(500); // Set blink rate (in milliseconds)
            }

            @Override
            protected synchronized void damage(Rectangle r) {
                if (r == null) return;
                x = r.x;
                y = r.y;
                width = 2; // Width of the caret
                height = r.height;
                repaint();
            }

            @Override
            public void paint(Graphics g) {
                if (isVisible()) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(Color.WHITE); // Set cursor color to white
                    g2d.fillRect(x, y, width, height); // Draw the caret
                }
            }
        });
        textArea.setEditable(false);
        return textArea;
    }

    private void addSection(JPanel panel, String title) {
        panel.add(createLabel(title, new Color(255, 255, 255)));
        panel.add(new JLabel(""));
    }

    private void calculatePrelimQuizzes() {
        try {
            // Check for empty fields
            if (lectureEssayField.getText().isEmpty() || lecturePvmField.getText().isEmpty() ||
                lectureJavaBasicsField.getText().isEmpty() || lectureJsIntroField.getText().isEmpty()) {
                quizzesResultField.setText(" All fields are required to have an input.");
                return;
            }
    
            // Parse input values
            double essayGrade = Double.parseDouble(lectureEssayField.getText());
            double pvmGrade = Double.parseDouble(lecturePvmField.getText());
            double jsBasicGrade = Double.parseDouble(lectureJavaBasicsField.getText());
            double javaIntroGrade = Double.parseDouble(lectureJsIntroField.getText());
    
            // Validate input ranges (assuming valid grades range from 0 to 100 for essay, 0 to max for others)
            if (essayGrade < 0 || essayGrade > 100 || pvmGrade < 0 || pvmGrade > 60 ||
                jsBasicGrade < 0 || jsBasicGrade > 40 || javaIntroGrade < 0 || javaIntroGrade > 40) {
                quizzesResultField.setText(" Invalid grade range");
                return;
            }
    
            // Standardize grades
            double scaledPvm = (pvmGrade / 60) * 100;
            double scaledJsBasic = (jsBasicGrade / 40) * 100;
            double scaledJavaIntro = (javaIntroGrade / 40) * 100;
    
            // Ensure values don't exceed 100
            scaledPvm = Math.min(scaledPvm, 100);
            scaledJsBasic = Math.min(scaledJsBasic, 100);
            scaledJavaIntro = Math.min(scaledJavaIntro, 100);
    
            // Compute Prelim Quizzes Grade (Essay: 25%, PVM: 25%, JS Basic: 25%, Java Intro: 25%)
            double prelimQuizzesGrade = (essayGrade * 0.25) + (scaledPvm * 0.25) + 
                                        (scaledJsBasic * 0.25) + (scaledJavaIntro * 0.25);
    
            quizzesResultField.setText(String.format("%.2f", prelimQuizzesGrade));
        } catch (NumberFormatException e) {
            quizzesResultField.setText("Invalid input: Enter numbers only");
        } catch (Exception e) {
            quizzesResultField.setText("An unexpected error occurred");
        }
    }
    
    private void calculateLectureAttendance(JCheckBox[] checkBoxes, JTextField resultField) {
        int absences = 0;
    
        // Count unchecked checkboxes as absences
        for (JCheckBox checkBox : checkBoxes) {
            if (!checkBox.isSelected()) {
                absences++;
            }
        }
    
        // Automatic fail if 4 or more absences
        double attendance = (absences >= 4) ? 0 : Math.max(100 - (absences * 10), 0);
    
        // Display result with fail condition
        if (absences >= 4) {
            resultField.setText("Attendance: FAIL (Below 60%)");
        } else {
            resultField.setText("Attendance: " + attendance + "%");
        }
    }
    
    

    private void calculateLabAttendance(JCheckBox[] checkBoxes, JTextField resultField) {
        int absences = 0;
    
        // Count unchecked boxes (absences)
        for (JCheckBox checkBox : checkBoxes) {
            if (!checkBox.isSelected()) {
                absences++;
            }
        }
    
        // Apply deduction (10% per absence, auto-fail at 4 absences)
        double attendance = Math.max(100 - (absences * 10), 0);
        
        if (absences >= 4) {
            resultField.setText("Attendance: FAIL (Below 60%)");
        } else {
            resultField.setText("Attendance: " + attendance + "%");
        }
    }
    

    private void calculatePrelimClassStanding() {
        try {
            double prelimQuizzes = Double.parseDouble(lecturePrelimQuizzesField.getText());
            double attendance = Double.parseDouble(lectureAttendanceField.getText());
            double classStanding = (prelimQuizzes * 0.6) + (attendance * 0.4);
            lectureClassStandingResultField.setText("Prelim Class Standing: " + classStanding + "%");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateLectureGrade() {
        try {
            double prelimExam = Double.parseDouble(lectureExamField.getText());
            double prelimClassStanding = Double.parseDouble(lecturePrelimClassStandingField.getText());
            double prelimGrade = (prelimExam * 0.6) + (prelimClassStanding * 0.4);
            resultArea.setText("Final Prelim Grade: " + prelimGrade + "%");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateLabGrade() {
        try {
            double prelimExam = Double.parseDouble(labExamField.getText());
            double prelimClassStanding = Double.parseDouble(labPrelimClassStandingField.getText());
            double prelimGrade = (prelimExam * 0.6) + (prelimClassStanding * 0.4);
            labResultArea.setText("Final Prelim Grade: " + prelimGrade + "%"); // Set result in the text area
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateLabWork() {
        try {
            double mp1 = Double.parseDouble(labMp1Field.getText());
            double mp2 = Double.parseDouble(labMp2Field.getText());
            double mp3 = Double.parseDouble(labMp3Field.getText());
            double mp3Doc = Double.parseDouble(labMp3DocField.getText());

            double labWorkGrade = (mp1 + mp2 + mp3 + mp3Doc) / 4;
            labWorkResultField.setText("Lab Work Grade: " + labWorkGrade + "%");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculatePrelimLabClassStanding() {
        try {
            double labWork = Double.parseDouble(labWorkResultField.getText().replaceAll("[^0-9.]", ""));
            double attendance = Double.parseDouble(labAttendanceField.getText());
            double prelimLabClassStanding = (labWork * 0.6) + (attendance * 0.4);
            labClassStandingResultField.setText("Lab Class Standing: " + prelimLabClassStanding + "%");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Lab Work and Attendance.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculatePrelimExam() {
        try {
            double java1 = Double.parseDouble(labJava1Field.getText());
            double java2 = Double.parseDouble(labJava2Field.getText());
            double js1 = Double.parseDouble(labJs1Field.getText());
            double js2 = Double.parseDouble(labJs2Field.getText());
            double prelimExam = (java1 * 0.2) + (java2 * 0.3) + (js1 * 0.2) + (js2 * 0.3);
            labPrelimExamResultField.setText("Prelim Exam: " + prelimExam + "%");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new app().setVisible(true));
    }
}
