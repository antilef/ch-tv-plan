package dev.antilef.chtvplan.controller;

import dev.antilef.chtvplan.dto.CreateDestinyPlanRequest;
import dev.antilef.chtvplan.entity.DestinyPlan;
import dev.antilef.chtvplan.repository.DestinyPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1")
@RestController
public class DestinyPlanController {



    @Autowired
    private DestinyPlanRepository repo;

    public DestinyPlanController(DestinyPlanRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/destinyPlanDemo")
    public List<DestinyPlan> getAllPlans(){
        List<DestinyPlan> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }
    @PostMapping("/destinyPlans")
    public void create(@RequestBody CreateDestinyPlanRequest request){
        DestinyPlan dp = new DestinyPlan();
        dp.setFixedCharge(Integer.parseInt(request.getFixedCharge()));
        dp.setName(request.getName());
        repo.save(dp);
    }

}
