package net.powerkg.market.file;

import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TaxSetting {
    public final Boolean EnablePublish = false;
    public final Double PublishTax = 0.0;

    TaxSetting(FileConfiguration config) {
        OhUseTheFuckingDarkReflect("Tax.", this, TaxSetting.class, config);
    }

    private static void OhUseTheFuckingDarkReflect(String head, Object obj, Class<?> clazz, FileConfiguration config) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {

            if (!(Modifier.isFinal(f.getModifiers()) && !Modifier.isStatic(f.getModifiers()))) {
                continue;
            }

            f.setAccessible(true);
            if (f.getType().equals(Boolean.class)) {
                try {
                    f.set(obj, config.getBoolean(head + f.getName()));
                } catch (NullPointerException | IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (f.getType().equals(Integer.class)) {
                try {
                    f.set(obj, config.getInt(head + f.getName()));
                } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            if (f.getType().equals(String.class)) {
                try {
                    if (config.getString(head + f.getName()) != null)
                        f.set(obj, config.getString(head + f.getName()).replaceAll("&", "§"));
                } catch (IllegalArgumentException | NullPointerException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (f.getType().equals(Double.class)) {
                try {
                    f.set(obj, config.getDouble(head + f.getName()));
                } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
