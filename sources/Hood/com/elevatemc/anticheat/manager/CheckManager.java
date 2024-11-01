package com.elevatemc.anticheat.manager;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.Check;
import lombok.Getter;
import org.atteo.classindex.ClassIndex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.text.Collator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CheckManager {
    private final Set<Class<? extends Check>> checkClasses = new HashSet<>();

    private final Set<Constructor<?>> constructors = new HashSet<>();

    public CheckManager() {
        ClassIndex.getSubclasses(Check.class, Check.class.getClassLoader()).forEach(clazz -> {
                    if (Modifier.isAbstract(clazz.getModifiers())) {
                        return;
                    }
                    checkClasses.add(clazz);
                });

        checkClasses.forEach(clazz -> {
            try {
                constructors.add(clazz.getConstructor(PlayerData.class));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
    }

    public List<String> getAlphabeticallySortedChecks() {
        return checkClasses.stream().map(Class::getSimpleName).sorted(Collator.getInstance()).collect(Collectors.toList());

    }
}