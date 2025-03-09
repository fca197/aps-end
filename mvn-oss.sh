export JAVA_HOME=~/Library/Java/JavaVirtualMachines/corretto-21.0.6/Contents/Home
echo $JAVA_HOME
mvn -DskipTests clean compile package  -s ~/.m2/settings-oss.xml

#zip -rj target/lib.zip target/lib/


#mvn  -v
