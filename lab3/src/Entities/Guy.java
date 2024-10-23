package Entities;

import Entities.Characteristics.Address;
import Entities.Characteristics.SchoolClass;

public class Guy extends Human {
    private SchoolClass schoolClass;
    private int power = 1;
    private int hp = 10;

    public Guy(String name, Address address) {
        super(name, address);
    }

    public Guy(String name, Address address, SchoolClass schoolClass) {
        super(name, address);
        this.schoolClass = schoolClass;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
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
            System.out.println(name + " заплакал");
            hp = 10;
        }
    }
}
