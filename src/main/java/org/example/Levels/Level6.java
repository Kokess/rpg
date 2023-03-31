package org.example.Levels;

import org.example.Characters.Npc.Enemy;
import org.example.Characters.Player.Wizard;
import org.example.Items.Item;
import org.example.Items.weapon.Wand;
import org.example.Items.weapon.Core;

import java.util.ArrayList;

public class Level6 extends Level{
    private String name;
    private Wizard wizard;
    private String jsonPath = "src/main/java/org/example/Dialogues/scriptLevel6.json";

    public Level6(String name,Wizard wizard){
        this.name=name;
        this.wizard = wizard;
        this.enemyList = new ArrayList<Enemy>();
        Wand enemyWand = new Wand(25,Core.TWO.name());
        Enemy deathEater1 = new Enemy("Death eater",enemyWand,80,40,5);
        Enemy deathEater2 = new Enemy("Death eater",enemyWand,80,40,5);
        Enemy deathEater3 = new Enemy("Death eater",enemyWand,80,40,5);
        Enemy deathEater4 = new Enemy("Death eater",enemyWand,80,40,5);
        enemyList.add(deathEater1);enemyList.add(deathEater2);enemyList.add(deathEater3);enemyList.add(deathEater4);
        isFinish = false;
        round = 0;
        this.availableWorldItem = new ArrayList<Item>();
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
        json.read(jsonPath,"intro");
        json.read(jsonPath,"spell");

    }

    @Override
    public void attackEnemySystem() {
        this.json.read(this.jsonPath,"fightBegin");
        while(!this.enemyList.isEmpty()){
            wizardStats(this.wizard);
            enemyStats(this.enemyList);
            this.userInteraction.actionChoiceEnemy(this.wizard.getInventory(),this.wizard.getSpellList(),this.wizard,this.enemyList,this.availableWorldItem,this.round);
            this.round +=1;
        }
    }

    @Override
    public void attackBossSystem() {

    }
}