import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Class KanaFlashCardsTest
 * Created by Jason Parsons
 * 
 * Purpose: This is a simple program that allows the user to test their knowledge of Japanese Hiragana and Katakana using 
 * a flashcard test. The user is presented with a GUI that allows them to choose between a Hiragana or a Katakana test. 
 * Once a test type is chosen, the user is quizzed on each Hiragana or Katakana symbol in a random order until all 46 have 
 * been completed. The user must enter the english romanization of eeach character (ie: if the user is shown the 
 * Hiragana symbol あ, they would answer with 'a'). At the end of the test, the user is presented with a score out of 46 
 * and asked if they'd like to test again.

 * Functionally, the program reads in text files containg key value pairs for hiragana and katakana (hiragana.txt and katakana.txt) 
 * and parses them into two separate hashmaps (one for hiragana and one for katakana). These test files are located at the root of 
 * the project's folder structure.
 */

public class KanaFlashCardTest {
    // HashMaps for each Kana type
    private static final Map<String, String> HIRAGANA_MAP = createMapFromFile("./KanaFlashCards/hiragana.txt");
    private static final Map<String, String> KATAKANA_MAP = createMapFromFile("./KanaFlashCards/katakana.txt");

    private static Map<String, String> createMapFromFile(String filename) {
        // Create a HashMap to store the key-value pairs
        Map<String, String> newMap = new HashMap<>();

        // Read the text file
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    newMap.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newMap;
    }

    // Show initial UI to allow user to choose the test type
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Kana Flash Card Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel instructionLabel = new JLabel("Choose Hiragana or Katakana test:");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
        panel.add(instructionLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.add(buttonPanel);

        JButton hiraganaButton = new JButton("Hiragana");
        JButton katakanaButton = new JButton("Katakana");
        buttonPanel.add(hiraganaButton);
        buttonPanel.add(katakanaButton);

        hiraganaButton.addActionListener(e -> startTest(HIRAGANA_MAP, frame));
        katakanaButton.addActionListener(e -> startTest(KATAKANA_MAP, frame));

        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    // Begin the test
    private static void startTest(Map<String, String> kanaMap, JFrame frame) {
        List<Map.Entry<String, String>> entries = new ArrayList<>(kanaMap.entrySet());
        Collections.shuffle(entries);

        int score = 0;
        for (Map.Entry<String, String> entry : entries) {
            String question = entry.getKey();
            String correctAnswer = entry.getValue();

            UIManager.put("OptionPane.messageFont", new Font("Yu Gothic", Font.BOLD, 20));
            
            String userAnswer = JOptionPane.showInputDialog(frame, "What is the romanization of " + question + "?");
            // Center the dialog box
            JOptionPane.getRootFrame().setLocationRelativeTo(null);

            if (userAnswer != null && userAnswer.equalsIgnoreCase(correctAnswer)) {
                JOptionPane.showMessageDialog(frame, "Correct!");
                score++;
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect. The correct answer is: " + correctAnswer);
            }

            // Center the dialog box
            JOptionPane.getRootFrame().setLocationRelativeTo(null);
        }

        int result = JOptionPane.showConfirmDialog(frame, "Test complete! Your score: " + score + "/46. Do you want to play again?", "Test Over", JOptionPane.YES_NO_OPTION);
        frame.dispose(); // Close the original frame
        if ( result == JOptionPane.YES_OPTION ) {
            createAndShowGUI();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KanaFlashCardTest::createAndShowGUI);
    }
}
