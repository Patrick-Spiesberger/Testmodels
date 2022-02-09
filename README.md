# Testmodelle

## Siehe auch
* Analyse: https://github.com/Patrick-Spiesberger/Palladio-Addons-ContextConfidentialityAnalysis_Mitigation
* Meta-Modell: https://github.com/Patrick-Spiesberger/Palladio-Addons-ContextConfidentialityMetamodell_Mitigation

Dieses Projekt enthält die zu der Analyse dazugehörigen Testmodelle

## Vorraussetzungen

* Java (getestet mit JDK 11.0.13)
* git (getestet mit Version 2.17.1)
* Zum Ausführen dieser Tests wird die Analyse, das Meta-Modell und JUnit 5 benötigt

## Installation
* Klonen Sie dieses Repository
* Überprüfen Sie die korrekte Installation der Analyse und des Metamodells
* Öffnen Sie in Eclipse die Analyse
  * Rechtsklick auf Paket "edu.kit.ipd.sdq.kamp4attack" -> "Run as" -> "Eclipse Application" (innere Instanz)
* In innerer Instanz:
	* File -> Import -> General -> Existing Projects -> entpackten Ordner auswählen
	* Select All -> Import
	* Project -> Clean
	* Eclipse neustarten
	* Project -> Clean
  
 
## Ausführung
* Navigieren Sie im Project Explorer zu "edu.kit.ipd.sdq.kamp4attack.tests.src"
* Rechtsklick -> JUnit Plug-in Test
* Alle Tests sollten erfolgreich durchlaufen

## Änderungen in den Testmodellen
Um Änderungen in den Testmodellen vorzunehmen, navigieren Sie zu "org.palladiosimulator.pcm.confidentiality.context.analysis.testmodels.models"
und wählen Sie den entsprechnden Unterordner aus. In diesen Unterordnern liegen drei wesentliche Dateien:
* x.attacker
* x.context
* x.system <br>
Die genaue Spezifikation ist in dem README.md des Modells zu finden.
Um Änderungen vorzunehmen eignet sich die Selection-Ansicht. Dort können Sie konkreteElemente aufrufen und in den Properties (falls nicht geöffnet: Window -> Show View -> Properties) ändern. Die Modelle müssen vor dem Ausführen gespeichert werden. 
