package org.example.Characters;

import org.example.Console.TextColor;

public abstract class Character {
    protected String name;
    protected int life;
    protected int successAttackRate;//taux de succès d'une attaque
    protected int defense;
    protected boolean canPlay = true;
    protected boolean canAttack = true;
    protected TextColor white; protected TextColor red; protected TextColor green;
    public abstract void take_dmg(int damage,int defense);
    public abstract void attack(Character character,int damage,int defense,int successAttackRate);

    public int getLife(){return this.life;}

    public String getName(){return this.name;}

    public int getDefense(){return this.defense;}

    public void setDefense(int value){
        this.defense = value;
    }
    public void setSuccessAttackRate(int value){
        this.successAttackRate = value;
    }
    public boolean isAlive(){
        if(this.life <=0){
            return false;
        }
        else{
            return true;
        }

    }
    public Boolean getCanAttack(){
        return this.canAttack;
    }
    public boolean getCanPlay(){
        return this.canPlay;
    }
    public void setCanAttack(Boolean value){
        this.canAttack = value;
    }
    public void setCanPlay(boolean value){
        this.canPlay = value;
    }

}
