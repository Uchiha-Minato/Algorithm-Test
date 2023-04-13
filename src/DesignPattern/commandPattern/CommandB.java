package DesignPattern.commandPattern;

/**
 * @author M1nato1
 * @date 2022/3/15 15:03
 * @product IntelliJ IDEA
 */
public class CommandB implements BattleCommand{

    ArmyB armyB;
    CommandB(ArmyB armyB){
        this.armyB = armyB;
    }
    @Override
    public void execute() {
        armyB.block();
    }
}
