1. To sign an ECA:
Create an account with the Eclipse Foundation if you have not already done so;
Sign a ECA.
Log into the Eclipse Contributor Agreement page;
Complete the form.
An Eclipse Account will grant you access to Gerrit, Bugzilla, and other Eclipse Foundation web resources.
Use the same account to contribute to Eclipse, LocationTech, and PolarSys projects.



2. **Create a Token:**
   - Click on `Personal access tokens` and then `Tokens (classic)`.
   - Click `Generate new token`.
   - Give your token a descriptive name and select the necessary scopes. At a minimum, you need `repo` access to push code to a repository.
   - Click `Generate token`.
   - **Copy the token**
   - To avoid being prompted for your PAT every time, you can store your credentials securely using a credential helper.
  ```bash
  git config --global credential.helper cache
  ```




3. Now time for Github
```
~/research$ cd org.eclipse.tracecompass
```

```
cat .git/config 
git config --global user.email "reza.rouhghalandari@ericsson.com"
git config "user.name" "Reza Rouhghalandari"
```

```
git checkout -b new-branch-name
git branch
git diff
git add file.name
git commit -m "some message!"
git push -u origin new-branch-name
```

```
Git staus
```
Choose what you want to add from the changes. For example you want to change 3 files: 

```
git add ctf/org.eclipse.tracecompass.tmf.ctf.core/src/org/eclipse/tracecompass/tmf/ctf/core/trace/CtfTmfTrace.java  tmf/org.eclipse.tracecompass.tmf.ui/src/org/eclipse/tracecompass/tmf/ui/project/model/TmfTraceElement.java  lttng/org.eclipse.tracecompass.lttng2.ust.core/src/org/eclipse/tracecompass/lttng2/ust/core/trace/LttngUstTrace.java -p 
```

