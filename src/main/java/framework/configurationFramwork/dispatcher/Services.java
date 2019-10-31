package framework.configurationFramwork.dispatcher;

import framework.configurationFramwork.annotations.Controller;
import framework.configurationFramwork.annotations.Path;
import framework.configurationFramwork.annotations.Service;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public class Services {
    private HashMap<String, Object> context;

    public Services(HashMap<String, Object> context) {
        this.context = context;
    }

    public void chargerServices() {
        Reflections reflections = new Reflections();
        Set<Class<? extends Object>> services =
                reflections.getTypesAnnotatedWith(Service.class);
        for (Class service : services) {
            try {
                Object o = service.newInstance();
                this.context.put(service.getName(), o);
                System.out.println("service.getName()" + service.getName());
                Class[] interfaces = service.getInterfaces();
                for (Class inter : interfaces) {
                    this.context.put(inter.getName(), o);
                    System.out.println("inter.getName()" + inter.getName());

                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
