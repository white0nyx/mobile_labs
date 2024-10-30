package com.example.dstu_4_2

class Director(
    empId: Int,
    name: String,
    ssn: String,
    baseSalary: Double,
    deptName: String,
    private val budget: Double
) : Manager(empId, name, ssn, baseSalary, deptName) {

    fun getBudget(): Double = budget
}