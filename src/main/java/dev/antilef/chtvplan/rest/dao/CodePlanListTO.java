package dev.antilef.chtvplan.rest.dao;


import java.util.List;

public class CodePlanListTO {
    List<PlanREST> list;

    public void add(PlanREST planToAdd){
        this.list.add(planToAdd);
    }
}
