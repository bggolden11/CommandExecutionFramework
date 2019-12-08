package Commands

import Executes.ExecuteImpl

sealed trait CheckDirectory

object Ls {
  sealed trait DirectoryToCheck extends CheckDirectory

  type MandatoryInfo = DirectoryToCheck

}

case class MyLsBuilder[I <: CheckDirectory](directory: Directory = Directory(None)) {
  //Set directory to check with ls
  def setDirectory(directory: Directory):MyLsBuilder[I with Ls.DirectoryToCheck] = {
    this.copy(directory = directory)
  }

//build and execute command
  def build(implicit ev:I =:= Ls.MandatoryInfo):Option[String] = {
    (new ExecuteImpl).executeLS(directory.directory.get)
  }
}
