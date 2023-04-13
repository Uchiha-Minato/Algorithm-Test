package DesignPattern.commandPattern;

/**
 * @author M1nato1
 * @date 2022/3/15 14:56
 * @product IntelliJ IDEA
 */
public class CommandA implements BattleCommand{
    ArmyA armyA;//含有接收者的引用
    CommandA(ArmyA armyA){
        this.armyA = armyA;
    }

    @Override
    public void execute() {
        armyA.attack();
    }
}
