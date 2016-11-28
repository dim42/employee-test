set GLASSFISH=c:\programs\glassfish3\glassfish\bin
set AGENT_URL=
set AGENT_USER=admin
rem full path to passwordfile
set AGENT_PASSWORD_FILE=c:\projects\glassfish\agent_password_file.txt
set SAAS_URL=
set SAAS_USER=admin
set SAAS_PASSWORD_FILE=c:\projects\glassfish\saas_password_file.txt
set AGENT_EAR_DIR=c:\projects\installation\agent
set SAAS_EAR_DIR=c:\projects\installation\saas

set SUBCOMMAND=deploy
if /I "%1" == "-re" (
	set SUBCOMMAND=redeploy
)
if /I "%1" == "-u" (
	goto undeploy
)
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% %SUBCOMMAND% --name oauth-admin-application %SAAS_EAR_DIR%\oauth-admin-application.ear
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% %SUBCOMMAND% --name ps-agent-application %SAAS_EAR_DIR%\ps-agent-application.ear
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% %SUBCOMMAND% --name ps-saas-admin-application %SAAS_EAR_DIR%\ps-saas-admin-application.ear
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% %SUBCOMMAND% --name ps-saas-application %SAAS_EAR_DIR%\ps-saas-application.ear
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% %SUBCOMMAND% --name ps-saas-payment-application %SAAS_EAR_DIR%\ps-saas-payment-application.ear
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% %SUBCOMMAND% --name ps-saas-provider-application %SAAS_EAR_DIR%\ps-saas-provider-application.ear
call %GLASSFISH%\asadmin --user %AGENT_USER% --passwordfile %AGENT_PASSWORD_FILE% --host %AGENT_URL% %SUBCOMMAND% --name oauth-application %AGENT_EAR_DIR%\oauth-application.ear
call %GLASSFISH%\asadmin --user %AGENT_USER% --passwordfile %AGENT_PASSWORD_FILE% --host %AGENT_URL% %SUBCOMMAND% --name ps-agent-application %AGENT_EAR_DIR%\ps-agent-application.ear
goto end

:undeploy
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% undeploy oauth-admin-application
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% undeploy ps-agent-application
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% undeploy ps-saas-admin-application
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% undeploy ps-saas-application
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% undeploy ps-saas-payment-application
call %GLASSFISH%\asadmin --user %SAAS_USER% --passwordfile %SAAS_PASSWORD_FILE% --host %SAAS_URL% undeploy ps-saas-provider-application
call %GLASSFISH%\asadmin --user %AGENT_USER% --passwordfile %AGENT_PASSWORD_FILE% --host %AGENT_URL% undeploy oauth-application
call %GLASSFISH%\asadmin --user %AGENT_USER% --passwordfile %AGENT_PASSWORD_FILE% --host %AGENT_URL% undeploy ps-agent-application
:end
