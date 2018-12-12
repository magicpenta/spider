ps -ef|grep com.panda.main.TaskCrawler|grep -v grep|cut -c 9-15|xargs kill -9
