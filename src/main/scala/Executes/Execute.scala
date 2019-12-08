package Executes



import org.slf4j.LoggerFactory

import sys.process._
import scala.language.postfixOps

trait Execute {
  def execute(execType:String, paramToRun:String):Option[String]
  def execute(execType:String):String
  def executeSsh(username:String, host:String):String
  def executeCd(directory:String):String

}

class ExecuteImpl extends Execute {

  override def execute(execType:String, paramToRun:String):Option[String] = {
    try {
      val result = s"$execType $paramToRun".!!
      Some(result)
    }
    catch{
      case x: Throwable => None
    }
  }

  def executeLS(paramToRun:String):Option[String] = {
    try{
      val result = s"ls $paramToRun".!!
      Some(result)
    }
    catch {
      case x: Throwable =>{
        def logger = LoggerFactory.getLogger(this.getClass)
        logger.error("The directory could not be found OR access was denied to the directory")
        None
      }
    }
  }

  override def executeCd(directory:String):String = {
    val s = Seq("/bin/sh","-c","cd src")
    s.!!
  }

  def execute(execType:String):String =
    execType.!!

  def executeSsh(username:String, host:String):String =
     s"ssh -T $username@$host".!!
}

