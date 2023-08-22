#!/bin/bash

# Set paths
#TOMCAT_PATH="/path/to/tomcat"
#PROJECT_ROOT="/path/to/your/project-root"
TOMCAT_PATH="/Users/pydiv/JAM/2023Aug/apache-tomcat-9.0.78"
PROJECT_ROOT="/Users/pydiv/JAM/2023Aug/spring-pawsandwhiskers

# Compile servlet
javac -classpath "$TOMCAT_PATH/lib/servlet-api.jar" -d "$PROJECT_ROOT/target/classes" "$PROJECT_ROOT/src/main/java/servlets/ImageDisplayServlet.java"

# Create WAR file
jar cvf "$PROJECT_ROOT/target/pawsandwhiskers.war" -C "$PROJECT_ROOT/target/classes" .

# Deploy to Tomcat
cp "$PROJECT_ROOT/target/pawsandwhiskers.war" "$TOMCAT_PATH/webapps/"

# Start Tomcat
"$TOMCAT_PATH/bin/startup.sh"

echo "Web application deployed and Tomcat started. Access it at: http://localhost:8080/pawsandwhiskers/"

# Wait for user input to stop Tomcat
read -p "Press Enter to stop Tomcat..."
"$TOMCAT_PATH/bin/shutdown.sh"

echo "Tomcat stopped."