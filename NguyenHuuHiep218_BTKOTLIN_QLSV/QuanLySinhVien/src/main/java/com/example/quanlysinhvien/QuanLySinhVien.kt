package com.example.quanlysinhvien

import java.util.Locale

data class Student(
    val studentId: String,
    var fullName: String,
    var age: Int,
    var major: String,
    var gpa: Double
)

class StudentManager {
    val students = mutableListOf<Student>()

    // ===== Chức năng cơ bản =====

    fun addStudent(s: Student) {
        students.add(s)
        println(">> Đa them sinh vien: ${s.fullName}")
    }

    fun displayAll() {
        if (students.isEmpty()) {
            println(">> Danh sach sinh vien đang trong.")
            return
        }
        println(String.format(Locale.US, "%-8s %-20s %-5s %-15s %-6s", "ID", "Ho ten", "Tuoi", "Nganh", "GPA"))
        println("-".repeat(65))
        for (s in students) {
            println(
                String.format(Locale.US,
                    "%-8s %-20s %-5d %-15s %-6.2f",
                    s.studentId, s.fullName, s.age, s.major, s.gpa
                )
            )
        }
    }

    fun searchById(id: String): Student? = students.find { it.studentId.equals(id, ignoreCase = true) }

    fun calculateAverageGpa(): Double {
        if (students.isEmpty()) return 0.0
        return students.sumOf { it.gpa } / students.size
    }

    fun findHighestGpa(): Student? = students.maxByOrNull { it.gpa }

    fun removeStudent(id: String): Boolean {
        val s = searchById(id)
        return if (s != null) {
            students.remove(s)
            true
        } else false
    }

    // ===== Yêu cầu 1 - 12 =====

    // 1. Đếm số sinh viên có GPA >= 8.0
    fun countGpaAbove8(): Int = students.count { it.gpa >= 8.0 }

    // 2. Đếm số sinh viên có GPA < 5.0
    fun countGpaBelow5(): Int = students.count { it.gpa < 5.0 }

    // 3. Tính GPA trung bình của sinh viên ngành được giao (theo major cụ thể)
    fun averageGpaByMajor(major: String): Double {
        val list = students.filter { it.major.equals(major, ignoreCase = true) }
        if (list.isEmpty()) return 0.0
        return list.sumOf { it.gpa } / list.size
    }

    // 4. Tìm sinh viên có GPA cao nhất -> findHighestGpa()

    // 5. Tìm sinh viên lớn tuổi nhất
    fun findOldestStudent(): Student? = students.maxByOrNull { it.age }

    // 6. Tìm sinh viên có GPA trong khoảng [7.0, 8.5]
    fun findGpaInRange(min: Double = 7.0, max: Double = 8.5): List<Student> =
        students.filter { it.gpa in min..max }

    // 7. Tìm tất cả sinh viên thuộc một ngành
    fun findByMajor(major: String): List<Student> =
        students.filter { it.major.equals(major, ignoreCase = true) }

    // 8. Tìm sinh viên theo một phần tên
    fun findByNamePart(part: String): List<Student> =
        students.filter { it.fullName.contains(part, ignoreCase = true) }

    // 9. Sắp xếp sinh viên theo GPA giảm dần
    fun sortByGpaDescending(): List<Student> = students.sortedByDescending { it.gpa }

    // 10. Hiển thị 3 sinh viên có GPA cao nhất
    fun top3ByGpa(): List<Student> = students.sortedByDescending { it.gpa }.take(3)

    // 11. Sắp xếp sinh viên theo tuổi
    fun sortByAge(): List<Student> = students.sortedBy { it.age }

    // 12. Sắp xếp sinh viên theo tên
    fun sortByName(): List<Student> = students.sortedBy { it.fullName }
}

// ===== Hàm hỗ trợ hiển thị danh sách bất kỳ (không phải toàn bộ students gốc) =====
fun printList(list: List<Student>) {
    if (list.isEmpty()) {
        println(">> Khong co sinh vien nao phu hop.")
        return
    }
    println(String.format(Locale.US, "%-8s %-20s %-5s %-15s %-6s", "ID", "Ho ten", "Tuoi", "Nganh", "GPA"))
    println("-".repeat(65))
    for (s in list) {
        println(
            String.format(Locale.US,
                "%-8s %-20s %-5d %-15s %-6.2f",
                s.studentId, s.fullName, s.age, s.major, s.gpa
            )
        )
    }
}

fun loadSampleData(manager: StudentManager) {
    manager.addStudent(Student("SV001", "Nguyen Huu Hiep", 20, "Cong nghe thong tin", 8.5))
    manager.addStudent(Student("SV002", "Tran Thi B", 21, "Cong nghe thong tin", 6.8))
    manager.addStudent(Student("SV003", "Le Van C", 19, "Ke toan", 7.2))
    manager.addStudent(Student("SV004", "Pham Thi D", 22, "Ke toan", 4.5))
    manager.addStudent(Student("SV005", "Hoang Van E", 20, "Dien tu vien thong", 9.1))
}

fun main() {
    val manager = StudentManager()
    loadSampleData(manager)

    while (true) {
        println("\n========== STUDENT MANAGEMENT ==========")
        println("1. Add student")
        println("2. Display all students")
        println("3. Search student")
        println("4. Calculate average GPA")
        println("5. Find student with highest GPA")
        println("6. Remove student")
        println("7. Dem SV GPA >= 8.0")
        println("8. Dem SV GPA < 5.0")
        println("9. GPA trung binh theo nganh")
        println("10. Tim SV lon tuoi nhat")
        println("11. Tim SV GPA trong khoang 7.0 - 8.5")
        println("12. Tim SV theo nganh")
        println("13. Tim SV theo mot phan ten")
        println("14. Sap xep SV theo GPA giam dan")
        println("15. Hien thi top 3 SV GPA cao nhat")
        println("16. Sap xep SV theo tuoi")
        println("17. Sap xep SV theo ten")
        println("0. Exit")
        println("=========================================")
        print("Choose: ")

        when (readln().trim()) {
            "1" -> {
                print("Student ID: "); val id = readln()
                print("Full Name: "); val name = readln()
                print("Age: "); val age = readlnOrNull()?.toIntOrNull() ?: 0
                print("Major: "); val major = readln()
                print("GPA: "); val gpa = readlnOrNull()?.toDoubleOrNull() ?: 0.0
                manager.addStudent(Student(id, name, age, major, gpa))
            }
            "2" -> manager.displayAll()
            "3" -> {
                print("Nhap Student ID can tim: ")
                val id = readln()
                val s = manager.searchById(id)
                if (s != null) printList(listOf(s)) else println(">> Khong tim thay sinh vien.")
            }
            "4" -> println(">> GPA trung binh toan bo: ${String.format(Locale.US, "%.2f", manager.calculateAverageGpa())}")
            "5" -> {
                val s = manager.findHighestGpa()
                if (s != null) printList(listOf(s)) else println(">> Danh sach trong.")
            }
            "6" -> {
                print("Nhap Student ID can xoa: ")
                val id = readln()
                if (manager.removeStudent(id)) println(">> Da xoa sinh vien.")
                else println(">> Khong tim thay sinh vien de xoa.")
            }
            "7" -> println(">> So sinh vien co GPA >= 8.0: ${manager.countGpaAbove8()}")
            "8" -> println(">> So sinh vien co GPA < 5.0: ${manager.countGpaBelow5()}")
            "9" -> {
                print("Nhap nganh can tinh GPA trung binh: ")
                val major = readln()
                println(">> GPA trung binh nganh $major: ${String.format(Locale.US, "%.2f", manager.averageGpaByMajor(major))}")
            }
            "10" -> {
                val s = manager.findOldestStudent()
                if (s != null) printList(listOf(s)) else println(">> Danh sach trong.")
            }
            "11" -> printList(manager.findGpaInRange())
            "12" -> {
                print("Nhap nganh can tim: ")
                val major = readln()
                printList(manager.findByMajor(major))
            }
            "13" -> {
                print("Nhap phan ten can tim: ")
                val part = readln()
                printList(manager.findByNamePart(part))
            }
            "14" -> printList(manager.sortByGpaDescending())
            "15" -> printList(manager.top3ByGpa())
            "16" -> printList(manager.sortByAge())
            "17" -> printList(manager.sortByName())
            "0" -> {
                println(">> Thoat chuong trinh. Tam biet!")
                return
            }
            else -> println(">> Lua chon khong hop le, vui long thu lai.")
        }
    }
}