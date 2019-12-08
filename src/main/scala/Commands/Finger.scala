package Commands

import Executes.{Execute, ExecuteImpl}

sealed trait GetInfoOnUser

object Finger {

  sealed trait User extends GetInfoOnUser

  type MandatoryInfo = User
}


case class MyFingerBuilder[I <: GetInfoOnUser](user:String = ""){

  //Set user to copy
  def setUser(user:String):MyFingerBuilder[I with Finger.User] =
    this.copy(user = user)

  //This parser was used to extract the data from what cmd returns
  def parser(initialOutput:String):Array[String] = {
    initialOutput.replace("Login: ","")
      .replace("\t\t\t","//")
      .replace("\t","//")
      .replace(" ","")
      .replace("Name:","")
      .replace("\n","//")
      .replace("Directory:","")
      .replace("Shell:","")
      .split("//")
  }
  //Begin build and execute command. It returns type fingeroutput
  def build(implicit ev: I =:= Finger.MandatoryInfo):FingerOutput = {

   val fingerExecute = new ExecuteImpl
   val initialOutput = fingerExecute.execute("finger",user).getOrElse(throw new Throwable("User was not found Exception"))
   val parsedOutput = parser(initialOutput)

    val login:Login = Login(parsedOutput.headOption)
    val name:Name = Name(parsedOutput.tail.headOption)
    val directory:Directory = Directory(parsedOutput.tail.tail.headOption)
    val shell:Shell = Shell(parsedOutput.tail.tail.tail.headOption)
    FingerOutput(login,name,directory,shell)
  }
}


//Types for fingeroutput
case class Login(login:Option[String])

case class Name(name:Option[String])

case class Directory(directory:Option[String])

case class Shell(shell:Option[String])


case class FingerOutput(Login: Login, Name: Name, Directory: Directory,Shell: Shell)
