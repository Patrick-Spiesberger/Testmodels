package edu.kit.ipd.sdq.kamp4attack.tests.contextSelection.extension.travelPlanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CompositeEmptyAttackerTests extends CompositeTravelPlannerCaseStudy {

    public CompositeEmptyAttackerTests() {
        this.PATH_ATTACKER = "contextSelectionModels/compositeTravelPlanner/tests/01/test_model.attacker";
        this.PATH_CONTEXT = "contextSelectionModels/compositeTravelPlanner/tests/01/test_model.context";
        this.PATH_MODIFICATION = "contextSelectionModels/compositeTravelPlanner/tests/01/test_model.kamp4attackmodificationmarks";

    }

    @Test
    void noPropagation() {
        runAnalysis();

        var change = getCredentials();

        assertEquals(0, change.getCompromisedassembly().size());
        assertEquals(0, change.getCompromisedlinkingresource().size());
        assertEquals(0, change.getCompromisedresource().size());
        assertEquals(0, change.getCompromisedservice().size());
        assertEquals(0, change.getContextchange().size());

//        var attacker = getBlackboardWrapper().getModificationMarkRepository().getSeedModifications()
//                .getAttackcomponent().get(0).getAffectedElement();

        assertEquals(0, change.getCompromiseddata().size());

    }

}
