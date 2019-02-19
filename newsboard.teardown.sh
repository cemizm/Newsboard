# Shut down the Docker containers 
docker-compose -f docker-compose.yml kill && docker-compose -f docker-compose.yml down

# remove local artifacts
rm -rf ./.gradle
find . -name build -type d -prune -exec rm -rf {} \;
find . -name node_modules -type d -prune -exec rm -rf {} \;
find . -name package-lock.json -type f -prune -exec rm -rf {} \;
find . -name __pycache__ -type d -prune -exec rm -rf {} \;
rm -f ./buildtools/initpayara/config/init.asadmin
rm -rf ./Web/web/bower_components

# remove newsboard docker images
docker rmi 2016_10_modulares_webnewsboard_newsboard.cli