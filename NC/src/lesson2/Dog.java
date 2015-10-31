package lesson2;

/**
 * Created by ִלטענטי on 28.10.2015.
 */
public class Dog {
    private final String name;
    private final String breed;

    private final Dog mom;
    private final Dog dad;

    public Dog(String name) {
        this(name, "", null, null);
    }


    public Dog(String name, String breed, Dog mom, Dog bad) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Enter DOG NAME");
        }
        if (breed == null) {
            throw new IllegalArgumentException("Enter BREED");
        }

        this.name = name;
        this.breed = breed;
        this.dad = bad;
        this.mom = mom;
    }

    boolean flag = true;

    public boolean isThoroughbred() {
        /*if (this.breed.equals(this.mom.getBreed()) && this.breed.equals(this.dad.getBreed())) {

            if(this.mom.getBreed().equals(this.mom.getMom().getBreed()) && this.dad.getBreed().equals(this.dad.getDad().getBreed()))
            {
                if(this.mom.getMom().getBreed().equals(this.mom.getMom().getMom().getBreed()) && this.dad.getDad().getBreed().equals(this.dad.getDad().getDad().getBreed()))
                {
                    System.out.println("+");
                }
            }

        }
        else System.out.println("-");*/

        if(this == null) return true;
        if(!flag) return false;

        if (this.getBreed().equals(this.getMom().getBreed()) && this.getBreed().equals(this.getDad().getBreed())) {
            isThoroughbred(this.getDad());
            isThoroughbred(this.getMom());
        }

        else flag = false;
        return true;

    }

    public Dog getMom() {
        return mom;
    }

    public Dog getDad() {
        return dad;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

}
