package com.core.revision.lambdas;

public class Main {

    public static void main(String[] args) {

        Samosa samosa = new PizzaSamosa();
        samosa.eat();

        Samosa samosa1 = new Samosa() {
            @Override
            public void eat() {
                System.out.println("you are eating another  samosa");
            }
        };

        samosa1.eat();

        Samosa samoa3 = () -> System.out.println("you are eating another  samosa by lamda");


        samoa3.eat();
    }
}
