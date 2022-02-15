package edu.kit.ipd.sdq.kamp4attack.tests.mitigation.extension.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.kit.ipd.sdq.kamp4attack.core.AttackPropagationAnalysis;
import edu.kit.ipd.sdq.kamp4attack.model.modificationmarks.KAMP4attackModificationmarks.ContextChange;
import edu.kit.ipd.sdq.kamp4attack.model.modificationmarks.KAMP4attackModificationmarks.CredentialChange;

class ContextModelsTest extends AbstractModelTest {

    ContextModelsTest() {
        this.PATH_ATTACKER = "contextSelectionModels/simpleAttackmodels/SimpleModelTest/My.attacker";
        this.PATH_ASSEMBLY = "contextSelectionModels/simpleAttackmodels/SimpleModelTest/newAssembly.system";
        this.PATH_ALLOCATION = "contextSelectionModels/simpleAttackmodels/SimpleModelTest/newAllocation.allocation";
        this.PATH_CONTEXT = "contextSelectionModels/simpleAttackmodels/SimpleModelTest/My.context";
        this.PATH_MODIFICATION = "contextSelectionModels/simpleAttackmodels/SimpleModelTest/My.kamp4attackmodificationmarks";
        this.PATH_REPOSITORY = "contextSelectionModels/simpleAttackmodels/SimpleModelTest/newRepository.repository";
        this.PATH_USAGE = "contextSelectionModels/simpleAttackmodels/SimpleModelTest/newUsageModel.usagemodel";
        this.PATH_RESOURCES = "contextSelectionModels/simpleAttackmodels/SimpleModelTest/newResourceEnvironment.resourceenvironment";
    }

    @BeforeEach
    protected void execute() {
        generateXML();
        final var wrapper = getBlackboardWrapper();
        (new AttackPropagationAnalysis()).runChangePropagationAnalysis(wrapper);
    }

    @Test
    void testCorrectReturnSize() {
        final var steps = this.modification.getChangePropagationSteps();

        assertEquals(1, steps.size());
        assertEquals(1, ((CredentialChange) steps.get(0)).getCompromisedassembly().size());
        assertEquals(1, ((CredentialChange) steps.get(0)).getCompromisedresource().size());
        assertEquals(3, ((CredentialChange) steps.get(0)).getContextchange().size());
    }

    @Test
    void testCorrectReturnTypes() {

        final var steps = this.modification.getChangePropagationSteps();

        assertTrue(steps.stream().allMatch(CredentialChange.class::isInstance));
    }

    @Test
    void testCorrectReturnValues() {
        final var steps = this.modification.getChangePropagationSteps();

        final var resource = ((CredentialChange) steps.get(0)).getCompromisedresource().get(0).getAffectedElement();
        final var assembly = ((CredentialChange) steps.get(0)).getCompromisedassembly().get(0).getAffectedElement();

        final var contexts = ((CredentialChange) steps.get(0)).getContextchange().stream()
                .map(ContextChange::getAffectedElement).collect(Collectors.toList());
        assertEquals("_Fg8BQe2_Eeq6pfPMAIqEqg", resource.getId());
        assertEquals("_oO9U8O2-Eeq6pfPMAIqEqg", assembly.getCompromisedComponents().get(0).getId());

        final var context0 = contexts.get(0);
        final var context1 = contexts.get(1);
        final var context2 = contexts.get(2);
        

        assertTrue((context0.getId().equals("_sKKUUe4ZEeu1msiU_4h_hw")
        		&& context1.getId().equals("_IU-TUe4REeu3pq-4zSJQvg")
                && context2.getId().equals("_0SaqUe4YEeu1msiU_4h_hw")));

    }

    @Test
    void testNoNullValue() {
        final var steps = this.modification.getChangePropagationSteps();
        assertNotNull(steps);
    }

}
