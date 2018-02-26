if ! ps aux|grep main.TaskCrawler|grep -vq grep ;
then
echo 'starting...'
cd ../..
nohup java -cp .:./lib/* main.TaskCrawler 2>././log/log.txt &
echo 'started!'
else echo 'TaskCrawler is runnings';
fi
