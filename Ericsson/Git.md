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

3. Now time for Github
```
~/research$ cd org.eclipse.tracecompass
```

```
git checkout -b new-branch-name
git branch
git diff
git add file.name
git commit -m "some message!"
git push -u origin new-branch-name
```


