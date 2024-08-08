### 1. API baseline:
An API baseline is a `reference point` used in software development to track and compare changes to an API over time. It serves as a snapshot of the API at a specific point in time, usually corresponding to a released version. By comparing the current state of the API against the baseline, developers can identify changes, ensure backward compatibility, and detect any unintended breakages.
#### Key Concepts of API Baselines

1. **Reference Point**:  2. **Backward Compatibility**:  3. **Change Detection**: 4. **Versioning**: 
#### Benefits of Using an API Baseline

- **Consistency**: Helps maintain a consistent API for users and developers.
- **Quality Assurance**: Provides a mechanism to check for accidental changes or breakages.
- **Documentation**: Assists in generating accurate change logs and documentation by highlighting differences from the baseline.
- **Compliance**: Ensures compliance with API design and versioning policies.
![image](https://github.com/user-attachments/assets/9c466a3c-1f66-483c-9c84-c56bfafc8ed9)



2. An example of depricate Fucntion in java:

Deprecation is a way to phase out old methods while maintaining backward compatibility. By deprecating a method, developers are informed that they should use a new or updated method instead. This is particularly useful in large codebases where immediate removal of old methods could break existing functionality.

```
  /**
    @Deprecated
    protected void handleGroupedCounterAspect(ITmfEvent event, ITmfStateSystemBuilder ss, CounterAspect aspect, int rootQuark) {
        handleGroupedCounterAspect(event, ss, rootQuark, aspect);
    }

 protected void handleGroupedCounterAspect(ITmfEvent event, ITmfStateSystemBuilder ss, int rootQuark, ITmfCounterAspect aspect) {
        }
```
3. ### Gerrit

`Gerrit` is a web-based code review tool that integrates with Git. Ensuring "cleanliness" in Gerrit typically refers to maintaining a well-organized, efficient, and manageable code review environment. This can include several practices aimed at keeping the codebase and review process in good shape.

#### Commands Recap

1. **Local Changes**:
   ```sh
   echo "print('Hello, Gerrit!')" > hello.py
   git add hello.py
   git commit -m "Add hello script"
   ```

2. **Push to Gerrit**:
   ```sh
   git push origin HEAD:refs/for/master
   ```

3. **Review and Approval**:
   - Use the Gerrit web interface for reviewing and approving changes.
