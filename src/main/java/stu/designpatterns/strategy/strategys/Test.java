package stu.designpatterns.strategy.strategys;

import java.io.Console;

public class Test {
    public static void  main(String[] args){
        Context context;
        context=new Context(new ConcreateStrategyA());
        context.ContextInterface();

        context=new Context(new ConcreateStrategyB());
        context.ContextInterface();

        context=new Context(new ConcreateStrategyC());
        context.ContextInterface();

        
    }
}
