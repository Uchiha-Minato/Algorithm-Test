package DesignPattern.commandPattern;

/**
 * 请求者invoker
 * */
public class ArmySuperior {
    BattleCommand command;//用来存放具体命令的引用

    public void setCommand(BattleCommand command){
        this.command = command;
    }

    public void startExecuteCommand(){
        command.execute();//让具体命令执行execute方法
    }
}
