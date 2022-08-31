import java.util.*

object AplikasiTodolist {
    var model = arrayOfNulls<String>(10)
    var scanner = Scanner(System.`in`)
    @JvmStatic
    fun main(args: Array<String>) {
        viewShowTodoList()
    }

    /**
     * Menampilkan todo list
     */
    fun showTodoList() {
        println("TODOLIST")
        for (i in model.indices) {
            val todo = model[i]
            val no = i + 1
            if (todo != null) {
                println("$no. $todo")
            }
        }
    }

    fun testShowTodoList() {
        model[0] = "Belajar Java Dasar"
        model[1] = "Studi Kasus Java Dasar : Aplikasi Todolist"
        showTodoList()
    }

    /**
     * Menambah todo ke list
     */
    fun addTodoList(todo: String?) {
        // cek apakah model penuh?
        var isFull = true
        for (i in model.indices) {
            if (model[i] == null) {
                // model masih ada yang kosong
                isFull = false
                break
            }
        }

        // jika penuh, kita resize ukuran array 2x lipat
        if (isFull) {
            val temp = model
            model = arrayOfNulls(model.size * 2)
            for (i in temp.indices) {
                model[i] = temp[i]
            }
        }

        // tambahkan ke posisi yang data array nya NULL
        for (i in model.indices) {
            if (model[i] == null) {
                model[i] = todo
                break
            }
        }
    }

    fun testAddTodoList() {
        for (i in 0..24) {
            addTodoList("Contoh Todo Ke.$i")
        }
        showTodoList()
    }

    /**
     * Mehapus todo dari list
     */
    fun removeTodoList(number: Int): Boolean {
        return if (number - 1 >= model.size) {
            false
        } else if (model[number - 1] == null) {
            false
        } else {
            for (i in number - 1 until model.size) {
                if (i == model.size - 1) {
                    model[i] = null
                } else {
                    model[i] = model[i + 1]
                }
            }
            true
        }
    }

    fun testRemoveTodoList() {
        addTodoList("Satu")
        addTodoList("Dua")
        addTodoList("Tiga")
        addTodoList("Empat")
        addTodoList("Lima")
        var result = removeTodoList(20)
        println(result)
        result = removeTodoList(7)
        println(result)
        result = removeTodoList(2)
        println(result)
        showTodoList()
    }

    fun input(info: String): String {
        print("$info : ")
        return scanner.nextLine()
    }

    fun testInput() {
        val name = input("Nama")
        println("Hi $name")
        val channel = input("Channel")
        println(channel)
    }

    /**
     * Menampilkan view todo list
     */
    fun viewShowTodoList() {
        while (true) {
            showTodoList()
            println("MENU : ")
            println("1. Tambah")
            println("2. Hapus")
            println("x. Keluar")
            val input = input("Pilih")
            if (input == "1") {
                viewAddTodoList()
            } else if (input == "2") {
                viewRemoveTodoList()
            } else if (input == "x") {
                break
            } else {
                println("Pilihan tidak dimengerti")
            }
        }
    }

    fun testViewShowTodoList() {
        addTodoList("Satu")
        addTodoList("Dua")
        addTodoList("Tiga")
        addTodoList("Empat")
        addTodoList("Lima")
        viewShowTodoList()
    }

    /**
     * Menampilkan view menambahkan todo list
     */
    fun viewAddTodoList() {
        println("MENAMBAH TODOLIST")
        val todo = input("Todo (x Jika Batal)")
        if (todo == "x") {
            // batal
        } else {
            addTodoList(todo)
        }
    }

    fun testViewAddTodoList() {
        addTodoList("Satu")
        addTodoList("Dua")
        viewAddTodoList()
        showTodoList()
    }

    /**
     * Menampilkan view menghapus todo list
     */
    fun viewRemoveTodoList() {
        println("MENGHAPUS TODOLIST")
        val number = input("Nomor yang Dihapus (x Jika Batal)")
        if (number == "x") {
            // batal
        } else {
            val success = removeTodoList(Integer.valueOf(number))
            if (!success) {
                println("Gagal menghapus todolist : $number")
            }
        }
    }

    fun testViewRemoveTodoList() {
        addTodoList("Satu")
        addTodoList("Dua")
        addTodoList("Tiga")
        showTodoList()
        viewRemoveTodoList()
        showTodoList()
    }
}