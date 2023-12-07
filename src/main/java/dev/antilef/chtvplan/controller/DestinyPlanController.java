package dev.antilef.chtvplan.controller;

import dev.antilef.chtvplan.entity.DestinyPlan;
import dev.antilef.chtvplan.repository.DestinyPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

}
