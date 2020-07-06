# CadastroColaborAPI #

Este projeto tem como objetivo otimizar o gerenciamento de colaboradores de uma empresa.

## Começando ##

Para executar o projeto será necessário instalar os seguintes programas:

* Instalar o [Git](https://git-scm.com/downloads)
* Instalar o [JDK8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* Instalar o [Maven](https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/)
* Instalar o [MySql](https://www.mysql.com/downloads/)
* Instalar o [Eclipse](https://www.eclipse.org/downloads/packages/release/mars/r/eclipse-ide-java-ee-developers)
* Instalar o [PostMan](https://www.postman.com/downloads/)


## Desenvolvimento ##

Após instalar os programas acima, abra o terminal, vá a um diretório de sua preferência e execute os códigos abaixo:
```shell
git clone https://devjony@bitbucket.org/devjony/javabackendjuniorjonatamicael.git
```

## Construçao (Build) ##

Para que o maven baixe todas as depnências necessárias para construir o projeto, execute o comando abaixo:
```shell
mvn clean install
```

## Configuração ##

### Banco de Dados MySql ###

![image](https://user-images.githubusercontent.com/51264643/86550245-4e2e5100-bf18-11ea-88f5-cfa316df03ba.png)

1. Crie previamente um banco de dados no MySql com o nome de sua preferência.
2. Navegue até o diretório ```src/main/resources/``` e abra o arquivo ```application.properties```
3. Substitua os trechos:
>```localhost/colaboradordb``` por ```seu ip/nome do seu banco``` |
>```root``` por ```usuario do banco``` |
>```password``` por ```senha do banco```

### Banco de Dados em memória H2 (Alternativa)###

![image](https://user-images.githubusercontent.com/51264643/86550542-08be5380-bf19-11ea-8e1c-62501b212798.png)

1. Comente todo o codigo relacionada ao MySql.
2. Descomente todo o codigo relacionado ao banco H2.
3. Substitua os trechos:(Opcional)
>```testdb``` por ```nome do banco desejado``` |
>```username=sa``` por ```username="username desejado"``` |
>```password=sa``` por ```password="password desejado"```
4. Estes dados serao utilizados para acessar o console do banco em: ```localhost:8080/h2-console```

![image](https://user-images.githubusercontent.com/51264643/86551102-89ca1a80-bf1a-11ea-8c2b-608d7d99af76.png)

## Testes ##

No diretório raíz do projeto tem uma colection de testes que pode ser importada para o postman com alguns testes já preparados
> #### javaBackendJuniorJonataMicael.postman_collection.json ####

## Documentaçao ##

Caso seu projeto esteja rodando na porta e ip padrão, acesse:
> #### localhost:8080/swagger-ui.html ####

## Backlog ##

1. CriteriaAPI: groupBy e paginação
2. Validar: colaborador < 18 anos, max. 20% / setor
3. Tratar: response de exceptions não lançadas por mim
4. Corrigir: docker não enxerga banco de dados local
5. Implementar: front-end gerenciado por npm (react)