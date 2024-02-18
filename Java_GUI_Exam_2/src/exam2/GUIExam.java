package exam2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Define a class that extends JFrame and implements ActionListener
public class GUIExam extends JFrame implements ActionListener {

	// Declare some constants for the window size and the number of questions
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int NUM_QUESTIONS = 5;

	// Declare some arrays to store the questions and answers for each section
	// These questions and answers can be change
	private String[] idQuestions = {"What is the name of the Java compiler?",
			"What is the name of the Java virtual machine?",
			"What is the extension of a Java source file?",
			"What is the keyword for inheritance in Java?",
	"What is the symbol for concatenation in Java?"};

	private String[] idAnswers = {"javac",
			"java",
			".java",
			"extends",
	"+"};

	private String[] tfQuestions = {"Java is a case-sensitive language.",
			"Java supports multiple inheritance.",
			"Java supports operator overloading.",
			"Java uses pass-by-reference for objects.",
	"Java has primitive and reference data types."};

	private boolean[] tfAnswers = {true,
			false,
			false,
			false,
			true};

	private String[] mcQuestions = {"Which of these is a valid comment in Java?",
			"Which of these is a valid identifier in Java?",
			"Which of these is a valid way to create an object in Java?",
			"Which of these is a valid way to declare an array in Java?",
	"Which of these is a valid way to cast an int to a double in Java?"};

	private String[][] mcOptions = {{"// This is a comment", "/* This is a comment */", "/** This is a comment */", "All of the above"},
			{"_name", "1stName", "name$", "All of the above"},
			{"Object obj = new Object();", "Object obj;", "Object obj();", "All of the above"},
			{"int[] arr = new int[10];", "int arr[] = new int[10];", "int[] arr = {1, 2, 3};", "All of the above"},
			{"(double) x", "double(x)", "x.doubleValue()", "None of the above"}};

	private int[] mcAnswers = {3, 3, 0, 3, 0};

	// Declare some variables to store the user's answers and score
	private String[] idUserAnswers = new String[NUM_QUESTIONS];
	private boolean[] tfUserAnswers = new boolean[NUM_QUESTIONS];
	private int[] mcUserAnswers = new int[NUM_QUESTIONS];
	private int score = 0;

	// Declare some GUI components as instance variables
	private JPanel mainPanel;
	private JPanel readyPanel;
	private JPanel idPanel;
	private JPanel tfPanel;
	private JPanel mcPanel;
	private JPanel scorePanel;
	private JCheckBox readyBox;
	private JTextField[] idFields;
	private JRadioButton[] tfButtons;
	private JComboBox[] mcBoxes;
	private JButton idSubmit;
	private JButton tfSubmit;
	private JButton mcSubmit;
	private JButton finish;
	private JLabel scoreLabel;

	// Define the constructor of the class
	public GUIExam() {
		// Set the title, size, layout and default close operation of the frame
		super("Java Exam");
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Center the frame on the monitor
		setLocationRelativeTo(null);

		// Create the main panel that will hold the other panels
		mainPanel = new JPanel();
		mainPanel.setLayout(new CardLayout());

		// Create the ready panel that will ask the user if they are ready for the exam
		readyPanel = new JPanel();
		readyPanel.setLayout(new FlowLayout());
		JLabel readyLabel = new JLabel("Are you ready for the exam?");
		readyBox = new JCheckBox("Yes, I am ready.");
		readyBox.addActionListener(this);
		readyPanel.add(readyLabel);
		readyPanel.add(readyBox);

		// Create the identification panel that will contain the identification questions and answer fields
		idPanel = new JPanel();
		idPanel.setLayout(new GridLayout(NUM_QUESTIONS + 1, 2));
		idFields = new JTextField[NUM_QUESTIONS];
		for (int i = 0; i < NUM_QUESTIONS; i++) {
			JLabel idLabel = new JLabel(idQuestions[i]);
			idFields[i] = new JTextField(20);
			idPanel.add(idLabel);
			idPanel.add(idFields[i]);
		}
		idSubmit = new JButton("Submit");
		idSubmit.addActionListener(this);
		idPanel.add(new JLabel()); // Add an empty label to fill the grid
		idPanel.add(idSubmit);

		// Create the true or false panel that will contain the true or false questions and answer buttons
		tfPanel = new JPanel();
		tfPanel.setLayout(new GridLayout(NUM_QUESTIONS + 1, 3));
		tfButtons = new JRadioButton[NUM_QUESTIONS * 2];
		ButtonGroup[] tfGroups = new ButtonGroup[NUM_QUESTIONS];
		for (int i = 0; i < NUM_QUESTIONS; i++) {
			JLabel tfLabel = new JLabel(tfQuestions[i]);
			tfButtons[i * 2] = new JRadioButton("True");
			tfButtons[i * 2 + 1] = new JRadioButton("False");
			tfGroups[i] = new ButtonGroup();
			tfGroups[i].add(tfButtons[i * 2]);
			tfGroups[i].add(tfButtons[i * 2 + 1]);
			tfPanel.add(tfLabel);
			tfPanel.add(tfButtons[i * 2]);
			tfPanel.add(tfButtons[i * 2 + 1]);
		}
		tfSubmit = new JButton("Submit");
		tfSubmit.addActionListener(this);
		tfPanel.add(new JLabel()); // Add an empty label to fill the grid
		tfPanel.add(tfSubmit);
		tfPanel.add(new JLabel()); // Add an empty label to fill the grid

		// Create the multiple choice panel that will contain the multiple choice questions and answer boxes
		mcPanel = new JPanel();
		mcPanel.setLayout(new GridLayout(NUM_QUESTIONS + 1, 2));
		mcBoxes = new JComboBox[NUM_QUESTIONS];
		for (int i = 0; i < NUM_QUESTIONS; i++) {
			JLabel mcLabel = new JLabel(mcQuestions[i]);
			mcBoxes[i] = new JComboBox(mcOptions[i]);
			mcPanel.add(mcLabel);
			mcPanel.add(mcBoxes[i]);
		}
		mcSubmit = new JButton("Submit");
		mcSubmit.addActionListener(this);
		mcPanel.add(new JLabel()); // Add an empty label to fill the grid
		mcPanel.add(mcSubmit);

		// Create the score panel that will display the score and the finish button
		scorePanel = new JPanel();
		scorePanel.setLayout(new FlowLayout());
		scoreLabel = new JLabel("Your score is: " + score + " / " + (NUM_QUESTIONS * 3));
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
		scoreLabel.setForeground(Color.BLUE);
		finish = new JButton("Finish");
		finish.addActionListener(this);
		scorePanel.add(scoreLabel);
		scorePanel.add(finish);

		// Add all the panels to the main panel
		mainPanel.add(readyPanel, "Ready");
		mainPanel.add(idPanel, "Identification");
		mainPanel.add(tfPanel, "True or False");
		mainPanel.add(mcPanel, "Multiple Choice");
		mainPanel.add(scorePanel, "Score");

		// Add the main panel to the frame
		add(mainPanel, BorderLayout.CENTER);

		// Make the frame visible
		setVisible(true);
	}

	// Define the method that handles the action events
	public void actionPerformed(ActionEvent e) {
		// Get the source of the event
		Object source = e.getSource();

		// If the source is the ready checkbox
		if (source == readyBox) {
			// If the checkbox is selected
			if (readyBox.isSelected()) {
				// Show the identification panel and hide the ready panel
				CardLayout cl = (CardLayout) mainPanel.getLayout();
				cl.show(mainPanel, "Identification");
			}
		}

		// If the source is the identification submit button
		if (source == idSubmit) {
			// Get the user's answers from the text fields and store them in the array
			for (int i = 0; i < NUM_QUESTIONS; i++) {
				idUserAnswers[i] = idFields[i].getText().trim();
			}

			// Ask the user if they are satisfied with their answers using a dialog box
			int idChoice = JOptionPane.showConfirmDialog(this, "Are you satisfied with your answers?", "Confirmation", JOptionPane.YES_NO_OPTION);

			// If the user chooses yes
			if (idChoice == JOptionPane.YES_OPTION) {
				// Show the true or false panel and hide the identification panel
				CardLayout cl = (CardLayout) mainPanel.getLayout();
				cl.show(mainPanel, "True or False");
			}
		}

		// If the source is the true or false submit button
		if (source == tfSubmit) {
			// Get the user's answers from the radio buttons and
			for (int i = 0; i < NUM_QUESTIONS; i++) {
				if (tfButtons[i * 2].isSelected()) {
					tfUserAnswers[i] = true;
				} else if (tfButtons[i * 2 + 1].isSelected()) {
					tfUserAnswers[i] = false;
				} else {
					// If the user hasn't selected an answer for a question, show an error message
					JOptionPane.showMessageDialog(this, "Please answer all questions.", "Error", JOptionPane.ERROR_MESSAGE);
					return; // exit the method
				}
			}

			// Ask the user if they are satisfied with their answers using a dialog box
			int tfChoice = JOptionPane.showConfirmDialog(this, "Are you satisfied with your answers?", "Confirmation", JOptionPane.YES_NO_OPTION);

			// If the user chooses yes
			if (tfChoice == JOptionPane.YES_OPTION) {
				// Show the multiple choice panel and hide the true or false panel
				CardLayout cl = (CardLayout) mainPanel.getLayout();
				cl.show(mainPanel, "Multiple Choice");
			}
		}

		// If the source is the multiple choice submit button
		if (source == mcSubmit) {
			// Get the user's answers from the combo boxes and store them in the array
			for (int i = 0; i < NUM_QUESTIONS; i++) {
				mcUserAnswers[i] = mcBoxes[i].getSelectedIndex();
			}

			// Calculate the score
			score = calculateScore();

			// Update the score label
			scoreLabel.setText("Your score is: " + score + " / " + (NUM_QUESTIONS * 3));

			// Show the score panel and hide the multiple choice panel
			CardLayout cl = (CardLayout) mainPanel.getLayout();
			cl.show(mainPanel, "Score");
		}

		// If the source is the finish button
		if (source == finish) {
			// Close the application
			System.exit(0);
		}
	}

	// Define a method to calculate the score
	private int calculateScore() {
		int totalScore = 0;

		// Check identification answers
		for (int i = 0; i < NUM_QUESTIONS; i++) {
			if (idUserAnswers[i].equalsIgnoreCase(idAnswers[i])) {
				totalScore++;
			}
		}

		// Check true or false answers
		for (int i = 0; i < NUM_QUESTIONS; i++) {
			if (tfUserAnswers[i] == tfAnswers[i]) {
				totalScore++;
			}
		}

		// Check multiple choice answers
		for (int i = 0; i < NUM_QUESTIONS; i++) {
			if (mcUserAnswers[i] == mcAnswers[i]) {
				totalScore++;
			}
		}

		return totalScore;
	}
}