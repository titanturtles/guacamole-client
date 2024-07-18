#!/bin/bash

# 2) copy file to the server
chmod +x ./guacamole/target/guacamole-1.5.5*.war
sudo cp ./guacamole/target/guacamole-1.5.5*.war /etc/guacamole/guacamole.war
sudo cp ./guacamole/target/guacamole-1.5.5*.war /var/lib/tomcat9/webapps/guacamole.war
sudo chown tomcat:tomcat /var/lib/tomcat9/webapps/guacamole.war

# 3) restart the service
sudo systemctl restart tomcat9 guacd
