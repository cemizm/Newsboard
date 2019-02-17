command -v docker-compose >/dev/null 2>&1 || { echo >&2 "Docker compose is require but it's not installed. Aborting."; exit 1; }

echo 
echo '-----------------------------------------------------'
echo 'Initializing build environment...'
docker-compose -f "docker-compose.yml" up -d newsboard.cli
echo '-----------------------------------------------------'

echo
echo '-----------------------------------------------------'
echo 'Building Newsboard and archives...'
docker exec newsboard.cli ./gradlew -g ../.gradle assemble
echo '-----------------------------------------------------'

echo
echo '-----------------------------------------------------'
echo 'Starting postgres and payara application server'
#payara server modifies the initialization config on each start so we need a copy of that file
cp -f ./buildtools/initpayara/config/template.asadmin ./buildtools/initpayara/config/init.asadmin
docker-compose -f "docker-compose.yml" up -d newsboard.payara
printf "Deploying Newsboard "
while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' localhost:8080/NewsBoard/WebService/frontend/news)" != "200" ]]; do printf "."; sleep 5; done
echo " done"
echo '-----------------------------------------------------'


echo
echo '-----------------------------------------------------'
echo 'Initializing Newsboard content'
docker exec newsboard.cli bash ./buildtools/initnewsboard/inittokens.sh
docker exec newsboard.cli bash ./buildtools/initnewsboard/initcontent.sh
echo '-----------------------------------------------------'

echo
echo
echo '-----------------------------------------------------'
docker exec newsboard.cli bash ./buildtools/initnewsboard/print.sh
echo '-----------------------------------------------------'