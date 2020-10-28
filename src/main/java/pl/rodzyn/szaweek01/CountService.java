package pl.rodzyn.szaweek01;

import org.springframework.stereotype.Service;

@Service
public class CountService {

    private int user;
    private int admin;

    public int getVisitNumber(String name){
        if(name == "User"){
            user++;
            return user;
        }else
            admin++;
            return admin;
    }

    public String getTime(int numberVisit){
        if(numberVisit == 1){
            return "time";
        }else{
            return "times";
        }
    }
}
