package framework.configurationFramwork.dispatcher;

import framework.configurationFramwork.annotations.Inject;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

public class Injections {
    private HashMap<String, Object> context;

    public Injections(HashMap<String, Object> context) {
        this.context = context;
    }

    public void injectFields() {
        Reflections reflections = new Reflections(new FieldAnnotationsScanner());
        Set<Field> fields = reflections.getFieldsAnnotatedWith(Inject.class);

        for (Field field : fields) {
            boolean acc = field.isAccessible();
            field.setAccessible(true);
            try {
                field.set(context.get(field.getDeclaringClass().getName()), context.get(field.getType().getName()));
            } catch (IllegalAccessException e) {
                field.setAccessible(acc);
                e.printStackTrace();
            }
            field.setAccessible(acc);
        }

    }
}
