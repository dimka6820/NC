package ru.dima;

import ru.dima.controllers.HomeController;

public class Start {


    public static void main(String[] args) {
        HomeController homeController = new HomeController();
        homeController.start();
    }
}
