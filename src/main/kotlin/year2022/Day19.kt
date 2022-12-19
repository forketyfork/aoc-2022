package year2022

class Day19 {

    data class Blueprint(
        val id: Int,
        val oreRobotOreCost: Int,
        val clayRobotOreCost: Int,
        val obsidianRobotOreCost: Int,
        val obsidianRobotClayCost: Int,
        val geodeRobotOreCost: Int,
        val geodeRobotObsidianCost: Int
    )

    data class State(
        val stepsLeft: Byte,
        val ore: Int,
        val clay: Int,
        val obsidian: Int,
        val geode: Int,
        val oreRobots: Byte,
        val clayRobots: Byte,
        val obsidianRobots: Byte,
        val geodeRobots: Byte
    ) {
        val isConsistent
            get() = stepsLeft > 0 && ore >= 0 && clay >= 0 && obsidian >= 0
    }

    private val regex =
        """Blueprint (\d*): Each ore robot costs (\d*) ore. Each clay robot costs (\d*) ore. Each obsidian robot costs (\d*) ore and (\d*) clay. Each geode robot costs (\d*) ore and (\d*) obsidian.""".toRegex()

    private fun solution(input: List<String>, steps: Byte): List<Int> {
        val blueprints = input.map(regex::find)
            .map { result -> result!!.groupValues.drop(1).map(String::toInt) }
            .map { list -> Blueprint(list[0], list[1], list[2], list[3], list[4], list[5], list[6]) }
            .toList()

        return blueprints.mapIndexed { idx, blueprint ->

            val memo = mutableMapOf<State, Int>()

            fun rec(state: State): Int {
                if (state.stepsLeft == 0.toByte()
                    || state.stepsLeft == 1.toByte() && state.geodeRobots == 0.toByte()
                    || state.stepsLeft == 2.toByte() && state.obsidianRobots == 0.toByte()
                    || state.stepsLeft == 3.toByte() && state.clayRobots == 0.toByte()
                ) {
                    return state.geode
                }
                if (memo[state] != null) {
                    return memo[state]!!
                }
                val oreRobots = state.oreRobots
                val clayRobots = state.clayRobots
                val obsidianRobots = state.obsidianRobots
                val geodeRobots = state.geodeRobots
                val result = listOf(
                    state,
                    state.copy(
                        ore = state.ore - blueprint.oreRobotOreCost,
                        oreRobots = (state.oreRobots + 1).toByte(),
                    ),
                    state.copy(
                        ore = state.ore - blueprint.clayRobotOreCost,
                        clayRobots = (state.clayRobots + 1).toByte(),
                    ),
                    state.copy(
                        ore = state.ore - blueprint.obsidianRobotOreCost,
                        clay = state.clay - blueprint.obsidianRobotClayCost,
                        obsidianRobots = (state.obsidianRobots + 1).toByte(),
                    ),
                    state.copy(
                        ore = state.ore - blueprint.geodeRobotOreCost,
                        obsidian = state.obsidian - blueprint.geodeRobotObsidianCost,
                        geodeRobots = (state.geodeRobots + 1).toByte()
                    )
                )
                    .filter { it.isConsistent }
                    .map {
                        it.copy(
                            stepsLeft = (it.stepsLeft - 1).toByte(),
                            ore = it.ore + oreRobots,
                            clay = it.clay + clayRobots,
                            obsidian = it.obsidian + obsidianRobots,
                            geode = it.geode + geodeRobots
                        )
                    }
                    .maxOf { rec(it) }
                memo[state] = result
                if (memo.size % 10000000 == 0) {
                    println("State size: ${memo.size}")
                }
                return result
            }

            val best = rec(State(steps, 0, 0, 0, 0, 1, 0, 0, 0))
            println("Best value for blueprint ${blueprint.id}: $best")
            best

        }
    }

    fun part1(input: String) = solution(input.lines(), 24).reduceIndexed { idx, acc, value -> acc + (idx + 1) * value }
    fun part2(input: String) = solution(input.lines().take(3), 32).reduce { a, b -> a * b }
}
