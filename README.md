# image.downloader

1. Clone project from git
2. Run command in terminal (Maven & Docker should be installed in your system):
  "sh runner.sh"
3. Send POST request in terminal for example:
"curl --request POST \
  --url http://localhost:8080/api/v1/ \
  --header 'content-type: application/json' \
  --data '{"urls":["IMAGE_URL", "IMAGE_URL", ....]}'"
4. Then you will get a response with objects containing the file name and an arrays of bytes of pictures 100x100
5. Application also saves pictures in the docker container. You can view them like this:
  - run command in terminal: 
      "sudo docker exec -it <container id> sh & ls"
6. If you want to stop the application safely, just send the request:
   - run command in terminal:
   "curl -X POST localhost:8080/actuator/shutdown"
