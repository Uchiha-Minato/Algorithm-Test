package DesignPattern.commandPattern;

/**
 * @author M1nato1
 * @date 2022/3/15 15:04
 * @product IntelliJ IDEA
 */
public class CommandC implements BattleCommand{

    ArmyC armyC;
    CommandC(ArmyC armyC){
        this.armyC = armyC;
    }
    @Override
    public void execute() {
        armyC.falseAttack();
    }
}
