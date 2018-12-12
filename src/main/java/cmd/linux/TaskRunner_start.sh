if ! ps aux|grep com.panda.main.MultipleTaskRunner|grep -vq grep ;
then
echo 'starting...'
cd ../..
nohup java -cp .:./lib/* com.panda.main.MultipleTaskRunner 2>././log/log.txt &
echo 'started!'
else echo 'TaskRunner is runnings';
fi

