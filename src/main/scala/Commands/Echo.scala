package Commands

import Executes.{Execute, ExecuteImpl}

sealed trait Echo

object Echo {

  sealed trait ToEcho extends Echo

  type MandatoryInfo = ToEcho
}

//MyEchoBuilder class
case class MyEchoBuilder[I <: Echo](toEcho:String = ""){
  //Set value to echo
  def setToEcho(toEcho:String):MyEchoBuilder[I with Echo.ToEcho] = {
    this.copy(toEcho = toEcho)
  }

  //begin building and executing command
  def build(implicit ev: I =:= Echo.MandatoryInfo):String =
    (new ExecuteImpl).execute("echo",toEcho).get

}


