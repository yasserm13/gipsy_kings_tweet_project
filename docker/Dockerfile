FROM jboss/wildfly
 RUN mkdir -p /opt/jboss/images
 RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent
 COPY ./docker/standalone.xml /opt/jboss/wildfly/standalone/configuration/
 RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/com/mysql/driver/main
 RUN cd /opt/jboss/wildfly/modules/system/layers/base/com/mysql/driver/main
 COPY ./docker/mysql-connector-java-5.1.39.jar /opt/jboss/wildfly/modules/system/layers/base/com/mysql/driver/main/
 COPY ./docker/module.xml /opt/jboss/wildfly/modules/system/layers/base/com/mysql/driver/main/
 COPY ./target/gipsy-kings-tweet.war /opt/jboss/wildfly/standalone/deployments/ 
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "--debug"]
