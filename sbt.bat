set SCRIPT_DIR=%~dp0
java  -Xms512M -Xmx1024M -Xss1M -XX:MaxPermSize=256M -Dfile.encoding=UTF-8 -XX:+CMSClassUnloadingEnabled -jar "%SCRIPT_DIR%\project\sbt-launch-0.12.1.jar" %*
