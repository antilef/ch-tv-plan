package dev.antilef.chtvplan.rest.dao;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodePlanListTO {
    List<PlanREST> list;

    public CodePlanListTO(List<PlanREST> list) {
        this.list = list;
    }
    public CodePlanListTO() {
        this.list = new ArrayList<>();
    }

    public void add(PlanREST planToAdd){
        this.list.add(planToAdd);
    }
}
