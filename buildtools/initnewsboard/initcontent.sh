pushd Examples/RSSCrawler > /dev/null
npm install -s > /dev/null
node RSSCrawler.js > /dev/null
echo "RSSCrawler: News published"
popd > /dev/null

pushd Examples/RandomAnalyzer > /dev/null
npm install -s > /dev/null
node RandomAnalyzer.js > /dev/null
echo "RandomAnalyzer: Analyzer results published"
popd > /dev/null

pushd Examples/TwitterCrawler > /dev/null
pip3 --quiet install requests pyquery > /dev/null
python3 Main.py > /dev/null
echo "TwitterCrawler: News published"
popd > /dev/null