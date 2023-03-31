package org.example.Levels;

import org.example.Characters.Npc.Boss;
import org.example.Characters.Player.Wizard;
import org.example.Console.UserInteraction;
import org.example.Dialogues.JsonRead;
import org.example.Items.Item;
import org.example.Items.objects.BasilicFang;
import org.example.Items.objects.GryffindorSword;
import org.example.Items.weapon.Weapon;
import org.example.Spells.Accio;
import org.example.Spells.AuthorizedSpell;

import java.util.ArrayList;

public class Level2 extends Level{
    private String name;
    private Wizard wizard;
    private String jsonPath = "src/main/java/org/example/Dialogues/script_level2.json";
    private Accio accio;
    private JsonRead json;
    private Boss basilic;
    private Weapon bossWeapon;
    private Weapon gryffindorSword;
    private BasilicFang fang;

    public Level2(String name, Wizard wizard){
        this.name = name;
        this.wizard = wizard;
        this.json = new JsonRead();
        this.userInteraction = new UserInteraction();
        this.bossWeapon = new Weapon("fang",15,5);
        this.gryffindorSword = new Weapon("Sword of Gryffindor",50,5);
        this.basilic = new Boss("Basilic",bossWeapon,1000,50,5);
        this.isFinish = false;
        this.availableWorldItem = new ArrayList<Item>();
        this.fang = new BasilicFang("Basilic fang",this.basilic);
    }
    @Override
    public void play() {
        while(!isFinish){
            introduction();
            attackBossSystem();
        }
    }

    @Override
    public void introduction(){
        String currentSpellName = "";
        AuthorizedSpell currentAuthorizedSpell;
        this.accio = new Accio("Accio",false);
        this.wizard.addSpell(this.accio);
        this.json.read(this.jsonPath,"intro");
        this.json.read(this.jsonPath,"spell");
        do{
            currentSpellName = this.userInteraction.ScannerSpellChoice(this.wizard.getSpellList());
        }
        while(currentSpellName != "Accio");
        currentAuthorizedSpell = this.wizard.getSpellList().get(currentSpellName);
        //currentSpell.doAction();
        this.json.read(this.jsonPath,"spell_success");
    }
    @Override
    public void attackEnemySystem() {
        //Only one boss in this level
    }
    @Override
    public void attackBossSystem() {
        this.json.read(this.jsonPath,"intro_boss");
        this.json.read(this.jsonPath,"bossDescription");
        if(this.wizard.getHouse().getName() == "Gryffindor"){
            this.json.read(this.jsonPath,"sword");
            GryffindorSword gryffindorSword = new GryffindorSword(this.basilic);
            this.availableWorldItem.add(gryffindorSword);
        }
        while(this.basilic.isAlive()){
            bossStats(this.basilic);
            wizardStats(this.wizard);
            this.userInteraction.actionChoiceBoss(this.wizard.getInventory(),this.wizard.getSpellList(),this.wizard,this.basilic,this.availableWorldItem);
            if(this.basilic.getLife() !=100){
                if(this.basilic.itemDrop(40)){
                    this.json.read(this.jsonPath,"itemDrop");
                    availableWorldItem.add(this.fang);
                }
            }
            if(this.basilic.isAlive()){
                this.basilic.attack(this.wizard,this.basilic.getWeapon().getDamage(),this.wizard.getDefense(),this.basilic.getSuccessAttackRate());
            }
        }
        this.json.read(this.jsonPath,"boss_finish");
        this.wizard.addGold(this.basilic.getGoldValue());
        this.wizard.getInventory().getItemsList().clear();
        setEnd();
    }
}
