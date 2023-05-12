package org.co2dice.mirai.core.decorator.env.chess.numeric;

import org.co2dice.mirai.core.bean.api.CAO;
import org.co2dice.mirai.core.bean.attribute.prototype.EliteAttribute;
import org.co2dice.mirai.core.bean.attribute.prototype.MobAttribute;
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericValueInstance;

import java.util.function.Function;

/**
* 属性的间接实体，记录了获取属性的方法，此处是返回数字类型的属性
*/
public final class ChessNumericType {

   public static final ChessNumericType CHAOS = new ChessNumericType(e -> {
       if (e instanceof CAO cao) {
           return cao.getChaos();
       } else {
           return null;
       }
   });

   public static final ChessNumericType ORDER = new ChessNumericType(e -> {
       if (e instanceof CAO cao) {
           return cao.getOrder();
       } else {
           return null;
       }
   });

   public static final ChessNumericType STR
           = new ChessNumericType(e -> e.getAttributeTable().getValue(EliteAttribute.STR));

    public static final ChessNumericType CON
            = new ChessNumericType(e -> e.getAttributeTable().getValue(EliteAttribute.CON));

    public static final ChessNumericType DEX
           = new ChessNumericType(e -> e.getAttributeTable().getValue(EliteAttribute.DEX));

    public static final ChessNumericType WIS
           = new ChessNumericType(e -> e.getAttributeTable().getValue(EliteAttribute.WIS));

   public static final ChessNumericType INT
           = new ChessNumericType(e -> e.getAttributeTable().getValue(EliteAttribute.INT));

   public static final ChessNumericType SAN
           = new ChessNumericType(e -> e.getAttributeTable().getValue(EliteAttribute.SAN));

   public static final ChessNumericType LOYALTY
           = new ChessNumericType(e -> e.getAttributeTable().getValue(MobAttribute.LOYALTY));


   private final Function<ChessmanInstance, Integer> getter;

   private ChessNumericType(Function<ChessmanInstance, Integer> getter) {
       this.getter = getter;
   }

   public GetChessNumericValueInstance getValue(ChessmanInstance target) {
       return new GetChessNumericValueInstance(getter.apply(target));
   }
}
