package cours;

import java.util.Random;
import java.util.Scanner;

public class PlusOuMoins {

    private static final int MAX_NUMBER = 200;
    private static final int MIN_NUMBER = 0;
    private static final int MAX_TRIES = 10;

    private static int number;
    private static int guess = 0;
    private static int tries = 0;

    private static Scanner input;

    public static void main(String[] args) {

        input = new Scanner(System.in);

        initGame();

        do {
            guess();
        } while (guess != number && tries <= MAX_TRIES);

        // Vérification de la victoire
        if (tries <= MAX_TRIES) {
            System.out.println("Vous avez gagné en " + tries + " coups !");
        } else {
            System.out.println("Vous avez perdu, le nombre était " + number + ".");
        }
    }

    /**
     * Initialisation du jeu
     */
    private static void initGame() {
        Random rand = new Random();
        number = rand.nextInt(MAX_NUMBER) + MIN_NUMBER;
    }

    private static void guess() {
        boolean ok;
        do {
            try {
                System.out.println("Devenez le nombre entre " + MAX_NUMBER + " et " + MIN_NUMBER);
                guess = input.nextInt();
                ok = true;

                if (guess < number) {
                    System.out.println("Le nombre est plus grand");
                    tries++;
                } else if (guess > number) {
                    System.out.println("Le nombre est plus petit");
                    tries++;
                }
            } catch (Exception e) {
                ok = false;
            }
        } while (!ok);
    }
}