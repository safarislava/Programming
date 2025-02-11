package Actions;

import Entities.Guy;
import Exceptions.EnemyException;
import Items.Abstactions.Weapon;

public class FightAction {
    private final Guy guy1;
    private final Guy guy2;
    private Weapon weaponGuy1;
    private Weapon weaponGuy2;

    public FightAction(Guy guy1, Guy guy2) {
        this.guy1 = guy1;
        this.guy2 = guy2;
    }

    public FightAction(Guy guy1, Guy guy2, Weapon weaponGuy1, Weapon weaponGuy2) {
        this(guy1, guy2);
        this.weaponGuy1 = weaponGuy1;
        this.weaponGuy2 = weaponGuy2;
    }

    public boolean start() {
        if (guy1 == guy2) {
            throw new EnemyException(String.format("%s - не Тайлер Дёрден", guy1));
        }

        if (guy1.isFriend(guy2) || guy2.isFriend(guy1)) {
            System.out.printf("Драка между %s и %s не состоялась%n", guy1, guy2);
            return false;
        }

        System.out.printf("%s и %s начали драку%n", guy1, guy2);

        int damageGuy1 = guy2.getPower();
        int damageGuy2 = guy1.getPower();

        if (weaponGuy1 != null) {
            if (weaponGuy1.use()) {
                damageGuy2 *= weaponGuy1.getPowerScale();
                System.out.printf("%s использует %s%n", guy1, weaponGuy1);
            }
        }

        if (weaponGuy2 != null) {
            if (weaponGuy2.use()) {
                damageGuy1 *= weaponGuy2.getPowerScale();
                System.out.printf("%s использует %s%n", guy2, weaponGuy2);
            }
        }

        guy1.applyDamage(damageGuy1);
        guy2.applyDamage(damageGuy2);


        guy1.addTrust(guy2, (float) damageGuy1 / guy1.getMaxHp());
        guy2.addTrust(guy1, (float) damageGuy2 / guy2.getMaxHp());

        return true;
    }
}
