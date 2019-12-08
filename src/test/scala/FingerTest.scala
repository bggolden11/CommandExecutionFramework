import Commands.MyFingerBuilder
import org.scalatest.FunSpec
class FingerTest extends FunSpec {
  describe("FingerCommand")
  {
    it("Should correctly change the user name of the finger")
      {
        assert((new MyFingerBuilder).setUser("testUser").user == "testUser")
      }

    it("The parser should correctly parse the data"){
      val testString = (new MyFingerBuilder).setUser("testUser").parser("Login: briangoldenberg\t\t\tName: Brian Goldenberg\nDirectory: /Users/briangoldenberg   \tShell: /bin/zsh\nOn since Fri Nov 29 00:51 (CST) on console, idle 11:03 (messages off)\nOn since Fri Nov 29 00:57 (CST) on ttys000\nNo Mail.\nNo Plan.")
      assert(testString sameElements Array("briangoldenberg", "BrianGoldenberg", "/Users/briangoldenberg", "/bin/zsh", "OnsinceFriNov2900:51(CST)onconsole,idle11:03(messagesoff)", "OnsinceFriNov2900:57(CST)onttys000", "NoMail.", "NoPlan."))
    }
  }
}
