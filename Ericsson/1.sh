```bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/jre/
export PATH=$JAVA_HOME/bin:$PATH


sudo update-java-alternatives --list
export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
java -version


sudo update-alternatives --config java
sudo update-alternatives --set java /usr/lib/jvm/java-11-openjdk-amd64/bin/java
source ~/.bashrc   # or source ~/.profile or source ~/.bash_profile
```
