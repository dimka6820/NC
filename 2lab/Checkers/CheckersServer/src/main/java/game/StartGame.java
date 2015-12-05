package game;

import org.apache.log4j.Logger;
import sessions.Session;
import stasuses.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 16.11.2015.
 */
public class StartGame extends Thread {
    public static List<Session> sessions;
    private final Logger log;

    public StartGame() {
        sessions = new ArrayList<Session>();
        log = Logger.getLogger(StartGame.class.getName());
    }

    @Override
    public void run() {
        while (true) {
            if (sessions.size() >= 2) {
                for (int i = 0; i < sessions.size(); i += 2) {
                    if (i + 1 != sessions.size()) {
                        sessions.get(i).getPlayer().setStatus(Status.GAMING);
                        sessions.get(i + 1).getPlayer().setStatus(Status.GAMING);
                        new Game(sessions.get(i).getPlayer(), sessions.get(i + 1).getPlayer()).start();
                        sessions.remove(i);
                        sessions.remove(i);
                        i = 0;
                    }
                }
            }

            try {
                sleep(3000);
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }
}
