# Criar utilizando *ReactJS* no frontend

# Projeto contem as configs necessarias para utilizar a base de dados disponbilizada no projeto MyMusic

# Para iniciar o desenvolvimento, favor realizar um fork da branch "master" com a seguinte estrutura de nome:

# desafio-mymusic-<nome_usuario_github> aonde <nome_usuario_github> e o usuario do candidato logado no github

# Os commits devem ser feitos neste fork para que possam ser avaliados

# Comentários

# Music Service
# http://localhost:8000/

#Endpoints
GET - http://localhost:8000/api/musicas/?filter=test

#Swagger
http://localhost:8000/swagger-ui.html#/

# Playlist Service
# http://localhost:8080/

#Endpoints
GET - http://localhost:8080/api/playlists/?user=default
PUT - http://localhost:8080/api/playlists/92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed/musicas
DELETE - http://localhost:8080/api/playlists/92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed/musicas/5fde0cb8-e715-4b10-a26f-427ae3f6c354

#Swagger
http://localhost:8080/swagger-ui.html#/

# Aplicação frontend
# Executar os comandos:
 - npm install
 - npm start

# http://localhost:3004/ 