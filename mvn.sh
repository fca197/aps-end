export JAVA_HOME=~/Library/Java/JavaVirtualMachines/corretto-21.0.5/Contents/Home
echo $JAVA_HOME
mvn -DskipTests clean package -U -s ~/.m2/settings-read.xml

#zip -rj target/lib.zip target/lib/


#mvn  -v
