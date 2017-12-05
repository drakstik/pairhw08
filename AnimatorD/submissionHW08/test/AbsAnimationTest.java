import org.junit.Test;

import cs3500.animator.model.Move;

import static junit.framework.TestCase.assertEquals;

public class AbsAnimationTest {
  //Things to test:
  //     -getBgn()
  //     -getEnd()
  //     -getName()

  @Test
  public void getBgnEndNameTest() {
    Move anim = new Move("R", 2, 5);
    assertEquals(2, anim.getBgn());
    assertEquals(5, anim.getEnd());
    assertEquals("R", anim.getName());
  }
}
