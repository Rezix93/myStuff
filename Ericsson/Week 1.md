
1. Change jdk version to fix crashes
2. 
```
 WEBKIT_DISABLE_DMABUF_RENDERER=1
 ~/research/eclipse/eclipse
```
3. Colors for graph
4. tooltip? how
5. 35hxc8bg password of something (Teams I guess)
6. Next projects
   6.1 Gerrit (software)
   6.2 Eifel
   6.3 scass
   6.4 Arm Automated Repository Manager (ARM) 
7.
```diff
-  SRE -> Gerrit vs Github
```

9. Get traces in VM for memory managing
10. One dataprover -> one veiw
11. for that 500m -> FIX
12. Control + T => All function overrided
13. XML Vs java
14.
```diff
- XML Vs java
```
15. Mr. Marc is someone from SRE
16. Create function shortcut: 
```diff
+ Alt + shift + m
```
17. 
```diff
- Beans Veiwer -> java
```
17. Tooltip
```diff
- Copy code here
```
18. PLEASE

```diff
+ Remove Print
```
19. Replace remove -> update null
```diff
-    ss.removeAttribute(ts, quarkKernelThread_task);
+    ss.modifyAttribute(ts, null, quarkKernelThread_task);
```
20. 
```diff
+ Make XY graph easier
```
21. Important!
```diff
@@ VisualVM
```
22. 
```diff
- Sampling vs Instrumening
```
23. Automatically Launch your analysis????
24. Schedluing the analysis
25. VisualVM:
setting: check tick
```diff
- How works!
```
26. VisualVM -> setting profile only classes: sampling frequency 50 ms -> org.eclipse.tracecompass.**
```diff
+ ~/Downloads/visualvm_219/bin/visualvm
```

![image](https://github.com/user-attachments/assets/d8a2ba5f-70b8-4b1e-a5d7-d4dd60926c84)

27. Tracing:
```diff
- Is there any problem?
- Where is it ?
- how can be solved?
```
28. ust tarces:
```
   Collection<ITmfTrace> traces = TmfTraceManager.getTraceSet(trace).stream().filter(tr-> tr instanceof LttngUstTrace).toList();
        List<ITmfTrace> filteredTraces = new ArrayList<>();
        //Filter UST events
        for (ITmfTrace t : traces) {
                if (t instanceof LttngUstTrace) {
                    filteredTraces.add(t);
                    //break;  // Exit the loop once the first LttngUstTrace is found and added
            }
        }
```
 29. ITmfCounterAspect vs CounterAspect
30. SparkTrace line 71 -> cheat ?
31. Spark Trace -> line 69 -> Create a function and draw it for all variables
32. list.of().toAaray Learn
33. casting<> in java learrn
```
ITmfTreeXYDataProvider<@NonNull ITmfTreeDataModel> provider = DataDrivenXYProviderFactory.create(trace, Collections.singletonList(stateSystem), Collections.singletonList(entry), ID + ":" + secondaryId);
```
34. A stack trace is a report of the active stack frames at a certain point in time during the execution of a program. It provides a snapshot of the call stack, which is a data structure that stores information about the active subroutines or function calls in a program.

35. For import all nessecary: 
```diff
- Control + shift + O
```
36. what is DPDK?
37. How trace compass works:
    
![5843825472255935601](https://github.com/user-attachments/assets/e9fabf41-ea5f-43c8-af30-64aee0bc164b)

38. By using the Counter we can bypas it and just parse and show it vitout analysis and statesytems
39. Counter.cores?
40. Application Meld?
41. For debugging: using F7 F8
42. Extend vs implement
43. In JAVA Class name start with Capital and varibale start with lowercase
44. After creating package in tarcecompass -> plugin.xml -> new Type -> Spark Trace -> screenshot: 
Package -> new class -> Spark Trace

write and show the process here
We want to have a new trace type. So we need a new package. where should we add it? yes in core. so we add a new pacakge name trace and in it we add a new class SparkTrace

&#x1F534; Then go to plgin.xml and add it as  new Extension 

"org.eclipse.linuxtools.tmf.core.analysis" spark kounter

In analysis there is a Counteranalysis existed in tarceCompass and we use it. and it needs a tracetype and we use SparkTrace.

![image](https://github.com/user-attachments/assets/9c59ee63-5c4c-445c-a6b9-8bc9a8b34040)

tracetype: SparkTrace (module):
![image](https://github.com/user-attachments/assets/7391bfd6-9ff1-4fe0-8af7-7c76a3a5d90b)

point="org.eclipse.linuxtools.tmf.core.tracetype" : Spark Trace (Type)
![image](https://github.com/user-attachments/assets/a771ec92-86b7-40c1-b2f2-78ddf0b53710)


Also in dependency add counter.core becasue we are using some existed core 
![image](https://github.com/user-attachments/assets/991c2a47-8085-4316-9c65-55a65fbee238)


In pluin.xml: 
![image](https://github.com/user-attachments/assets/b8994008-f2f3-4bf4-9852-d788320fe584)

Also in dependencies of ui.plugin.xml -> 
  ```
<analysisModuleClass
org.eclipse.tracecompass.analysis.counters.ui;bundle-version="2.1.0"
</analysisModuleClass>
 ```

```
Core:
1.1: mf.core.analysis -> spark Analysis (module) -> LttngUSTTrace
or
1.2 tmf.core.analysis -> spark Counter (module) -> SparkTrace


2. we also need dataprovider:
```
<dataProviderFactory
      class="org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis.TaskXYDataProviderFactory"
      id="org.eclipse.tracecompass.incubator.spark.taskxy.dataprovider">
</dataProviderFactory>
```
3. Also in some case like this we need new trace type: 
```
   <extension
         point="org.eclipse.linuxtools.tmf.core.tracetype">
      <type
            event_type="org.eclipse.tracecompass.tmf.core.event.TmfEvent"
            id="org.eclipse.tracecompass.incubator.spark_test1.core.type1"
            isDirectory="true"
            name="Spark Trace"
            trace_type="org.eclipse.tracecompass.incubator.internal.spark_test1.core.trace.SparkTrace">
      </type>
   </extension>
```

UI: 
```
45. aspects -> culumn
46. 
