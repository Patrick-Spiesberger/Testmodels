package edu.kit.ipd.sdq.kamp4attack.tests.mitigation.extension.casestudies.travelplanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class CompositeEmptyAttackTests extends CompositeTravelPlannerCaseStudy {

    public CompositeEmptyAttackTests() {
        this.PATH_ATTACKER = "compositeTravelPlanner/tests/02/test_model.attacker";
        this.PATH_CONTEXT = "compositeTravelPlanner/tests/02/test_model.context";
        this.PATH_MODIFICATION = "compositeTravelPlanner/tests/02/test_model.kamp4attackmodificationmarks";
    }

    @Test
    void noPropagation() {
        runAnalysis();

        var change = getCredentials();

        assertEquals(1, change.getCompromisedassembly().size());
        assertEquals(0, change.getCompromisedlinkingresource().size());
        assertEquals(0, change.getCompromisedresource().size());
        //assertEquals(3, change.getCompromisedservice().size());
        assertEquals(0, change.getContextchange().size());

        checkAssembly(change);

//        var attacker = getBlackboardWrapper().getModificationMarkRepository().getSeedModifications()
//                .getAttackcomponent().get(0).getAffectedElement();


        //assertEquals(10, change.getCompromiseddata().size());


    }

    @Override
    protected boolean assemblyNameMatch(String name) {
        var set = Set.of("Assembly_TravelPlanner");
        return set.contains(name);
    }

}
