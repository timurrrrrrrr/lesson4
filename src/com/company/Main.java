package com.company;

import java.util.Random;

public class Main {
    public static int roundNumber = 1;
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static String[] heroesAttacktype = {
            "Physycal", "Magical", "Kinetic", "Medic", "Lucky", "Golem","Berserk"};
    public static int[] heroesHealth = {260, 270, 250, 200, 200, 450,300};
    public static int[] heroesDamage = {15, 20, 25, 0, 10, 5,10};
    public static Random random = new Random();

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }


    public static void chooseBossDefense() {
        int randomindex = random.nextInt(heroesAttacktype.length);
        bossDefence = heroesAttacktype[randomindex];
        System.out.println("Boss Choose" + bossDefence);
    }

    public static void round() {
        System.out.println(roundNumber + "ROUND");
        chooseBossDefense();
        bossHits();
        luckyRound();
        golemEffects();
        medicHp();
        heroesHit();
        printStatistics();
        roundNumber++;

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
       /*if (heroesHealth[0]>=0 && heroesHealth[1]>=0 && heroesHealth[2]>=0){
            System.out.println("Boss won!!!");
            return true;
            }

        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss Won");

        }
        return allHeroesDead;
    }

    public static void medicHp() {
        int hp = random.nextInt(40);
        int randomHero = random.nextInt(heroesAttacktype.length);
        if (randomHero == 3) {
            medicHp();
        }
        if (heroesHealth[randomHero] < 100 && heroesHealth[randomHero] > 0 && heroesHealth[3] > 0) {
            heroesHealth[randomHero] = heroesHealth[randomHero] + hp;
            System.out.println(heroesAttacktype[randomHero] + " healed " + hp);
        }
    }

    public static void luckyRound() {
        boolean uklon = random.nextBoolean();
        if (heroesHealth[4] > 0 && uklon) {
            heroesHealth[4] += bossDamage;
            System.out.println("Lucky Round!!!");
        }
    }

    public static void golemEffects() {
        int partDamage = bossDamage / 5;
        int aliveHeroes = 0;

        if (heroesHealth[5] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 5) continue;
                else if (heroesHealth[i] > 0) {
                    aliveHeroes += 1;
                    heroesHealth[i] += partDamage;

                }
            }
            heroesHealth[5] -= partDamage * aliveHeroes;
            System.out.println("Golem take: " + partDamage * aliveHeroes + " damage");
        }
    }





    public static void heroesHit() {
        Random random = new Random();
        int coeff = random.nextInt(9);
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttacktype[i] == bossDefence) {
                } else {
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                        System.out.println("Critical Damage" + heroesDamage[i] * coeff);
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;

                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[0] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("________________");
        System.out.println("Boss Health" + bossHealth +
                ";Boss Damage:" + bossDamage);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(
                    heroesAttacktype[i] + "Health:"
                            + heroesHealth[i] +
                            "; " + heroesAttacktype[i] + "Damage:"
                            + heroesDamage[i]);
        }
        System.out.println("________________");

    }
}
