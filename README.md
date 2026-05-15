# Projeto para prova de Desenvolvimento de Sistemas Distribuídos

## Instruções

* Avaliação individual, não sendo permitido contato com colega, caso ocorra a prova será desconsiderada e a nota será zerada!
* Avaliação prática, logo:
    - Permite a utilização de computador
    - Permite a utilização de programas (exceto programas de mensagens, leia acima)
    - Permite acesso a internet


## Entrega

##### Preparar
* Executa o `mvn clean`, para limpar as pastas **target**.
* Compactar a pasta (repositório) com todos os arquivos, exceto as pastas target.
* Enviar em anexo por email, caso o anexo não seja permitido, o aluno deverá enviar para o um gerenciador de arquivo online (Google Drive, Microsoft One, Dropbox, WeTransfer, etc) e enviar um email com o link de download.
* O email de envio <ramyres90@gmail.com> com:
    - Assunto: **Avaliação Fatesg_2026-1_ADS4_DSD_N2 - [SEU_NOME]**
    - Mensagem: 
        ```txt
        Avaliação em anexo.
        Matrícula: [SUA MATRICULA]
        Aluno: [SEU NOME COMPLETO]
        Github: [SUA CONTA DO GITHUB]
        ```
    - Anexo: **Avaliacao_Fatesg_2026_1_ADS4_DSD_N2.zip**


##### Atenção:
* A pasta .git deve estar no arquivo compactado, **sem ela a nota é zero!**
* As questões serão corrigidas pelo commit realizado, logo faça o commit questão por questão. _Lembrem-se de verificar as configurações de usuário do git (git config --list)_
* Siga todas as orientações conforme foram descritas.


## Cofigurações

### Java

* Versão JDK >= 21
* Versão JRE >= 21
* Configuração das variáveis de ambiente
    - JAVA_HOME
        * Ex: "C:\Ferramentas\jdk-24.0.1"
    - PATH (Já existe a variável no sistema, apenas adicione a pasta bin)
        * Ex: "C:\Ferramentas\jdk-24.0.1\bin"


### Maven

* Versão Apache Maven >= 3.9 
* Configuração das variáveis de ambiente
    - PATH (Já existe a variável no sistema, apenas adicione a pasta bin)
        * Ex: "C:\Ferramentas\maven\bin"

### Node \[Opcional\]

* Frontend

### Git

* Versão >= 2.49
* Conta no Github <https://www.github.com>


##### Configuração

* `git config --local user.email [SEU EMAIL DO GITHUB]`
* `git config --local user.name [SEU NOME]`


##### Fork

1. Acesso o repositório 
1. Clique no botão Fork <button> <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-repo-forked mr-1 tmp-mr-1">
    <path d="M5 5.372v.878c0 .414.336.75.75.75h4.5a.75.75 0 0 0 .75-.75v-.878a2.25 2.25 0 1 1 1.5 0v.878a2.25 2.25 0 0 1-2.25 2.25h-1.5v2.128a2.251 2.251 0 1 1-1.5 0V8.5h-1.5A2.25 2.25 0 0 1 3.5 6.25v-.878a2.25 2.25 0 1 1 1.5 0ZM5 3.25a.75.75 0 1 0-1.5 0 .75.75 0 0 0 1.5 0Zm6.75.75a.75.75 0 1 0 0-1.5.75.75 0 0 0 0 1.5Zm-3 8.75a.75.75 0 1 0-1.5 0 .75.75 0 0 0 1.5 0Z"></path>
</svg> Fork </button>

1. Realize a copia do repositório para sua conta
1. Faça o clone
    * `git clone [URL DO REPOSITORIO NA SUA CONTA]`


### VSCode

* Extensões:
    - Extension Pack for Java Microsoft <https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack>


### Banco de Dados

Utilizaremos o banco de dados employees do repositótio test_db.

* By: Giuseppe Maxia [@datacharmer](https://github.com/datacharmer)
* Disponível em: <https://github.com/datacharmer/test_db>
* Referência: <https://dev.mysql.com/doc/employee/en/>


##### Importação

1. Baixe o repositório como zip (<https://github.com/datacharmer/test_db/archive/refs/heads/master.zip>)
1. Descompacte o arquivo baixado
1. Acesse a pasta via terminal (run `cmd`)
1. Realize a restauração com o comando: `mysql -u [USUÁRIO ROOT OU DBA] -p --commands < employees.sql`
1. Verifique se não ocorreu nenhum erro.

##### Conexão do servidor de dados

* Verifique o código fonte em _servidor_de_dados\src\main\java\com\fatesg\config\DbConfig.java_
* Adicione as suas configurações

## Testes

* Para execução dos testes, utilize o Maven

```
mvn test
```


## Projetos

##### Estrutura

```txt
+---biblioteca: Classes compartilhadas.
+---servidor_de_dados: Aplicação que conecta ao MySQL para obtenção de dados.
+---servidor_de_calculos: Aplicação que realiza calculos com dados de outra aplicação.
+---web_api: API para consumo dos servidores de dados e de cálculo.
\---web_app: App frontend (Em andamento)
```

##### Comandos

```bat
> mvn clean :: Para limpar arquivos compilados (pasta raiz e pasta dos projetos)
> mvn install :: Para instalar arquivos compilados no ambiente (pasta raiz e pasta dos projetos)
> mvn clean install :: Uso conjunto do clean e install (pasta raiz e pasta dos projetos)
> mvn test :: Execução de testes (pasta raiz e pasta dos projetos)
> mvn package :: Empacotamento dos projetos gerando o jar (pasta raiz e pasta dos projetos)
> mvnw spring-boot:run :: Execução do Spring Boot (pasta do projeto web-api)
```


##### Scripts de apoio

```bat
> kill-all.bat                     
> start-all.bat                    
> start-servidor_de_calculos.bat   
> start-servidor_de_dados.bat
> start-web_api.bat                
> start-web_app.bat
```

## Avaliação

1. (2,0) [GERAL] Configuração do ambiente, executar atividades abaixo e realizar commit com a mensagem: "Atividade 1: Ok"
    * [ ] Leitura do deste documento
    * [ ] Fazer Fork
    * [ ] Realizar o Clone
    * [ ] Configurar o Git (caso necessário)
    * [ ] Configurar variáveis de ambiente
    * [ ] Compilação do projeto (mvn clean install)
    * [ ] Execução do projeto
    * [ ] Realizar Commit conforme solicitado (Confira as informações no commit se estão corretas.)

1. (2,0) [WEB_API] O sistema (todos as aplicações) precisa ser iniciado em ordem, por causa das depêndencias entre elas, logo, o projeto web_api precisa do servidor_de_dados e do servidor_de_calculo executando, o servidor_de_calculo depende do servidor_de_dados e o servidor_de_dados depende do MySQL Server. 
No projeto web_api apenas o endpoint de departamentos tem implementado a retentativa para o servidor de dados, para caso o principal falhe ou a conexão seja perdida. 
Implemente a retentativa para a classe de serviço de Funcionários, implementando no pacote web_api.apis uma classe ServidorDeDadosFuncionarioApi que implementa a interface _ServidorDeDadosFuncionarioInterface_. 
Conforme ServidorDeDadosDepartamentoApi.
Faça o commit com a mensagem: "Atividade 2: Implementação do stub para o ServidorDeDadosFuncionarioInterface"
    * [ ] Implementar retentativa no serviço de listagem de funcionários
    * [ ] Implementar retentativa no serviço de obter funcionário por id

1. (2,0) [WEB_API] Está utilizando apenas um servidor de calculos para realizar as requisições.
Implemente uma lógica semelhante a um "LoadBalancer" que distribui as requisições entre os dois servidores de calculos iniciados nas portas 1198 e 1199.
Faça o commit com a mensagem: "Atividade 3: Implementação de um LoadBalancer para o servidor de calculos"
    * [ ] Implementar o acesso ao socket das configurações para o segundo servidor
    * [ ] Implementar algoritmo que envia para servidores diferentes (seja simples!)

1. (2,0) [SERVIDOR_DE_CALCULO] No cálculo da folha de pagamento o método busca todos os salários e depois chama o método calcular recibo de pagamento, que faz uma nova chamada ao servico de dados para obter o salário pelo id do funcionário.
Corrija para que não haja esta segunda chamada evitando latência e sobrecarga do serviço de dados. 
Faça o commit com a mensagem "Atividade 4: Melhoria de performance no servidor de calculos e diminuído processamento do servidor de dados."
    * [ ] Refatorar método calcularFolhaDePagamento para realizar o calculo feito no calcularReciboDePagamento sem buscar o salário novamente.

1. (2,0) [EXPERIÊNCIA] Crie um arquivo **OPNIOES.md** e responda as seguintes questões abaixo e
Faça o commit com a mensagem: "Atividade 5: Respondendo as questões"
    1. Qual tecnologia de comunicação o servidor de cálculo utiliza?
    1. Qual protocolo de mensagem o servidor de cálculo utiliza? 
    1. Qual tecnologia de comunicação o servidor de cálculo utiliza?
    1. Qual estilo arquitetural o projeto web api implementou para expor suas funcionalidades?  
    1. Faça no Paint, no [Diagrams](app.diagrams.net/) ou no [Excalidraw](excalidraw.com) a arquitetura do sistemas, contendo seus componentes (nós/aplicações) e seus relacionamentos. Salve a imagem como PNG ou JPEG com o nome **ARQUITETURA.[png|jpg]**