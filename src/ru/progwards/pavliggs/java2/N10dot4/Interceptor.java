package ru.progwards.pavliggs.java2.N10dot4;

import java.lang.instrument.Instrumentation;

public class Interceptor {
    public static void premain(String agentArguments, Instrumentation instrumentation) {
        System.out.println("Interceptor: premain стартовал");
        instrumentation.addTransformer(new SimpleTransformer());
        System.out.println("Interceptor: На перехвате установлен SimpleTransformer");
        System.out.println("Interceptor: premain завершён");
    }
}
