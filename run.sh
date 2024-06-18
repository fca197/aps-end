export JAVA_HOME=/Users/wangbao/Library/Java/JavaVirtualMachines/corretto-17.0.8.1/Contents/Home
echo $JAVA_HOME
mvn -DskipTests clean package
/Users/wangbao/Library/Java/JavaVirtualMachines/corretto-17.0.8.1/Contents/Home/bin/java -jar  ./target/app-encrypted.jar
#mvn  -v

#/Users/wangbao/Library/Java/JavaVirtualMachines/corretto-17.0.8.1/Contents/Home/bin/java   -jar ./target/app.jar

