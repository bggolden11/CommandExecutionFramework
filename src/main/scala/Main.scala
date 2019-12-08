import Commands.{Directory, FingerOutput, MyEchoBuilder, MyFingerBuilder, MyGetAllUsersBuilder, MyLsBuilder, MyPWDBuilder}
import Exceptions.DirectoryNotFoundORPermissionDeniedException
import Executes.ExecuteImpl


//Main driver functions
//Works as an exmaple
object Main extends App {

  println("Welcome to my framework")
  println("This is an example of how it works!")

    (new MyGetAllUsersBuilder).build.map(user =>
      (new MyFingerBuilder).setUser(user).build).map(fingerOutput => (
      (new MyLsBuilder).setDirectory(fingerOutput.Directory).build)).map{
      case Some(i) => println(i)
      case None => (new MyEchoBuilder).setToEcho("Not Found").build
    }

  (new MyPWDBuilder).build.map(x => (new MyEchoBuilder).setToEcho(x).build)
}
