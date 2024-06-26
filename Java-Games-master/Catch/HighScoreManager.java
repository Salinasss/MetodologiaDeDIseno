import java.io.*;

public class HighScoreManager {
    private String archivo = "highscore.txt";
    private int highScore;

    public HighScoreManager() {
        highScore = cargarScore();
    }

    public int getHighScore() {
        return highScore;
    }

    public void checkScore(int score) {
        if (score > highScore) {
            highScore = score;
            salvarScore(highScore);
        }
    }

    private int cargarScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            // Si ocurre un error al cargar o el archivo no existe, se inicia con una puntuación de 0
            return 0;
        }
    }

    private void salvarScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
