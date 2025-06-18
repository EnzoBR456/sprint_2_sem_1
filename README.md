Integrantes:
Enzo dos Santos Beserra RM 552340
Thiago Shiromoto Sardinha RM 98483
Vitor Hugo Ferreira de Andrade RM 99635

comando para compilar: mvn spring-boot:run
localização do h2: ./data/readings.mv.db

endpoints disponíveis:
POST	/api/readings	Cadastra nova leitura
GET	/api/readings	Lista todas as leituras
GET	/api/readings/sensor/{sensorId}	Lista leituras de um sensor

testes curl:
curl -X POST http://localhost:8080/api/readings -H "Content-Type: application/json" -d "{\"sensorId\":\"temperatura\",\"nome\":\"Sensor de angulo\",\"status\":\"OK\",\"readingValue\":32,\"timestamp\":\"2025-06-17T09:00:00\"}"

curl http://localhost:8080/api/readings


