package sessions;

import game.Game;
import models.Player;
import org.apache.log4j.Logger;
import game.StartGame;
import wait.WaitingClients;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Дмитрий on 04.11.2015.
 */
public class Session extends Thread {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Player player;
    private final Logger log;
    private Game game;

    public Session(Socket socket) throws IOException {
        log = Logger.getLogger(Session.class.getName());
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        start();
    }

    @Override
    public void run() {
        try {

            player = (Player) inputStream.readObject();

            player.setInputStream(inputStream);
            player.setOutputStream(outputStream);
            StartGame.sessions.add(this);
        } catch (IOException e) {
            log.error(e);
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
