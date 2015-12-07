package game;

import checkers.Checker;
import models.Move;
import models.Player;
import org.apache.log4j.Logger;
import shells.EnterError;
import shells.Shell;
import shells.TypeObject;
import wait.WaitingClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 10.11.2015.
 */
public class Game extends Thread {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player currentPlayer;
    private Player winner;
    private Shell shell;

    private List<Checker> whiteCheckers;
    private List<Checker> blackCheckers;
    private List<Checker> currentCheckers;
    private List<Checker> opponentCheckers;
    private final Logger log;

    private boolean whoseTurn;

    public Game(Player whitePlayer, Player blackPlayer) {
        log = Logger.getLogger(Game.class.getName());
        this.whitePlayer = currentPlayer = whitePlayer;
        this.blackPlayer = blackPlayer;

        whiteCheckers = new ArrayList<Checker>();
        blackCheckers = new ArrayList<Checker>();
        createCheckers();
 //      whiteCheckers.add(new Checker(3, 3, true));
 //      whiteCheckers.add(new Checker(5, 3, true));
 //      whiteCheckers.add(new Checker(7, 5, true));

 //      blackCheckers.add(new Checker(4, 4, false));
 //      blackCheckers.add(new Checker(6, 6, false));
 //      blackCheckers.add(new Checker(6, 8, false));


        currentPlayer.setMove(true);
        currentCheckers = whiteCheckers;
        opponentCheckers = blackCheckers;
        whoseTurn = true;
        shell = new Shell();
        if (!whoMustMove()) whoCanMove();
    }

    public void run() {
        Checker currentChecker;
        List<Checker> tempList = new ArrayList<Checker>();
        try {
            whitePlayer.getOutputStream().writeObject(blackPlayer);
            blackPlayer.getOutputStream().writeObject(whitePlayer);
        } catch (IOException e) {
            log.error(e);
        }
        while (!gameOver()) {
            boolean isContinue = false;
            tempList.clear();
            for (Checker checker : currentCheckers) {
                if (checker.isCanMove()) tempList.add(checker);
            }
            try {
                shell.addObjectList(TypeObject.LIST, tempList);

                currentPlayer.getOutputStream().writeObject(shell);
                currentPlayer.getOutputStream().flush();

                shell = new Shell();

                Move move = (Move) currentPlayer.getInputStream().readObject();
                log.debug("Player: " + currentPlayer + "move: " + move);
                currentChecker = findMustMoveChecker(move);
                if (currentChecker == null) {
                    log.debug("ERROR: you can't walk this checker");
                    shell.addObjectList(TypeObject.ERROR, new EnterError("!!!you can't walk this checker!!!"));
                    isContinue = true;
                } else {
                    for (int[] i : move.getTo()) {
                        if (!moveChecker(currentChecker, i[0], i[1])) {
                            log.debug("ERROR: false move");
                            shell.addObjectList(TypeObject.ERROR, new EnterError("!!!false move!!!"));
                            isContinue = true;
                            break;
                        }
                    }
                }

            } catch (IOException e) {
                log.error(e);
            } catch (ClassNotFoundException e) {
                log.error(e);
            }
            if (!isContinue) {
                endTurn();
            }
        }
        try {
            shell.clearList();
            shell.addObjectList(TypeObject.PLAYER, winner);

            whitePlayer.getOutputStream().writeObject(shell);
            blackPlayer.getOutputStream().writeObject(shell);
        } catch (IOException e) {
            log.error(e);
        }
    }

    private Checker findMustMoveChecker(Move move) {
        for (Checker checker : currentCheckers) {
            if (move.getFromX() == (checker.getX()) && move.getFromY() == (checker.getY()) && checker.isCanMove()) {
                return checker;
            }
        }
        return null;
    }

    private void whoCanMove() {

        if (whoseTurn) {
            for (Checker c : currentCheckers) {
                if (placeIsEmpty(c.getX() + 1, c.getY() + 1) || placeIsEmpty(c.getX() - 1, c.getY() + 1)) {
                    c.setCanMove(true);
                } else {
                    c.setCanMove(false);
                }
            }
        } else {
            for (Checker c : currentCheckers) {
                if (placeIsEmpty(c.getX() + 1, c.getY() - 1) || placeIsEmpty(c.getX() - 1, c.getY() - 1)) {
                    c.setCanMove(true);
                } else {
                    c.setCanMove(false);
                }
            }
        }
    }

    private boolean whoMustMove() {
        boolean flag = false;
        for (Checker current : currentCheckers) {
            for (Checker opponent : opponentCheckers) {
                if ((opponent.getX() == current.getX() + 1) && (opponent.getY() == current.getY() + 1) && placeIsEmpty(opponent.getX() + 1, opponent.getY() + 1)) {
                    flag = true;
                    current.setCanMove(true);
                    break;
                }
                if ((opponent.getX() == current.getX() - 1) && (opponent.getY() == current.getY() + 1) && placeIsEmpty(opponent.getX() - 1, opponent.getY() + 1)) {
                    current.setCanMove(true);
                    flag = true;
                    break;
                }
                if ((opponent.getX() == current.getX() - 1) && (opponent.getY() == current.getY() - 1) && placeIsEmpty(opponent.getX() - 1, opponent.getY() - 1)) {
                    current.setCanMove(true);
                    flag = true;
                    break;
                }
                if ((opponent.getX() == current.getX() + 1) && (opponent.getY() == current.getY() - 1) && placeIsEmpty(opponent.getX() + 1, opponent.getY() - 1)) {
                    current.setCanMove(true);
                    flag = true;
                    break;
                }
                current.setCanMove(false);
            }
        }

        return flag;
    }

    public void endTurn() {
        whoseTurn = !whoseTurn;
        if (whoseTurn) {
            currentPlayer = whitePlayer;
            currentCheckers = whiteCheckers;
            opponentCheckers = blackCheckers;
        } else {
            currentPlayer = blackPlayer;
            currentCheckers = blackCheckers;
            opponentCheckers = whiteCheckers;
        }

        if (!whoMustMove()) whoCanMove();
    }

    private boolean placeIsEmpty(int x, int y) {
        if ((x < 1) || (8 < x) || (y < 1) || (8 < y)) {
            return false;
        }
        if ((x + y) % 2 == 1) {
            return false;
        }
        for (Checker c : whiteCheckers) {
            if ((c.getX() == x) && (c.getY() == y)) {
                return false;
            }
        }
        for (Checker c : blackCheckers) {
            if ((c.getX() == x) && (c.getY() == y)) {
                return false;
            }
        }
        return true;
    }


    private void createCheckers() {
        for (int i = 1; i < 5; i++) {
            whiteCheckers.add(new Checker(2 * i - 1, 1, true));
            whiteCheckers.add(new Checker(2 * i, 2, true));
            whiteCheckers.add(new Checker(2 * i - 1, 3, true));

            blackCheckers.add(new Checker(2 * i, 6, false));
            blackCheckers.add(new Checker(2 * i - 1, 7, false));
            blackCheckers.add(new Checker(2 * i, 8, false));
        }
    }

    private boolean moveChecker(Checker checker, int x, int y) {
        if (!placeIsEmpty(x, y)) {
            return false;
        }


        if (checker.isWhite()) {
            if (((x == checker.getX() - 1) && (y == checker.getY() + 1)) || ((x == checker.getX() + 1) && (y == checker.getY() + 1))) {
                checker.setX(x);
                checker.setY(y);
                if (checker.getY() == 8) {
                    checker.setKing();
                }
                return true;
            }
        } else {
            if (((x == checker.getX() - 1) && (y == checker.getY() - 1)) || ((x == checker.getX() + 1) && (y == checker.getY() - 1))) {
                checker.setX(x);
                checker.setY(y);
                if (checker.getY() == 1) {
                    checker.setKing();
                }
                return true;
            }
        }

        if ((x == checker.getX() + 2) && (y == checker.getY() + 2)) {
            for (Checker c : opponentCheckers) {
                if ((c.getX() == checker.getX() + 1) && (c.getY() == checker.getY() + 1)) {
                    move(checker, x, y);
                    opponentCheckers.remove(c);
                    return true;
                }
            }
            return false;
        }

        if ((x == checker.getX() - 2) && (y == checker.getY() + 2)) {
            for (Checker c : opponentCheckers) {
                if ((c.getX() == checker.getX() - 1) && (c.getY() == checker.getY() + 1)) {
                    move(checker, x, y);
                    opponentCheckers.remove(c);
                    return true;
                }
            }
            return false;
        }

        if ((x == checker.getX() - 2) && (y == checker.getY() - 2)) {
            for (Checker c : opponentCheckers) {
                if ((c.getX() == checker.getX() - 1) && (c.getY() == checker.getY() - 1)) {
                    move(checker, x, y);
                    opponentCheckers.remove(c);
                    return true;
                }
            }
            return false;
        }
        if ((x == checker.getX() + 2) && (y == checker.getY() - 2)) {
            for (Checker c : opponentCheckers) {
                if ((c.getX() == checker.getX() + 1) && (c.getY() == checker.getY() - 1)) {
                    move(checker, x, y);
                    opponentCheckers.remove(c);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private void move(Checker checker, int x, int y) {


        checker.setX(x);
        checker.setY(y);
        if (checker.getY() == 8 && checker.isWhite()) {
            checker.setKing();
        }
        if (checker.getY() == 1 && !checker.isWhite()) {
            checker.setKing();
        }

    }

    private boolean gameOver() {
        if (whiteCheckers.isEmpty() || blackCheckers.isEmpty()) {
            winner = currentPlayer;
            return true;
        }
        return false;
    }
}
