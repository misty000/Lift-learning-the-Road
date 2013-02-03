SCRIPT_DIR=`dirname $0`
java -Drun.mode=production  -Xms512M -Xmx768M -Xss1M -XX:MaxPermSize=256M -Dfile.encoding=UTF-8 -XX:+CMSClassUnloadingEnabled -jar "$SCRIPT_DIR/project/sbt-launch-0.12.2.jar" $@
#java -Dfile.encoding=UTF-8 -XX:+CMSClassUnloadingEnabled -jar "$SCRIPT_DIR/project/sbt-launch-0.12.2.jar" $@
