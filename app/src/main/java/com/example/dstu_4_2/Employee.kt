package com.example.dstu_4_2

open class Employee(
    private val empId: Int,
    private var name: String,
    private val ssn: String,
    protected var baseSalary: Double // Изменено имя поля с `salary` на `baseSalary`
) {
    // Геттеры для полей
    fun getEmpId(): Int = empId
    fun getName(): String = name
    fun getSsn(): String = ssn
    fun getSalary(): Double = baseSalary

    // Сеттер только для имени
    fun setName(newName: String) {
        name = newName
    }

    // Метод для увеличения зарплаты
    fun raiseSalary(increase: Double) {
        if (increase > 0) {
            baseSalary += increase
        }
    }
}
