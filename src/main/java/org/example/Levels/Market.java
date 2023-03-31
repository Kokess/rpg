package org.example.Levels;

import org.example.Characters.Character;
import org.example.Characters.Player.Wizard;
import org.example.Console.UserInteraction;
import org.example.Items.consumable.Potion;
import org.example.Items.consumable.PotionType;

public class Market {
    private Character merchant;
    private Wizard wizard;
    private UserInteraction userInteraction;

    private boolean isFinish;
    public Market(Wizard wizard){
        this.isFinish = false;
        this.userInteraction = new UserInteraction();
        this.wizard = wizard;
    }

    public void checkPrice(Potion potion){
        if(this.wizard.getGold() >= potion.getPrice()){
            this.wizard.spendGold(potion.getPrice());
            this.wizard.addPotion(potion);
        }
        else{
            System.out.println("Vous n'avez pas assez d'argent!");
        }
    }
    public void buyPotion(){
        boolean isFinish = false;
        while(!isFinish){
            System.out.println("Vous avez " + this.wizard.getGold() + "🪙");
            int potionNumberChoice = userInteraction.Scanner_Int("Quelle potion voulez vous acheter? 1(Heal),2(Mana),3(Strenght),4(Luck), 5 (Shield), 6(partir)");
            int sizePotionList = this.wizard.getPotionsList().size();
            String sizePotionListToString = Integer.toString(sizePotionList);
            switch (potionNumberChoice){
                case 1:
                    Potion potionHeal = new Potion(PotionType.HEAL,20);
                    checkPrice(potionHeal);
                    break;
                case 2:
                    Potion potionMana = new Potion(PotionType.MANA,15);
                    checkPrice(potionMana);
                    break;
                case 3:
                    Potion potionStrenght = new Potion(PotionType.STRENGTH,25);
                    checkPrice(potionStrenght);
                    break;
                case 4:
                    Potion potionLuck = new Potion(PotionType.LUCK,15);
                    checkPrice(potionLuck);
                    break;
                case 5:
                    Potion potionShield = new Potion(PotionType.SHIELD,15);
                    checkPrice(potionShield);
                    break;
                case 6:
                    System.out.println("Au revoir");
                    isFinish = true;
                    break;
            }
        }

    }

    public void buyFood(){
        System.out.println("Vous avez " + this.wizard.getGold() + " 🪙");
    }
    public void play(){
        this.isFinish = false;
        while(!isFinish){
            System.out.println("Bienvenue sur le chemin de traverse!");
            int itemNumberChoice = userInteraction.Scanner_Int("Que voulez vous acheter? 1 (Potion), 2(Nourriture), 3(partir)");
            if(itemNumberChoice == 1){
                buyPotion();
            }
            else if(itemNumberChoice == 2){
                buyFood();
            }
            else if(itemNumberChoice ==3){
                System.out.println("Au revoir!");
                this.isFinish = true;
            }
        }
    }
}
