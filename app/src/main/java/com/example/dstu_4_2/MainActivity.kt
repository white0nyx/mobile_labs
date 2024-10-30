package com.example.dstu_4_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dstu_4_2.Admin
import com.example.dstu_4_2.Director
import com.example.dstu_4_2.Engineer
import com.example.dstu_4_2.Employee
import com.example.dstu_4_2.Manager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Создание объектов классов и их заполнение
        val eng = Engineer(101, "Евгений Селезнёв", "012-34-5678", 120_345.27)
        val mgr = Manager(207, "Юлия Денисова", "054-12-2367", 109_501.36, "Международный отдел")
        val adm = Admin(304, "Борис Мигалов", "108-23-2367", 75_002.34)
        val dir = Director(12, "Александр Весловскиий", "099-45-2340", 120_567.36, "Российский отдел", 1_000_000.00)

        // Отображение данных объектов
        printEmployee(eng)
        printEmployee(mgr)
        printEmployee(adm)
        printEmployee(dir)
    }

    // Метод отображения данных объекта Employee
    private fun printEmployee(emp: Employee) {
        println("Employee ID: ${emp.getEmpId()}")
        println("Employee Name: ${emp.getName()}")
        println("Employee Soc Sec #: ${emp.getSsn()}")
        println("Employee Salary: ${emp.getSalary()}")
    }
}
