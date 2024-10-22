package Actions;

import Actions.Abstractions.ActionHumans;
import Entities.Guy;
import Items.Abstactions.Weapon;

public class FightAction extends ActionHumans {
    private Weapon weaponGuy1;
    private Weapon weaponGuy2;

    public FightAction(Guy guy1, Guy guy2) {
        super(guy1, guy2);
    }

    public FightAction(Guy guy1, Guy guy2, Weapon weaponGuy1, Weapon weaponGuy2) {
        super(guy1, guy2);
        this.weaponGuy1 = weaponGuy1;
        this.weaponGuy2 = weaponGuy2;
    }

    @Override
    public boolean start() {
        System.out.println(human1.getName() + " и " + human2.getName() + " подрались");

        int damageGuy1 = ((Guy) human2).getPower();
        int damageGuy2 = ((Guy) human1).getPower();

        if (weaponGuy1 != null) {
            if (weaponGuy1.use()) {
                damageGuy2 *= weaponGuy1.getPowerScale();
                System.out.println(human1.getName() + " использует " + weaponGuy1.getName());
            }
        }

        if (weaponGuy2 != null) {
            if (weaponGuy2.use()) {
                damageGuy1 *= weaponGuy2.getPowerScale();
                System.out.println(human2.getName() + " использует " + weaponGuy2.getName());
            }
        }

        ((Guy) human1).applyDamage(damageGuy1);
        ((Guy) human1).applyDamage(damageGuy2);

        return true;
    }
}
