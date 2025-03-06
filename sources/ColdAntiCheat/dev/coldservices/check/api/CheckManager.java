package dev.coldservices.check.api;

import lombok.Getter;
import lombok.SneakyThrows;
import dev.coldservices.CAC;
import dev.coldservices.check.Check;
import dev.coldservices.data.PlayerData;
import dev.coldservices.util.ClassUtils;
import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Getter
public final class CheckManager {

    private final List<Class<?>> checks = new ArrayList<>();
    private final List<Constructor<?>> constructors = new ArrayList<>();

    public CheckManager() {
        for (Class<?> clazz : ClassUtils.getClassesInPackage(CAC.get().getPlugin(), "dev.coldservices.check.impl")) {

            if (!Check.class.isAssignableFrom(clazz)) {
                continue;
            }

            try {
                this.checks.add(clazz);
                this.constructors.add(clazz.getConstructor(PlayerData.class));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        Bukkit.getConsoleSender().sendMessage("Added Checks " + checks.size());
    }

    @SneakyThrows
    public List<Check> loadChecks(final PlayerData data) {
        final List<Check> checkList = new ArrayList<>();

        for (final Constructor<?> constructor : this.constructors) {
            checkList.add((Check) constructor.newInstance(data));
        }
        Bukkit.getConsoleSender().sendMessage("Checks debloat. " + checkList.size());

        return checkList;
    }
}