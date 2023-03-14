package dev.lcy0x1.predicate.syntax.operation

import dev.lcy0x1.predicate.api.instance.IFunction
import dev.lcy0x1.predicate.datagen.encoder.IValueToken
import dev.lcy0x1.predicate.instance.IValueInstance
import dev.lcy0x1.predicate.instance.IValueInstanceList
import dev.lcy0x1.predicate.instance.PredicateContext
import dev.lcy0x1.predicate.instance.ValueInstanceConstant
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationFactory
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstance
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstanceSingle
import dev.lcy0x1.predicate.syntax.param.type.IParam
import dev.lcy0x1.predicate.syntax.param.type.ParameterSet
import dev.lcy0x1.predicate.syntax.type.NamedEnum
import dev.lcy0x1.predicate.syntax.type.OperandType
import dev.lcy0x1.predicate.syntax.type.OperandTypes
import dev.lcy0x1.util.ListHelper
import dev.lcy0x1.util.type.Either
import java.util.function.BiFunction

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-11:02
 * @Message: Have a good time!  :)
 **/
class Operator<T : IOperationInstance>(
    override val name: String,
    private val factory: IOperationFactory<T>,
) : NamedEnum {
    init {
        REGISTRY[name] = this
        //构造时会注册
    }

    fun getOperator(set: ParameterSet?): T {
        return factory.parse(set)
    }

    fun toFunctionType(): Either<OperandType<IFunction<*, *>?>?, OperandType<BiFunction<*, *, *>?>?>? {
        return factory.toFunctionType()
    }

    fun getParams(): List<IParam?>? {
        return factory.params
    }






    companion object{

        private val REGISTRY: MutableMap<String, Operator<*>> = mutableMapOf()
        //注册表

        fun getType(type: String): Operator<*>? {
            if (!REGISTRY.containsKey(type)) {
                throw RuntimeException("type $type not found")
            }
            return REGISTRY[type]
        }
        //获取注册表中的操作符

        val NOT = Operator("NOT",
            IOperationFactory.simpleS(OperandTypes.BOOL,
                IParam.getSingleton(OperandTypes.BOOL, "val"))
            //操作符的返回类型为bool,传入参数为bool,名称为val
            { ctx: PredicateContext?, e: IValueInstance<Boolean?> -> OperandTypes.BOOL.parse(ctx!!)
            { c: PredicateContext? -> !e.getVal(c)!! }
            //处理函数为:获取IValueInstance的值,并取反
            })

        //以and举例:他的factory是一个函数，这个函数的参数是一个ParameterSet，返回值是一个IOperationInstanceSingle
        val OR = Operator("OR",
            factory = IOperationFactory.simpleL(OperandTypes.BOOL,
            IParam.getList(OperandTypes.BOOL, "val", 2))
            { ctx: PredicateContext?, e: IValueInstanceList<Boolean> -> OperandTypes.BOOL.parse(ctx!!)
            { c: PredicateContext? -> ListHelper.reduce(false, e.unwrap(c))
            { u: Boolean, x: ValueInstanceConstant<Boolean> -> u || x.getVal(c) }
            }
        })
        //或
        val XOR = Operator("XOR",
            factory = IOperationFactory.simpleL(OperandTypes.BOOL,
            IParam.getList(OperandTypes.BOOL, "val", 2))
            { ctx: PredicateContext?, e: IValueInstanceList<Boolean> -> OperandTypes.BOOL.parse(ctx!!)
            { c: PredicateContext? -> ListHelper.reduce(false, e.unwrap(c))
            { u: Boolean, x: ValueInstanceConstant<Boolean> -> u xor x.getVal(c) }
            }
        })
        //异或

        val EQ = Operator("EQ",
            factory = IOperationFactory.simpleL(OperandTypes.BOOL,
                IParam.getList(OperandTypes.NUMBER, "val", 2))
            { ctx: PredicateContext?, e: IValueInstanceList<Int?> -> OperandTypes.BOOL.parse(ctx!!)
            { c: PredicateContext? -> ListHelper.eq(e.getVal(c)) }
            })

        val NEQ = Operator("NEQ",
            factory = IOperationFactory.simpleL(OperandTypes.BOOL,
                IParam.getList(OperandTypes.NUMBER, "val", 2))
            { ctx: PredicateContext?, e: IValueInstanceList<Int?> -> OperandTypes.BOOL.parse(ctx!!)
            { c: PredicateContext? -> ListHelper.neq(e.getVal(c)) }
            })

        val LE = Operator("LE",
            factory = IOperationFactory.simpleSS(OperandTypes.BOOL,
                IParam.getSingleton(OperandTypes.NUMBER, "left"),
                IParam.getSingleton(OperandTypes.NUMBER, "right"))
            { ctx: PredicateContext?, a: IValueInstance<Int>, b: IValueInstance<Int> -> OperandTypes.BOOL.parse(ctx!!)
            { c: PredicateContext? -> a.getVal(c) < b.getVal(c) }
            })

        val LEQ = Operator("LEQ",
            factory = IOperationFactory.simpleSS(OperandTypes.BOOL,
                IParam.getSingleton(OperandTypes.NUMBER, "left"),
                IParam.getSingleton(OperandTypes.NUMBER, "right"))
            { ctx: PredicateContext?, a: IValueInstance<Int>, b: IValueInstance<Int> -> OperandTypes.BOOL.parse(ctx!!)
            { c: PredicateContext? -> a.getVal(c) <= b.getVal(c) }
            })


        val GE = Operator("GE",
            factory = IOperationFactory.simpleSS(OperandTypes.BOOL,
                IParam.getSingleton(OperandTypes.NUMBER, "left"),
                IParam.getSingleton(OperandTypes.NUMBER, "right"))
            { ctx: PredicateContext?, a: IValueInstance<Int>, b: IValueInstance<Int> -> OperandTypes.BOOL.parse(ctx!!)
            { c: PredicateContext? -> a.getVal(c) > b.getVal(c) }
            })

        val GEQ = Operator("GEQ",
            factory = IOperationFactory.simpleSS(OperandTypes.BOOL,
                IParam.getSingleton(OperandTypes.NUMBER, "left"),
                IParam.getSingleton(OperandTypes.NUMBER, "right"))
            { ctx: PredicateContext?, a: IValueInstance<Int>, b: IValueInstance<Int> -> OperandTypes.BOOL.parse(ctx!!)
            { c: PredicateContext? -> a.getVal(c) >= b.getVal(c) }
            })

        val ADD = Operator("ADD",
            factory = IOperationFactory.simpleL(OperandTypes.NUMBER,
                IParam.getList(OperandTypes.NUMBER, "val", 2))
            { ctx: PredicateContext?, e: IValueInstanceList<Int> -> OperandTypes.NUMBER.parse(ctx!!)
            { c: PredicateContext? -> ListHelper.reduce(0, e.unwrap(c))
            { u: Int, x: ValueInstanceConstant<Int> -> u + x.getVal(c) } }
            })

        val SUBTRACT = Operator("SUBTRACT",
            factory = IOperationFactory.simpleSS(OperandTypes.NUMBER,
                IParam.getSingleton(OperandTypes.NUMBER, "left"),
                IParam.getSingleton(OperandTypes.NUMBER, "right"))
            { ctx: PredicateContext?, a: IValueInstance<Int>, b: IValueInstance<Int> -> OperandTypes.NUMBER.parse(ctx!!)
            { c: PredicateContext? -> a.getVal(c) - b.getVal(c) }
            })

        val MULTIPLY = Operator("MULTIPLY",
            factory = IOperationFactory.simpleL(OperandTypes.NUMBER,
                IParam.getList(OperandTypes.NUMBER, "val", 2))
            { ctx: PredicateContext?, e: IValueInstanceList<Int> -> OperandTypes.NUMBER.parse(ctx!!)
            { c: PredicateContext? -> ListHelper.reduce(0, e.unwrap(c))
            { u: Int, x: ValueInstanceConstant<Int> -> u * x.getVal(c) } }
            })
    }


}