package Commands

import Commands.GetAllUsers.MandatoryInfo
import Executes.ExecuteImpl

trait GetInfo
//sealed trait
object GetAllUsers{

  type MandatoryInfo = Null
}

//Command to retrieve all users
class MyGetAllUsersBuilder[I <: GetInfo] {
  def parser(inputString:String): List[String] ={
    inputString.split("\n").toList
  }
  def build(implicit ev: I =:= MandatoryInfo):List[String] = {
    val initalOutput = (new ExecuteImpl).execute("cut -d : -f 1 /etc/passwd")
    parser(initalOutput)
  }
}


