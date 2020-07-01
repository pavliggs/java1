package ru.progwards.pavliggs.java2.N10dot4;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class SimpleTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(
            ClassLoader         loader,
            String              className,
            Class<?>            classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[]              classfileBuffer
    ) {
        if (className.contains("Simple"))
            System.out.println("SimpleTransformer: загружается класс " + className);
        return null;
    }
}
