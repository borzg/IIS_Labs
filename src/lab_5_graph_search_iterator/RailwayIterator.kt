package lab_5_graph_search_iterator

import java.lang.IllegalStateException

class RailwayIterator(private val towns: List<Town>, private val railways: List<Pair<Town, Town>>) : CountryIterator {

    private var currentTown = towns.first()
    private val visitedSet = mutableSetOf<Town>()


    private val headers: Map<Town, Town> by lazy {
        val result = mutableMapOf<Town, Town>()
        getHeaders(towns.first(), result)
        result
    }

    private fun getHeaders(town: Town, mutableMap: MutableMap<Town, Town>) {
        for (i in getAvailableFromTown(town)) {
            mutableMap[i] = town
            getHeaders(i, mutableMap)
        }
    }

    private val ways: Map<Town, List<Town>> by lazy {
        val result = mutableMapOf<Town, List<Town>>()
        railways.forEach {
            if (headers[it.first] != it.second) result[it.first] = mutableListOf(it.second).apply { result[it.first]?.let { it1 -> addAll(it1) } }
        }
        result
    }

    override fun hasNext(): Boolean {
        if (visitedSet.containsAll(towns)) return false
        return true
    }

    var isFirstIteration = true

    override fun next(): Town {
        if (hasNext()) {
            if (isFirstIteration) {
                visitedSet.add(towns.first())
                isFirstIteration = false
                return towns.first()
            }

            val notVisitedAndAvailable = getNotVisitedAndAvailableFromTown(currentTown)
            if (notVisitedAndAvailable.isNotEmpty()) {
                currentTown = notVisitedAndAvailable.first()
                visitedSet.add(currentTown)
                return currentTown
            }

            return searchFrom(currentTown)
        }
        throw IllegalStateException("No next")
    }

    private fun searchFrom(town: Town): Town {
        ways[town]?.forEach {
            if (!visitedSet.contains(it)) {
                currentTown = it
                visitedSet.add(it)
                return it
            }
        }
        headers[town]?.let {
            currentTown = searchFrom(it)
            visitedSet.add(currentTown)
            return currentTown
        }
        throw IllegalStateException("No next")
    }

    private fun getAvailableFromTown(town: Town): List<Town> {
        return railways.filter {
            it.first == town
        }.map {
            if (it.first == town) it.second
            else it.first
        }
    }

    private fun getNotVisitedAndAvailableFromTown(town: Town): List<Town> {
        return railways.filter {
            it.first == town && !visitedSet.contains(it.second)
        }.map {
            if (it.first == town) it.second
            else it.first
        }
    }

}