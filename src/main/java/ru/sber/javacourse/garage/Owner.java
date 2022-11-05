package ru.sber.javacourse.garage;

import java.util.Objects;

public class Owner {
    public long getOwnerId() {
        return ownerId;
    }

    private final long ownerId;
    private final String name;
    private final String lastName;

    public int getAge() {
        return age;
    }

    private final int age;

    public Owner(long ownerId, String name, String lastName, int age) {
        this.ownerId = ownerId;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return ownerId == owner.ownerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId);
    }
}
