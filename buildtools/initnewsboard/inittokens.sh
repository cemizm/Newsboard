curl -H "Content-Type: application/json" \
     -H "Authorization:Basic $AUTH_TOKEN" \
     -d '{"name":"Test Crawler 1","token":"'"$TOKEN_CRAWLER"'"}' \
     -s \
     http://newsboard.payara:8080/NewsBoard/WebService/backend/crawler >> /dev/null

curl -H "Content-Type: application/json" \
     -H "Authorization:Basic $AUTH_TOKEN" \
     -d '{"name":"Test Analyzer 1","token":"'"$TOKEN_ANALYZER"'"}' \
     -s \
     http://newsboard.payara:8080/NewsBoard/WebService/backend/analyzer >> /dev/null

echo "Crawler and Analyzer tokens created"