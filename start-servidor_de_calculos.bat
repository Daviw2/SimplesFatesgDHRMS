@echo off
:: Verifica se o arquivo .jar do servidor de dados existe antes de tentar executá-lo
if not exist ".\servidor_de_calculos\target\servidor_de_calculos-1.0-SNAPSHOT-jar-with-dependencies.jar" (
    echo "O arquivo .jar do servidor de dados não foi encontrado. Certifique-se de que o projeto foi compilado corretamente."
    pause
    exit /b
)
java -jar ".\servidor_de_calculos\target\servidor_de_calculos-1.0-SNAPSHOT-jar-with-dependencies.jar" %1