package org.example.Core;

import org.example.Characters.Player.Wizard;
import org.example.Clans.House;
import org.example.Clans.SortingHat;
import org.example.Companion.Pet;
import org.example.Console.UserInteraction;
import org.example.Items.consumable.Potion;
import org.example.Items.weapon.Wand;
import org.example.Items.weapon.Core;
import org.example.Levels.*;

import java.util.ArrayList;;

public class Game {
    public Game(){}
    private Wizard player;
    private House Gryffindor; private House Hufflepuff; private House Ravenclaw; private House Slytherin;private House player_house;
    private UserInteraction user_interaction;
    private House[] houses;
    private ArrayList<Potion> potions;
    private Pet phoenix;private Pet goat;private Pet cat;private Pet owl;private Pet toad;private Pet player_pet;
    private Wand wand;
    private String core;
    private int life_player;
    private Level1 level1;private Level2 level2;private Level3 level3;private Level4 level4;private Level5 level5;private Level6 level6;private Level7 level7;
    private Market market;
    public void initialization(){
        user_interaction = new UserInteraction();
        Gryffindor = new House("Gryffindor");
        Hufflepuff = new House("Hufflepuff");
        Ravenclaw = new House("Ravenclaw");
        Slytherin = new House("Slytherin");
        //houses = {Gryffindor,Hufflepuff,Ravenclaw,Slytherin};
        life_player = 100;
        potions = new ArrayList<Potion>();

    }

    public void playerCreation(){
        this.user_interaction.print("Bienvenue sur Harry Potter at Home!");
        String name_player = user_interaction.Scanner_String("Veuillez choisir un nom pour votre Personnage");
        this.user_interaction.print("Bienvenue à Poudlard " + name_player);
        this.user_interaction.print("Le choipeaux magique va maintenant choisir votre maison");
        SortingHat sortinghat = new SortingHat();
        sortinghat.speak();
        int house_number = user_interaction.Scanner_Int("Veuillez choisir votre maison: 1 (Gryffondor), 2 (Serpentard), 3(Serdaigle), 4 (Poussoufle)");
        house_choice(house_number);
        this.user_interaction.print("oui! je pense que vous serez un excellent " + player_house.getName());
        this.user_interaction.print("Pour vous accompagner tout au long de votre scolarité, vous pouvez également choisir un compagnon!");
        int pet_number = user_interaction.Scanner_Int("Choisissez un numéro: 1 (phoenix), 2 (chèvre), 3(chat), 4(hiboux), 5(toad)");
        pet_choice(pet_number);
        this.user_interaction.print("Il ne vous reste plus qu'à choisir votre baguette, choisissez bien!");
        int size_wand = user_interaction.Scanner_Int("Choisissez d'abord la taille de votre baguette (entre 22 et 32cm)");
        int core_number = user_interaction.Scanner_Int("Vous devez maintenant choisir un coeur de baguette, 1 (Plume de phoenix), 2(Ventricule de dragon), 3(Crin de licorne)");
        core_choice(core_number);
        wand = new Wand(size_wand,core);
        this.user_interaction.print("Tout est bon! Bienvenue jeune sorcier");
        this.player = new Wizard(name_player,wand,potions,player_pet,life_player,player_house);
        setBuff(this.player);
    }

    public void levelInitialization(){
        level1 = new Level1("The Philosopher’s Stone", this.player);
        level2 = new Level2("The chamber of secret",this.player);
        level3 = new Level3("The Prisonner of Azkaban", this.player);
        level4 = new Level4("The Goblet of Fire", this.player);
        level5 = new Level5("The Order of Phenix",this.player);
        level6 = new Level6("The Half-Blood Prince",this.player);
        level7 = new Level7("The Deathly Hallows",this.player);
        market = new Market(this.player);
    }

    public void house_choice(int number){
        switch(number){
            case 1:
                player_house = Gryffindor;
                break;

            case 2:
                player_house = Slytherin;
                break;

            case 3:
                player_house = Ravenclaw;
                break;

            case 4:
                player_house = Hufflepuff;
                break;
        }
    }

    public void pet_choice(int number){
        switch(number){
            case 1:
                player_pet = phoenix;
                break;

            case 2:
                player_pet = goat;
                break;

            case 3:
                player_pet = cat;
                break;

            case 4:
                player_pet = owl;
                break;

            case 5:
                player_pet = toad;
                break;
        }
    }

    public void core_choice(int number){
        switch(number){
            case 1:
                core = Core.ONE.name();
                break;
            case 2:
                core = Core.TWO.name();
                break;
            case 3:
                core = Core.THREE.name();
                break;
        }
    }
    public void setBuff(Wizard wizard){
        switch (wizard.getHouse().getName()){
            case "Griffindor":
                wizard.setDefense(10);
                break;
            case "Slytherin":
                //TODO faire buff attaque
                break;
            case "Ravenclaw":
                wizard.setSuccessAttackRate(85);
                break;
            case "Hufflepuff":
                //TODO faire buff potion
                break;

        }
    }
    public void run(){
        while(true){
            initialization();
            playerCreation();
            levelInitialization();
            while(player.isAlive()){
                level1.play();
                //market.play();
                //level2.play();
                //market.play();
                //level3.play();
                market.play();
                level4.play();
                market.play();
                level5.play();
                market.play();
                level6.play();
                market.play();
                level7.play();
            }
            System.out.println("Vous êtes mort!");
        }
    }
}
