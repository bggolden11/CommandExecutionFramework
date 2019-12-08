import Commands.MyGetAllUsersBuilder
import org.scalatest.FunSpec

class RetrieveAllUsersTest extends FunSpec{
  describe("Test RetriveAllUser"){
    it("Parser correctly parses the output"){
      assert((new MyGetAllUsersBuilder).parser("test1\n test2\n test3\n")== List("test1", " test2", " test3"))
    }
  }
}
