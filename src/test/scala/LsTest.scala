import Commands.{Directory, MyLsBuilder}
import org.scalatest.FunSpec

class LsTest extends FunSpec{
  describe("Test LS Command")
  {
    it("Ensure set diretory properly changes the directory"){
      assert((new MyLsBuilder).setDirectory(Directory(Option("TestDirectory"))).directory == Directory(Some("TestDirectory")))
    }
  }

}
