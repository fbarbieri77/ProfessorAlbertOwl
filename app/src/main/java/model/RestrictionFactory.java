package model;
//import static org.reflections.getSubTypesOf;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class RestrictionFactory {
    public List<Restriction> create() throws ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException,
            InstantiationException {
        List<Restriction> res = new ArrayList<>();
        res.add(new Level0_sum());
        /*
        reflections.getSubTypesOf(Restriction);
        Class<?>[] levels = Restriction.class.getClasses();
        for (Class<?> level : levels) {
            Restriction instance = (Restriction) Class.forName(levels
                            .getClass()
                            .getName())
                    .getConstructor()
                    .newInstance();
            res.add(instance);
        }
        */
        return res;
    }
}
