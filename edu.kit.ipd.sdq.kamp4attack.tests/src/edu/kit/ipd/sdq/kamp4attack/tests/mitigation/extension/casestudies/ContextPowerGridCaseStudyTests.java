package edu.kit.ipd.sdq.kamp4attack.tests.mitigation.extension.casestudies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.confidentiality.context.system.UsageSpecification;
import org.palladiosimulator.pcm.confidentiality.context.system.pcm.structure.ServiceRestriction;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;

import edu.kit.ipd.sdq.kamp4attack.model.modificationmarks.KAMP4attackModificationmarks.CompromisedAssembly;
import edu.kit.ipd.sdq.kamp4attack.model.modificationmarks.KAMP4attackModificationmarks.CompromisedLinkingResource;
import edu.kit.ipd.sdq.kamp4attack.model.modificationmarks.KAMP4attackModificationmarks.CompromisedResource;
import edu.kit.ipd.sdq.kamp4attack.model.modificationmarks.KAMP4attackModificationmarks.CompromisedService;
import edu.kit.ipd.sdq.kamp4attack.model.modificationmarks.KAMP4attackModificationmarks.ContextChange;
import edu.kit.ipd.sdq.kamp4attack.model.modificationmarks.KAMP4attackModificationmarks.CredentialChange;
import edu.kit.ipd.sdq.kamp4attack.tests.change.AbstractChangeTests;

public class ContextPowerGridCaseStudyTests extends AbstractChangeTests {

	public ContextPowerGridCaseStudyTests() {

		this.PATH_ATTACKER = "contextSelectionModels/powerGrid/My.attacker";
		this.PATH_ASSEMBLY = "contextSelectionModels/powerGrid/powerGrid.system";
		this.PATH_ALLOCATION = "contextSelectionModels/powerGrid/powerGrid.allocation";
		this.PATH_CONTEXT = "contextSelectionModels/powerGrid/My.context";
		this.PATH_MODIFICATION = "contextSelectionModels/powerGrid/My.kamp4attackmodificationmarks";
		this.PATH_REPOSITORY = "contextSelectionModels/powerGrid/target.repository";
		this.PATH_RESOURCES = "contextSelectionModels/powerGrid/powerGrid.resourceenvironment";
	}

	@Test
	void defaultCase() {

		runAnalysis();
	}

	@Test
	void defaultCaseCorrectAssemblyNumber() {
		runAnalysis();
		final var change = (CredentialChange) this.modification.getChangePropagationSteps().get(0);
		assertEquals(1, change.getCompromisedresource().size()); //5
		System.out.println(change.getCompromisedassembly().get(0).getAffectedElement().getCompromisedComponents()
				.stream().map(AssemblyContext::getEntityName).collect(Collectors.toList()));
		System.out.println(change.getCompromisedassembly().get(1).getAffectedElement().getCompromisedComponents()
				.stream().map(AssemblyContext::getEntityName).collect(Collectors.toList()));
		System.out.println(change.getCompromisedassembly().get(2).getAffectedElement().getCompromisedComponents()
				.stream().map(AssemblyContext::getEntityName).collect(Collectors.toList()));
		assertEquals(3, change.getCompromisedassembly().size()); //7
		assertEquals(3, change.getCompromisedservice().size()); //8
		assertEquals(6, change.getContextchange().size()); //4
		assertEquals(0, change.getCompromisedlinkingresource().size()); //1

		var containsRequiredAssemblies = change.getCompromisedassembly().stream().map(this::mapAffectedAssembly)
				.map(AssemblyContext::getEntityName).allMatch(this::assemblyNameMatch);

		var containsRequiredResources = change.getCompromisedresource().stream()
				.map(CompromisedResource::getAffectedElement).map(ResourceContainer::getEntityName)
				.allMatch(this::resourceNameMatch);

		var containsRequiredLinking = change.getCompromisedlinkingresource().stream()
				.map(CompromisedLinkingResource::getAffectedElement).map(LinkingResource::getEntityName)
				.allMatch(this::linkingNameMatch);

		var containsRequiredContext = change.getContextchange().stream().map(ContextChange::getAffectedElement)
				.allMatch(this::checkAttribute);

		change.getCompromisedservice().stream().map(CompromisedService::getAffectedElement)
				.allMatch(this::checkServiceRestriction);

		assertTrue(containsRequiredAssemblies);
		assertTrue(containsRequiredResources);
		assertTrue(containsRequiredLinking);
		assertTrue(containsRequiredContext);

	}

	private boolean assemblyNameMatch(String name) {
		var set = Set.of("Assembly_StorageApplication", "Assembly_CallCenterApplication", "ICS-VPN-Bridge",
				"AssemblyWithVPNRights", "Assembly_DomainControler", "AssemblyWithoutRights", "ExternalVPNBridge");
		return set.contains(name);
	}

	private boolean resourceNameMatch(String name) {
		var set = Set.of("Workstation01", "CallCenter", "DataCenter", "Workstation02", "VPNBridgeExternal");
		return set.contains(name);
	}

	private boolean linkingNameMatch(String name) {
		var set = Set.of("CorporateNetwork");
		return set.contains(name);
	}

	private boolean checkAttribute(UsageSpecification specification) {
		var attributeEquals = specification.getAttribute().getId().equals("_8fjUoi8jEeylPOrRpUZy4w");
		if (!attributeEquals) {
			return attributeEquals;
		}
		var set = Set.of("_-E3soC8jEeylPOrRpUZy4w", "_CkwYUC8kEeylPOrRpUZy4w", "_GNARYC8kEeylPOrRpUZy4w",
				"_XsEwUC8kEeylPOrRpUZy4w");
		return set.contains(specification.getAttributevalue().getId());
	}

	private boolean checkServiceRestriction(ServiceRestriction restriction) {
		var setAssembly = Set.of("Assembly_StorageApplication", "Assembly_CallCenterApplication", "ICS-VPN-Bridge",
				"AssemblyWithVPNRights", "Assembly_DomainControler", "AssemblyWithoutRights",
				"Assembly_DMSClientApplication", "Assembly_DMSServerApplication", "ExternalVPNBridge");

		var equalAssembly = setAssembly.contains(restriction.getAssemblycontext().getEntityName());
		if (!equalAssembly) {
			return equalAssembly;
		}
		var setServices = Set.of("_Q-7lUCwjEeylP6vhO63XvA", "_xXMikCwiEeylP6vhO63XvA", "_B6cScCwjEeylP6vhO63XvA",
				"_9onQ4CwiEeylP6vhO63XvA", "_xWrqQC2YEeyiUoiCEbquLw", "_G-t3wC2bEeyiUoiCEbquLw",
				"_ZHSHAC2YEeyiUoiCEbquLw", "_ZIvfkC2YEeyiUoiCEbquLw");
		return setServices.contains(restriction.getService().getId());

	}

	private AssemblyContext mapAffectedAssembly(CompromisedAssembly component) {
		return component.getAffectedElement().getCompromisedComponents().get(0);
	}
}
