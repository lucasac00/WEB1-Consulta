# Trabalho 1 - Agendamento de consultas médicas 

Esse é um projeto feito em servlets para um sistema de agendamento de consultas médicas, para a disciplina Desenvolvimento de Software para Web 

## JDK 

Instalar o Java Development Kit. Para este projeto, utilizamos a versão 19.0.2 

O JDK pode ser instalado pelas seguintes opções: 

https://www.oracle.com/technetwork/java/javase/downloads/index.html

https://openjdk.java.net/

## Apache Maven

Instalar o Maven pelo apt. Para este projeto, utilizamos a versão 3.6.3

```
sudo apt install maven
```

Verificar a versão do maven 

```
mvn -version
```

## Apache Tomcat 

Baixar o arquivo zip de http://tomcat.apache.org/. Neste projeto, utilizamos o tomcat 9.0.91

Descompactar em uma pasta sem espaços ou acentos (diretório <instalação tomcat>)


Abrir o arquivo conf/tomcat-users.xml (configuração dos usuários)

Modificar conf/tomcat-users.xml, adicionando a seguinte linha antes de ``` </tomcat-users> ``` 

<user username="admin" password="admin" roles="manager-gui, manager-script" />

Esse será o seu login e senha para acessar o tomcat

Executar startup.sh (ou catalina.sh run) no diretório <instalação tomcat>\bin [pode ser necessário dar permissão – executar comando chmod 755]

```
chmod 755 catalina.sh
```

```
./catalina.sh run 
```

Abrir em um navegador a URL: http://localhost:8080

## MySQL 

Neste projeto, utilizamos o MySQL 8.0.39

Para baixar o MySQL, siga os seguintes passos:


```
sudo apt update 
```

Instale o pacote mysql-server
```
sudo apt install mysql-server
```

começe o serviço do mysql 
```
sudo systemctl start mysql.service
```

### configuração do MySQL 

Abra o MySQL CLI 
```
sudo mysql
```

Em seguida, execute o seguinte comando ALTER USER para alterar o método de autenticação do usuário root para um que use uma senha. Substitua password com sua senha 

```
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
```

```
exit
```

Para acessar o MySQL, você deve utilizar: 
```
mysql -uroot -p 
```
e utilizar a senha cadastrada

## Clone o repositório 

Clone o repositório utilizando :
```
git clone https://github.com/lucasac00/WEB1-Consulta.git
```

## Build

Para dar build no projeto, os seguintes passos devem ser utilizados: 

Certifique se que o Tomcat esteja ativo,

No diretório raiz, utilize 
```

```

