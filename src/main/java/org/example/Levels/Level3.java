package org.example.Levels;

import org.example.Characters.Npc.Enemy;
import org.example.Characters.Player.Wizard;
import org.example.Console.UserInteraction;
import org.example.Dialogues.JsonRead;
import org.example.Items.Item;
import org.example.Items.weapon.Weapon;
import org.example.Spells.ExpectoPatronum;

import java.util.ArrayList;

public class Level3 extends Level{

    private String name;
    private Wizard wizard;
    private String jsonPath;
    private ExpectoPatronum expectoPatronum;


    public Level3(String name,Wizard wizard){
        this.name = name;
        this.wizard = wizard;
        this.userInteraction = new UserInteraction();
        this.json = new JsonRead();
        this.jsonPath = "src/main/java/org/example/Dialogues/scriptLevel3.json";
        this.enemyList = new ArrayList<Enemy>();
        this.availableWorldItem = new ArrayList<Item>();
        Weapon dementorWeapon = new Weapon("Dementor's kiss",10,5);
        Enemy dementor_1 = new Enemy("Dementor",dementorWeapon,75,20,3);
        Enemy dementor_2 = new Enemy("Dementor",dementorWeapon,75,20,3);
        Enemy dementor_3 = new Enemy("Dementor",dementorWeapon,75,20,3);
        this.enemyList.add(dementor_1);this.enemyList.add(dementor_2);this.enemyList.add(dementor_3);
        this.isFinish = false;
        this.round =0;
        runSystemAttack = true;
    }

    @Override
    public void play() {
        while(!isFinish){
            introduction();
            attackEnemySystem();
            setEnd();
        }


    }

    @Override
    public void introduction() {
        this.json.read(this.jsonPath,"intro");
        this.expectoPatronum = new ExpectoPatronum();
        this.wizard.addSpell(expectoPatronum);
    }

    @Override
    public void attackEnemySystem() {
        this.json.read(this.jsonPath,"fightBegin");
        this.json.read(this.jsonPath,"enemyDescription");
        while(runSystemAttack){
            wizardStats(this.wizard);
            enemyStats(this.enemyList);
            this.userInteraction.actionChoiceEnemy(this.wizard.getInventory(),this.wizard.getSpellList(),this.wizard,this.enemyList,this.availableWorldItem,this.round);
            this.round +=1;
            checkWizardLife();
            checkEnemyList();
        }
        this.json.read(this.jsonPath,"endFight");

    }

    @Override
    public void attackBossSystem() {
        //No boss here
    }
}

