package framework.controlers;

import framework.configurationFramwork.annotations.*;
import framework.configurationFramwork.enumerations.HttpMethods;
import framework.configurationFramwork.models.Model;
import framework.models.User;
import framework.services.ServiceUser;

@Controller
@Path("/user")
public class UsersController {
    @Inject
    private ServiceUser serviceUser;

    @Path("/list")
    public String users(Model model) {
        model.put("users", serviceUser.findAll());
        model.put("motCle", serviceUser.getMotCle());
        return "user/list";
    }

    @Path(value = "/search", method = HttpMethods.GET)
    public String search(@Param(name = "motCle", defaultValue = "") String motCle) {
        serviceUser.setMotCle(motCle);
        return "redirect:/user/list";
    }

    @Path("/form")
    public String user(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }

    @Path(value = "/saveuser", method = HttpMethods.POST)
    public String saveUser(@Body User user) {
        serviceUser.save(user);
        return "redirect:/user/list";
    }

    @Path(value = "/delete", method = HttpMethods.GET)
    public String delete(@Param(name = "id") int id) {
        serviceUser.delete(id);
        return "redirect:/user/list";
    }

    @Path(value = "/edit", method = HttpMethods.GET)
    public String edit(Model model, @Param(name = "id") int id) {
        User r = serviceUser.findById(id);
        model.addAttribute("user", r);
        return "user/form";
    }
}
