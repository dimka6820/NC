import game.StartGame;
import wait.WaitingClients;

/**
* Created by Дмитрий on 16.11.2015.
        */
public class Start {
    public static void main(String[] args) {
        WaitingClients waitingClients = new WaitingClients();
        waitingClients.start();

        StartGame startGame = new StartGame();
        startGame.start();
    }
}
