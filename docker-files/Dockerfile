FROM mariadb
ENV MYSQL_ROOT_PASSWORD test1357
ENV MYSQL_DATABASE mydb
COPY ./mydb.sql /docker-entrypoint-initdb.d/
EXPOSE 3306