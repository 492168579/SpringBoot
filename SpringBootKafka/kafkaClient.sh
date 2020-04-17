#!/bin/bash

context="-Dspring.profiles.active=prod "
appName="kafkaClient.jar"

if [ "$1" = "" ];
then
    echo -e "\033[0;31m 未输入操作名 \033[0m  \033[0;34m {start|stop|restart|status} \033[0m"
    exit 1
fi

if [ "$appName" = "" ];
then
    echo -e "\033[0;31m 未输入应用名 \033[0m"
    exit 1
fi

function start()
{
	count=`ps -ef |grep java|grep $appName|grep -v grep|wc -l`
	if [ $count != 0 ];then
		echo "$appName is running..."
	else
		echo java -jar $context$appName&
		java -jar $context$appName&
		echo "Start $appName success..."
	fi
}

function stop()
{
	echo "Stop $appName"
	boot_id=`ps -ef |grep java|grep $appName|grep -v grep|awk '{print $2}'`
	count=`ps -ef |grep java|grep $appName|grep -v grep|wc -l`

	if [ $count != 0 ];then
	    kill $boot_id
    	count=`ps -ef |grep java|grep $appName|grep -v grep|wc -l`

		boot_id=`ps -ef |grep java|grep $appName|grep -v grep|awk '{print $2}'`
		kill -9 $boot_id
	fi
}

function restart()
{
	stop
	sleep 2
	start
}

function status()
{
    count=`ps -ef |grep java|grep $appName|grep -v grep|wc -l`
    if [ $count != 0 ];then
        echo "$appName is running..."
    else
        echo "$appName is not running..."
    fi
}

case $1 in
	start)
	start;;
	stop)
	stop;;
	restart)
	restart;;
	status)
	status;;
	*)

	echo -e "\033[0;31m Usage: \033[0m  \033[0;34m sh  $0  {start|stop|restart|status}  {appNameJarName} \033[0m
\033[0;31m Example: \033[0m
	  \033[0;33m sh  $0  start esmart-test.jar \033[0m"
esac
