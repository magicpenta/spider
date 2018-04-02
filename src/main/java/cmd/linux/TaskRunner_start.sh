if ! ps aux|grep main.MultipleTaskRunner|grep -vq grep ;
then
echo 'starting...'
cd ../..
nohup java -cp .:./lib/* main.MultipleTaskRunner 2>././log/log.txt &
echo 'started!'
else echo 'TaskRunner is runnings';
fi

