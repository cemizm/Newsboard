pushd Examples/RSSCrawler > /dev/null
npm install -s 
node RSSCrawler.js
popd > /dev/null

pushd Examples/RandomAnalyzer > /dev/null
npm install -s
node RandomAnalyzer.js
popd > /dev/null

pushd Examples/TwitterCrawler > /dev/null
pip3 --quiet install requests pyquery
python3 Main.py
popd > /dev/null