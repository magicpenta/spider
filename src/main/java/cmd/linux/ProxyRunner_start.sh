if ! ps aux|grep main.ProxyRunner|grep -vq grep ;
then
echo 'starting...'
cd ../..
nohup java -cp .:./lib/* main.ProxyRunner 2>././log/log.txt &
echo 'started!'
else echo 'ProxyRunner is runnings';
fi
