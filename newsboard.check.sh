command -v docker-compose >/dev/null 2>&1 || { echo >&2 "Docker compose is require but it's not installed. Aborting."; exit 1; }

echo 
echo '-----------------------------------------------------'
echo 'Initializing build environment...'
docker-compose -f "docker-compose.yml" up -d newsboard.cli
echo '-----------------------------------------------------'

echo
echo '-----------------------------------------------------'
echo 'Building Newsboard and archives...'
docker exec newsboard.cli ./gradlew -g ../.gradle clean check
echo '-----------------------------------------------------'
