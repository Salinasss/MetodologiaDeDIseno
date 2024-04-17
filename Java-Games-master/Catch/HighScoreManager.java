import java.io.*;

public class HighScoreManager {
    private String fileName = "highscore.txt";
    private int highScore;

    public HighScoreManager() {
        highScore = loadScore();
    }

    public int getHighScore() {
        return highScore;
    }

    public void checkScore(int score) {
        if (score > highScore) {
            highScore = score;
            saveScore(highScore);
        }
    }

    private int loadScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            // Si ocurre un error al cargar o el archivo no existe, se inicia con una puntuación de 0
            return 0;
        }
    }

    private void saveScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
