package org.co2dice.mirai.bean.game.decorator.api;

/**
 * interface for decorator action requests.
 * This symbolizes the invocation process of function call.
 * The record should hold all parameter for the hypothetical function call.
 * <br>
 * Since records cannot be modified, modification of the context should return a
 * new context. This is intended.
 */
//作用为装饰器的传参
    //如果你把decorator当做一个function call，context就是parameter，value instance就是return value
        //记录装饰器的逻辑，如要装饰一个成就（钻石护身），那么value instance就是成就的逻辑（当xx部位穿戴xx物品时触发），返回xxx
public interface DecoratorContext<C extends Record & DecoratorContext<C>> {
}
