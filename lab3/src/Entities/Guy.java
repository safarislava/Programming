package Entities;

import Entities.Characteristics.Address;
import Entities.Characteristics.SchoolClass;

import java.util.Random;

public class Guy extends Human {
    private static final int MIN_POWER_BOUND = 0;
    private static final int MAX_POWER_BOUND = 10;
    private static final int MIN_HP_BOUND = 1;
    private static final int MAX_HP_BOUND = 10;

    private SchoolClass schoolClass;
    private int power;
    private int hp;
    private final int maxHp;

    public Guy(String name, Address address) {
        super(name, address);

        Random random = new Random();
        power = random.nextInt(MIN_POWER_BOUND, MAX_POWER_BOUND);
        maxHp = random.nextInt(MIN_HP_BOUND, MAX_HP_BOUND);
        hp = maxHp;
    }

    public Guy(String name, Address address, SchoolClass schoolClass) {
        this(name, address);
        this.schoolClass = schoolClass;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public float getMaxHp() {
        return maxHp;
    }

    public int getPower() {
        return power;
    }

    public void addPower(int power) {
        this.power += power;
    }

    public int getHp() {
        return hp;
    }

    public void applyDamage(int damage){
        hp -= damage;
        if (hp <= 0) {
            System.out.printf("%s заплакал%n", name);
            hp = maxHp;
        }
    }
}
