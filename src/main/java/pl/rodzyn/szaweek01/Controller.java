package pl.rodzyn.szaweek01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Controller {

    private CountService countService;

    @Autowired
    public Controller(CountService countService) {
        this.countService = countService;
    }

    @GetMapping("/getAdmin")
    public String getAdmin(Principal principal){
        int visitNumber = countService.getVisitNumber(principal.getName());
        String time = countService.getTime(visitNumber);
        return "Hello: " + principal.getName() + " nice to see you." + '\n' +
                "You visit us " + visitNumber + " " + time;
    }


    @GetMapping("/getUser")
    public String getUser(Principal principal){
        int visitNumber = countService.getVisitNumber(principal.getName());
        String time = countService.getTime(visitNumber);
        return "Hello: " + principal.getName() + " nice to see you. "+ '\n' +
                "You visit us " + visitNumber +" " + time;
    }

    @GetMapping("/getVisitor")
    public String getVisitor(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello: " + authentication.getName() +",  nice to see you.";
    }

    @GetMapping("/getAll")
    public String getAll(){
        return "Papa";
    }


}
