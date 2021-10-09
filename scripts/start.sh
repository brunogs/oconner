#api_key is required
if [ "$1" = "" ]
then
  echo "Please pass API_KEY for OMDb API"
  exit
fi

#create application image
../mvnw -f ../pom.xml clean package -DskipTests spring-boot:build-image -Dspring-boot.build-image.imageName=oconner

#define environment variable and up images
API_KEY=$1 docker-compose -f ../docker-compose.yml up -d