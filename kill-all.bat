@echo off
:: Encerra todos os projetos java
:: Cuidado, verificar Gerenciador de tarefas antes.
taskkill /F /IM "java.exe"

taskkill /FI "WINDOWTITLE eq ServidorDeDados_1098" 
taskkill /FI "WINDOWTITLE eq ServidorDeDados_1099" 
taskkill /FI "WINDOWTITLE eq ServidorDeCalculos_1198" 
taskkill /FI "WINDOWTITLE eq ServidorDeCalculos_1199" 
taskkill /FI "WINDOWTITLE eq WebAPI_8080" 
