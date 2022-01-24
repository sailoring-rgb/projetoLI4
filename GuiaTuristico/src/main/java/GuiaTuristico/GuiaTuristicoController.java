package GuiaTuristico;

import GuiaTuristicoLN.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class GuiaTuristicoController {

    private Logger log = LoggerFactory.getLogger(GuiaTuristicoController.class);

    @Autowired
    IGestUser igestuser;

    @Autowired
    IGestPlace igestplaces;

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
        return "myprofile";
    }

    @GetMapping("/places/{city}")
    public String getAllPlaces(@PathVariable String city, Model model){
        List<Place> places = igestplaces.filter_by_city(city);
        /*log.info(city);
        for(Place p : places){
            log.info(p.getName());
            log.info(p.getCategory());
            log.info(p.getLocation());
            log.info(p.getId());
        }*/
        model.addAttribute("places",places);
        return "plans";
    }

    @GetMapping("/reviews/{user_id}")
    public String getAllReviews(@PathVariable String user_id, Model model){
        List<Review> reviews = igestuser.get_reviews_by_user(user_id);
        model.addAttribute("reviews",reviews);
        return "ReviewTable";
    }

    @GetMapping("/reviews/{user_id}/{place_id}")
    public String getUserPlaceReview(@PathVariable String user_id, @PathVariable String place_id, Model model){
        Review rev = igestuser.getReviewUserPlace(user_id,place_id);
        Place place = igestplaces.getPlaces().get(place_id);
        float classification = place.calculateClassification();
        model.addAttribute("review",rev);
        model.addAttribute("place",place);
        model.addAttribute("classification",classification);
        return "rev";
    }

}
