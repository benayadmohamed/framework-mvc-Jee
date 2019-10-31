package framework.controlers;

import framework.configurationFramwork.annotations.Controller;
import framework.configurationFramwork.annotations.Path;
import framework.configurationFramwork.models.Model;

@Controller
@Path("/")
public class HomeController {

    @Path("/")
    public String index() {

        return "redirect:/user/list";
    }
}
