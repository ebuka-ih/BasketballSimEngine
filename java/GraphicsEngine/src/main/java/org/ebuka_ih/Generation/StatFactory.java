package org.ebuka_ih.Generation;

import org.ebuka_ih.Player.Stats;
import org.ebuka_ih.Util.Util;
import org.ebuka_ih.en.Position;

public class StatFactory {

    public static Stats build(Position position) {
        Stats stats = new Stats();
        int finishing = Util.RANDOM.nextInt(51) + 20;
        int midrange = Util.RANDOM.nextInt(41) + 30;
        int threePoint = Util.RANDOM.nextInt(41) + 10;
        int fourPoint = Util.RANDOM.nextInt(21) + 10;
        int ballHandling = Util.RANDOM.nextInt(66) + 10;
        int passing = Util.RANDOM.nextInt(66) + 10;

        int physicalDefense = Util.RANDOM.nextInt(46) + 30;
        int perimeterDefense = Util.RANDOM.nextInt(36) + 15;
        int stealing = Util.RANDOM.nextInt(31) + 5;
        int blocking = Util.RANDOM.nextInt(31) + 5;
        int rebounding = Util.RANDOM.nextInt(31) + 10;
        int speed = Util.RANDOM.nextInt(61) + 20;
        int stamina = Util.RANDOM.nextInt(51) + 20;
        int clutch = Util.RANDOM.nextInt(51) + 25;
        int consistency = Util.RANDOM.nextInt(31) + 20;
        int confidence = Util.RANDOM.nextInt(51) + 30;

        switch (position) {
            case PG -> {
                speed += Util.RANDOM.nextInt(13) + 3;
                passing += Util.RANDOM.nextInt(16) + 5;
                midrange += Util.RANDOM.nextInt(16) + 5;
                threePoint += Util.RANDOM.nextInt(13) + 3;
                fourPoint += Util.RANDOM.nextInt(11);
                ballHandling += Util.RANDOM.nextInt(23) + 3;
                finishing += Util.RANDOM.nextInt(8) + 3;
                stealing += Util.RANDOM.nextInt(3) + 5;
                clutch += Util.RANDOM.nextInt(5);

            }
            case SG -> {
                speed += Util.RANDOM.nextInt(13) + 3;
                passing += Util.RANDOM.nextInt(11) + 5;
                midrange += Util.RANDOM.nextInt(21) + 5;
                threePoint += Util.RANDOM.nextInt(18) + 3;
                fourPoint += Util.RANDOM.nextInt(16);
                ballHandling += Util.RANDOM.nextInt(16) + 3;
                finishing += Util.RANDOM.nextInt(11) + 5;
                clutch += Util.RANDOM.nextInt(10);
            }
            case C -> {
                physicalDefense += Util.RANDOM.nextInt(21) + 5;
                perimeterDefense += Util.RANDOM.nextInt(11) + 3;
                rebounding += Util.RANDOM.nextInt(19) + 7;
                blocking += Util.RANDOM.nextInt(23) + 3;
                stealing += Util.RANDOM.nextInt(8) + 3;
                finishing += Util.RANDOM.nextInt(21) + 5;
            }
            case PF -> {
                physicalDefense += Util.RANDOM.nextInt(11) + 5;
                perimeterDefense += Util.RANDOM.nextInt(8) + 3;
                stealing += Util.RANDOM.nextInt(3) + 3;
                passing += Util.RANDOM.nextInt(6) + 5;
                midrange += Util.RANDOM.nextInt(11) + 5;
                threePoint += Util.RANDOM.nextInt(8) + 3;
                fourPoint += Util.RANDOM.nextInt(6);
                ballHandling += Util.RANDOM.nextInt(10) + 3;
                finishing += Util.RANDOM.nextInt(11) + 5;
                blocking += Util.RANDOM.nextInt(13) + 3;
            }
            case SF -> {
                speed += Util.RANDOM.nextInt(11) + 3;
                physicalDefense += Util.RANDOM.nextInt(8) + 8;
                perimeterDefense += Util.RANDOM.nextInt(16) + 5;
                stealing += Util.RANDOM.nextInt(11) + 5;
                passing += Util.RANDOM.nextInt(6) + 5;
                midrange += Util.RANDOM.nextInt(16) + 5;
                threePoint += Util.RANDOM.nextInt(9) + 2;
                ballHandling += Util.RANDOM.nextInt(5) + 3;
                finishing += Util.RANDOM.nextInt(16) + 5;
                blocking += Util.RANDOM.nextInt(18) + 3;
            }
        }
        stats.setFinishing(finishing);
        stats.setMidrange(midrange);
        stats.setThreePoint(threePoint);
        stats.setFourPoint(fourPoint);
        stats.setBallHandling(ballHandling);
        stats.setPassing(passing);

        stats.setPhysicalDefense(physicalDefense);
        stats.setPerimeterDefense(perimeterDefense);
        stats.setStealing(stealing);
        stats.setBlocking(blocking);
        stats.setRebounding(rebounding);
        stats.setSpeed(speed);
        stats.setStamina(stamina);
        stats.setClutch(clutch);
        stats.setConsistency(consistency);
        stats.setConfidence(confidence);

        return stats;
    }
}
