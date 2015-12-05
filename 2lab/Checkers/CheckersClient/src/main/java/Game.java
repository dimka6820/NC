import models.Move;
import models.Player;
import org.apache.log4j.Logger;
import shells.Shell;
import shells.TypeObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Дмитрий on 16.11.2015.
 */
public class Game {
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private BufferedReader keyboard;
    private Player opponentPlayer;
    private StringBuilder println;

    private final Logger log;
    private Shell shell;

    public Game(ObjectOutputStream outputStream, ObjectInputStream inputStream, BufferedReader keyboard) {
        log = Logger.getLogger(Game.class.getName());
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.keyboard = keyboard;
        println = new StringBuilder();
        shell = new Shell();
    }

    public void run() {
        try {
            opponentPlayer = (Player) inputStream.readObject();
            System.out.println("Opponent: " + opponentPlayer);
            boolean gameOver = false;
            while (true) {
                println.setLength(0);
                shell = (Shell) inputStream.readObject();

                for (TypeObject item : shell.getObjectList().keySet()) {
                    if (item == TypeObject.PLAYER) {
                        Player winner = (Player) shell.getObjectList().get(item);
                        gameOver = true;
                        if (!opponentPlayer.equals(winner)) System.out.println("you lose");
                        else System.out.println("you won");
                    }
                    println.append(shell.getObjectList().get(item).toString());
                }
                System.out.println(println);
                if (gameOver) break;
                outputStream.writeObject(enterMove());
            }
        } catch (IOException e) {
            log.error(e);
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
    }

    private Move enterMove() throws IOException {
        Move move = new Move();
        while (true) {
            System.out.println("Enter From: ");
            String from = keyboard.readLine();
            if (check(from)) {
                move.setFromX(from.charAt(0) - 48);
                move.setFromY(from.charAt(1) - 48);
                break;
            } else {
                System.out.println("ERROR");
            }
        }
        String to = "";
        while (true) {
            while (true) {
                System.out.println("Enter to: (if exit, enter 0)");
                to = keyboard.readLine();
                if (check(to) || to.equals("0")) {
                    break;
                } else {
                    System.out.println("ERROR");
                }

            }
            if (to.equals("0")) break;
            char[] temp;
            temp = to.toCharArray();
            move.setTo(new int[]{temp[0] - 48, temp[1] - 48});
        }
        return move;
    }

    private boolean check(String testString) {
        Pattern p = Pattern.compile("^[1-8][1-8]$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }
}
