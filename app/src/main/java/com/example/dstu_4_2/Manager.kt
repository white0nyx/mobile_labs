package com.example.dstu_4_2

open class Manager(
    empId: Int,
    name: String,
    ssn: String,
    baseSalary: Double,
    private val deptName: String
) : Employee(empId, name, ssn, baseSalary) {

    fun getDeptName(): String = deptName
}
