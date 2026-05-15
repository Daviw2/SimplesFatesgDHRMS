:: Iniciar o servidor RMI
@echo off
:: echo Compilando projetos
:: mvn clean install package -DskipTests

echo Iniciando o servidor de dados RMI...

start /MIN /NORMAL "ServidorDeDados_1098" "start-servidor_de_dados.bat" 1098
start /MIN /NORMAL "ServidorDeDados_1099" "start-servidor_de_dados.bat" 1099

echo Iniciando o servidor de calculos JsonRPC

start /MIN /NORMAL "ServidorDeCalculos_1198" "start-servidor_de_calculos.bat" 1198
start /MIN /NORMAL "ServidorDeCalculos_1199" "start-servidor_de_calculos.bat" 1199

echo Iniciando a API Web...

start /MIN /NORMAL "Swagger API" "http://localhost:8080/swagger-ui/index.html#"
start /MIN /NORMAL "WebAPI_8080" "start-web_api.bat"

:: echo iniciando a aplicação web...
:: start /MIN /NORMAL "WebApp Navegador" "http://localhost:5173/"
:: start /MIN /NORMAL "WebApp Projeto" "start-web_app.bat" prod