package game;

import model.Move;
import model.Player;
import stasuses.TypeChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 10.11.2015.
 */
public class Game extends Thread{
    private Player player;
    private Player adversary;
    private Move move;
    private List<Move> moves;

    private TypeChecker[][] board;

    public Game(Player player, Player adversary) throws IOException {
        this.player = player;
        this.player.setMove(true);
        this.adversary = adversary;
        this.player.setChecker(TypeChecker.BLACK);
        this.adversary.setChecker(TypeChecker.WHITE);
        move = new Move();
        moves = new ArrayList<Move>();

        player.getOutputStream().writeObject(adversary);
        adversary.getOutputStream().writeObject(player);

        board = new TypeChecker[8][8];
        fillingBoard();
    }

    @Override
    public void run() {
        Player playerNow = player;
        int fromLetter = 0;
        int fromNumber = 0;
        int toNumber = 0;
        int toLetter = 0;
        while (true) {
            try {
                Move playerMove = (Move) playerNow.getInputStream().readObject();
                if (playerNow.equals(player)) {
                    fromLetter = getInt(playerMove.getFrom().charAt(0));
                    fromNumber = (int) (8-(playerMove.getFrom().charAt(1) - 48));
                    toNumber = (int) (8-(playerMove.getTo().charAt(1) - 48));
                    toLetter = getInt(playerMove.getTo().charAt(0));
                } else {
                    fromLetter = 7-getInt(playerMove.getFrom().charAt(0));
                    fromNumber = playerMove.getFrom().charAt(1) - 48 -1;
                    toNumber = playerMove.getTo().charAt(1) - 48 -1;
                    toLetter = 7-getInt(playerMove.getTo().charAt(0));
                }

                if (board[fromNumber][fromLetter] != null) {
                    if (board[toNumber][toLetter] == null ) {
                        board[toNumber][toLetter] = board[fromNumber][fromLetter];
                        board[fromNumber][fromLetter] = null;
                        if (playerNow.equals(adversary)) {
                            playerNow = player;
                        } else {
                            playerNow = adversary;
                        }
                        playerNow.getOutputStream().writeObject(playerMove);
                        printBoard();
                        moves.add(playerMove);

                    } else {
                        playerNow.getOutputStream().writeObject("Неверный ход");
                    }
                } else {
                    playerNow.getOutputStream().writeObject("Неверный ход");
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int getInt(char a) {
        switch (a) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            default:
                return 0;
        }
    }

    private void fillingBoard() {
        int j;
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                j = 1;
            } else {
                j = 0;
            }

            for (; j < 8; j += 2) {
                if (i < 3) board[i][j] = TypeChecker.BLACK;
                if (i == 3) i = 4;
                if (i > 4) board[i][j] = TypeChecker.WHITE;
            }
        }
    }

    private void printBoard() {
        int j = 0;
        for (int i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println("\n");
        }
    }
}
