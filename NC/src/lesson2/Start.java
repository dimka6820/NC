package lesson2;

/**
 * Created by ִלטענטי on 28.10.2015.
 */
public class Start {
    private static boolean flag = true;
    public static void main(String[] args) {
        Dog dog = new Dog("qwer", " ",
                new Dog("qwer", " ", new Dog("qwer", " ",  new Dog("qwer", " ", new Dog("12"), new Dog("12")),  new Dog("qwer", " ", new Dog("12"), new Dog("12"))), new Dog("qwer", " ",  new Dog("qwer", " ", new Dog("12"), new Dog("12")),  new Dog("qwer", " ", new Dog("12"), new Dog("12")))),
                new Dog("qwer", " ", new Dog("qwer", " ",  new Dog("qwer", " ", new Dog("12"), new Dog("12")),  new Dog("qwer", " ", new Dog("12"), new Dog("12"))), new Dog("qwer", " ",  new Dog("qwer", " ", new Dog("12"), new Dog("12")),  new Dog("qwer", " ", new Dog("12"), new Dog("12")))));

        System.out.println(isThoroughbred(dog));
    }

    public static boolean isThoroughbred(Dog dog)
    {
        if(dog == null) return true;
        if(!flag) return false;

        if (dog.getBreed().equals(dog.getMom().getBreed()) && dog.getBreed().equals(dog.getDad().getBreed())) {
            isThoroughbred(dog.getDad());
            isThoroughbred(dog.getMom());
        }

        else flag = false;
        return true;

    }

}
