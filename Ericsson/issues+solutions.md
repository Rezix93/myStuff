```
eclipse.buildId=4.30.0.20231201-1200
java.version=17.0.9
java.vendor=Eclipse Adoptium
BootLoader constants: OS=linux, ARCH=x86_64, WS=gtk, NL=en_CA
Framework arguments:  -product org.eclipse.epp.package.committers.product
Command-line arguments:  -os linux -ws gtk -arch x86_64 -product org.eclipse.epp.package.committers.product

org.eclipse.core.resources
Error
Mon Jul 29 11:58:36 EDT 2024
Problems occurred when invoking code from plug-in: "org.eclipse.core.resources".

org.eclipse.core.runtime.CoreException: Variable references non-existent resource : ${workspace_loc:/org.eclipse.tracecompass.tmf.pcap.doc.user/build.xml}
	at org.eclipse.debug.internal.core.variables.WorkspaceResolver.resolveValue(WorkspaceResolver.java:66)
	at org.eclipse.core.internal.variables.DynamicVariable.getValue(DynamicVariable.java:56)
	at org.eclipse.core.internal.variables.StringSubstitutionEngine.resolve(StringSubstitutionEngine.java:271)
	at org.eclipse.core.internal.variables.StringSubstitutionEngine.substitute(StringSubstitutionEngine.java:195)
	at org.eclipse.core.internal.variables.StringSubstitutionEngine.performStringSubstitution(StringSubstitutionEngine.java:91)
	at org.eclipse.core.internal.variables.StringVariableManager.performStringSubstitution(StringVariableManager.java:548)
	at org.eclipse.core.internal.variables.StringVariableManager.performStringSubstitution(StringVariableManager.java:347)
	at org.eclipse.core.externaltools.internal.launchConfigurations.ExternalToolsCoreUtil.getLocation(ExternalToolsCoreUtil.java:75)
	at org.eclipse.ant.internal.launching.launchConfigurations.AntLaunchDelegate.launch(AntLaunchDelegate.java:172)
	at org.eclipse.debug.internal.core.LaunchConfiguration.launch(LaunchConfiguration.java:805)
	at org.eclipse.debug.internal.core.LaunchConfiguration.launch(LaunchConfiguration.java:716)
	at org.eclipse.debug.internal.core.LaunchConfiguration.launch(LaunchConfiguration.java:711)
	at org.eclipse.core.externaltools.internal.model.ExternalToolBuilder.launchBuild(ExternalToolBuilder.java:188)
	at org.eclipse.core.externaltools.internal.model.ExternalToolBuilder.clean(ExternalToolBuilder.java:303)
	at org.eclipse.core.internal.events.BuildManager$2.run(BuildManager.java:1084)
	at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:47)
	at org.eclipse.core.internal.events.BuildManager.basicBuild(BuildManager.java:296)
	at org.eclipse.core.internal.events.BuildManager.basicBuild(BuildManager.java:352)
	at org.eclipse.core.internal.events.BuildManager$1.run(BuildManager.java:441)
	at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:47)
	at org.eclipse.core.internal.events.BuildManager.basicBuild(BuildManager.java:444)
	at org.eclipse.core.internal.events.BuildManager.basicBuildLoop(BuildManager.java:555)
	at org.eclipse.core.internal.events.BuildManager.basicBuildLoop(BuildManager.java:503)
	at org.eclipse.core.internal.events.BuildManager.build(BuildManager.java:585)
	at org.eclipse.core.internal.resources.Workspace.buildInternal(Workspace.java:594)
	at org.eclipse.core.internal.resources.Workspace.build(Workspace.java:483)
	at org.eclipse.ui.internal.ide.dialogs.CleanDialog.doClean(CleanDialog.java:355)
	at org.eclipse.ui.internal.ide.dialogs.CleanDialog$1.runInWorkspace(CleanDialog.java:159)
	at org.eclipse.core.internal.resources.InternalWorkspaceJob.run(InternalWorkspaceJob.java:43)
	at org.eclipse.core.internal.jobs.Worker.run(Worker.java:63)
```
