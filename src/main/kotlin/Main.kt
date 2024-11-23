import kotlin.random.Random

fun main() {

    var n: Int? = null

    // Запрашиваем ввод, пока пользователь не введет корректное число
    while (n == null || n <= 0) {
        print("Введите количество философов ")
        n = readLine()?.toIntOrNull()
        if (n == null || n <= 0) {
            println("Некорректный ввод. Пожалуйста, введите положительное число больше и 0.")
        }
    }
    val forks = BooleanArray(n) { false } // Массив вилок: false - свободна, true - занята
    val philosophers = MutableList(n) { false } // Массив философов: false - размышляет, true - обедает

    // Перемешиваем порядок философов для выбора
    val order = (0 until n).shuffled()  //shufled - метод для перемешивания порядка номеров
    println("Порядок выбора философов: $order")

    // Каждый философ пытается взять вилки
    for (philosopher in order) {
        val leftFork = philosopher
        val rightFork = (philosopher + 1) % n

        if (!forks[leftFork] && !forks[rightFork]) {
            // Если обе вилки свободны, философ берет случайную
            if (Random.nextBoolean()) {
                forks[leftFork] = true
                philosophers[philosopher] = true
                println("Философ №$philosopher берет левую вилку ($leftFork) и обедает.")
            } else {
                forks[rightFork] = true
                philosophers[philosopher] = true
                println("Философ №$philosopher берет правую вилку ($rightFork) и обедает.")
            }
        } else if (!forks[leftFork]) {
            // Если свободна только левая вилка
            forks[leftFork] = true
            philosophers[philosopher] = true
            println("Философ №$philosopher берет левую вилку ($leftFork) и обедает.")
        } else if (!forks[rightFork]) {
            // Если свободна только правая вилка
            forks[rightFork] = true
            philosophers[philosopher] = true
            println("Философ №$philosopher берет правую вилку ($rightFork) и обедает.")
        } else {
            // Если обе вилки заняты
            println("Философ №$philosopher не смог взять вилки и размышляет.")
        }
    }

    // Вывод результатов
    println("\nРезультаты:")
    philosophers.forEachIndexed { index, isEating ->  //forEachIndexed — метод, который перебирает список, предоставляя не только сам элемент, но и его индекс.
        if (isEating) {
            println("Философ №$index обедает.")
        } else {
            println("Философ №$index размышляет.")
        }
    }
}
