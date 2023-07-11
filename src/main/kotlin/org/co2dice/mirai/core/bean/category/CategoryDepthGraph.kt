package org.co2dice.mirai.core.bean.category

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-08-12:30
 * @Message: 词缀tag的深度有向图
 **/
object CategoryDepthGraph {

    private val nodes : Array<CategoryTag?> = arrayOfNulls(1024)

    private val edges : MutableSet<Pair<Int,Int>> = mutableSetOf()
    //边的对象，first代表起点，second代表终点

    fun belong(sup : CategoryTag,sub : CategoryTag) : Boolean{
        //检查sup是不是sub的父级
        return dfs(selectNodeId(sup), this :: findSubNode,{ it == sub }) != null
    }

    fun getAllTopNode() : List<CategoryTag>{
        val result = mutableListOf<CategoryTag>()
        nodes.forEachIndexed { index, categoryTag -> if (categoryTag != null && findSupNode(index) == null) result.add(categoryTag) }
        return result
    }

    fun getAllBottomNode() : List<CategoryTag>{
        val result = mutableListOf<CategoryTag>()
        nodes.forEachIndexed { index, categoryTag -> if (categoryTag != null && findSubNode(index) == null) result.add(categoryTag) }
        return result
    }


    private fun findSupNode(id: Int) : List<Int>?{
        //找寻上级，就是找终点为id的边的起点
        val node = nodes[id] ?: return null
        val result = mutableListOf<Int>()
        edges.forEach { if (it.second == id) result.add(it.first) }
        return result.distinct()
    }

    private fun dfs(startId : Int,
                    detectFunc : (Int) -> List<Int>?,
                    //搜索函数,往上搜索或者同类搜索
                    finishFunc : (CategoryTag?) -> Boolean,
                    //返回函数,true就强制结束
                    times : Int = 0,
                    //遍历的次数
                    doneList : MutableList<Int> = mutableListOf(),
                    //已经经过遍历的节点存储
        )
    : Int?{
        //从该节点出发,往上找,查出所有上方节点的id
        //用dfs，循环调用这个方法本身
        if (times > 32) throw Exception("超过最大深度")
        //往外找节点,找到的节点不存在就说明到尽头了
        //接下来对exteriors进行dfs内循环,如果finishFunc触发了,或者没有其他已经可遍历的节点了,就强制结束这个循环,将第一个触发finishFunc的节点的id返回
        val exteriors = detectFunc(startId) ?: return null
        exteriors.forEach {
            if ( finishFunc(nodes[it]) ){
                return it
            }
        }
        exteriors.forEach {
            if ( !doneList.contains(it) ){
                doneList.add(it)
                val result = dfs(it, detectFunc, finishFunc, times + 1, doneList)
                if ( result != null ){
                    return result
                }
            }
        }
        return null
    }

    private fun findSubNode(id: Int) : List<Int>?{
        //找寻上级，就是找终点为id的边的起点
        val node = nodes[id] ?: return null
        val result = mutableListOf<Int>()
        edges.forEach { if (it.first == id) result.add(it.second) }
        return result.distinct()
    }

    fun addNode(node : CategoryTag) : Boolean{
        val index = nodes.find { it == null }?.let { nodes.indexOf(it) } ?: -1
        if ( index >= 0 ){
            nodes[index] = node
            return true
        }
        return false
    }

    fun addNode(node : CategoryTag,sup : List<Int>,sub : List<Int> ) : Boolean{
        //sub是这个节点的子级,sup是这个节点的父级
        if (!addNode(node)) return false
        val nodeId = selectNodeId(node)
        addEdge(mutableSetOf<Pair<Int,Int>>().apply {
            for (i in sup){
                add(Pair(i,nodeId))
            }
            for (i in sub){
                add(Pair(nodeId,i))
            }
        }.toSet())
        return true
    }

    fun removeNode(id : Int) : Boolean{
        if ( id < 0 || id >= nodes.size || nodes[id] == null ){
            return false
        }
        nodes[id] = null
        edges.forEach { if (it.first == id || it.second == id) edges.remove(it)}
        return true
    }

    fun removeNode(name : String) : Boolean{
        val index = nodes.indexOfFirst { it?.name == name }
        return if ( index >= 0 ) removeNode(index) else false
    }

    fun selectNode(name : String) : CategoryTag?{
        return nodes.find { it?.name == name }
    }

    private fun selectNode(ids : List<Int>) : List<CategoryTag>{
        return ids.mapNotNull { nodes[it] }
    }

    private fun selectNodeId(node : CategoryTag) : Int{
        return nodes.indexOf(node)
    }

    private fun selectNodeId(name : String) : CategoryTag?{
        return nodes.find { it?.name == name }
    }

    fun addEdge(start : CategoryTag, end : CategoryTag) : Boolean{
        return addEdge(nodes.indexOf(start),nodes.indexOf(end))
    }

    private fun addEdge(pairSet : Set<Pair<Int,Int>>) : Boolean{
        pairSet.any {
            //如果有一个不合法的边,所有都不会操作
            pair ->  edgeLegal(pair.first,pair.second)
        }
        return edges.addAll(pairSet)
    }

    private fun addEdge(start : Int, end : Int) : Boolean{
        if (edgeLegal(start,end)) return false
        edges.add(Pair(start,end))
        return true
    }

    private fun edgeLegal(start : Int, end : Int) : Boolean{
        return start < 0 || end < 0
            || start >= nodes.size || end >= nodes.size
            || nodes[start] == null || nodes[end] == null
            || edges.find { ( it.first == start && it.second == end )
            || (it.first == end && it.second == start)} != null
    }
}