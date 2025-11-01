Integrantes:
Enzo dos Santos Beserra RM 552340
Thiago Shiromoto Sardinha RM 98483
Vitor Hugo Ferreira de Andrade RM 99635

Instruções para rodar backend e frontend em conjunto:
rode primeiro o Backend, basta clicar no arquivo BackendApplication e depois clicar no símbolo de play que fica no superior direito e depois rode o Frontend usando o npx expo start, assim eles funcionaram devidamente, no frontend use o web para abrir o aplicativo.

## Como rodar
Banco Docker compose MySQL:

É preciso que comece a iniciar o projeto pelo banco docker , primeiro já tenha instalado o docker desktop Utilize "docker compose up -d" no Terminal do Backend para subir o banco

Backend: mvn spring-boot:run

Frontend: npm install 
npx react-native run-android`
também pode rodar o "npx expo start" caso prefira

API
- `GET http://localhost:8080/api/readings`
- `POST http://localhost:8080/api/readings`
