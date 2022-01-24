package GuiaTuristico;

import GuiaTuristicoLN.IGestUser;
import GuiaTuristicoLN.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuiaTuristicoController {

    private Logger log = LoggerFactory.getLogger(GuiaTuristicoController.class);

    @Autowired
    IGestUser igestuser;

    @GetMapping("/Home")
    public String homePg(Model model){
        return "Pg";
    }

    @GetMapping("/user/{user_id}")
    public String getUserName(Model model, @PathVariable String user_id) {
        log.info("entrou");
        log.info(user_id);
        User user = igestuser.get_user(user_id);
        String user_name = user.getName();
        String password = user.getPassword();
        String email = user.getEmail();
        //String name_plano = user.getPlans().get("Visita Igreja").getName();
        model.addAttribute("user_name", user_name);
        model.addAttribute("password", password);
        model.addAttribute("email", email);
        //model.addAttribute("plano", name_plano);
        return "user";
    }


    @GetMapping("/SignUp")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("signup", user);
        return "SignUp";
    }

    @PostMapping("/SignUp")
    public String signUpyes(@ModelAttribute(value="signup") User user, Model model) {
        model.addAttribute("signupsucess", user);
        model.addAttribute("islogged", true);
        String size = String.valueOf(igestuser.getUsers().size() + 1);
        user.setId(size);
        igestuser.getUsers().put(size, user);
        return "SignUpYes";
    }

    @GetMapping("/show_form")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "greeting";
    }

    @PostMapping("/save_user")
    public String greetingSubmit(@ModelAttribute(value="user") User user, Model model) {
        log.info(user.getName());
        log.info(user.getId());
        log.info(user.getEmail());
        model.addAttribute("user", user);
        return "result";
    }

    @GetMapping("/Login")
    public String logIn(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }


    @PostMapping("/Login")
    public String logInYes(@ModelAttribute(value= "user" ) User user, Model model){
        model.addAttribute("userYes",user);
        return "loginYes";
    }

    @GetMapping("/Perfil")
    public String perfil(@ModelAttribute User user,Model model){
        model.addAttribute("perfil",user);
        return "Perfil";
    }

}
