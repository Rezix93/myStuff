https://stackoverflow.com/questions/75556273/slf4j-marker-when-used-with-fluent-apis-is-ignored-by-log4j2-configuration-but
Use Log4j2 API instead of SLF4J. Log4j2 API is basically a superset of SLF4J (cf. Log4j2 API Logger and SLF4J Logger) and except a package change it is mostly source compatible with SLF4J.

Using the Log4j2 API, a call to atTrace() will return a real log builder if you use global filters and a no-op log builder if you don't (for improved performance, cf. the benchmarks in GH PR #1203).

You can keep using SLF4J, but you need to replace:
```bash
log.atTrace().addMarker(marker).log("This is logged using fluent API");
```
with
```bash
log.makeLoggingEventBuilder(Level.TRACE)
   .addMarker(marker)
   .log("This is logged using fluent api");
```
(cf. SLF4J-560). This solution will incur in a performance penalty for disabled loggers (whether you use global filters or not) and will always create a temporary object (that need to be GC-ed).


