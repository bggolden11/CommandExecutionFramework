import Commands.MyEchoBuilder
import org.scalatest.FunSpec

class EchoTest extends FunSpec{
  describe("EchoCommand"){
    it("The echo output should change"){
      assert((new MyEchoBuilder).setToEcho("TestEcho").toEcho == "TestEcho")
    }
  }
}
