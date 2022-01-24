package GuiaTuristico;

import GuiaTuristicoLN.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public String signUpyes(@ModelAttribute(value="signup") User user, Model model) throws SQLException, ParseException {
        Boolean is_sign = igestuser.register(user.getPassword(), user.getName(), user.getEmail());
        model.addAttribute("signupsucess", user);
        model.addAttribute("islogged", true);
        String size = String.valueOf(igestuser.getUsers().size());
        user.setId(size);
        log.info(user.getId());
        igestuser.getUsers().put(size, user);
        igestuser.saveUser(user);
        model.addAttribute("userId", user.getId());
        return "SignUpYes";
    }

    @GetMapping("/create_review")
    public String showForm(Model model) {
        Review rev = new Review();
        model.addAttribute("review", rev);
        return "greeting";
    }

    @PostMapping("/save_review")
    public String greetingSubmit(@ModelAttribute(value="review") Review rev, Model model) throws SQLException {
        model.addAttribute("review_saved", rev);
        igestuser.get_user(rev.getUserId()).getReviews().put(rev.getPlaceId(),rev);
        igestuser.saveReview(rev);
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
        Boolean is_log = igestuser.login(user.getId(),user.getPassword());
        User u = igestuser.get_user(user.getId());
        model.addAttribute("userYes",u);
        model.addAttribute("islogged", is_log);
        if(is_log) {
            return "loginYes";
        }
        else return "loginNo";
    }

    @GetMapping("/Perfil/{user_id}")
    public String perfil(@PathVariable String user_id, Model model){
        User u = igestuser.get_user(user_id);
        model.addAttribute("perfil",u);
        return "myprofile";
    }

    @GetMapping("/places/{city}")
    public String getAllPlaces(@PathVariable String city, Model model){
        List<Place> places = igestplaces.filter_by_city(city);
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

    @GetMapping("/plans/{user_id}")
    public String getAllPlans(@PathVariable String user_id, Model model){
        List<Plan> plans = igestuser.get_plans_by_user(user_id);
        Plan plan = plans.get(0);
        plan.setSTime(plan.getStartTime().toString());
        plan.setFTime(plan.getFinishTime().toString());
        model.addAttribute("plans",plan);
        return "plans";
    }

    @GetMapping("/create_plan")
    public String createPlan(Model model) {
        Plan plan = new Plan();
        model.addAttribute("plan", plan);
        return "createPlan";
    }

    @PostMapping("/save_plan")
    public String savePlan(@ModelAttribute(value="plan") Plan plan, Model model) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTimeS = LocalDateTime.parse(plan.getSTime(), formatter);
        LocalDateTime dateTimeF = LocalDateTime.parse(plan.getFTime(), formatter);
        model.addAttribute("plan_saved", plan);
        Boolean create_plan = igestuser.create_plan(plan.getUserID(),plan.getName(),dateTimeS,dateTimeF,plan.getDay(),plan.getCity());
        if(create_plan) {
            igestuser.updateOnePlan(plan);
        } else {
            igestuser.saveOnePlan(plan);
        }
        return "savePlan";
    }

}
