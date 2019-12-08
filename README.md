[![license](https://img.shields.io/github/license/mashape/apistatus.svg) ](LICENSE)
[![Actions Status](https://github.com/bggolden11/CommandExecutionFramework/workflows/Scala CI/badge.svg)](https://github.com/bggolden11/CommandExecutionFramework/actions)



## Command Execution Framework
### Description: Using the builder design pattern, I was able to construct an elegant shell command framework in scala
#### You can obtain this Git Repo using the command `git clone https://github.com/bggolden11/CommandExecutionFramework.git`.

## How to Install and Run the Program
##### This program was built and tested on Ubuntu 19.04
1. Run the following command to pull the repo ```git clone git@bitbucket.org:bggolden11/brian_goldenberg_hw3.git``` 
2. Then move into the programs directory: ```cd brian_goldenberg_hw3```
3. Obtain the following jar files: ```sbt: ch.qos.logback:logback-core:1.2.3:jar```, ```  "org.scalactic" %% "scalactic" % "3.0.8"```, and  ```sbt: org.scalatest:scalatest_2.13:3.0.8:jar```
    - You may need to download the jar files separably and add them through intellij if it does not initially work. See: [Correct way to add external jars (lib/*.jar) to an IntelliJ IDEA project](https://stackoverflow.com/questions/1051640/correct-way-to-add-external-jars-lib-jar-to-an-intellij-idea-project)
4. Compile and run the program ```sbt run```


## Overview and Functionality
The project allows users to use the following commands in scala: ```ls```, ```echo```, ```finger```, ```whoami```, ```ssh```.
Each of these commands uses the builder design pattern. They build on top of objects and take advantage of scala's
```case class```. To provide the final implementation of the command. From each respected class the ```execute``` class is called where it 
uses the ```sys.process``` library to execute shell commands. **Please Keep in mind that this program needs to be executed on a Unix based shell**
as some commands will not work on a windows shell, For example: ```ls```.

## Overall Design Choices
The program is divided within 3 packages. The first layer being the ```Execute``` which is called when the commands need to be executed on the shell.
Then the ```Commands``` package which stores all the commands. Final there is main which stores one implementation of the commands.  
I then used the design pattern within my commands as can be seen.

### Custom Objects 
```scala
Login(login:Option[String])
Name(name:Option[String])
Directory(directory:Option[String])
Shell(shell:Option[String])
FingerOutput(Login: Login, Name: Name, Directory: Directory,Shell: Shell)
```
### ls Command
The ls command will accepts a type of ```Directory``` that takes in the directory name you will like the list of files from. The return type of the build command is an ```Option```. 
If the directory is not found OR the user does not have proper access it will return type ```None```, Else it return type ```Some``` with a string containing all the 
files in the directory. 
#### Example:
##### Use:
```scala
(new MyLsBuilder).setDirectory(Directory(Some("/var"))).build
```
##### Example return:
```scala
   Some(backups
   cache
   crash
   lib
   local
   lock
   log
   mail
   metrics
   opt
   run
   snap
   spool
   tmp
   )
```

### Finger Command
The ```MyFingerBuilder``` accepts input ```user:String``` which gets information for that specific user. It will return type ```FingerOutput``` (refer to Custom Objects section).
#### Example:
##### Use:
```scala
(new MyFingerBuilder).setUser("Username").build
```
#### Example return:
```
FingerOutput(Login(Some(UserName)),Name(Some(name)),Directory(Some(/home/Username)),Shell(Some(/usr/bin/zsh)))
```

### RetrieveAllUsers Command
The ```MyGetAllUsersBuilder``` does not take any input. The functions runs the following command on the shell: ```"cut -d : -f 1 /etc/passwd```. The output is List of Strings containing all the names of the users on the computer. 
#### Example:
##### Use:
```scala
(new MyGetAllUsersBuilder).build
```
#### Example return:
```scala
List(root, daemon, bin, sys, sync, games, man, lp, mail, news, uucp, proxy, www-data, backup, list, irc, gnats, nobody, systemd-timesync, systemd-network, systemd-resolve, messagebus, syslog, _apt, uuidd, avahi-autoipd, usbmux, rtkit, dnsmasq, cups-pk-helper, speech-dispatcher, kernoops, avahi, saned, nm-openvpn, whoopsie, colord, hplip, geoclue, pulse, gnome-initial-setup, gdm, username, systemd-coredump, sbt)
```

### PWD Command
The ```MyPWDBuilder``` does not accept any input; however it returns a list of strings of the current directory the user is in.
#### Example:
##### Use:
```scala
(new MyPWDBuilder).build
```
#### Example return:
```scala
List(/home/bggolden11/github/brian_goldenberg_hw3)
```



### Echo Command
The ```MyEchoBuilder``` accepts a ```String``` input to echo onto the console. If the command was successful it will return a ```String``` containing your input string. This string was also outputted to the console. 

#### Example: 
##### Use:
```scala
(new MyEchoBuilder).setToEcho("Hello").build
```
#### Example return:
```
Hello
```

### Logger Use:
The logger was very beneficial during the Execute stage. Where I used the logger to tell the user whether or not the ```ls``` call worked. 
The ```ls```, may have failed because the directory may not have been found or the user may not have proper access to this file. If either one of
these were the case the logger will log the following: 
```scala
The directory could not be found OR access was denied to the directory
``` 

### Config:
For this project I did not hard code any values. Most inputs need to come directly from the user of the functions;
therefore, I did  not use any config files. 

### Testing: 
For the testing portion I planned to use mockito to test my framework; unfortunatly, I was having trouble getting it to propery 
work and I couldn't use it. However, I was able to test my framework by making sure things like parser and setter methods were working
correctly.

 









