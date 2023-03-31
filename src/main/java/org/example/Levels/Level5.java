package org.example.Levels;

import org.example.Characters.Npc.Enemy;
import org.example.Characters.Player.Wizard;
import org.example.Console.UserInteraction;
import org.example.Dialogues.JsonRead;
import org.example.Items.Item;
import org.example.Items.weapon.Wand;
import org.example.Items.weapon.Core;
import org.example.Spells.Petrificus;

import java.util.ArrayList;

public class Level5 extends Level{
    private String name;
    private Wizard  wizard;
    private String jsonPath;
    private Wand doloresWand;
    private Enemy dolores;
    public Level5(String name,Wizard wizard){
        this.name = name;
        this.wizard = wizard;
        this.json = new JsonRead();
        this.jsonPath = "src/main/java/org/example/Dialogues/scriptLevel5.json";
        this.enemyList = new ArrayList<Enemy>();
        this.doloresWand = new Wand(22,Core.ONE.name());
        this.dolores = new Enemy("Dolores",doloresWand,150,100,5);
        this.enemyList.add(dolores);
        this.isFinish = false;
        this.availableWorldItem = new ArrayList<Item>();
        this.userInteraction = new UserInteraction();
        this.round =0;
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
        Petrificus petrificus = new Petrificus();
        this.wizard.addSpell(petrificus);
    }

    @Override
    public void attackEnemySystem() {
        json.read(jsonPath,"fightBegin");
        while(!this.enemyList.isEmpty()){
            wizardStats(this.wizard);
            enemyStats(this.enemyList);
            userInteraction.actionChoiceEnemy(wizard.getInventory(),wizard.getSpellList(),wizard,enemyList,availableWorldItem,round);
            round +=1;
        }

    }

    @Override
    public void attackBossSystem() {

    }
}