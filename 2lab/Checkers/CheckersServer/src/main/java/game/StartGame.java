package game;

import sessions.Session;
import stasuses.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 16.11.2015.
 */
public class StartGame extends Thread {
    public static List<Session> sessions;

    public StartGame() {
        sessions = new ArrayList<Session>();
    }

    @Override
    public void run() {
        while (true) {
            if (sessions.size() >= 2) {
                for (int i = 0; i < sessions.size(); i += 2) {
                    if (i + 1 != sessions.size()) {
                        try {
                            sessions.get(i).getPlayer().setStatus(Status.GAMING);
                            sessions.get(i+1).getPlayer().setStatus(Status.GAMING);
                            Game game = new Game(sessions.get(i).getPlayer(), sessions.get(i+1).getPlayer());
                            game.start();
                            sessions.remove(i);
                            sessions.remove(i);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
