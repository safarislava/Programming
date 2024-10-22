import Actions.FightAction;
import Actions.OpenAction;
import Actions.PeaceAction;
import Actions.TalkAction;
import Entities.Characteristics.Address;
import Entities.Characteristics.SchoolClass;
import Entities.Guy;
import Entities.Human;
import Exceptions.MalishEnemyException;
import Items.Rock;
import Items.Wardrobe;
import Places.Room;
import Places.School;
import Places.Street;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // TODO enum, interface
        // TODO toString, hashCode

        Room room = new Room();
        room.addItem(new Wardrobe());

        School school17 = new School(17);

        Street lomonosovaStreet = new Street("Ломоносова");
        Human mother = new Human("Мама", new Address(lomonosovaStreet, 11, 94));

        ArrayList<Guy> guys = new ArrayList<>();
        Guy malish = new Guy("Малыш", mother.getAddress(), new SchoolClass(school17, 6, 'A'));

        Address gunillaAddress = new Address(lomonosovaStreet, (int) (Math.random() * 20), (int) (Math.random() * 100));
        Guy gunilla = new Guy("Гунилла", gunillaAddress, malish.getSchoolClass());
        Address cristerAddress = new Address(lomonosovaStreet, (int) (Math.random() * 20), (int) (Math.random() * 100));
        Guy crister = new Guy("Кристер", cristerAddress, malish.getSchoolClass());

        guys.add(malish);
        guys.add(crister);
        guys.add(new Guy("Леонардо", new Address(new Street("Строителей"), 120, 15)));
        guys.add(new Guy("Раф", new Address(lomonosovaStreet, (int) (Math.random() * 20), (int) (Math.random() * 100))));
        guys.add(new Guy("Донателло", new Address(new Street("Карла Маркса"), 100, 5)));
        guys.add(new Guy("Микелянжело", new Address(lomonosovaStreet, (int) (Math.random() * 20), (int) (Math.random() * 100))));
        guys.add(new Guy("Сплинтер", new Address(new Street("Лебедева"), 1, 5)));
        guys.add(gunilla);

        mother.setTrust(malish, 1);
        gunilla.setTrust(malish, (float) Math.random());
        crister.setTrust(gunilla, (float) Math.random());

        malish.enter(room);
        ArrayList<Wardrobe> wardrobes = room.getItemsByType();
        OpenAction openAction = new OpenAction(malish, wardrobes.get(0));
        openAction.start();

        Rock rock = new Rock();

        System.out.println("-------START-------");

        openAction.start();
        gunilla.enter(malish.getPlace());
        crister.enter(malish.getPlace());

        System.out.print("У Малыша и Гуниллы ");
        malish.getAddress().equals(gunilla.getAddress());
        System.out.print("И у Малыша и Кристера ");
        malish.getAddress().equals(crister.getAddress());

        // TODO repeat with school class

        malish.addOpinion(gunilla, "ужасно хорошая");

        TalkAction talkAction = new TalkAction(malish, mother);
        talkAction.setStatement(gunilla.getName() + " " + malish.getOpinion(gunilla));
        talkAction.start();

        malish.addOpinion(crister, "любит, давно уже простил ему шишку на лбу");

        FightAction fightAction = new FightAction(malish, crister);
        PeaceAction peaceAction = new PeaceAction(malish, crister);

        for (int i = 0; i < 3; i++){
            fightAction.start();
            peaceAction.start();
        }

        for (Guy guy : guys){
            if (guy.getAddress().street().equals(malish.getAddress().street())) {
                try {
                    if (guy == malish) throw new MalishEnemyException("Малыш не Тайлер Дёрден");
                    if (guy == gunilla) throw new MalishEnemyException("Малыш никогда не бил Гуниллу");

                    fightAction = new FightAction(malish, guy);
                    fightAction.start();
                }
                catch (MalishEnemyException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }

        talkAction = new TalkAction(malish, gunilla);
        talkAction.setStatement("Карлсон существует");
        if (!talkAction.start()) {
            talkAction = new TalkAction(gunilla, crister);
            talkAction.setStatement("Это всё выдумки");
            if (talkAction.start()) {
                fightAction = new FightAction(malish, crister, rock, null);
                fightAction.start();
            }
        }
    }
}