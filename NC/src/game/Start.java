package game;

import game.controller.Controller;
import game.users.Dealer;

/**
 * Created by ִלטענטי on 21.10.2015.
 */
public class Start {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        Controller controller = new Controller(dealer);
        controller.start();
    }
}
