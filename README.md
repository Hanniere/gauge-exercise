# gauge-exercise

Foi criado o módulo "gauge-service" para ser o webservice e servir os arquivos json.

A aplicação foi feita utilizando o Wildfly-10.0.0.Final

Para executar a aplicação, é necessário o server estar no ar e executar o seguinte comando: "mvn clean package wildfly:deploy"

A aplicação estará disponivel em: http://localhost:8080/gauge-web/

O serviço estará disponível em : http://localhost:8080/gauge-service/api/brands   ou users ou interactions no final da uri.

