Reza and Patrick, this is a way to have Mentat and Spark unified in their dataprovider strategy, but also, contribute towards customizable x-y plots.
 
Basically, we have the  
AbstractTreeCommonXDataProvider
that should be enough. But many dataproviders don't use it. There is the 
ITmfTreeXYDataProvider
 and the 
ITmfXYDataProvider
that work in similar ways. This effort should aim to have a single file to extend for ALL XY data providers. The end result being a simplified way of working and one single area to extend and improve when the need arises."
![image](https://github.com/user-attachments/assets/aa89b29d-215f-400a-b6dd-44db6922059b)
