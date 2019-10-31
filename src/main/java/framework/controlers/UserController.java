package framework.controlers;

import framework.configurationFramwork.annotations.*;
import framework.configurationFramwork.models.Model;
import framework.configurationFramwork.enumerations.HttpMethods;
import framework.models.Ana;
import framework.services.ServiceUserImpl;


@Controller
@Path("c")
public class UserController {

    @Inject
    ServiceUserImpl serviceUser;

    @Path(value = "user", method = HttpMethods.POST)
    public String postindex(@Body Ana ana, Model model) {
        model.put("w", 100);
        System.out.println("Khdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaam = [" + ana + "]");
        return "list";
    }

    @Path(value = "/user/", method = HttpMethods.GET)
    public String getindex() {
        return "index";
    }

    @Path(value = "/user2/", method = HttpMethods.GET)
    public String getindex2(@Param(name = "name") String name, @Param(name = "id") int id, Model model) {
        model.put("name", serviceUser.findAll());
        model.put("id", id);
        return "index";
    }

    @Path(value = "/walo/an/ajhbcjhbd/sdbcjhs/sdcbjhsbcd/", method = HttpMethods.GET)
    public String walowalo() {
        return "list";
    }

}
