/*******************************************************************************
 * (c) Copyright IBM Corporation 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package framework.configurationFramwork.dispatcher;

import framework.configurationFramwork.annotations.Body;
import framework.configurationFramwork.annotations.Param;
import framework.configurationFramwork.enumerations.HttpMethods;
import framework.configurationFramwork.models.Model;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HashMap<String, Object> context = new HashMap();
    private HashMap<String, Method> urlPaths = new HashMap();
    private Controllers controllers;
    private Services services;
    private Injections injections;
    private Tools tools;


    @Override
    public void init() throws ServletException {
        super.init();
        controllers = new Controllers(context, urlPaths);
        services = new Services(context);
        injections = new Injections(context);
        tools = new Tools();

        services.chargerServices();
        controllers.chargerControllers();
        injections.injectFields();

    }


    private String handleRequest(Method method, HttpServletRequest request, HttpMethods httpMethod) {
        String viewName = null;
        try {
            Parameter[] parameters = method.getParameters();
            ArrayList<Object> params = new ArrayList<>();
            Model model = new Model();
            if (httpMethod == HttpMethods.POST || httpMethod == HttpMethods.PUT) {
                for (Parameter parameter : parameters) {
                    if (parameter.isAnnotationPresent(Body.class)) {
                        params.add(this.tools.inputStreamToObject(request.getInputStream(), parameter.getType()));
                    } else if (parameter.getType() == Model.class) {
                        params.add(model);
                    }
                }
            } else {
                for (Parameter parameter : parameters) {
                    if (parameter.getType() == Model.class) {
                        params.add(model);
                    } else if (parameter.isAnnotationPresent(Param.class)) {
                        Param param = parameter.getAnnotation(Param.class);
                        System.out.println("parameter.getType() = [" + parameter.getType() + "]");
                        String valueparam = request.getParameter(param.name());
                        if (valueparam == null || valueparam.isEmpty())
                            valueparam = param.defaultValue();
                        if (parameter.getType() == int.class) {
                            params.add(Integer.parseInt(valueparam));
                        } else if (parameter.getType() == float.class)
                            params.add(Float.parseFloat(valueparam));
                        else if (parameter.getType() == double.class)
                            params.add(Double.parseDouble(valueparam));
                        else if (parameter.getType() == long.class)
                            params.add(Long.parseLong(valueparam));
                        else
                            params.add(valueparam);
                    } else {
                        params.add(request.getParameter(parameter.getName()));
                    }
                }
            }
            System.out.println("method params.size()  = [" + params.size() + "]");
            viewName = (String) method.invoke(context.get(method.getDeclaringClass().getName()), params.toArray());

            for (String key : model.keySet()) {
                request.setAttribute(key, model.get(key));
            }

        } catch (IOException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return viewName;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpMethods httpMethod = HttpMethods.valueOf(request.getMethod());
        Method method = controllers.getMethod(request.getServletPath(), httpMethod);
        if (method != null) {
            String viewName = handleRequest(method, request, httpMethod);
            if (viewName.startsWith("redirect:"))
                response.sendRedirect(request.getContextPath() + "" + viewName.replace("redirect:", ""));
            else
                request.getRequestDispatcher("/" + viewName + ".jsp").forward(request, response);
        } else {
            response.sendError(404, "not found HHHHHHHHHHHHH");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
