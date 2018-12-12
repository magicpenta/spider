ps -ef|grep com.panda.main.ProxyRunner|grep -v grep|cut -c 9-15|xargs kill -9
