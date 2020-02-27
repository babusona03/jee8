# Build
mvn clean package && docker build -t com.javaee.pilot/jee8 .

# RUN

docker rm -f jee8 || true && docker run -d -p 8080:8080 -p 4848:4848 --name jee8 com.javaee.pilot/jee8 