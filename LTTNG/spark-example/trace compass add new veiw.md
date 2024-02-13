1. In-package core
1.2. 1. Add an analysis Java class
1.3. in plugin.xml add it as a core.analysis
1.4. You need to add a data provider for some views like the one created from state systems. add it here as a core.dataprovider. If there was no dataprovider for the views, there is no need to add anything in this part.
![image](https://github.com/Rezix93/MyStuff/assets/80580733/0eb8645b-4033-439a-adc1-6224e6a31372)

2. In package view

2.1 create a Java class for that and import data provider or analysis class based on our application.
2.2 in the plugin we add a core.views and add our class
2.3 in the plugin we add a core.analysis  and the analysis we added before to the plugin over there.

![image](https://github.com/Rezix93/MyStuff/assets/80580733/ac8983d2-d449-4f86-93f1-a35af86e33e2)
