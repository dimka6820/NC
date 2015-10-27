package game.controller;

import game.Status;
import game.users.Dealer;
import game.users.Player;
import game.users.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ִלטענטי on 21.10.2015.
 */
public class Controller {

    private Scanner scanner;
    private int countPlayer;
    private List<Player> players;
    private Dealer dealer;

    public Controller(Dealer dealer) {
        scanner = new Scanner(System.in);
        this.players = new ArrayList<Player>();
        this.dealer = dealer;
    }

    public void start() {
        boolean flag = true;
        System.out.println("Welcome!\n");
        Dealer dealer = new Dealer();

        System.out.println("Enter count Player:");
        countPlayer = enterInt();

        for (int i = 0; i < countPlayer; i++) {
            Player player = new Player(i + 1);
            player.addCard(dealer.returnCard());
            players.add(player);
        }


        for (Player item : players) {
            System.out.println("Hello, " + item.toString() + ", money = " + item.getMoney() + "\n" +
                    "Enter your rate: ");
            item.setRate(enterInt());
            System.out.println("Your rate : " + item.getRate());
        }

        while (true)
        {
            System.out.println(" Games?:\n" +
                    "1. Yes\n" +
                    "2. NO");

            switch (enterInt()) {
                case 1:
                    for (Player item: players) {
                        item.getCards().clear();
                        if(!flag)item.addCard(dealer.returnCard());
                        System.out.println(item.toString() + ", money = " + item.getMoney());
                    }
                    flag = false;
                    games();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Enter number from 1 to 2");
                    break;
            }
        }
    }

    private void games() {
        boolean flag = true;

        while (true) {
            if (flag) {
                flag = false;
                for (Player item : players) {
                    if (item.getStatus() == Status.GAME) {
                        flag = true;
                        System.out.println(item.toString() + " Enter item:\n" +
                                "1. Yet\n" +
                                "2. Result");

                        switch (enterInt()) {
                            case 1:
                                item.addCard(dealer.returnCard());
                                System.out.println(item.toString());
                                if (item.countPoints() > 21) {
                                    System.out.println("BUST\n");
                                    item.setStatus(Status.BUST);
                                    break;
                                }
                                break;
                            case 2:
                                item.setStatus(Status.STOP);
                                break;
                            default:
                                System.out.println("Enter number from 1 to 2");
                                break;
                        }
                    }
                }
            } else {
                result();
                return;
            }
        }

    }

    private void result() {
        System.out.println("result:\n"+dealer.toString());
        Player championPlayer = null;

        for (Player item : players) {
            System.out.println(item.toString());
            if (item.getStatus() != Status.BUST)
                if (item.countPoints() > dealer.countPoints() || championPlayer == null) championPlayer = item;
        }

        if(championPlayer != null && championPlayer.equals(dealer) == false)
        {
            championPlayer.setMoney(championPlayer.getRate() * 2);
        }

        if(championPlayer != null) printChampion(championPlayer);
        else printChampion(dealer);
    }

    private void printChampion(User user)
    {
        if(user != null)System.out.println(user.getName() + "IS CHAMPION, count points: " + user.countPoints());
        else System.out.println("DRAW");
    }
    private int enterInt() {
        int number;
        while (true) {
            try {
                scanner = new Scanner(System.in);
                number = scanner.nextInt();
                return number;
            } catch (InputMismatchException ex) {
                System.out.println("ERROR: ENTER NUMBER");
            }
        }
    }
}
