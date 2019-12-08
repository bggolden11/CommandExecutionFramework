package Commands

import Commands.GetAllUsers.MandatoryInfo
import Executes.ExecuteImpl

trait GetDirectory

object PWD{
  type MandatoryInfo = Null
}
//Build the pwd command
class MyPWDBuilder[I <: GetInfo] {
  def build(implicit ev: I =:= MandatoryInfo):List[String] = {
    List((new ExecuteImpl).execute("pwd"))
  }
}
