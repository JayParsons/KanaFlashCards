## Getting Started

This is a simple program that allows the user to test their knowledge of Japanese Hiragana and Katakana using a flashcard test. The user is presented with a GUI that allows them to choose between a Hiragana or a Katakana test. Once a test type is chosen, the user is quizzed on each Hiragana or Katakana symbol in a random order until all 46 have been completed. The user must enter the english romanization of eeach character (ie: if the user is shown the Hiragana symbol あ, they would answer with 'a'). At the end of the test, the user is presented with a score out of 46 and asked if they'd like to test again.

Functionally, the program reads in text files containg key value pairs for hiragana and katakana (hiragana.txt and katakana.txt) and parses them into two separate hashmaps (one for hiragana and one for katakana). These test files are located at the root of the project's folder structure.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources:
    - KanaFlashCardTest.java
- `lib`: the folder to maintain dependencies (empty as there are no dependencies for this project)

## Running the Program

The code was developed in VS Code using Java 1.8.0_321. Depending on which version of the JRE you have installed you can run this program in any IDE of your choosing, or you can compile and run the program via the command line as follows (from the src folder):

- javac KanaFlashCardTest.java
- java KanaFlashCardTest.java

## Future upgradees

This program is merely for demonstration purposes, but if further work is done on it, these are some possible improvements to pursue:

- The ability to output a report containg the user's results from a given session
- A means of saving user's and their associated data to persist between sessions
- A nicer more robust UI
- Turn this into a mobile app for iOS and Android
