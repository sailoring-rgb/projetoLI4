package GuiaTuristico;

import GuiaTuristicoLN.*;
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
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Controller
public class GuiaTuristicoController {

    @Autowired
    IGestUser igestuser;

    @Autowired
    IGestPlace igestplaces;

    //Home Page
    @GetMapping("/Home")
    public String homePg(Model model){
        return "home";
    }

    //Home Page of the user
    @GetMapping("/Home/{user_id}")
    public String homePost(@PathVariable String user_id, Model model){
        User u = igestuser.get_user(user_id);
        model.addAttribute("user",u);
        return "homePost";
    }


    //Login Page
    @GetMapping("/Login")
    public String logIn(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    //Post Login page
    @PostMapping("/Login")
    public String logInYes(@ModelAttribute(value= "user" ) User user, Model model){
        Boolean is_log = igestuser.login(user.getId(),user.getPassword());
        User u = igestuser.get_user(user.getId());
        model.addAttribute("userPost",u);
        if(is_log) {
            return "loginPost";
        }
        else return "loginError";
    }

    //SignUp Page
    @GetMapping("/SignUp")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("signup", user);
        return "signup";
    }

    //Sucessful signup
    @PostMapping("/SignUp")
    public String signUpyes(@ModelAttribute(value="signup") User user, Model model) throws SQLException, ParseException {
        Boolean is_sign = igestuser.register(user.getPassword(), user.getName(), user.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("islogged", true);
        String size = String.valueOf(igestuser.getUsers().size());
        user.setId(size);
        igestuser.getUsers().put(size, user);
        igestuser.saveUser(user);
        return "signupPost";
    }

    //List of places
    @GetMapping("/Place")
    public String place(Model model){
        List<Place> p = igestplaces.getPlaces().values().stream().map(Place::clone).collect(Collectors.toList());
        model.addAttribute("places",p);
        return "placesTable";
    }

    //List of pages when user is logged
    @GetMapping("/Place/u/{user_id}")
    public String placeU(@PathVariable String user_id,Model model){
        List<Place> p = igestplaces.getPlaces().values().stream().map(Place::clone).collect(Collectors.toList());
        User u = igestuser.get_user(user_id);
        model.addAttribute("user",u);
        model.addAttribute("places",p);
        return "placesTablePost";
    }

    //Place's page
    @GetMapping("/Place/{place_id}")
    public String place(@PathVariable String place_id, Model model){
        Place p = igestplaces.getOnePlace(place_id);
        float classification = p.calculateClassification();
        model.addAttribute("classification",classification);
        model.addAttribute("place",p);
        return "place";
    }

    //Place's page when user is logged
    @GetMapping("/Place/{place_id}/{user_id}")
    public String place(@PathVariable String place_id,@PathVariable String user_id, Model model){
        Place p = igestplaces.getOnePlace(place_id);
        float classification = p.calculateClassification();
        User u = igestuser.get_user(user_id);
        model.addAttribute("user",u);
        model.addAttribute("classification",classification);
        model.addAttribute("place",p);
        return "placePost";
    }

    //Create Review Page
    @GetMapping("/create_review")
    public String showForm(Model model) {
        Review rev = new Review();
        model.addAttribute("review", rev);
        return "greeting";
    }

    //Save Review page
    @PostMapping("/save_review")
    public String greetingSubmit(@ModelAttribute(value="review") Review rev, Model model) throws SQLException {
        model.addAttribute("review_saved", rev);
        igestuser.get_user(rev.getUserId()).getReviews().put(rev.getPlaceId(),rev);
        igestuser.saveReview(rev);
        return "result";
    }

    //Profile page
    @GetMapping("/Perfil/{user_id}")
    public String perfil(@PathVariable String user_id, Model model){
        User u = igestuser.get_user(user_id);
        model.addAttribute("user",u);
        return "profile";
    }

    //List of user reviews
    @GetMapping("/Reviews/{user_id}")
    public String getAllReviews(@PathVariable String user_id, Model model){
        List<Review> reviews = igestuser.get_reviews_by_user(user_id);
        User u = igestuser.get_user(user_id);
        model.addAttribute("user",u);
        model.addAttribute("reviews",reviews);
        return "reviewsTable";
    }

    //Page of the user's review
    @GetMapping("/Reviews/{user_id}/{place_id}")
    public String getUserPlaceReview(@PathVariable String user_id, @PathVariable String place_id, Model model){
        Review rev = igestuser.getReviewUserPlace(user_id,place_id);
        Place place = igestplaces.getPlaces().get(place_id);
        User u = igestuser.get_user(user_id);
        float classification = place.calculateClassification();
        model.addAttribute("user",u);
        model.addAttribute("review",rev);
        model.addAttribute("place",place);
        model.addAttribute("classification",classification);
        return "review";
    }

    //Page of the user's plan
    @GetMapping("/Plan/{user_id}")
    public String getAllPlans(@PathVariable String user_id, Model model){
        List<Plan> plans = igestuser.get_plans_by_user(user_id);
        User u = igestuser.get_user(user_id);
        model.addAttribute("user", u);
        if (plans.size() > 0) {
            if (plans.get(0) != null) {
                Plan plan = plans.get(0);
                plan.setSTime(plan.getStartTime().toString());
                plan.setFTime(plan.getFinishTime().toString());
                model.addAttribute("plan", plan);
                return "plan";
            }
        }
        return "planEmpty";
    }

    //Page to create a place
    @GetMapping("/create_plan")
    public String createPlan(Model model) {
        Plan plan = new Plan();
        model.addAttribute("plan", plan);
        return "createPlan";
    }

    //Save plan page
    @PostMapping("/save_plan")
    public String savePlan(@ModelAttribute(value="plan") Plan plan, Model model) throws SQLException {
        model.addAttribute("plan_saved", plan);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTimeS = LocalDateTime.parse(plan.getSTime(), formatter);
        LocalDateTime dateTimeF = LocalDateTime.parse(plan.getFTime(), formatter);
        plan.setFinishTime(dateTimeF);
        plan.setStartTime(dateTimeS);
        Boolean create_plan = igestuser.create_plan(plan.getUserID(),plan.getName(),dateTimeS,dateTimeF,plan.getDay(),plan.getCity());
        if(create_plan) {
            igestuser.updateOnePlan(plan);
        } else {
            igestuser.savePlan(plan);
        }
        return "savePlan";
    }

}
