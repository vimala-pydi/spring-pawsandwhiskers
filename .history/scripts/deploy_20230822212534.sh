#!/bin/bash

# Set paths
#TOMCAT_PATH="/path/to/tomcat"
#PROJECT_ROOT="/path/to/your/project-root"
TOMCAT_PATH="/Users/pydiv/JAM/2023Aug/apache-tomcat-9.0.78"
PROJECT_ROOT="/Users/pydiv/JAM/2023Aug/spring-pawsandwhiskers"


echo "**************************************************************"
echo "Compilation started!"
# Compile servlet
javac -classpath "$TOMCAT_PATH/lib/servlet-api.jar:$PROJECT_ROOT/lib/*" -d "$PROJECT_ROOT/target/classes" "$PROJECT_ROOT/src/main/java/servlets/ImageDisplayServlet.java"
echo "Compilation completed!"
echo "**************************************************************"

# Create WAR file
echo "Creating WAR file..."
mkdir -p "$PROJECT_ROOT/target/pawsandwhiskers/WEB-INF/classes" # Create directories
cp -r "$PROJECT_ROOT/src/main/webapps/" "$PROJECT_ROOT/target/pawsandwhiskers/"
cp -r "$PROJECT_ROOT/src/main/java/servlets/" "$PROJECT_ROOT/target/pawsandwhiskers/WEB-INF/classes/"
cp "$PROJECT_ROOT/lib/"* "$PROJECT_ROOT/target/pawsandwhiskers/WEB-INF/lib/"
cd "$PROJECT_ROOT/target/pawsandwhiskers/"
jar cvf "$PROJECT_ROOT/target/pawsandwhiskers.war" *
echo "JAR completed!"
echo "**************************************************************"
echo "Deploying to webserver.."
# Deploy to Tomcat
cp "$PROJECT_ROOT/target/pawsandwhiskers.war" "$TOMCAT_PATH/webapps/"
echo "**************************************************************"

echo "**************************************************************"
echo "Start Tomcat"
# Start Tomcat
"$TOMCAT_PATH/bin/startup.sh"
echo "**************************************************************"

echo "Web application deployed and Tomcat started. Access it at: http://localhost:8080/pawsandwhiskers/"

# Wait for user input to stop Tomcat
read -p "Press Enter to stop Tomcat..."
"$TOMCAT_PATH/bin/shutdown.sh"

echo "Tomcat stopped."