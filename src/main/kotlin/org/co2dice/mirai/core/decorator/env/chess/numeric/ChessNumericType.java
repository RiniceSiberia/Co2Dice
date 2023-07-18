package org.co2dice.mirai.core.decorator.env.chess.numeric;

import org.co2dice.mirai.core.bean.attribute.prototype.*;
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance;
import org.co2dice.mirai.core.bean.chessman.instance.FieldChessmanInstance;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericValueInstance;

import java.util.function.Function;

/**
* 属性的间接实体，记录了获取属性的方法，此处是返回数字类型的属性
 * @author DUELIST
 */
public final class ChessNumericType {

   public static final ChessNumericType CHAOS = new ChessNumericType(ChessmanInstance::getChaos);

   public static final ChessNumericType ORDER = new ChessNumericType(ChessmanInstance::getOrder);

   public static final ChessNumericType STR
           = new ChessNumericType(e -> e.getAttributeTable().getValue(Strength.INSTANCE));

    public static final ChessNumericType CON
            = new ChessNumericType(e -> e.getAttributeTable().getValue(Constitution.INSTANCE));

    public static final ChessNumericType DEX
           = new ChessNumericType(e -> e.getAttributeTable().getValue(Dexterity.INSTANCE));

    public static final ChessNumericType WIS
           = new ChessNumericType(e -> e.getAttributeTable().getValue(Wisdom.INSTANCE));

   public static final ChessNumericType INT
           = new ChessNumericType(e -> e.getAttributeTable().getValue(Intelligence.INSTANCE));

   public static final ChessNumericType SAN
           = new ChessNumericType(e -> e.getAttributeTable().getValue(Sanity.INSTANCE));


   private final Function<ChessmanInstance, Integer> getter;

   private ChessNumericType(Function<ChessmanInstance, Integer> getter) {
       this.getter = getter;
   }

   public GetChessNumericValueInstance getValue(ChessmanInstance target) {
       return new GetChessNumericValueInstance(getter.apply(target));
   }
}
