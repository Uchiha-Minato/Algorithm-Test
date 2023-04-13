package DesignPattern.commandPattern;

/**
 * @author M1nato1
 * @date 2022/3/15 15:09
 * @product IntelliJ IDEA
 */
public class Application {
    public static void main(String[] args) {
        ArmyA DuLiTuan = new ArmyA();
        ArmyB SanTuan = new ArmyB();
        ArmyC DuLiYing = new ArmyC();

        CommandA commandA = new CommandA(DuLiTuan);
        System.out.print("独立团");
        ArmySuperior ZhiHuiGuan = new ArmySuperior();
        ZhiHuiGuan.setCommand(commandA);
        ZhiHuiGuan.startExecuteCommand();

        CommandB commandB = new CommandB(SanTuan);
        System.out.print("三团");
        ZhiHuiGuan.setCommand(commandB);
        ZhiHuiGuan.startExecuteCommand();

        CommandC commandC = new CommandC(DuLiYing);
        System.out.print("独立营");
        ZhiHuiGuan.setCommand(commandC);
        ZhiHuiGuan.startExecuteCommand();
    }
}
