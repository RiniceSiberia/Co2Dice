package org.co2dice.mirai.core.utils

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.exp
import kotlin.math.ln
import kotlin.random.Random


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-24-18:22
 * {@code @Message:} Have a good time!  :)
 **/
object MathUtils {
    fun factorial(n : Int) : Long{
        var result = 1L
        for (i in 2..n) result *= i
        return result
    }

    fun combination(n: Int, k: Int): Long {
        //从n个元素中取k个元素的组合数
        return factorial(n) / (factorial(k) * factorial(n - k))
    }

    fun BigDecimal.pow(exponent: BigDecimal,precision : Int = 4,roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal {
        // 创建一个MathContext对象，默认指定4位有效数字和四舍五入模式
        val mc = MathContext(precision, roundingMode)
        // 计算base的自然对数
        val lnBase = BigDecimal(ln(this.toDouble()), mc)
        // 计算exponent乘以lnBase
        val product = exponent.multiply(lnBase, mc)
        // 计算e的product次方
        // 返回结果
        return BigDecimal(exp(product.toDouble()), mc)
    }

    /*
    * 仅为求概率而定制
    * 当介于0和pY之间时会自动停止计算
     */
//    fun definiteIntegral(
//        pmf : (BigDecimal) -> BigDecimal,
//        up : BigDecimal,
//        down : BigDecimal,
//        precisionX: Int = 4,
//        timeBreak : Int = 16777216
//        //计算次数熔断开关,16777216
//    ) : BigDecimal{
//        val dX = BigDecimal.ONE / BigDecimal.TEN.pow(precisionX)
//        var dY : BigDecimal
//        val times = ((up-down)/dX).toInt()
//        if (times >= timeBreak) throw Exception("Too many times to calculate")
//        var index : BigDecimal = down
//        var result : BigDecimal = BigDecimal.ZERO
//        while (index < up ){
//            dY = pmf(index)
//            result += dY*dX
//            index += dX
//        }
//        return result
//    }

    fun <X,Y,Z> ((X, Y) -> Z).partial(y: Y): (X) -> Z {
        return { x -> this(x, y) }
    }

    fun <L : List<T>,T> L.listToMap() : Map<Int,T>{
        val result = mutableMapOf<Int,T>()
        for (i in this.indices){
            result[i] = this[i]
        }
        return result
    }

    /**
     * 计算集合的幂集（包含所有可能的子集），并返回一个包含所有子集的集合。
     *
     * @param inputSet 输入集合，表示要计算幂集的原始集合。
     * @return 幂集的集合，其中每个元素都是输入集合的一个子集。注意，空集 {} 也是结果集合的子集。
     */
    fun <T> powerSet(inputSet: Collection<T>): Set<Set<T>> {
        if (inputSet.isEmpty()) {
            return setOf(emptySet())
        }

        val element = inputSet.first()
        val restOfSet = inputSet.minus(element)
        val powerSetWithoutElement = powerSet(restOfSet)

        val powerSetWithElement = powerSetWithoutElement.map { subset -> subset + element }

        return powerSetWithoutElement + powerSetWithElement
    }

    /**
     * 统计满足条件的子集在集合中的出现次数，并返回一个包含子集及其出现次数的映射。
     *
     * @param inputCollection 输入集合，表示要统计子集的集合。
     * @param checkFunc 检查函数，用于判断给定子集是否满足条件。
     * @return 一个映射，其中包含满足 checkFunc 条件的子集以及它们在输入集合中的出现次数。
     */
    fun <T> countSubsetOccurrences(inputCollection: Collection<T>, checkFunc: (Set<T>) -> Boolean): Map<Set<T>, Int> {
        val result = mutableMapOf<Set<T>, Int>()

        // 获取输入集合元素的幂集
        val powerSet = powerSet(inputCollection.toSet())

        // 统计满足 checkFunc 条件的子集在输入集合中的出现次数
        for (subset in powerSet) {
            if (checkFunc(subset)) {
                val count = inputCollection.count { it in subset }
                result[subset] = count
            }
        }

        return result
    }

    // apply simpson rule to approximately compute the integration.
    fun simpsonRule(upper: BigDecimal,
                    lower: BigDecimal,
                    n: Int,
                    df: (BigDecimal) -> BigDecimal): BigDecimal {
        //上限，下限，需要分割成的段，函数
        //默认精度200
        var result = BigDecimal.ZERO
        val unit = (upper - lower) / n.toBigDecimal()
        val factor1 = unit / BigDecimal(3)
        val x = arrayOf<BigDecimal>()
        for (i in x.indices) {
            x[i] = lower + unit * i.toBigDecimal()
        }
        for (i in x.indices) {
            result += if (i == 0 || i == x.size - 1) {
                df(x[i])
            } else if (i % 2 == 0) { // if 'i' is even num.
                BigDecimal(2) * df(x[i])
            } else { // if i is odd num.
                BigDecimal(4) * df(x[i])
            }
        }
        result *= factor1
        return result
    }


    /**
     * 获取多个List中每个List的Set元素的所有组合，并按照指定的排序函数排序，去除重复的组合。
     *
     * @param lists     包含多个List的列表，每个List包含一组Set元素。
     * @param sort      排序函数，用于比较两个List的Set元素，根据返回的值进行排序，默认按照Set元素个数升序排列。
     * @return          返回所有合法组合的列表，按照排序函数的规则排序且去除重复组合。
     */
    fun <T : Any> getCombinations(
        lists: List<List<Set<T>>>,
        sort: (List<Set<T>>) -> Int = { it.sumOf { set -> set.size } }
    ): List<List<Set<T>>> {
        val result: MutableList<List<Set<T>>> = mutableListOf()

        /**
         * 检查一个组合是否有效，即是否没有重复元素。
         *
         * @param combination   要检查的组合，包含多个Set。
         * @return              如果组合有效（没有重复元素），返回true，否则返回false。
         */
        fun isValid(combination: List<Set<T>>): Boolean {
            val seen: MutableSet<T> = mutableSetOf()
            for (set in combination) {
                for (element in set) {
                    if (seen.contains(element)) {
                        return false
                    } else {
                        seen.add(element)
                    }
                }
            }
            return true
        }

        /**
         * 递归生成所有组合的内部函数。
         *
         * @param depth     当前递归深度，从0开始。
         * @param current   当前正在生成的组合。
         */
        fun generateCombinations(depth: Int, current: MutableList<Set<T>>) {
            if (depth == lists.size) {
                if (isValid(current)) {
                    result.add(ArrayList(current))
                }
                return
            }

            for (set in lists[depth]) {
                current.add(set)
                generateCombinations(depth + 1, current)
                current.removeAt(current.size - 1)
            }
        }

        generateCombinations(0, mutableListOf())
        return result.distinct().sortedBy(sort)
    }


    fun <K, V> getAllCombinations(map: Map<K, List<V>>): List<Map<K, V>> {
        val keys = map.keys.toList()
        val combinations = mutableListOf<Map<K, V>>()

        fun generateCombinations(index: Int, current: MutableMap<K, V>) {
            if (index == keys.size) {
                combinations.add(current.toMap())
                return
            }

            val key = keys[index]
            val values = map[key] ?: emptyList()

            for (value in values) {
                if (!current.values.contains(value)) {
                    current[key] = value
                    generateCombinations(index + 1, current)
                    current.remove(key)
                }
            }
        }

        generateCombinations(0, mutableMapOf())
        return combinations
    }


    /**
     * 生成样本空间，并统计符合条件的子集及其出现次数。
     *
     * @param collection 输入的集合，包含泛型元素A。
     * @param checkFunc 检查子集是否符合条件的函数。默认为 { it.size == 1 }，即只包含一个元素的子集。
     * @param frequencyInSampling 随机抽样计算的次数。大于0则视为使用随机抽样。默认为-1，即使用精确递归计算。
     * @return 返回包含符合条件的子集及其出现次数的Map。
     */
    fun <A> generateSamples(
        collection: Collection<A>,
        checkFunc: (Set<A>) -> Boolean = { it.size == 1 },
        frequencyInSampling: Int = -1
    ): Map<Set<A>, Int> {
        // 创建一个样本空间，用于存储符合条件的子集及其出现次数
        val sampleSpace = mutableMapOf<Set<A>, Int>()

        /**
         * 递归生成子集并统计符合条件的子集及其出现次数。
         *
         * @param index 当前元素在集合中的索引。
         * @param subset 当前子集。
         */
        fun generateSubsets(index: Int, subset: MutableSet<A>) {
            // 如果当前索引等于集合大小，表示子集生成完成
            if (index == collection.size) {
                // 检查子集是否符合条件，若符合则将其添加到样本空间中
                if (checkFunc(subset)) {
                    sampleSpace[subset.toSet()] = sampleSpace.getOrDefault(subset.toSet(), 0) + 1
                }
                return
            }

            // 排除当前索引对应的元素
            generateSubsets(index + 1, subset)

            // 包含当前索引对应的元素
            subset.add(collection.elementAt(index))
            generateSubsets(index + 1, subset)
            subset.remove(collection.elementAt(index))
        }

        // 根据useRandomSampling决定使用精确递归计算还是随机抽样计算
        if (frequencyInSampling > 0) {
            repeat(frequencyInSampling) {
                // 随机抽样生成一个子集
                val randomSubset = collection.filter { Random.nextBoolean() }.toSet()
                // 检查子集是否符合条件，若符合则将其添加到样本空间中
                if (checkFunc(randomSubset)) {
                    sampleSpace[randomSubset] = sampleSpace.getOrDefault(randomSubset, 0) + 1
                }
            }
        } else {
            // 使用精确递归计算生成子集
            generateSubsets(0, mutableSetOf())
        }

        return sampleSpace
    }




    /**
     * 计算从外层的collection中，遍历内层的每个collection，求从所有内层collection中抽取元素的可能排列组合总和，并统计结果。
     * 注:该方法和getAllCombinations不同，不是求幂集的
     *
     * @param collections 外层的collection，其中每个元素都是内层的collection（List<A>），A为泛型。
     * @param selector 一个函数 (List<A>) -> R，用于将统计结果变为R类型。
     * @return 返回一个Map<R, Int>，其中key为selector函数对应的结果，value为该结果出现的次数。
     */
    fun <T, R> calculatePermutationsAndCount(
        collections: List<Collection<T>>,
        selector: (List<T>) -> R
    ): Map<R, Int> {
        val allCombinations = mutableListOf<List<T>>()
        generateCombinations(collections, 0, emptyList(), allCombinations)

        // 统计结果的Map
        val resultMap = mutableMapOf<R, Int>()

        for (combination in allCombinations) {
            val result = selector(combination)
            resultMap[result] = resultMap.getOrDefault(result, 0) + 1
        }

        return resultMap
    }

    /**
     * 递归函数，生成从所有内层collection中抽取元素的可能排列组合。
     *
     * @param collections 外层的collection，其中每个元素都是内层的collection（List<A>），A为泛型。
     * @param index 当前遍历到的外层collection的索引。
     * @param currentCombination 当前已经生成的排列组合。
     * @param allCombinations 存储所有排列组合的结果列表。
     */
    private fun <T> generateCombinations(
        collections: List<Collection<T>>,
        index: Int,
        currentCombination: List<T>,
        allCombinations: MutableList<List<T>>
    ) {
        if (index == collections.size) {
            allCombinations.add(currentCombination)
            return
        }

        val currentCollection = collections[index]
        for (item in currentCollection) {
            val newCombination = currentCombination + item
            generateCombinations(collections, index + 1, newCombination, allCombinations)
        }
    }

    private fun <A,K> convertPairToMap(pairs: Map<K, Map<Int, A>>): List<Map<K, A>> {
        //内外翻转
        val outerKeys = pairs.keys.toList()
        val innerMaps = pairs.values.toList()
        val combinations = mutableListOf<Map<K, A>>()

        fun generateCombination(index: Int, currentCombination: MutableMap<K, A>) {
            if (index == innerMaps.size) {
                combinations.add(currentCombination.toMap())
                return
            }

            val innerMap = innerMaps[index]
            for ((innerKey, value) in innerMap) {
                currentCombination[outerKeys[index]] = value
                generateCombination(index + 1, currentCombination)
                currentCombination.remove(outerKeys[index])
            }
        }

        generateCombination(0, mutableMapOf())
        return combinations
    }

    /**
     * 生成所有可能的组合列表
     * @param oto Map<String, A>
     * @param mto Map<String, Map<Int, A>>
     * @param mtm Map<String, Map<Int, Set<A>>>
     * @return 所有可能的组合列表
     */

    fun <A> generateAllCombinations(
        oto: Map<String, A>,
        mto: Map<String, Map<Int, A>>,
        mtm: Map<String, Map<Int, Set<A>>>
    ): List<Map<String, TriEither<A, Pair<Int, A>, Pair<Int, Set<A>>>>> {

        val pairs: MutableMap<String, Map<Int, TriEither<A, Pair<Int, A>, Pair<Int, Set<A>>>>> = mutableMapOf()

        oto.forEach { (string, value) ->
            pairs[string] = mapOf(1 to TriEither.Left(value))
        }

        mto.forEach { (string, map) ->
            pairs[string] = map.mapValues { TriEither.Middle(it.toPair()) }
        }

        mtm.forEach { (string, setMap) ->
            pairs[string] = setMap.mapValues { TriEither.Right(it.toPair()) }
        }

        return filterCombinations(convertPairToMap(pairs))
    }

    /**
     * 筛选并返回符合条件的组合列表
     * @param combinations 所有可能的组合列表
     * @return 符合条件的组合列表
     */
    fun<A> filterCombinations(combinations: List<Map<String, TriEither<A, Pair<Int, A>, Pair<Int, Set<A>>>>>): List<Map<String, TriEither<A, Pair<Int, A>, Pair<Int, Set<A>>>>> {
        val filteredCombinations = mutableListOf<Map<String, TriEither<A, Pair<Int, A>, Pair<Int, Set<A>>>>>()

        for (combination in combinations) {
            if (isValidCombination(combination)) {
                filteredCombinations.add(combination)
            }
        }

        return filteredCombinations
    }

    /**
     * 检查组合是否满足条件
     * @param combination 单个组合
     * @return 是否满足条件
     */
    private fun<A> isValidCombination(combination: Map<String, TriEither<A, Pair<Int, A>, Pair<Int, Set<A>>>>): Boolean {
        for (triEither1 in combination) {
            for (triEither2 in combination.filter { it != triEither1 }) {
                if (!triEitherFilter(triEither1.value, triEither2.value)) {
                    return false
                }
            }
        }
        return true
    }

    /**
     * 对比TriEither是否满足条件
     * @param a TriEither对象A
     * @param b TriEither对象B
     * @return 是否满足条件
     */
    private fun <A> triEitherFilter(a: TriEither<A, Pair<Int, A>, Pair<Int, Set<A>>>, b: TriEither<A, Pair<Int, A>, Pair<Int, Set<A>>>): Boolean {
        return when (a) {
            is TriEither.Left -> when (b) {
                is TriEither.Left -> a.value != b.value
                is TriEither.Middle -> a.value != b.value.second
                is TriEither.Right -> a.value !in b.value.second
            }
            is TriEither.Middle -> when (b) {
                is TriEither.Left -> a.value.second != b.value
                is TriEither.Middle -> a.value.second != b.value.second
                is TriEither.Right -> a.value.second !in b.value.second
            }
            is TriEither.Right -> when (b) {
                is TriEither.Left -> b.value !in a.value.second
                is TriEither.Middle -> b.value.second !in a.value.second
                is TriEither.Right -> a.value.second.intersect(b.value.second).isEmpty()
            }
        }
    }


}