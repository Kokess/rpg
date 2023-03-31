package org.example.Levels;

import org.example.Characters.Npc.Boss;
import org.example.Characters.Npc.Enemy;
import org.example.Characters.Player.Wizard;
import org.example.Console.UserInteraction;
import org.example.Dialogues.JsonRead;
import org.example.Items.Item;
import org.example.Spells.AuthorizedSpell;
import org.example.Characters.Character;

import java.util.ArrayList;
import java.util.List;

public abstract class Level {
    protected String name;
    protected ArrayList<Enemy> enemyList;
    protected ArrayList<Item> availableWorldItem;
    protected Boss boss;
    protected Boolean isFinish;
    protected Wizard wizard;
    protected UserInteraction userInteraction;
    protected int round;

    protected JsonRead json;

    Level(){}

    //get methods
    public boolean isOver(){return this.isFinish;}
    public void setEnd(){
        System.out.println("Félicitation! Vous avez réussi le niveau!");
        this.isFinish = true;}
    public abstract void play();
    public abstract void introduction();
    public abstract void attackEnemySystem();
    public abstract void attackBossSystem();
    public void bossStats(Boss boss){
        System.out.println("\u001B[31m" + "Le boss a " + boss.getLife() + "❤️" + "\u001B[0m");
    }
    public void wizardStats(Wizard wizard){
        System.out.println("\u001B[32m" + "Vos statistiques " +"❤️" + wizard.getLife() +" | " + "🛡️" + wizard.getDefense() + " | " + "💧" + wizard.getMana()+ " | " + "🪙" + wizard.getGold() + "\u001B[0m");
    }

    public void executeDamageSpellAction(AuthorizedSpell authorizedSpell, Character character){
        switch (authorizedSpell.getName()){
            case "Wingardium Leviosa":
                authorizedSpell.actionOnCharacter(character);
        }
    }

    public void enemyStats(ArrayList<Enemy>enemyList){
        for(int i=0; i < enemyList.size();i++){
            System.out.print("\u001B[31m" + enemyList.get(i).getName() +"("+enemyList.get(i).getLife()+"❤️ | " + enemyList.get(i).getDefense() + "🛡️) " + ", " + "\u001B[0m" );
        }
    }


}
