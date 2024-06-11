# Checklist API

SpringBoot API criada para prover recursos para a [SPA de Checklist](https://github.com/calazarin/checklist-spa).

## Rodando a aplicação local

Utilizando uma janela do Prompt Comand (ou terminal de um sistema Unix):

1. Primeiramente defina o profile do Maven a ser ativado:
   1. Construa a aplicação com o seguinte comando: `mvn clean install -Plocal -DprofileIdEnabled=true`
2. Para rodar a aplicação digite o seguinte comando: `mvn spring-boot:run -Dspring-boot.run.profiles=local`

## Rodando a aplicação na AWS

Para rodar a API na AWS é necessário contruí-la utilizando o profile do Maven como 'aws' Utilizando uma janela do Prompt Comand (ou terminal de um sistema Unix):

1. Construa a aplicação com o seguinte comando: `mvn clean install -Paws -DprofileIdEnabled=true`
2. Faça o deploy da aplicação e inclua nas variáveis de ambiente o profile do Spring como 'aws'.

## Versão Atualizada

Caso queria uma versão atualizada dessa API utilizando Java 17, SpringBoot 3 e Gradle, acesse
https://github.com/calazarin/checklist-api-2