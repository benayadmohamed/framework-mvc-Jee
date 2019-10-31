package framework.configurationFramwork.dispatcher;

import framework.configurationFramwork.annotations.Controller;
import framework.configurationFramwork.annotations.Path;
import framework.configurationFramwork.enumerations.HttpMethods;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public class Controllers {
    private HashMap<String, Object> context;
    private HashMap<String, Method> urlPaths;

    public Controllers(HashMap<String, Object> context, HashMap<String, Method> urlPaths) {
        this.context = context;
        this.urlPaths = urlPaths;
    }

    public Method getMethod(String url, HttpMethods httpMethod) {
        //mode strict
        //   Method method = urlPaths.get(url+ "_" + httpMethod.toString());

        Method method = urlPaths.get(url.replace("/", "") + "_" + httpMethod.toString());
        return method;
    }

    public void chargerControllers() {
        Reflections reflections = new Reflections();
        Set<Class<? extends Object>> allClasses =
                reflections.getTypesAnnotatedWith(Controller.class);
        for (Class aClass : allClasses) {
            try {
                this.context.put(aClass.getName(), aClass.newInstance());
                Method[] methods = aClass.getMethods();
                Path cPath = (Path) aClass.getAnnotation(Path.class);
                String cUrl = cPath.value().replace("/", "");
                // mode strict
                /*if (cPath != null && !cPath.defaultValue().isEmpty()) {
                    cUrl = cUrl + "/" + cPath.defaultValue().replace("/", "");
                }*/
                // fin mode strict

                for (Method method : methods) {
                    Path mPath = method.getAnnotation(Path.class);
                    if (mPath != null) {
                        String mUrl = mPath.value();
                        // mode strict
                      /*  if (!mUrl.startsWith("/")) {
                            mUrl = "/" + mUrl;
                        }
                        if (mUrl.endsWith("/")) {
                            mUrl = mUrl.substring(0, mUrl.length() - 1);
                        }
                        urlPaths.put(cUrl + mUrl + "_" + mPath.method().toString(), method);*/
                        // fin mode strict

                        urlPaths.put(cUrl + mUrl.replace("/", "") + "_" + mPath.method().toString(), method);
                    }

                }

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
