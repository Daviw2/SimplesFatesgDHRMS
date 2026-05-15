@echo off

if /i "%~1"=="prod" goto :prod

:dev
cd web_api
echo Compilando projetos
start /B mvnw.cmd clean install package -DskipTests
start /B mvnw.cmd spring-boot:run
goto :eof

:prod
:: Verifica se o arquivo .jar do web_api existe antes de tentar executá-lo
if not exist ".\web_api\target\web_api-1.0-SNAPSHOT.jar" (
    echo "O arquivo .jar do web_api não foi encontrado. Certifique-se de que o projeto foi compilado corretamente."
    pause
    exit /b
)
java -jar ".\web_api\target\web_api-1.0-SNAPSHOT.jar"

:eof
cd ..
exit