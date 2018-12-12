if ! ps aux|grep com.panda.main.ProxyRunner|grep -vq grep ;
then
echo 'starting...'
cd ../..
nohup java -cp .:./lib/* com.panda.main.ProxyRunner 2>././log/log.txt &
echo 'started!'
else echo 'ProxyRunner is runnings';
fi
