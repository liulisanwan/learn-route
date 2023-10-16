package com.liuli.a_f;

import lombok.Data;

@Data
public class DogPrototype implements Cloneable{
    private Dog dog;

    @Override
    public DogPrototype clone() {
        try {
            DogPrototype clone = (DogPrototype) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        DogPrototype dogPrototype = new DogPrototype();
        dogPrototype.setDog(new Dog());
        DogPrototype dogPrototypeClone = dogPrototype.clone();
        System.out.println(dogPrototype == dogPrototypeClone);
        System.out.println(dogPrototype.getDog() == dogPrototypeClone.getDog());
    }
}