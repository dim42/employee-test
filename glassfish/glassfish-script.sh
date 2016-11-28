#!/bin/sh
# Put Connector/J (e.g mysql-connector-java-5.1.38-bin.jar) to $glassfish/domains/domain1/lib/ext 
# full path to glassfish
GLASSFISH=~/Java/glassfish4/glassfish/bin
SERVER_URL=127.0.0.1
SERVER_USER=admin
# full path to passwordfile
SERVER_PASSWORD_FILE=~/projects/employee-test/glassfish/server_password_file.txt
SERVER_HOST="--user $SERVER_USER --passwordfile $SERVER_PASSWORD_FILE --host $SERVER_URL"
DB_URL=127.0.0.1
DB_NAME=employee_db
DB_CON_POOL=jdbc/employeeConPool
DB_DATA_SOURCE=jdbc/employeeDataSource
DB_PORT=3306
DB_USER=root
DB_PASSWORD=pass

if [ "$1" = "-d" ]; then
    #delete JDBC resources
    $GLASSFISH/asadmin $SERVER_HOST delete-jdbc-resource $DB_DATA_SOURCE
    
    $GLASSFISH/asadmin $SERVER_HOST delete-jdbc-connection-pool $DB_CON_POOL
    
    $GLASSFISH/asadmin $SERVER_HOST list-jdbc-connection-pools
    $GLASSFISH/asadmin $SERVER_HOST list-jdbc-resources
else
    #create JDBC resources
    $GLASSFISH/asadmin $SERVER_HOST create-jdbc-connection-pool --isolationlevel read-committed --ping true --restype javax.sql.XADataSource --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlXADataSource --property user=$DB_USER:password=$DB_PASSWORD:DatabaseName=$DB_NAME:ServerName=$DB_URL:port=$DB_PORT:CharacterEncoding=UTF-8:ClobCharacterEncoding=UTF-8 $DB_CON_POOL
    $GLASSFISH/asadmin $SERVER_HOST ping-connection-pool $DB_CON_POOL

    $GLASSFISH/asadmin $SERVER_HOST create-jdbc-resource --connectionpoolid $DB_CON_POOL $DB_DATA_SOURCE

    $GLASSFISH/asadmin $SERVER_HOST list-jdbc-connection-pools
    $GLASSFISH/asadmin $SERVER_HOST list-jdbc-resources
fi
