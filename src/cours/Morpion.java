package cours;

import java.util.Scanner;

public class Morpion {

    private final static int BOARD_WIDTH_HEIGHT = 3;

    private enum Roles { X, O, None }

    private static String[][] board;
    private static int turnNumber = 1;

    private static Scanner input;

    public static void main(String[] args) {
        startGame();
    }

    /**
     * Démarre le jeu
     */
    private static void startGame() {
        board = new String[BOARD_WIDTH_HEIGHT][BOARD_WIDTH_HEIGHT];
        Roles gagnant;

        input = new Scanner(System.in);

        do {
            int x = 0, y = 0;
            boolean ok;

            do {
                showBoard();

                try {
                    System.out.println("Entrez X: ");
                    x = input.nextInt();

                    System.out.println("Entrez Y: ");
                    y = input.nextInt();

                    if (x < BOARD_WIDTH_HEIGHT && y < BOARD_WIDTH_HEIGHT) {
                        if (board[x][y] == null) {
                            ok = true;
                        } else {
                            System.out.println("Cette position est déjà jouée !\nVeuillez rejouer ...");
                            ok = false;
                        }
                    } else {
                        ok = false;
                    }
                } catch (ArithmeticException e) {
                    ok = false;
                }

            } while (!ok);

            if (turnNumber % 2 == 1) {
                play(Roles.X, x, y);
            } else {
                play(Roles.O, x, y);
            }

            gagnant = checkVictory();
            turnNumber++;
        } while (gagnant == null);

        // Affichage du résultat
        showBoard();
        switch (checkVictory().toString()) {
            case "X":
                System.out.println("Le joueur 1 à gagné !");
                break;
            case "O":
                System.out.println("Le joueur 2 à gagné !");
                break;
            case "None":
                System.out.println("Personne n'a gagné..");
            default:
                System.out.println("WTF did you do ?!");
        }
    }

    /**
     * Affiche le plateau
     */
    private static void showBoard() {
        for (int i = 0; i < BOARD_WIDTH_HEIGHT; i++) {
            String line = "|";

            // Récupération de la ligne
            for (int j = 0; j < BOARD_WIDTH_HEIGHT; j++) {
                line += (board[i][j] != null) ? board[i][j] : " ";
                line +=  "|";
            }

            System.out.println(line);
        }
    }

    /**
     * Permet de jouer
     * @param role: Le role jouant
     * @param x: La position horizontale
     * @param y: La position verticale
     */
    private static void play(Roles role, int x, int y) {
        if (x < BOARD_WIDTH_HEIGHT && y < BOARD_WIDTH_HEIGHT) {
            board[y][x] = role.toString();
        }
    }

    /**
     * Vérifie qu'un joueur à gagné
     * @return role: Le gagnant
     */
    private static Roles checkVictory() {

        Roles gagnant = null;

        // Vérification sur l'axe horizontal
        for (int i = 0; i < BOARD_WIDTH_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH_HEIGHT; j++) {
                if (board[i][j] != null) {
                    if (BOARD_WIDTH_HEIGHT - j >= 3) {
                        if (board[i][j + 1] == board[i][j] && board[i][j + 2] == board[i][j]) {
                            gagnant = getRoleFromString(board[i][j]);
                        }
                    }
                }
            }
        }

        // Vérification sur l'axe vertical
        for (int i = 0; i < BOARD_WIDTH_HEIGHT; i++) {
            if (BOARD_WIDTH_HEIGHT - i >= 3) {
                for (int j = 0; j < BOARD_WIDTH_HEIGHT; j++) {
                    if (board[i][j] != null) {
                        if (board[i + 1][j] == board[i][j] && board[i + 2][j] == board[i][j]) {
                            gagnant = getRoleFromString(board[i][j]);
                        }
                    }
                }
            }
        }

        // Vérification sur l'axe diagonal
        for (int i = 0; i < BOARD_WIDTH_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH_HEIGHT; j++) {
                if (board[i][j] != null) {
                    if (BOARD_WIDTH_HEIGHT - j >= 3 && BOARD_WIDTH_HEIGHT - i >= 3) {
                        if (i - 2 >= 0 && j - 2 >= 0) {
                            if (board[i - 1][j - 1] == board[i][j] && board[i - 2][j - 2] == board[i][j]) {
                                gagnant = getRoleFromString(board[i][j]);
                            }
                        } else if (board[i + 1][j + 1] == board[i][j] && board[i + 2][j + 2] == board[i][j]) {
                            gagnant = getRoleFromString(board[i][j]);
                        }
                    }
                }
            }
        }

        return gagnant;
    }

    /**
     * Récupère le role à partir de son signe
     * @param role: Le signe du role
     * @return role: Le role lié au signe
     */
    private static Roles getRoleFromString(String role) {
        switch (role.trim().toUpperCase()) {
            case "X":
                return Roles.X;
            case "O":
                return Roles.O;
            default:
                return Roles.None;
        }
    }
}