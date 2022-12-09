import org.co2dice.mirai.bean.dice.Dice;
import org.co2dice.mirai.bean.dice.DiceList;
import org.co2dice.mirai.bean.dice.UsuallyDices;
import org.co2dice.mirai.utils.DiceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.platform.commons.JUnitException;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.List;

public class DiceTest {
    private static final Logger logger = LoggerFactory.getLogger(DiceTest.class);
    @Test
    public void testDice() {
        List<Double> median = DiceUtils.INSTANCE.getEXPECTED_VALUE();
        for (Double d : median){
            DiceList diceList = DiceUtils.INSTANCE.getExpectDice(d);
            System.out.print("cost = "+median.indexOf(d)+" median = " + d + " diceList = " + diceList);
            System.out.println("");
        }
    }

}
