
#ps -def |grep java | grep -v grep |cut -c 9-15| xargs kill -9
ps -ef|grep java|grep -v grep|awk  '{print "kill -9 " $2}' |sh
ps -ef |grep java


rm -rf   log.log
rm -rf gc.log
nohup java  -Dfile.encoding=UTF-8     -jar   -Dspring.profiles.active=fwq  -Dserver.port=8080 app.jar >  log.log  2>&1 &